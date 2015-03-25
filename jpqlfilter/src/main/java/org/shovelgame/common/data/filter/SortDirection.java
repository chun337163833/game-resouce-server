/**
 * 
 */
package org.shovelgame.common.data.filter;

/**
 * Direction of sorting.
 * 
 * @author MacikJ
 */
public enum SortDirection {

  ASC, DESC;

  /**
   * Converts string representation of sort to this enum.
   * 
   * @param s - String
   * @return SortDirection enum
   */
  public static SortDirection fromString(String s) {
    if (ASC.toString().equals(s)) {
      return ASC;
    } else if (DESC.toString().equals(s)) {
      return DESC;
    } else {
      throw new IllegalArgumentException("Unknown sort direction '" + s + "'!");
    }
  }

}
