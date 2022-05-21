import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		while(balance >= 300){
			try{
				belowUpperLimitFundsCondition.await();
			}catch(InterruptedException e){
				return;
			}
		}
		balance += amount;
		System.out.println(balance);
		sufficientFundsCondition.signalAll();
		lock.unlock();
	}
	
	public void withdraw(double amount){
		lock.lock();
		while(balance <= 0){
			try{
				sufficientFundsCondition.await();
				}catch(InterruptedException e){
				return;
			}
		}
		balance -= amount;
		System.out.println(balance);
		belowUpperLimitFundsCondition.signalAll();
		lock.unlock();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		LinkedList<DepositRunnable> deposits = new LinkedList<>();
		LinkedList<WithdrawRunnable> withdraws = new LinkedList<>();
		LinkedList<Thread> threads = new LinkedList<>();

		for(int i=0;i<7;i++){
			DepositRunnable dep = new DepositRunnable(bankAccount);
			deposits.add(dep);
			Thread t = new Thread( dep );
			threads.add(t);
			t.start();
		}

		for(int i = 0; i < 5; i++){
			WithdrawRunnable with = new WithdrawRunnable(bankAccount);
			withdraws.add(with);
			Thread t = new Thread( with );
			threads.add(t);
			t.start();

		}
		Thread.sleep(4000);
		deposits.forEach((DepositRunnable d)->d.setDone());
		withdraws.forEach((WithdrawRunnable w)->w.setDone());
		threads.forEach((Thread t)->t.interrupt());
		System.out.println(bankAccount.balance);
	}
}
