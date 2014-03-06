package chapter01;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
	public static void main(String[] args) {
		Thread task = new Thread(new Task());
		task.setUncaughtExceptionHandler(new ExceptionHandler());

		task.start();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}

}

class Task implements Runnable {
	@Override
	public void run() {
		Integer.parseInt("FCK");
	}

}
