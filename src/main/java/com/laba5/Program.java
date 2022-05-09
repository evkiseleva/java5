package com.laba5;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    /**
     * Стартовая точка программы
     * @param args
     * */
    public static void main(String[] args) throws IOException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Injector injector = new Injector("file.txt");
        SomeBean someBean = injector.inject(new SomeBean());
        someBean.foo();
    }
}
