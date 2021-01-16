import java.lang.reflect.Method;

public class ParamsForCache
{

    public Cache.СacheType cacheType=Cache.СacheType.IN_MEMORY;
    public String prefix;
    public boolean zip=false;

    public ParamsForCache(Method invokeMethod) {
        if (invokeMethod.isAnnotationPresent(Cache.class)) {
            Cache cache= invokeMethod.getAnnotation(Cache.class);
            cacheType = cache.cacheType();
            prefix = cache.fileNamePrefix();
            zip = cache.zip();
        }
    }
}
