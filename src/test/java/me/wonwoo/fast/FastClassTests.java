package me.wonwoo.fast;

import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FastClassTests {

  @Test
  void testFastClass() throws Exception {
    FastClass fastClass = FastClass.create(SampleBean.class);
    FastMethod fastMethod = fastClass.getMethod(SampleBean.class.getMethod("getValue"));
    SampleBean myBean = new SampleBean();
    myBean.setValue("Hello cglib!");
    assertEquals("Hello cglib!", fastMethod.invoke(myBean, new Object[0]));
  }

}
