package chapter03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private final Semaphore semaphore;
	private boolean freePrinters[];
	private Lock lockPrinters;

	public PrintQueue() {
		this.semaphore = new Semaphore(3);
		this.freePrinters = new boolean[3];
		for (int i = 0; i < 3; i++) {
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			semaphore.acquire();
			int assignedPrinter = getPrinter();
<<<<<<< HEAD
			long duration = (long) (Math.random() * 1000);
			System.out.printf("%s: PrintQueue: Printing a Job during %d millisecond\n", Thread.currentThread()
					.getName(), duration);
			Thread.sleep(duration);
=======

			long duration = (long) (Math.random() * 10);
			System.out
					.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",
							Thread.currentThread().getName(), assignedPrinter,
							duration);
			TimeUnit.SECONDS.sleep(duration);
>>>>>>> 33e75d60bb3c0c068bd2b2b06eae9e47df622606
			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;
		try {
			lockPrinters.lock();
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
<<<<<<< HEAD
			lockPrinters.unlock();
		} catch (Exception e) {
			e.printStackTrace();
=======
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPrinters.unlock();
>>>>>>> 33e75d60bb3c0c068bd2b2b06eae9e47df622606
		}
		return ret;
	}

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}

		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}

class Job implements Runnable {
	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n", Thread.currentThread()
				.getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread
				.currentThread().getName());
	}
}
