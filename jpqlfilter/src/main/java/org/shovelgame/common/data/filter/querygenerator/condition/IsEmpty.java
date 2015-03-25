/**
 * 
 */
package org.shovelgame.common.data.filter.querygenerator.condition;


/**
 * IS EMPTY condition
 * This expression tests whether or not the collection designated by the collection-valued path expression is empty (i.e, has no elements).
 * 
 * If the value of the param is "false" it will generate IS NOT EMPTY
 * If the value of the param is "true" it will generate IS EMPTY 
 * 
 * @author haveljan
 *
 */
public class IsEmpty extends IsNull {

  //private static final Logger LOG = Logger.getLogger(IsEmpty.class);

  /**
   * Constructor
   */
  public IsEmpty() {
    super("empty");
  }
}
