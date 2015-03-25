/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;

import java.text.MessageFormat;

import org.shovelgame.common.data.filter.querygenerator.ConditionGeneratorContext;


/**
 * Is null condition
 * 
 * A null comparison expression tests whether or not the single-valued path expression or input parameter is a NULL value. 
 * 
 * If the value of the param is "false" it will generate IS NOT NULL
 * If the value of the param is "true" it will generate IS NULL 
 * 
 * @author haveljan
 *
 */
public class IsNull extends AbstractConditionGenerator {

  protected String isWhat;
  
  public IsNull(){
    this("null");
  }
  
  public IsNull(String isWhat){
    this.isWhat = isWhat;
  }

//  private static final Logger LOG = Logger.getLogger(IsNull.class);

  public void generateCondition(ConditionGeneratorContext context, String conditionName, Object value) {
    String not = "";
    if (value != null){
      if(value.toString().toUpperCase().equals("FALSE") || value.toString().toUpperCase().equals("0")){
        not = "not ";
      }
    }
    
    context.addWherePartCondition(conditionName, MessageFormat.format("{0} is {1}{2}", conditionName, not, isWhat));
  }
}
