package me.wonwoo.immutable;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.ImmutableBean;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SampleBeanTests {
  @Test
  void testImmutableBean() throws Exception {
    SampleBean bean = new SampleBean();
    bean.setValue("Hello world!");
    SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean);
    assertEquals("Hello world!", immutableBean.getValue());
    bean.setValue("Hello world, again!");
    assertEquals("Hello world, again!", immutableBean.getValue());
    assertThrows(IllegalStateException.class, () ->  immutableBean.setValue("Hello cglib!"));
  }

  @Test
  void testBeanGenerator() throws Exception {
    BeanGenerator beanGenerator = new BeanGenerator();
    beanGenerator.setSuperclass(Dummy.class);
    beanGenerator.addProperty("value", String.class);
    Object myBean = beanGenerator.create();

    Method setter = myBean.getClass().getMethod("setValue", String.class);
    setter.invoke(myBean, "Hello cglib!");
    Method getter = myBean.getClass().getMethod("getValue");
    assertEquals("Hello cglib!", getter.invoke(myBean));
  }

  static class Dummy {

  }

}
