import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private ConcurrentHashMap<Path, AtomicInteger> counter = new ConcurrentHashMap<Path,AtomicInteger>();
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
        counter.putIfAbsent(path,new AtomicInteger(0));
        counter.get(path).incrementAndGet();
    }

    public AtomicInteger getCount(Path path){
        return counter.compute(path,(Path k, AtomicInteger v)->{return v==null? new AtomicInteger(1): v;});
    }

}
