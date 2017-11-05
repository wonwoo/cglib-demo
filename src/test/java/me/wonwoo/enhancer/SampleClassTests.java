package me.wonwoo.enhancer;


import net.sf.cglib.proxy.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SampleClassTests {

  @Test
  void testFixedValue() throws Exception {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallback(new FixedValue() {
      @Override
      public Object loadObject() throws Exception {
        return "Hello cglib!";
      }
    });
    SampleClass proxy = (SampleClass) enhancer.create();
    assertEquals("Hello cglib!", proxy.toString());
//    System.out.println(proxy.hashCode()); //error
    assertEquals("Hello cglib!", proxy.test(null));
  }

  @Test
  void testInvocationHandler() throws Exception {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallback((InvocationHandler) (proxy, method, args) -> {
      if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
        return "Hello cglib!";
      } else {
        throw new RuntimeException("Do not know what to do.");
      }
    });
    SampleClass proxy = (SampleClass) enhancer.create();
    assertEquals("Hello cglib!", proxy.test(null));
//    assertNotEquals("Hello cglib!", proxy.toString()); //error
  }

  @Test
  void testMethodInterceptor() throws Exception {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
          throws Throwable {
        if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
          return "Hello cglib!";
        } else {
          return proxy.invokeSuper(obj, args);
        }
      }
    });
    SampleClass proxy = (SampleClass) enhancer.create();
    assertEquals("Hello cglib!", proxy.test(null));
    String actual = proxy.toString();
    assertNotEquals("Hello cglib!", actual);
    int i = proxy.hashCode();
    System.out.println(i);
  }

  @Test
  void testCallbackFilter() throws Exception {
    Enhancer enhancer = new Enhancer();
    CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
      @Override
      protected Object getCallback(Method method) {
        if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
          return new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
              return "Hello cglib!";
            }
          };
        } else {
          return NoOp.INSTANCE; // A singleton provided by NoOp.
        }
      }
    };
    enhancer.setSuperclass(SampleClass.class);
    enhancer.setCallbackFilter(callbackHelper);
    enhancer.setCallbacks(callbackHelper.getCallbacks());
    SampleClass proxy = (SampleClass) enhancer.create();
    assertEquals("Hello cglib!", proxy.test(null));
    assertNotEquals("Hello cglib!", proxy.toString());
    proxy.hashCode(); // Does not throw an exception or result in an endless loop.
  }

}
