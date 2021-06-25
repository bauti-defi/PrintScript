package edu.austral.ingsis.interpreter;

public class NumberUtils {

  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (RuntimeException e) {
      return false;
    }
  }

  public static boolean isDouble(String s) {
    try {
      Double.parseDouble(s);
      return true;
    } catch (RuntimeException e) {
      return false;
    }
  }

  public static String numberToString(double i) {
    String val = String.valueOf(i);
    if (val.contains(".0") || val.contains(".00")) {
      return String.valueOf(Integer.parseInt(val.split("\\.")[0]));
    }
    return val;
  }
}
