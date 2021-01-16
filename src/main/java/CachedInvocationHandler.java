
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CachedInvocationHandler implements InvocationHandler {

    private final Map <Object, Object> resultByArg=new HashMap <>();
    private final Object delegate;

    public CachedInvocationHandler(Object delegate) {
        this.delegate=delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        boolean stopInvoke = false;
        Calendar metric1 =Calendar.getInstance();
        Calendar metric2 = null;

        if(method.isAnnotationPresent(Cache.class)) {

            if (resultByArg.containsKey(key(method, args))) {
                System.out.println("Получение из кэша: " + method.getName());
                result = resultByArg.get(key(method, args));
                stopInvoke = true;
            }
            else {
                System.out.println("Вызов: " + method.getName());
                result = method.invoke(delegate, args);
                resultByArg.put(key(method, args), result);
                stopInvoke = true;
            }
        }

        if (!stopInvoke) {
            result=method.invoke(delegate, args);
        }

        if(method.isAnnotationPresent(Metric.class)){
            metric2 = Calendar.getInstance();
            System.out.println("Время выполенения: " + (metric2.getTime().getTime()-metric1.getTime().getTime()) + " наносек");
        }

        return result;
    }

    private Object key(Method method , Object[] args){
        List <Object> key = new ArrayList <>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

}
