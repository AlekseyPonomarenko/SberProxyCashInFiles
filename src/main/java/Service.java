import java.io.Serializable;

public interface Service {

    @Cache(cacheType = Cache.СacheType.FILE, fileNamePrefix = "data", zip = true)
    Object doHardWork (Object t1, Object t2);

}
