package me.wonwoo.mixin;

import net.sf.cglib.proxy.Mixin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MixinInterfaceTests {

  @Test
  void testMixin() throws Exception {
    Mixin mixin = Mixin.create(new Class[]{Interface1.class, Interface2.class,
        MixinInterface.class}, new Object[]{new Class1(), new Class2()});
    MixinInterface mixinDelegate = (MixinInterface) mixin;
    assertEquals("first", mixinDelegate.first());
    assertEquals("second", mixinDelegate.second());
  }

}