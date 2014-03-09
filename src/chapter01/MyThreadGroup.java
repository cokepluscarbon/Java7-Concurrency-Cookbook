package chapter01;

import java.util.Random;

public class MyThreadGroup extends ThreadGroup {

	public static void main(String[] arg) {
		ThreadGroup threadGroup = new MyThreadGroup("TaskGroup");

		Task2 task = new Task2();
		for (int i = 0; i < 10; i++) {
			new Thread(threadGroup, task).start();
		}

	}

	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}

}

class Task2 implements Runnable {
	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / ((int) (random.nextDouble() * 1000));
			System.out.printf("%s : %f\n", Thread.currentThread().getId(), result);
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}

}
