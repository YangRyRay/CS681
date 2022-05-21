public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnablePrimeGenerator gen11 = new RunnablePrimeGenerator(1, 2000000L);
		Thread t11 = new Thread(gen11);
		t11.start();
		long before = System.currentTimeMillis();
		try {
			t11.join();
		} catch (InterruptedException e) {}
		long after = System.currentTimeMillis();
		long primeNum = gen11.getPrimes().size();
		System.out.println("===================================");
		System.out.println("Threads: 1"+"\n" + primeNum + " prime numbers are found in total.");
		System.out.printf("Elapsed time: %s \n", after-before);

		RunnablePrimeGenerator gen21 = new RunnablePrimeGenerator(1, 1000000L);
		RunnablePrimeGenerator gen22 = new RunnablePrimeGenerator(1000001L, 2000000L);
		Thread t21 = new Thread(gen21);
		Thread t22 = new Thread(gen22);
		t21.start();
		t22.start();
		long before2 = System.currentTimeMillis();
		try {
			t21.join();
			t22.join();
		} catch (InterruptedException e) {}
		long after2 = System.currentTimeMillis();
		primeNum = gen21.getPrimes().size()+gen22.getPrimes().size();
		System.out.println("===================================");
		System.out.println("Threads: 2"+"\n" + primeNum + " prime numbers are found in total.");
		System.out.printf("Elapsed time: %s \n", after2-before2);

		RunnablePrimeGenerator gen41 = new RunnablePrimeGenerator(1, 500000L);
		RunnablePrimeGenerator gen42 = new RunnablePrimeGenerator(500001L, 1000000L);
		RunnablePrimeGenerator gen43 = new RunnablePrimeGenerator(1000000L, 1500000L);
		RunnablePrimeGenerator gen44 = new RunnablePrimeGenerator(1500001L, 2000000L);
		Thread t41 = new Thread(gen41);
		Thread t42 = new Thread(gen42);
		Thread t43 = new Thread(gen43);
		Thread t44 = new Thread(gen44);
		t41.start();
		t42.start();
		t43.start();
		t44.start();
		long before4 = System.currentTimeMillis();
		try {
			t41.join();
			t42.join();
			t43.join();
			t44.join();
		} catch (InterruptedException e) {}
		long after4 = System.currentTimeMillis();
		//gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		//gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		//gen3.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		//gen4.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		primeNum = gen41.getPrimes().size()+gen42.getPrimes().size()+gen43.getPrimes().size()+gen44.getPrimes().size();
		System.out.println("===================================");
		System.out.println("Threads: 4"+"\n" + primeNum + " prime numbers are found in total.");
		System.out.printf("Elapsed time: %s \n", after4-before4);
        

	}

}
