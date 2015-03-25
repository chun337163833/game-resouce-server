package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Condition generator for between condition. Expected value type is Between.Value only ! If
 * <code>from</code> or <code>to</code> is null then between condition is transformed to {@see
 * Greater} or {@see Lesser}.
 * 
 * @author MacikJ
 * 
 */
public class Between extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(Between.class);
  
  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   * 
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    Assert.isInstanceOf(Between.Value.class, value,
        "Value for condition between must be of type Between.Value");
    Between.Value val = (Between.Value) value;
    if (val.getFrom() == null && val.getTo() == null) {
      LOG.warn("Values from and to for condition between are null! Skiping condition");
      return;
    }

    if (val.getFrom() == null) {
      //only >=
      new SimpleConditionGenerator(">=").generateCondition(context, conditionName, value);
    } else {
      //only <=
      if (val.getTo() == null) {
        new SimpleConditionGenerator("<=").generateCondition(context, conditionName, value);
      } else {
        //full between
        String paramId1 = "c" + context.nextValueId();
        String paramId2 = "c" + context.nextValueId();
        context.addWherePartCondition(
            conditionName, conditionName + " between :" + paramId1 + " and :" + paramId2);
        context.addValue(paramId1, val.getFrom());
        context.addValue(paramId2, val.getTo());
      }
    }
  }

  /**
   * Simple data storage for condition Between.
   * 
   * @author MacikJ
   * 
   */
  public static class Value {

    private Object from;

    private Object to;

    /**
     * Constructor.
     * 
     * Call super class constructor
     * 
     * @param from - Object
     * @param to - Object
     */
    public Value(Object from, Object to) {
      super();
      this.from = from;
      this.to = to;
    }

    /**
     * Getter for field from.
     * 
     * @return the from
     */
    public Object getFrom() {
      return from;
    }

    /**
     * Getter for field to.
     * 
     * @return the to
     */
    public Object getTo() {
      return to;
    }

  }

}
