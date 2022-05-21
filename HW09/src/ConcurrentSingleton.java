import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton{
    private ConcurrentSingleton(){};
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>();
    public static ConcurrentSingleton getInstance(){
            if(instance.get()==null){
                instance.set(new ConcurrentSingleton());
            }
            return instance.get();
    }

    public static void main(String[] args) {
        for(int i=0; i<100; i++){
            new Thread(
                    () -> {System.out.println(ConcurrentSingleton.getInstance());}
            ).start();
        }
    }
}
