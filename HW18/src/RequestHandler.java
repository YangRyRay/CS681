import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;


public class RequestHandler implements Runnable{
    private volatile boolean flag = true;

    public void flipFlag(){
        flag = false;
    }

    public void run() {
        File[] lst = new File("TestFiles").listFiles();
        Random rand = new Random();
        while(flag){
            if(Thread.interrupted()){
                System.out.println("Stopped");
                break;            }
            AccessCounter counter = AccessCounter.getInstance();
            Path file = lst[rand.nextInt(lst.length)].toPath();
            counter.increment(file);
            System.out.println(file+": "+counter.getCount(file));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedList<RequestHandler> handlers = new LinkedList<>();
        ExecutorService executor = newCachedThreadPool();
        for (int i=0;i<10;i++){
            RequestHandler handler = new RequestHandler();
            executor.execute(handler);
        }
        Thread.sleep(1000);
        handlers.forEach((RequestHandler r)->r.flipFlag());
        executor.shutdownNow();

    }
}
