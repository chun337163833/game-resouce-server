/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import java.text.MessageFormat;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate condition for part of date. For example year( dateColumnName ) = 2005. Type of column on
 * which is generated condition have to be Date. Names of functions are in JPQL documentation.
 * 
 * @author haveljan
 */
public class EqualEnumString extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(EqualEnumString.class);

//  private String dateFunction;

  /**
   * Condition constructor.
   * 
   * @param dateFunction name of function to use
   */
//  public EqualEnumString(String dateFunction) {
//    super();
//    this.dateFunction = dateFunction;
//  }

  public EqualEnumString() {
    super();
  }

  /**
   * generateCondition method
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   * 
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName, Object value) {
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    context.addWherePartCondition(conditionName, MessageFormat.format("{0}({1}) = :{2}", "str", conditionName, paramId));
    context.addValue(paramId, value);
  }

}
