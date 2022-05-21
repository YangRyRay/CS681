import java.io.File;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Random;


public class RequestHandler implements Runnable{
    private volatile boolean flag = true;

    public void flipFlag(){
        flag = false;
    }

    public void run() {
        File[] lst = new java.io.File("TestFiles").listFiles();
        Random rand = new Random();
        while(flag){
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
        LinkedList<Thread> threads = new LinkedList<>();
        for (int i=0;i<10;i++){
            RequestHandler handler = new RequestHandler();
            handlers.add(handler);
            Thread thread = new Thread(handler, "Thread "+i);
            threads.add(thread);
            thread.start();
        }
        Thread.sleep(1000);
        handlers.forEach((RequestHandler r)->r.flipFlag());
        threads.forEach((Thread t)->t.interrupt());
        AccessCounter counter = AccessCounter.getInstance();

    }
}
