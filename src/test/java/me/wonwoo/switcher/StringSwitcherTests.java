package me.wonwoo.switcher;

import net.sf.cglib.util.StringSwitcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSwitcherTests {
  @Test
  void testStringSwitcher() throws Exception {
    String[] strings = new String[]{"one", "two"};
    int[] values = new int[]{10, 20};
    StringSwitcher stringSwitcher = StringSwitcher.create(strings, values, true);
    assertEquals(10, stringSwitcher.intValue("one"));
    assertEquals(20, stringSwitcher.intValue("two"));
    assertEquals(-1, stringSwitcher.intValue("three"));
  }

}
