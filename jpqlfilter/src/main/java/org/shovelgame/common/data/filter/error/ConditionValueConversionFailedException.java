package org.shovelgame.common.data.filter.error;


public class ConditionValueConversionFailedException extends Exception {

  
  
  private String[] messageKeys;

  public ConditionValueConversionFailedException(String message, String[] messageKeys, Throwable cause) {
    super(message, cause);
    this.messageKeys = messageKeys;
    // TODO Auto-generated constructor stub
  }

  
  public String[] getMessageKeys() {
    return messageKeys;
  }
  
  
}
