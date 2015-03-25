/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import java.text.MessageFormat;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Podminka na rovnost poli enumu ulozenych jako string
 * HQL:
 *   str(name) in (:name)
 * 
 * @author macikj
 */
public class InEnumString extends AbstractConditionGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(InEnumString.class);

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

  public InEnumString() {
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
    context.addWherePartCondition(conditionName, MessageFormat.format("{0}({1}) in :{2}", "str", conditionName, paramId));
    context.addValue(paramId, value);
  }

}
