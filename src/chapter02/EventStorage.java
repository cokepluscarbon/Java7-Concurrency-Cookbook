package chapter02;

import java.util.Date;
import java.util.LinkedList;

public class EventStorage {
	private int maxSize;
	private LinkedList<Date> storage;

	public static void main(String[] args) {
		EventStorage storage = new EventStorage();

		Thread producer1 = new Thread(new Producer(storage));
		Thread producer2 = new Thread(new Producer(storage));
		Thread customer1 = new Thread(new Customer(storage));
		Thread customer2 = new Thread(new Customer(storage));


		producer1.start();
		producer2.start();
		customer1.start();
		customer2.start();
	}

	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.offer(new Date());
		System.out.printf("Set: %d\n", storage.size());
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
		notifyAll();
	}
}

class Producer implements Runnable {
	private EventStorage storage;

	public Producer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}
}

class Customer implements Runnable {
	private EventStorage storage;

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

	public Customer(EventStorage storage) {
		this.storage = storage;
	}

}
