package me.wonwoo.delegate;

import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.reflect.MethodDelegate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanDelegateTests {

  @Test
  void testMethodDelegate() throws Exception {
    SampleBean bean = new SampleBean();
    bean.setValue("Hello cglib!");
    BeanDelegate delegate = (BeanDelegate) MethodDelegate.create(
        bean, "getValue", BeanDelegate.class);
    assertEquals("Hello cglib!", delegate.getValueFromDelegate());
  }
}