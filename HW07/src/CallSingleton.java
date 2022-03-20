public class CallSingleton implements Runnable{
    public void run() {
        for(int i=0;i<10; i++){
            System.out.println(ConcurrentSingleton.getInstance());
        }

    }

    public static void main(String[] args) {
        CallSingleton sing1 = new CallSingleton();
        CallSingleton sing2 = new CallSingleton();
        Thread t1 = new Thread(sing1);
        Thread t2 = new Thread(sing2);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
    }
}
