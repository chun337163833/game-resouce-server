package org.shovelgame.domain.localizedfield;

import java.util.Locale;
import java.util.Map;

public interface LocalizedValue {
  
  
  
  public String get(Language key);
  
  public String get(String langKey);
  
  public String get(Locale locale);
  
  public void put(Language key, String value);

  public int size();
  
  Map<String, String> getAsMap();
  
  public String getNullSafe(Language selectedLanguage);
  
  public String getNullSafe(Locale locale);

}