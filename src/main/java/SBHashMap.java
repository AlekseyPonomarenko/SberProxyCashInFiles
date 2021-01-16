import java.util.HashMap;
import java.util.Map;

public class SBHashMap extends HashMap<Object, Object> {

    public SBHashMap() {
        System.out.println("Создан новый SBHashMap");
    }

    @Override
    public Object put(Object key, Object value) {
        System.out.println("Добавили SBHashMap");
        return super.put(key, value);
    }


    @Override
    public Object get(Object key) {
        System.out.println("Получили из SBHashMap");
        return super.get(key);
    }


    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }
}
