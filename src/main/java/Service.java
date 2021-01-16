import java.io.Serializable;

public interface Service {

    @Cache
    Object doHardWork (Object t1, Object t2);

}
