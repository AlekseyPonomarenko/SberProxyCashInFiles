
public class Main {
    public static void main(String[] args) {

       SBHashMapService.loadLastVersion();

        Service service = ServiceImpl.createNewProxy();
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t1", 1));
        System.out.println(service.doHardWork("t3", 3));
        System.out.println(service.doHardWork("t4", 4));
        SBHashMapService.saveLastVersion();

    }
}
