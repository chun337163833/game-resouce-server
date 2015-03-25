/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;
import org.springframework.util.Assert;

/**
 * Implementation of like condition on localized value fields.
 * 
 * @author MacikJ
 * 
 */
public class RLikeLocalizedValue extends AbstractConditionGenerator {

 

  /**
   * Constructor
   * 
   */
  public RLikeLocalizedValue() {
    super();

  }
  
  /**
   * Generate condition.
   * example:
   * <code>exists ( select lv from LocalizedValue lv join lv.messages lvm where lvm.message like '%' and lv.id = c.name.id )</code>
   * 
   * @param context - ConditionGeneratorContext
   * @param conditionName - String
   * @param value - Object
   */
  public void generateCondition(ConditionGeneratorContext context, String conditionName,
      Object value) {
    
    Assert.isTrue(context.getEntitiesInQuery().size() == 1, "More than one entity as query result!");
    int paramNumber = context.nextValueId();
    String paramId = "c" + paramNumber;
    value = value + "%";
    String selectedEntityAlias = context.getEntitiesInQuery().get(0).getAlias();
    StringBuilder condition = new StringBuilder();
    condition.append("exists ( ");
    condition.append(  "select lv from LocalizedValue lv join lv.messages lvm where lvm.message like :").append(paramId);
    condition.append(    " and lv.id = ").append(conditionName).append(".id");
    condition.append(" ) ");
    
    context.addWherePartCondition(conditionName, condition.toString() );
    context.addValue(paramId, value);
  }

}
