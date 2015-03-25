/**
 * 
 */
package org.shovelgame.common.data.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implementace s integraci s requestem.
 * 
 * @author MacikJ
 */
public class RequestBasedFilter extends MapBasedFilter {
  
  private static final Logger log = LoggerFactory.getLogger(RequestBasedFilter.class);

  private String cookieName = "jpqlfilter";
  
  /**
   * Zda ukladat nastaveni filtru do cookie
   */
  private boolean storeSettingToCookie = true;
  
  /**
   * Zda nacitat nastaveni filtru z cookie
   */
  private boolean readSettingFromCookie = true;
  
  
  
  
  private String clearFilterRequestParameter = "clearfilter";
  
  
  /**
   * 
   */
  public RequestBasedFilter() {
    // TODO Auto-generated constructor stub
    init();
  }

  /**
   * @param defaultSortBy
   */
  public RequestBasedFilter(Sort defaultSortBy) {
    super(defaultSortBy);
    init();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param defaultSortBy
   * @param pageSetting
   */
  public RequestBasedFilter(Sort defaultSortBy, PageSetting pageSetting) {
    super(defaultSortBy, pageSetting);
    init();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param defaultConditions
   * @param defaultSortBy
   * @param pageSetting
   */
  public RequestBasedFilter(Map<String, String> defaultConditions, Sort defaultSortBy,
      PageSetting pageSetting) {
    super(defaultConditions, defaultSortBy, pageSetting);
    init();
    // TODO Auto-generated constructor stub
  }
  
  protected void init() {
    setIgnoreKeys(new HashSet<String>(){{
      add(clearFilterRequestParameter);
    }});
  }
  
  /**
   * Nacte/ulozi podminky z/do cookie a parametru requestu.
   * @param request
   * @param response
   */
  public void configureFilterFromHttp(HttpServletRequest request, HttpServletResponse response) {            
    String clrFilterParam = request.getParameter(clearFilterRequestParameter);    
    boolean cookieStored = false;
    
    if (clrFilterParam!=null && ! "false".equals(clrFilterParam.toLowerCase())) {//smazani filteru - napr. z menu
     
    } else { 
      String storedFilterId = request.getParameter(keyForFilterId);    
      if (storedFilterId!=null && storedFilterId.length()>0) {//nacteni filtru s uloziste
        //nacteni podminek
        loadFromStore(new Long(storedFilterId));
        //ulozeni nastaveni do cookie
        if (storeSettingToCookie) {
          StringBuilder sb = new StringBuilder();
          sb.append(keyForFilterId).append("=").append(storedFilterId);
          storeCookie(sb.toString(), request, response);
          cookieStored = true;
        }
      } else {//pokus o nacteni z cookie          
        if (readSettingFromCookie) {
          Cookie filterCookie = findFilterCookie(request);          
          if (filterCookie!=null) {
            readSettingsFromCookie(request, filterCookie);  
          }           
        }
        readConditions(request);
      }
    }
      
    if (storeSettingToCookie && !cookieStored) {
      saveSettingsToCookie(request, response);
    }       
  }
  
  /**
   * Nacte parametru z requestu - pokud zadne nejsou nastaveny. Jsou ignorovany 
   * parametry nastavene pres ignoreKeys
   * @param request
   */
  @Deprecated 
  public void readConditions(HttpServletRequest request) {
    Map<String, String[]> params = request.getParameterMap();
    addConditions(params);      
  }
  
  public void readSettingsFromCookie(HttpServletRequest request) {
    Cookie filterCookie = findFilterCookie(request);
    readSettingsFromCookie(request, filterCookie);
  }
  
  /**
   * Nacte podminky atd. z cookie;
   * @param request
   */
  public void readSettingsFromCookie(HttpServletRequest request, Cookie filterCookie) {
    setConditions(new HashMap<String, String>());
    Map<String, String[]> params = request.getParameterMap();    
    for (String paramName: params.keySet()) {
      if (isKeySupported(paramName) && ! paramName.startsWith("_")) {
        return;//byl nalezen parameter ktery muze byt podminka - nastaveni v cookie bude ignorovano
      }
    }
    
    //nacteni z cookie                 
    Map<String, String> cookieParams = parseCookie(filterCookie);
    String storedFilterId = cookieParams.get(keyForFilterId);
    if (storedFilterId!=null) {
      loadFromStore(new Long(storedFilterId));
    } else {          
      putAll(cookieParams);                  
    }
    
    Map<String,String> obsCond = new SimpleObservableHashMap( getConditions(), new SimpleObservableHashMap.OnMapChange() {
      public void mapChanged() {
        getPage().setCurrentPageNumber(PageSetting.INITIAL_PAGE_NUMBER);
      }
    });
    setConditions(obsCond);         
  }
  
  /**
   * Najde cookie ktera patri filtru.
   * @param request
   * @return
   */
  private Cookie findFilterCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    Cookie filterCookie = null;
    if (cookies!=null) {
      for (Cookie cookie: cookies) {
        if (cookieName.equals(cookie.getName())) {
          filterCookie = cookie;
          break;
        }
      }
    }
    return filterCookie;
  }
  
  /**
   * Zparsuje cookie a vrati jeji parametry
   * @param filterCookie
   * @return
   */
  private Map<String, String> parseCookie(Cookie filterCookie) {
    try {
      Map<String, String> parsedConditions = new HashMap<String, String>();
      String value = filterCookie.getValue();     
      value = URLDecoder.decode(value, "UTF-8");
      String[] args = value.split("[&]");
      for(String arg: args) {
        String[] param = arg.split("[=]");
        String conditionName = param[0];
        String conditionValue = param[1];
        conditionValue = URLDecoder.decode(conditionValue, "UTF-8");  
        parsedConditions.put(conditionName, conditionValue);
      }
      return parsedConditions;
    } catch (UnsupportedEncodingException e) {
      log.error("", e);
      throw new RuntimeException("", e);
    }
    
  }

  /**
   * Ulozi nastaveni filtru do cookie.
   * @param request
   * @param response
   */
  public void saveSettingsToCookie(HttpServletRequest request, HttpServletResponse response) {
    String cookiePath = request.getRequestURI();
    saveSettingsToCookie(request, response, cookiePath);
  }
  
  /**
   * Ulozi nastaveni filtru do cookie.
   * @param request
   * @param response
   */
  public void saveSettingsToCookie(HttpServletRequest request, HttpServletResponse response, String cookiePath) {    
    try {
      List<String> args = new ArrayList<String>(); 
      for (Iterator<Map.Entry<String, String>> it = getConditions().entrySet().iterator() ; it.hasNext() ;) {
        Map.Entry<String, String> conditionE = it.next();
        if (!conditionE.getKey().startsWith("_")) { //parametry zacinajici na _ jsou ignorovany          
          args.add(conditionE.getKey() + "=" + URLEncoder.encode(conditionE.getValue(), "UTF-8"));
        }
      }
      
      if (getPage().isPagingEnabled()) {
        args.add(getKeyForPageNumber() + "=" + getPage().getCurrentPageNumber());
      } else {
        args.add( getKeyForEnablePagigng() + "=" + Boolean.FALSE);
      }
      
      Sort sortBy =getSortBy(); 
      if (sortBy.getColumn()!= null && sortBy.getColumn().length()>0) {
        args.add( getKeyForSortBy()+ "=" + sortBy.getColumn());
        args.add( getKeyForSortOrder()+ "=" + sortBy.getSortDirection().toString().toLowerCase());
      }
      if (visibleColumns!=null && visibleColumns.size()>0) {
        String columnsStr = get(getKeyForVisibleColumns());     
        columnsStr = URLEncoder.encode(columnsStr, "UTF-8");
        args.add(getKeyForVisibleColumns() + "=" + columnsStr);        
      }
      
      StringBuilder sb = new StringBuilder();
      for (Iterator<String> it = args.iterator() ; it.hasNext();) {
        String arg = it.next();
        sb.append(arg);
        if (it.hasNext()) {
          sb.append("&");
        }
      }
      storeCookie(sb.toString(), request, response, cookiePath);     
    } catch (UnsupportedEncodingException e) {
      log.error("", e);
      throw new RuntimeException("", e);
    }
  }

  /**
   * Prida ke cookie nastaveni o platnosti a ulozi cookie do response. 
   * @param sb
   * @param request
   * @param response
   */
  protected void storeCookie(String value, HttpServletRequest request, HttpServletResponse response) {
    String cookiePath = request.getRequestURI();
    storeCookie(value, request, response, cookiePath);
  }
  
  /**
   * Prida ke cookie nastaveni o platnosti a ulozi cookie do response. 
   * @param sb
   * @param request
   * @param response
   */
  protected void storeCookie(String value, HttpServletRequest request, HttpServletResponse response, String cookiePath) {
    //
    Cookie oldCookie = findFilterCookie(request);
    if (oldCookie!=null && oldCookie.getValue()!=null && oldCookie.getValue().equals(value)) {
      return;//cookie jsou stejne
    }
    
    try {
      value = URLEncoder.encode(value, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    StringBuilder sb = new StringBuilder(value);
//    sb.append(";");
//    sb.append(cookiePath);
    
    //sb.append(";");
    //Date expdate= new Date();
    //expdate.setTime (expdate.getTime() + (3600 * 10));//10 minut
    //DateFormat df = new SimpleDateFormat("dd MMM yyyy kk:mm:ss z");
    //df.setTimeZone(TimeZone.getTimeZone("GMT"));
   // String cookieExpire = "expires " + df.format(expdate);
    //sb.append(cookieExpire);
    
    Cookie cookie = new Cookie(cookieName, sb.toString());
    cookie.setPath(cookiePath);
    cookie.setMaxAge(600);//10min
    response.addCookie(cookie);
  }

  
  public boolean isStoreSettingToCookie() {
    return storeSettingToCookie;
  }

  
  public void setStoreSettingToCookie(boolean storeSettingToCookie) {
    this.storeSettingToCookie = storeSettingToCookie;
  }
  
  
  
  
  public boolean isReadSettingFromCookie() {
    return readSettingFromCookie;
  }

  public void disableCookie() {
    setStoreSettingToCookie(false);
    setReadSettingFromCookie(false);
  }
  
  public void setReadSettingFromCookie(boolean readSettingToCookie) {
    this.readSettingFromCookie = readSettingToCookie;
  }

  @Override
  public void setIgnoreKeys(Set<String> ignoreKeys) {
    Set<String> lIgnoreKeys = new HashSet<String>(ignoreKeys);
    lIgnoreKeys.add(clearFilterRequestParameter);
    super.setIgnoreKeys(lIgnoreKeys);
  }
  
  
  public void setCookieName(String cookieName) {
    this.cookieName = cookieName;
  }
  
  
  public String getCookieName() {
    return cookieName;
  }


}
