package com.laba5;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class InjectorTest {
    @Test
    void inject() throws NoSuchFieldException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Field field1 = SomeBean.class.getDeclaredField("field1");
        Field field2 = SomeBean.class.getDeclaredField("field2");
        field1.setAccessible(true);
        field2.setAccessible(true);
        Injector injector = new Injector("file.txt");
        SomeBean someBean = injector.inject(new SomeBean());
        assertTrue(field1.get(someBean) instanceof SomeImpl);
        assertTrue(field2.get(someBean) instanceof SODoer);
    }
}