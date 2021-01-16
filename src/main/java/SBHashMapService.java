import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SBHashMapService {

    static private Map <Object, Object> resultByArg = new HashMap <>();
    static final private String catalog = "D:\\Temp";

    static void newTempCatalog(){
        File file = new File(catalog);
        if (!file.exists()){
            file.mkdir();
        }


    }

    static boolean loadFromFile(String filename){
        return  true;
    }


    static public Object put(Object key, Object value, Method invokeMethod) {

        System.out.println("Добавили SBHashMap:" + value);

        String cacheType = "FILE";
        String prefix = invokeMethod.getName();

        if (cacheType.equals("FILE")){
            newTempCatalog();

            String newFileName = prefix +"_"+ UUID.randomUUID().toString() + ".sbdat";
            String fullFileName = catalog + "\\" + newFileName;

            //сериализовать
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fullFileName)))
            {
                oos.writeObject(value);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }

            value = newFileName;
        }

        return resultByArg.put(key, value);
    }

    static public Object get(Object key, Method invokeMethod) {

        Object result = resultByArg.get(key);

        String cacheType = "FILE";
        String prefix = invokeMethod.getName();

        if (cacheType.equals("FILE")){

            String fullFileName = catalog + "\\" + result;

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fullFileName)))
            {
                result= (Object) ois.readObject();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
           return result;
        }

        System.out.println("Получили из SBHashMap " + result);
        return result;
    }

    static public boolean containsKey(Object key) {
        return resultByArg.containsKey(key);
    }

}
