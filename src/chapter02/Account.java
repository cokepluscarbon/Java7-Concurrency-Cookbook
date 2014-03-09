package chapter02;

public class Account {
	public static void main(String[] args) throws InterruptedException {
		Account account = new Account();

		System.out.printf("BEG: Account balance is : %s\n", account.getBalance());

		Thread company = new Thread(new Company(account));
		Thread bank = new Thread(new Bank(account));

		company.start();
		bank.start();

		company.join();
		bank.join();

		System.out.printf("END: Account balance is : %s\n", account.getBalance());
	}

	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public synchronized void addAmount(double amount) {
		double tmp = balance;

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
	}

	public synchronized void subtractAmount(double amount) {
		double tmp = balance;

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;
	}
}

class Bank implements Runnable {
	private Account account;

	public Bank(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.subtractAmount(1000);
		}
	}

}

class Company implements Runnable {
	private Account account;

	public Company(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.addAmount(1000);
		}
	}

}