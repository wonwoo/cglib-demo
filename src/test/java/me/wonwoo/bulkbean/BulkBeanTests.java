package me.wonwoo.bulkbean;

import me.wonwoo.immutable.SampleBean;
import net.sf.cglib.beans.BulkBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BulkBeanTests {

  @Test
  void testBulkBean() throws Exception {
    BulkBean bulkBean = BulkBean.create(SampleBean.class,
        new String[]{"getValue"},
        new String[]{"setValue"},
        new Class[]{String.class});
    SampleBean bean = new SampleBean();
    bean.setValue("Hello world!");
    assertEquals(1, bulkBean.getPropertyValues(bean).length);
    assertEquals("Hello world!", bulkBean.getPropertyValues(bean)[0]);
    bulkBean.setPropertyValues(bean, new Object[] {"Hello cglib!"});
    assertEquals("Hello cglib!", bean.getValue());
  }

}
