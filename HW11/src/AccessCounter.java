import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private HashMap<Path,Integer> counter = new HashMap<Path,Integer>();
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock iLock = new ReentrantLock();
    private static AccessCounter instance;

    public static AccessCounter getInstance(){
        iLock.lock();
        try{
            if(instance==null){
                instance = new AccessCounter();
            }
            return instance;
        }finally {
            iLock.unlock();
        }
    }


    public void increment(Path path){
        lock.lock();
        try{
            if(counter.containsKey(path)){
                counter.replace(path,counter.get(path)+1);
            }else{
                counter.put(path,1);
            }
        }finally {
            lock.unlock();
        }
    }

    public int getCount(Path path){
        lock.lock();
        try {
            if(counter.containsKey(path)){
                return(counter.get(path));
            }else{
                return(0);
            }
        }finally {
            lock.unlock();
        }
    }

}
