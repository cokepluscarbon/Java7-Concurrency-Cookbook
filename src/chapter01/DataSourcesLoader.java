package chapter01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable {

	public static void main(String[] args) {
		Thread dataSources = new Thread(new DataSourcesLoader(), "DateSources Thread");
		Thread network = new Thread(new NetworkConnectionsLoader(), "NetWork Thread");

		dataSources.start();
		network.start();

		try {
			dataSources.join();
			network.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());

	}

	@Override
	public void run() {
		System.out.printf("Beginning data sources loading: %s\n", new Date());

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Data sources loading has finished: %s\n", new Date());
	}

}

class NetworkConnectionsLoader implements Runnable {
	@Override
	public void run() {
		System.out.printf("Beginning NetworkConnectionsLoader : %s\n", new Date());

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("NetworkConnectionsLoader finished: %s\n", new Date());
	}

}
