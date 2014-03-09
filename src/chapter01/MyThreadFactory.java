package chapter01;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MyThreadFactory implements ThreadFactory {
	private int counter;
	private String name;
	private List<String> status;

	public static void main(String[] args) {
		Task3 task3 = new Task3();
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");

		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 10; i++) {
			factory.newThread(task3);
		}

		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}

	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		status = new ArrayList<String>();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, name + "_Thread-" + counter);
		counter++;
		status.add(String.format("Created thread %d with name %s on %s\n", thread.getId(), thread.getName(), new Date()));
		return thread;
	}

	public String getStats() {
		StringBuffer sb = new StringBuffer();
		for (String statu : status) {
			sb.append(statu + "\n");
		}

		return sb.toString();
	}
}

class Task3 implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
