package chapter01;

public class PrimeGenerator extends Thread {

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime\n.", number);
			}

			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted\n");
				return;
			}

			number++;
		}
	}

	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		Thread task = new PrimeGenerator();
		task.start();

		Thread.sleep(3000);
		task.interrupt();
		Thread.sleep(3000);
	}
}
