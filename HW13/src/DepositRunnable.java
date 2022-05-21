public class DepositRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;

	public void setDone(){
		done=true;
	}

	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		while(true){
			if(done){break;}
			account.deposit(100);
			setDone();
		}
	}
}
