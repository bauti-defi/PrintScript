import static edu.austral.ingsis.interpreter.NumberUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilTest {

  @Test
  public void testIsDouble() {
    assertTrue(isDouble("1.1"));
    assertTrue(isDouble("1.0"));
    assertTrue(isDouble("1"));
    assertTrue(isDouble("0.1"));
    assertFalse(isDouble("0.12s"));
  }

  @Test
  public void testIsInteger() {
    assertFalse(isInteger("1.1"));
    assertFalse(isInteger("1.0"));
    assertTrue(isInteger("1"));
    assertFalse(isInteger("0.1"));
    assertFalse(isInteger("1s"));
  }

  @Test
  public void testNumberToString() {
    assertEquals("1.1", numberToString(1.1));
    assertEquals("1.111", numberToString(1.111));
    assertEquals("1", numberToString(1.0));
    assertEquals("1", numberToString(1));
  }
}
