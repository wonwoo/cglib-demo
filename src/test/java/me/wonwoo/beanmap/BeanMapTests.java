package me.wonwoo.beanmap;

import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.beans.BeanMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeanMapTests {

  @Test
  void testBeanGenerator() throws Exception {
    SampleBean bean = new SampleBean();
    BeanMap map = BeanMap.create(bean);
    bean.setValue("Hello cglib!");
    assertEquals("Hello cglib!", map.get("value"));
  }

}
