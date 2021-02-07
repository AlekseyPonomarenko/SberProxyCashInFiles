import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class Main {

    private static Service service;

    public static void main(String[] args) {

        //загружаем последюю сохраненную версию
       // SBHashMapService.loadLastVersion();

        //наш прокси
        service = ServiceImpl.createNewProxy();

        //генератор потоков
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        for (int i=0; i<1; i++ ) {
            for (int j = 0; j < 2; j++) {
                MyRunnable task  = new MyRunnable("t" + i, i);
                executor.execute(task);
            }
        }



        executor.shutdown();
        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //сохраняем последнюю версию
       // SBHashMapService.saveLastVersion();
    }

    public static Service getService() {
        return service;
    }
}
