package me.wonwoo.copier;


import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.beans.BeanCopier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherSampleBeanTests {

  @Test
  void testBeanCopier() throws Exception {
    BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);
    SampleBean bean = new SampleBean();
    bean.setValue("Hello cglib!");
    OtherSampleBean otherBean = new OtherSampleBean();
    copier.copy(bean, otherBean, null);
    assertEquals("Hello cglib!", otherBean.getValue());
  }
}