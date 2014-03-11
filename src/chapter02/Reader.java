package chapter02;

public class Reader implements Runnable {
	private PricesInfo pricesInfo;
	
	public static void main(String[] args) {
		PricesInfo pricesInfo=new PricesInfo();
		
		Reader readers[]=new Reader[5];
	    Thread threadsReader[]=new Thread[5];
	    
	    for (int i=0; i<5; i++){
	      readers[i]=new Reader(pricesInfo);
	      threadsReader[i]=new Thread(readers[i]);
	    }
	    
	    Writer writer=new Writer(pricesInfo);
	    Thread  threadWriter=new Thread(writer);
	    
	    for (int i=0; i<5; i++){
	        threadsReader[i].start();
	    }
	    threadWriter.start();
	}

	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
		}
	}

}

class Writer implements Runnable {
	private PricesInfo pricesInfo;

	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		for (int i=0; i<3; i++) {
		      System.out.printf("Writer: Attempt to modify the prices.\n");
		      pricesInfo.setPrices(Math.random()*10, Math.random()*8);
		      System.out.printf("Writer: Prices have been modified.\n");
		      try {
		        Thread.sleep(2);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		}
	}

}
