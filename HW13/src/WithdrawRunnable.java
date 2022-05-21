public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;

	public void setDone(){
		done=true;
	}

	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		while(true){
			if(done){break;}
			account.withdraw(100);
			setDone();
		}
	}
}
