import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Service service;

    public static void main(String[] args) {

        SBHashMapService.loadLastVersion();
        service = ServiceImpl.createNewProxy();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        for (int i=0; i<10; i++ ) {
            for (int j = 0; j < 5; j++) {
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
        SBHashMapService.saveLastVersion();

       /*
        System.out.println(service.doHardWork("t1", 1));*/
       /* System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));*/
        //System.out.println(service.doHardWork("t3", 3));
        //System.out.println(service.doHardWork("t4", 4));


        //System.out.println(service.doHardWork("t8", 7));



    }

    public static Service getService() {
        return service;
    }
}
