package com.laba5;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Класс Injector, осуществляющий инициализацию полей, помеченных аннотацией @AutoInjectable,
 * объектами классов, заданных в файле properties
 **/
public class Injector {
    /**
     * Путь к файлу properties
     **/
    public String properites;
    /**
     * Конструктор класса Injector
     * @param properites - путь к файлу
     **/
    public Injector(String properites)
    {
        this.properites = properites;
    }
    /**
     * Функция, изменяющая значение поля  properites
     * @param properites - новое значение
     **/
    public void setProperites(String properites)
    {
        this.properites = properites;
    }
    /**
     * Функция, возвращающая значение поля properites
     * @return properites
     **/
    public String getProperites() {
        return properites;
    }
    /**
     * Функция, которая инициализирует поля объекта obj, которые помечены аннотацией,
     * объектами из properties и выдает исключение, если инициализация даже 1 поля оказалась неудачной
     * @param <T> - тип объекта
     * @param obj - объект с неинициализированными полями
     * @return obj - объект с инициализированными полями
     **/
    public <T> T inject(T obj)throws IOException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Properties propertiesNew = new Properties();
        propertiesNew.load(Injector.class.getClassLoader().getResourceAsStream(this.properites));
        for(Field field: obj.getClass().getDeclaredFields())
        {
            if(field.isAnnotationPresent(AutoInjectable.class))
            {
                field.setAccessible(true);
                var name = propertiesNew.getProperty(field.getType().getCanonicalName());
                var instance = Class.forName(name).getConstructor().newInstance();
                field.set(obj, instance);
            }
        }
        return obj;
    }
}
