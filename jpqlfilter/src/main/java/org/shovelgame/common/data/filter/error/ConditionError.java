package org.shovelgame.common.data.filter.error;


public class ConditionError {

  /**
   * Klic podminky tak jak je v conditions.
   */
  private String conditionKey;
  
  /**
   * Klic po nahrazeni z conditionReplacements
   */
  private String conditionFullKey;
  
  private String errorMessage;
  
  private String[] messageKeys;
  
  private Exception cause;

  public ConditionError(String conditionKey, String conditionFullKey, String errorMessage,
      String[] messageKeys, Exception cause) {
    super();
    this.conditionKey = conditionKey;
    this.conditionFullKey = conditionFullKey;
    this.errorMessage = errorMessage;
    this.messageKeys = messageKeys;
    this.cause = cause;
  }

  
  public String getConditionKey() {
    return conditionKey;
  }

  
  public String getConditionFullKey() {
    return conditionFullKey;
  }

  
  public String getErrorMessage() {
    return errorMessage;
  }

  
  public String[] getMessageKeys() {
    return messageKeys;
  }

  
  public Exception getCause() {
    return cause;
  }
  
  
  
  
}
