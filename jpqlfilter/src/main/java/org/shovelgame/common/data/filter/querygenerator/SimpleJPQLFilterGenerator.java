package org.shovelgame.common.data.filter.querygenerator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ognl.DefaultTypeConverter;
import ognl.TypeConverter;

import org.shovelgame.common.data.converter.BooleanAsNumberConverter;
import org.shovelgame.common.data.converter.BooleanConverter;
import org.shovelgame.common.data.converter.CurrentDateConverter;
import org.shovelgame.common.data.converter.DateConverter;
import org.shovelgame.common.data.converter.EnumAsStringConverter;
import org.shovelgame.common.data.converter.LongArrayConverter;
import org.shovelgame.common.data.converter.SpecialDateConverter;
import org.shovelgame.common.data.converter.StringArrayConverter;
import org.shovelgame.common.data.filter.AdvancedConditions;
import org.shovelgame.common.data.filter.Association;
import org.shovelgame.common.data.filter.AssociationItem;
import org.shovelgame.common.data.filter.AssociationType;
import org.shovelgame.common.data.filter.ConditionReplacement;
import org.shovelgame.common.data.filter.FilterSetting;
import org.shovelgame.common.data.filter.IFilter;
import org.shovelgame.common.data.filter.Sort;
import org.shovelgame.common.data.filter.error.ConditionError;
import org.shovelgame.common.data.filter.error.ConditionGenerationFailedException;
import org.shovelgame.common.data.filter.error.ConditionValueConversionFailedException;
import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext.KeyValue;
import org.shovelgame.common.data.filter.querygenerator.condition.Condition;
import org.shovelgame.common.data.filter.querygenerator.condition.ConditionGenerator;
import org.shovelgame.common.data.filter.querygenerator.condition.Equal;
import org.shovelgame.common.data.filter.querygenerator.condition.EqualDateWithoutTime;
import org.shovelgame.common.data.filter.querygenerator.condition.EqualEnumString;
import org.shovelgame.common.data.filter.querygenerator.condition.EqualIgnoreCase;
import org.shovelgame.common.data.filter.querygenerator.condition.Greater;
import org.shovelgame.common.data.filter.querygenerator.condition.GreaterOrEqual;
import org.shovelgame.common.data.filter.querygenerator.condition.GreaterOrEqualDate;
import org.shovelgame.common.data.filter.querygenerator.condition.InElementsOperator;
import org.shovelgame.common.data.filter.querygenerator.condition.InEnumString;
import org.shovelgame.common.data.filter.querygenerator.condition.InOperator;
import org.shovelgame.common.data.filter.querygenerator.condition.IsEmpty;
import org.shovelgame.common.data.filter.querygenerator.condition.IsNull;
import org.shovelgame.common.data.filter.querygenerator.condition.LRLike;
import org.shovelgame.common.data.filter.querygenerator.condition.LRLikeStripSpaces;
import org.shovelgame.common.data.filter.querygenerator.condition.Lesser;
import org.shovelgame.common.data.filter.querygenerator.condition.LesserOrEqual;
import org.shovelgame.common.data.filter.querygenerator.condition.LesserOrEqualDate;
import org.shovelgame.common.data.filter.querygenerator.condition.MemberOfOperator;
import org.shovelgame.common.data.filter.querygenerator.condition.RLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;



/**
 * Generates JPQL (HQL) query with conditions from Filter. Condition names can be used to control
 * condition generation:
 * <ul>
 * <li>conditions that begins with <code>!</code> are skipped</li>
 * <li>condition name is in format <code>column|ConditionGenerator(ColumnType)</code></li>
 * <li>condition name is in format <code>column|ConditionGenerator(ColumnType1,ColumnType2)</code> - when ColumnType1 convertor
 * fails ColumnType2 will be used.</li>
 * <li>If there is an orderBy Part and is using dot notation is rewrited using left join</li>
 * <li>If there is property in current entity with same name as entityPrefix it must be specified
 * twice to get things working. <b>example</b>: If entityAlias is hr, property hr.exampleProperty
 * must be acessed <code>hr.hr.exampleProperty</code></li>
 * </ul>
 * 
 * @author MacikJ, HolikP
 */
public class SimpleJPQLFilterGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(SimpleJPQLFilterGenerator.class);

  public static final String SKIP_CONDITION_CHAR = "!";

  public static final char CONDITION_TYPE_CHAR = '|';

  public static final char PARAMETER_TYPE_CHAR = '(';

  public String defaultConditionType = "default";

  public static final String DEFAULT_CONVERTOR_NAME = "default";

  public String defaultParameterType = "String";

  public static final String PARAMETER_TYPE_BOOLEAN = "Boolean";

  public static final String PROPERTY_SEPARATOR_CHAR = ".";

  // protected Class resultClass;

  // TODO this is very quick implementation
  protected Map<String, TypeConverter> typeConvertors = new HashMap<String, TypeConverter>() {{
    put(DEFAULT_CONVERTOR_NAME, new DefaultTypeConverter());
    put(PARAMETER_TYPE_BOOLEAN, new BooleanConverter());
    put("ISODate", new DateConverter());
    put("FGDate", new DateConverter("dd.MM.yyyy"));
    put("StringArray", new StringArrayConverter());
    put("String[]", new StringArrayConverter());
    put("Long[]", new LongArrayConverter());
    put("Auto", new DefaultTypeConverter());//special - handled manualy
//    put("SetOfLong", new LongSetConverter());
//    put("SCS", new SpringConversionServiceConverter());
    put("CurrentDate", new CurrentDateConverter());
    put("EnumAsString", new EnumAsStringConverter());
    put("BooleanAsNumber", new BooleanAsNumberConverter());
    put("SpecialDate", new SpecialDateConverter());
  }};

  protected Map<String, ConditionGenerator> conditionGenerators = new HashMap<String, ConditionGenerator>() {{
	  put(defaultConditionType, new RLike());
	  put("Equal", new Equal());
	  put("EqualIgnoreCase", new EqualIgnoreCase());
	  put("Greater", new Greater());
	  put("GreaterOrEqual", new GreaterOrEqual());
	  put("Lesser", new Lesser());
	  put("LesserOrEqual", new LesserOrEqual());
	  put("RLike", new RLike());
	  put("LRLike", new LRLike());
	  put("LRLikeStripSpaces", new LRLikeStripSpaces());
	  put("In", new InOperator());
	  put("IsNull", new IsNull());
	  put("IsEmpty", new IsEmpty());
	  put("EqualDateWithoutTime", new EqualDateWithoutTime());
	  put("MemberOf", new MemberOfOperator());
	  put("InElements", new InElementsOperator());
	  put("EqualEnumString", new EqualEnumString());	  
	  put("InEnumString", new InEnumString());
	  put("LesserOrEqualDate", new LesserOrEqualDate());
	  put("GreaterOrEqualDate", new GreaterOrEqualDate());
  }};

  protected Map<String, Class> conversionTypes = new HashMap<String, Class>() {{
    put("String", String.class);
    put("Integer", Integer.class);
    put("Long", Long.class);
    put("Integer[]", Integer[].class);
    put("Boolean", Boolean.class);
    put("StringArray", String[].class);
    put("Long[]", Long[].class);    
  }};
  
  /**
   * Vychozi podminky pro typy.
   */
  protected Map<Class<?>, String> defaultConditionGenerators = new HashMap<Class<?>, String>(){{
    put(String.class, "LRLike");
    put(Integer.class, "Equal");
    put(Long.class, "Equal");
    put(Boolean.class, "Equal");
    put(String[].class, "In");
    put(Long[].class, "In");
    put(Date.class, "Equal");
  }};

//  protected EntityIntrospector entityIntrospector = new EntityIntrospector();
  
  protected ConversionService springConversionService;
  
  /**
   * Defaultni nastaveni pro entity - lze explicitne zmenit.
   */
//  protected Map<Class, EntitySetting> entitySettings = new HashMap<Class, EntitySetting>();
  
  /**
   * Zaregistruje dalsi {@link ConditionGenerator}.
   * @param conditionGenerators
   */
  public void setConditionGenerators(Map<String, ConditionGenerator> conditionGenerators) {
    this.conditionGenerators.putAll(conditionGenerators);
  }
  
  /**
   * Zaregistruje dalsi  {@link TypeConverter}.
   * @param typeConvertors
   */
  public void setTypeConvertors(Map<String, TypeConverter> typeConvertors) {
    this.typeConvertors.putAll(typeConvertors);
  }
  
  /**
   * Zaregistruje dalsi converzni typ - mapping typu.
   * @param typeConvertors
   */
  public void setConversionTypes(Map<String, Class> conversionTypes) {
    this.conversionTypes.putAll(conversionTypes);
  }
  
  /**
   * Generate query for <code>filter</code> settings.
   * 
   * @param filter filter setting
   * @param entitiesInQuery list of entities used in query (in current implementation is supported
   *          only one entity )
   * @return QueryGeneratorResult
   * @throws ConditionGenerationFailedException 
   */
  public QueryGeneratorResult generateQuery(IFilter filter, List<EntityAlias> entitiesInQuery) throws ConditionGenerationFailedException {
    JPQLGenerator generator = new JPQLGenerator(filter, entitiesInQuery, new FilterSetting());
    return generator.generateQuery();
  }
  
//  protected EntitySetting getEntitySetting(Class<?> entity) {
//    EntitySetting result = this.entitySettings.get(entity);
//    if (result == null) {
//      result = entityIntrospector.detectEntitySetting(entity);
//      entitySettings.put(entity, result);
//    }
//    return result;
//  }

  private class JPQLGenerator {
    
    private IFilter filter;
    
    private List<EntityAlias> entitiesInQuery;
    
    private FilterSetting filterSetting;
    

    private EntityAlias entityAlias;
 
    private Map<String, ConditionReplacement> conditionReplacementsMap;

    //mapa aliasu a asociaci ktere je obsahuji
    private Map<String, Association> availableAssociations;
    
    private Set<Association> usedAssociations;
    
    private Sort processedSort;
    
    private Map<String, String> conditions;
    
    private List<ConditionError> errors = new ArrayList<ConditionError>();
    
    public String defaultConditionType;
    
    public String defaultParameterType;
    
//    private EntitySetting entitySetting;
    
    public JPQLGenerator(IFilter filter, List<EntityAlias> entitiesInQuery,
        FilterSetting filterSetting) {
      super();
      
      Assert.isTrue(entitiesInQuery.size() == 1, "Only one entity type supported in filter"); // exactly one entity is a must
      entityAlias = entitiesInQuery.get(0);
      
      this.filter = filter;
      this.entitiesInQuery = entitiesInQuery;
      this.filterSetting = filterSetting;
//      this.entitySetting = SimpleJPQLFilterGenerator.this.getEntitySetting(entityAlias.getEntityClass());
      initialize();
    }

    public void initialize() {      
            
      this.conditions = new HashMap<String, String>(filter.getDefaultConditions());
      this.conditions.putAll(filter.getConditions());
      
      this.defaultConditionType = filter.getDefaultConditionType();
      if (this.defaultConditionType==null) {
        this.defaultConditionType = SimpleJPQLFilterGenerator.this.defaultConditionType;
      }
      
      this.defaultParameterType = filter.getDefaultParameterType();
      if (this.defaultParameterType==null) {
        this.defaultParameterType = SimpleJPQLFilterGenerator.this.defaultParameterType;
      }
      
      //initialization of condition replacement
      List<ConditionReplacement> conditionReplacements = filter.getConditionReplacements();
      conditionReplacementsMap = new HashMap<String, ConditionReplacement>();
      for (ConditionReplacement cr: conditionReplacements) {
        conditionReplacementsMap.put(cr.getOldConditionName(), cr);
      }
      
      //zpracovani asociaci aby se lepe zjistovalo ktere pouzit a ktere ne
      availableAssociations = new HashMap<String, Association>();
      usedAssociations= new HashSet<Association>();
      List<Association> associations = filter.getAssociations();
      for (int i=0, m=associations.size() ; i<m ; i++ ) {        
        Association association = associations.get(i);    
        if (!association.isOptional()) {//pokud neni volitelna prida se do dotazu vzdy!
          usedAssociations.add(association);
        }
        List<AssociationItem> associationItems = association.getAssociations();
        Assert.notEmpty(associationItems, "no association item found.");
        for (int j=0, n=associationItems.size() ; j<n ; j++ ) {        
          AssociationItem item = associationItems.get(j);
          Assert.isTrue(!availableAssociations.containsKey(item.getAlias()), "Filter jiz obsahuje alias " +item.getAlias() );
          availableAssociations.put(item.getAlias(), association);          
        }
        this.conditions.putAll(association.getAdditionalConditions());
      }
      
      processedSort = new Sort(filter.getSortBy());
      if (processedSort.getColumn()!=null) {
        //nahrazeni jmena sloupce
        String newColumnName = replaceColumnNameIfNeeded(processedSort.getColumn());
        //odstraneni nastaveni typu a podminek - neni treba pro sort
        ParsedCondition parsedCondition = parseCondition(newColumnName);
        processedSort.setColumn(parsedCondition.conditionName);              
        detectIfColumnContainsAssociation(processedSort.getColumn());                   
      }
    }
    
    /**
     * Generate query for <code>filter</code> settings.
     * 
     * @param filter filter setting
     * @param entitiesInQuery list of entities used in query (in current implementation is supported
     *          only one entity )
     * @param filterSetting setting for parameters in filter
     * @return QueryGeneratorResult
     * @throws ConditionGenerationFailedException 
     */
    public QueryGeneratorResult generateQuery() throws ConditionGenerationFailedException {
      if (LOG.isDebugEnabled()) {
        LOG.debug("Starting query generation");
      }
      ConditionGeneratorContext cgContext = new ConditionGeneratorContext(filterSetting
          .getCurrentSequenceValue());
      cgContext.setOrConditions(filter.getOrConditions());
      cgContext.setEntitiesInQuery(entitiesInQuery);
  
      QueryGeneratorResult result = processConditions(cgContext, filter, entitiesInQuery);
  
      if (LOG.isDebugEnabled()) {
        StringBuilder out = new StringBuilder();
        out.append("generated queries are: \n").append(
            "  selectData: " + result.getSelectDataQuery() + "\n").append(
            "  selectCount: " + result.getSelectCountQuery() + "\n").append("  values: " + "\n");
        List<KeyValue> params = result.getParameters();
        int m = params.size();
        for (KeyValue param : params) {
          out.append("    ").append(param.getKey()).append("=");
          if (param.getValue() != null) {
            out.append("'").append(String.valueOf(param.getValue())).append("' class:").append(
                param.getValue().getClass().getSimpleName());
          } else {
            out.append("null (skipped)");
          }
        }
        LOG.debug(out.toString());
      }
  
      return result;
    }
  
    protected QueryGeneratorResult processConditions(ConditionGeneratorContext cgContext,
        IFilter filter, List<EntityAlias> entitiesInQuery) throws ConditionGenerationFailedException {
//      Map<String, String> conditions = filter.getConditions();                                 
      // process simple conditions
      for (Iterator<Map.Entry<String, String>> iterator = conditions.entrySet().iterator(); iterator.hasNext();) {
        Map.Entry<String, String> condition = iterator.next();
        String cName = condition.getKey();
        String cValue = condition.getValue();       
        processCondition(cgContext, cName, cValue);                     
      }
  
      // process advanced conditions
      AdvancedConditions advancedConditions = filter.getAdvanced();
      if (advancedConditions != null) {
        for (Condition condition : advancedConditions.conditions()) {
          ConditionGenerator conditionGenerator = condition.getConditionGenerator();
          Object value = condition.getValue();   
//          try {
          conditionGenerator.generateCondition(cgContext, addEntityAlias(entityAlias, condition
              .getName()), value);
//          } catch (ConditionValueConversionFailedException e) {
//            conversionErrors.add(e);
//          }
        }
      }
      
      if (this.errors.size() == 0) {  
        FilterQueries xx = createQuery(filter, cgContext, entitiesInQuery);  
        return new QueryGeneratorResult(xx.selectCount, xx.selectData, cgContext.getValues(), cgContext);
      } else {
        throw new ConditionGenerationFailedException("Condition generation failed for " + this.errors.size()  + " conditions", this.errors);
      }
    }
    
    protected void processCondition(ConditionGeneratorContext cgContext, String conditionKey, String cValue)  {
    //condition replacement       
      String cName = replaceColumnNameIfNeeded(conditionKey);
      
      try {
        // skip empty conditions and conditions with !
        if (cName.startsWith(SKIP_CONDITION_CHAR) || cValue == null || cValue.length() == 0 || "[]".equals(cValue)) {
          return;
        }
  
        ParsedCondition parsedCondition = parseCondition(cName);
  
        ConditionGenerator conditionGenerator;
        String conditionName = parsedCondition.conditionName;      
        //!!! nastavuje parsedCondition.condisionType - nutne refaktorovat a zpruhlednit
        Object convertedValue = convertValue(conditionName, cValue, parsedCondition);
  
        String condisionType = parsedCondition.condisionType !=null ? parsedCondition.condisionType: defaultConditionType;
        if (conditionGenerators.containsKey(parsedCondition.condisionType)) {
          conditionGenerator = conditionGenerators.get(condisionType);
        } else {
          // unknown condition
          LOG.error(MessageFormat.format(
              "Unknown condition type: '{0}' in filter: '{1}' (condition is case sensitive)",
              parsedCondition.condisionType, cName));
          throw new NullPointerException("Unknown filter condition " + parsedCondition.condisionType);
        }
                        
        conditionName = addEntityAlias(entityAlias, conditionName);                     
        conditionGenerator.generateCondition(cgContext, conditionName, convertedValue);
      } catch (ConditionValueConversionFailedException e) {
        this.errors.add(new ConditionError(conditionKey, cName, e.getMessage(), e.getMessageKeys(), e));
      }
    }
  
    /**
     * Nahradi jmeno podminky (sloupec) definovanou nahradou z {@link #conditionReplacementsMap}.
     * @param columnName
     * @return
     */
    protected String replaceColumnNameIfNeeded(String columnName) {
      if (conditionReplacementsMap.containsKey(columnName)) {
        return conditionReplacementsMap.get(columnName).getNewConditionName();
      }
      return columnName;
    }
    
    protected ParsedCondition parseCondition(String conditionKey) {
      ParsedCondition result = new ParsedCondition();
      
      int ctrlCharPosition = conditionKey.indexOf(CONDITION_TYPE_CHAR);
      if (ctrlCharPosition != -1) { // parse |
        result.condisionType = conditionKey.substring(ctrlCharPosition + 1);
        result.conditionName = conditionKey.substring(0, ctrlCharPosition);
        ctrlCharPosition = result.condisionType.indexOf(PARAMETER_TYPE_CHAR);
        if (ctrlCharPosition != -1) { // parse ( )
          String parameterTypes = result.condisionType.substring(ctrlCharPosition + 1,
              result.condisionType.length() - 1);          
          result.parameterType = parameterTypes.split("[,]");//
          result.condisionType = result.condisionType.substring(0, ctrlCharPosition);
        } 
      } else {
        result.conditionName = conditionKey;
      }
      if ("".equals(result.condisionType)) {
        result.condisionType = null;
      }
            
      return result;
    }
    
    /**
     * Vrati true pokud jmeno sloupce obsahuje znamou asociaci. Zaroven tuto asociaci prida mezi
     * pouzite v tomto dotazu.
     * @param conditionName
     * @return
     */
    protected boolean detectIfColumnContainsAssociation(String conditionName) {
      if (conditionName.contains(".")) {//contains association paths
        int firstSeparator = conditionName.indexOf(".");
        String alias =  conditionName.substring(0, firstSeparator);
        if (availableAssociations.containsKey(alias)) {
          usedAssociations.add(availableAssociations.get(alias));
          return true;
        }        
      }
      return false;
    }
  
    protected Object convertValue(String conditionName, String stringValue, ParsedCondition parsedCondition) throws ConditionValueConversionFailedException {
      if (parsedCondition.parameterType==null) {
        parsedCondition.parameterType= new String[] {defaultParameterType};
      }
      
      String[] newTypeNames = parsedCondition.parameterType;
      List<ConditionValueConversionFailedException> conversionFailures = new ArrayList(0);
      for (int i = 0; i < newTypeNames.length; i++) {
        String newTypeName = newTypeNames[i];        
        try {
          if ("Auto".equals(newTypeName)) {//TODO constant
            //new feature - conversion using Spring converter                   
            return convertValueUsingConversionService(conditionName, stringValue, parsedCondition);
          } else {//old code
            return convertValueUsingFilterConfiguration(newTypeName, conditionName, stringValue, parsedCondition);
          }
        } catch (ConditionValueConversionFailedException e) {
          LOG.debug("conversion using convertor " + newTypeName + " failed", e);
          conversionFailures.add(e);
        }                      
      }      
      for (ConditionValueConversionFailedException e: conversionFailures) {
        LOG.error("conversion using convertor failed", e);
      }
      throw conversionFailures.get(0);//throws first failure            
    }
    
    /**
     * Convertuje hodnoty na zaklade property v entite a nastaveni conversion service springu.
     * @param conditionName
     * @param stringValue
     * @param parsedCondition
     * @return
     * @throws ConditionValueConversionFailedException 
     */
    protected Object convertValueUsingConversionService(String conditionName, String stringValue, ParsedCondition parsedCondition) throws ConditionValueConversionFailedException {
      Assert.notNull(springConversionService);
      if (stringValue !=null) {   
        try {
          Object newEntityInstance=null;        
          newEntityInstance = entityAlias.entityClass.newInstance();        
          //TODO prenest na uroven instance
          BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(newEntityInstance);
          bw.setAutoGrowNestedPaths(true);          
          TypeDescriptor strDesc = TypeDescriptor.valueOf(String.class);
          TypeDescriptor td = bw.getPropertyTypeDescriptor(conditionName);                
          if (td!=null) {
            if (parsedCondition.condisionType==null) {//nebylo explicitne specifikovano a tedy nastavi dle defaultu podle detekovaneho typu
              String conditionGenerator = defaultConditionGenerators.get(td.getElementType());
              if (conditionGenerator!=null) {
                parsedCondition.condisionType = conditionGenerator;
              } else {
                parsedCondition.condisionType = defaultConditionType;
              }
            }
            
            Object value = null;
            if (springConversionService.canConvert(td, strDesc)) {
              try {
                value = springConversionService.convert(stringValue, strDesc, td ); 
              } catch (Exception eConv) {
                throw new ConditionValueConversionFailedException("Conversion using conversion service failed!", 
                    new String[]{"filter.conversionfailed", "typeMismatch", "typeMismatch." + td.getName()}, eConv);
              }
              return value;
            } else {                   
              return stringValue;
            }
          } else {
            LOG.error("detection of property type  for property {} on entity {} failed. Switching to old conversion.", conditionName, entityAlias.entityClass.getName());
            throw new RuntimeException("Detection of property type failed - property not found.");            
          }
        } catch (ConditionValueConversionFailedException ec) {
          throw ec;
        } catch (Exception e) {
          LOG.error("Conversion using ConversionService failed for property {} on entity {} failed. Switching to old conversion.", conditionName, entityAlias.entityClass.getName());
          throw new ConditionValueConversionFailedException("Conversion using ConversionService failed.", new String[]{"filter.conversionfailed", "typeMismatch"}, e);
        }
      } else {
        return null;
      }
    }

    
    
    protected Object convertValueUsingFilterConfiguration(String parameterType, String conditionName,  String stringValue, ParsedCondition parsedCondition) throws ConditionValueConversionFailedException {      
      if (parsedCondition.condisionType==null) {        
        parsedCondition.condisionType = defaultConditionType;//stary kod nema detekci property
      }
      TypeConverter typeConverter = findConvertorForType(parameterType);      
      if (!defaultParameterType.equals(parsedCondition.condisionType)) {
        Class<?> conditionValueClass = conversionTypes.get(parameterType);
        try {
          return typeConverter.convertValue(null, null, null, null, stringValue, conditionValueClass);
        } catch (Exception eConv) {
          throw new ConditionValueConversionFailedException("Conversion using filter configuration failed!", 
              new String[]{"filter.conversionfailed", "typeMismatch", "typeMismatch." + parameterType}, eConv);
        }
      } else {        
        return stringValue;
      }
    }
    
  
    //TODO rozdelit na nekolik funkci
    protected FilterQueries createQuery(IFilter filter, ConditionGeneratorContext cgContext,
        List<EntityAlias> entitiesInQuery) {
      FilterQueries result = new FilterQueries();
      // create of general query part
      StringBuilder query = new StringBuilder();
      
      Assert.isTrue(entitiesInQuery.size() == 1, "Only one entity type supported in filter"); // exactly one entity is a must
      EntityAlias entityAlias = entitiesInQuery.get(0);
      query.append(" from ");       
      query.append(entityAlias.getEntityClass().getSimpleName()).append(" ").append(
            entityAlias.getAlias()).append(" ");
      
  
      //associations (joins)    
      {
        int i=0;
        for (Iterator<Association> it = usedAssociations.iterator() ; it.hasNext() ;) {
          query.append(", ");
          Association association = it.next();  
          List<AssociationItem> associationItems = association.getAssociations();
          Assert.notEmpty(associationItems, "no association item found.");
          //Entity ej1 join ej1.property joinAlias      
          String currentAlias = entityAlias.getAlias() + "j" + i;
          String joinAlias = currentAlias;
          for (int j=0, n=associationItems.size() ; j<n ; j++ ) {        
            AssociationItem item = associationItems.get(j);        
            if (j==0) {          
              query.append(entityAlias.getEntityClass().getSimpleName()).append(" ")
                .append(currentAlias).append(" ");          
            }
            if (AssociationType.INNER_JOIN.equals( item.getAssociationType())) {
              query.append(" join ");  
            } else if (AssociationType.LEFT_JOIN.equals( item.getAssociationType())) {
              query.append(" left join ");  
            } else {
              throw new IllegalStateException("Unknown join");
            }
            
            query.append(currentAlias).append(".").append(item.getProperty())
              .append(" ").append(item.getAlias());                                  
            
            currentAlias = item.getAlias();        
          }
          //e=ej1
          cgContext.addWherePartCondition("assoc_" + i, entityAlias.getAlias() + "=" + joinAlias);          
          i++;
        }
      }
    
      if (cgContext.getWherePart().length() > 0 ) {
        query.append(" where ");
        if (cgContext.getWherePart().length() > 0 ) {
          query.append(cgContext.getWherePart());
        }
      }
      
      // creating of select count query
      String distinctPart = "";
      if (filter.isAddDistinctToQuery()) {
        distinctPart = " distinct ";
      }
      StringBuilder selectRowsCountQuery = new StringBuilder()
        .append("select count(" + distinctPart + entityAlias.alias + ") ")
        .append(query);
      result.selectCount = selectRowsCountQuery.toString();
  
      // creating select data query
      // complete query and selects rows
      StringBuilder selectRowsQuery = new StringBuilder().append("select ");
      if (filter.isAddDistinctToQuery()) {
        selectRowsQuery.append("distinct ");
      }
      for (int i = 0, m = entitiesInQuery.size(); i < m; i++) {
        selectRowsQuery.append(entitiesInQuery.get(i).getAlias());
        if ((i + 1) != m) { // TODO check condition
          selectRowsQuery.append(", ");
        } else {
          selectRowsQuery.append(" ");
        }
      }
      selectRowsQuery.append(query);
  
      //we are not using createOrderByPart function because of back compatibility reasons
//      if (orderByEP != null) {
      if (processedSort != null && processedSort.getColumn() != null) {
        selectRowsQuery.append(" order by ");
        selectRowsQuery.append(addEntityAlias(entityAlias, processedSort.getColumn()));  
        selectRowsQuery.append(" ").append(processedSort.getSortDirection().name());
      }
  
      result.selectData = selectRowsQuery.toString();
      return result;
    }
  

    private String addEntityAlias(EntityAlias entityAlias, String property) {            
      // if property starts with ignore character we simply ignore it
      //TODO why???
      if (property.startsWith("!") == true) {
        LOG.debug("Leaving property '" + property + "'");
        return property.substring(1, property.length());
      }
            
      // if property starts with entity alias we do not add entity alias again
      //TODO why???
      if (property.startsWith(entityAlias.alias + PROPERTY_SEPARATOR_CHAR) == true) {
        LOG.warn("Leaving property '" + property + "'");
        return property;
      }
      
      if(detectIfColumnContainsAssociation(property)) {
        return property;
      }
      
      // in all other cases
      return entityAlias.getAlias() + PROPERTY_SEPARATOR_CHAR + property;

      
    }
  
//    protected FieldSetting getSettingForField(String field) {
//      return entitySetting.getFieldSettings().
//    }
    
  }
  
  
  protected TypeConverter findConvertorForType(String typeName) {
    TypeConverter result = this.typeConvertors.get(typeName);
    if (result != null) {
      return result;
    } else {
      return this.typeConvertors.get(DEFAULT_CONVERTOR_NAME);
    }
  }
  
  /**
   * Method create "order by" part of HQL query
   * 
   * @param filter Filter with sort conditions
   * @return Order by part
   */
  @Deprecated
  public static StringBuilder createOrderByPart(IFilter filter) {
    StringBuilder returnQuery = new StringBuilder();
    Sort sort = filter.getSortBy();
    if (sort != null && sort.getColumn() != null) {
      returnQuery.append(" order by ").append(sort.getColumn()).append(" ").append(
          sort.getSortDirection().name());
    }
    return returnQuery;
  }
  
  /**
   * ParsedCondition class
   * 
   * @author dusakk
   * 
   */
  protected static class ParsedCondition {

    
    
 
    String conditionName;
    String condisionType;// = defaultConditionType;
    String[] parameterType;// = defaultParameterType;
    //zda podminka zacina na zakladni entite (lze k ni pridat alias zakladni entity)
   
  }

  /**
   * FilterQueries class
   * 
   * @author dusakk
   * 
   */
  protected static class FilterQueries {

    String selectCount;
    String selectData;
  }

  /**
   * DTO that stores entity class and it's alias in query.
   * 
   * @author MacikJ
   * 
   */
  public static class EntityAlias {

    private String alias;
    private Class entityClass;

    /**
     * Constructor
     * 
     * @param alias entity alias (in query)
     * @param entityClass entity class
     */
    public EntityAlias(String alias, Class entityClass) {
      super();
      this.alias = alias;
      this.entityClass = entityClass;
    }

    /**
     * Getter for field alias.
     * 
     * @return the alias
     */
    public String getAlias() {
      return alias;
    }

    /**
     * Getter for field entityClass.
     * 
     * @return the entityClass
     */
    public Class getEntityClass() {
      return entityClass;
    }

  }

  
  public void setSpringConversionService(ConversionService springConversionService) {
    this.springConversionService = springConversionService;
  }
  
  
  public void setDefaultConditionType(String defaultConditionType) {
    this.defaultConditionType = defaultConditionType;
  }
  
  
  public void setDefaultParameterType(String defaultParameterType) {
    this.defaultParameterType = defaultParameterType;
  }

//  public void registerDefaultConditionGenerator()
}
