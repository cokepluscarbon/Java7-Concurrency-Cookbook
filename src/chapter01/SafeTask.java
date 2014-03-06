package chapter01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTask implements Runnable {
	private ThreadLocal<Date> startDate = new ThreadLocal<Date>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable task = new SafeTask();

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();

			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		startDate.set(new Date());
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());

		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Finish Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
	}

}
