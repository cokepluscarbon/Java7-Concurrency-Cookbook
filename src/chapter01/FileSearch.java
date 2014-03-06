package chapter01;

import java.io.File;

public class FileSearch implements Runnable {
	private String fileName;
	private String initPath;

	public FileSearch(String fileName, String initPath) {
		this.fileName = fileName;
		this.initPath = initPath;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException ex) {
				System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
			}
		}

	}

	private void directoryProcess(File file) throws InterruptedException {
		File[] files = file.listFiles();
		for (File subFile : files) {
			if (!subFile.isDirectory()) {
				fileProcess(subFile);
			} else {
				directoryProcess(subFile);
			}

			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
		}

	}

	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.printf("Find : %s\n", file.getAbsoluteFile());
		} else {

		}

		if (Thread.interrupted()) {
			throw new InterruptedException();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		Thread fileSearch = new Thread(new FileSearch("readme.txt", "D:\\"));
		fileSearch.start();

		Thread.sleep(5000);
		fileSearch.interrupt();
	}
}
