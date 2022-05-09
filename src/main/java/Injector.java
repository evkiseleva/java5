import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Injector {
    public String properites;

    public Injector(String properites)
    {
        this.properites = properites;
    }

    public void setProperites(String properites)
    {
        this.properites = properites;
    }

    public String getProperites() {
        return properites;
    }

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
