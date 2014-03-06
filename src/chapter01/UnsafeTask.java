package chapter01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UnsafeTask implements Runnable {
	private Date startDate;

	public static void main(String[] args) {
		Runnable task = new UnsafeTask();

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
		startDate = new Date();
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);

		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Finish Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
	}

}
