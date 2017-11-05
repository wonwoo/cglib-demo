package me.wonwoo.keyfactory;

import net.sf.cglib.core.KeyFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SampleKeyFactoryTests {
  @Test
  void testKeyFactory() throws Exception {
    SampleKeyFactory keyFactory = (SampleKeyFactory) KeyFactory.create(SampleKeyFactory.class);
    Object key = keyFactory.newInstance("foo", 42);
    Map<Object, String> map = new HashMap<>();
    map.put(key, "Hello cglib!");
    assertEquals("Hello cglib!", map.get(keyFactory.newInstance("foo", 42)));
  }

}