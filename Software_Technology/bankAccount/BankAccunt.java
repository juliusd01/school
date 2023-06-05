package bankAccount;

public class BankAccount {

	private double balance;
	private double lineOfCredit;
	private String accountNumber;
	private AccountState state;
	private Positive positive = new Positive();
	private Negative negative = new Negative();
	private Frozen frozen = new Frozen();
	private Closed closed = new Closed();
	
	public BankAccount (String accountNumber, double lineOfCredit) {
		if (accountNumber==null) {
			throw new NullPointerException("accNumber must not be null");
		}
		else if (accountNumber.isEmpty() || lineOfCredit<0) {
			throw new IllegalArgumentException("Illegal arguments");
		}
		balance=0;
		this.accountNumber=accountNumber;
		this.lineOfCredit=lineOfCredit;
		state = positive;
	}
	
	public boolean payIn (double amount) {
		if(amount<=0) {
			throw new IllegalArgumentException("Illegal arguments");
		}
		return state.payIn(amount);
	}
	
	public boolean payOff (double amount) {
		if (amount<=0) {
			throw new IllegalArgumentException("Illegal arguments");
		}
		return state.payOff(amount);
	}
	
	public boolean close() {
		if(getBalance()==0) {
			state=closed;
			return true;
		}
		else 
			return false;
	}
	
	public double getBalance() {
		return this.balance; 
	}
	
	public String getState() {
		return state.toString();
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void printBalance() {
		state.printBalance();
	}
	
	public void payInterest() {
		state.payInterest();
	}
	
	public abstract class AccountState {

		public boolean payIn(double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException("Illegal arguments");
			}
			return false;
		}
		public boolean payOff(double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException("Illegal arguments");
			}
			return false;
		}
		public String toString() {
			if (state==positive) {
				return "Positive";
			}
			else if (state==negative) {
				return "Negative";
			}
			else if (state==frozen) {
				return "Frozen";
			}
			else return "Closed";
		}
		public void payInterest() {
			state.payInterest();
		}
		public abstract void printBalance();
	}
	
	class Positive extends AccountState{

		public boolean payIn (double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException("Illegal arguments");
			}
			balance=getBalance()+amount;
			return true;
		}
		public boolean payOff(double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException("Illegal arguments");
			}
			if (getBalance()-amount<0-lineOfCredit) {
				return false;
			}
			else if (getBalance()-amount==0-lineOfCredit) {
				state=frozen;
				balance=getBalance()-amount;
			}
			else if (getBalance()-amount<0) {
				state=negative;
				balance = getBalance()-amount;
			}
			else if (getBalance()-amount>=0) {
				balance=getBalance()-amount;
			}
			return true;
		}
		public void payInterest() {
			if (getBalance()==0) {
				balance=0;
			}
			else balance=getBalance()*1.01;
		}
		public void printBalance(){
			System.out.println("Balance is POSITIVE: +" + getBalance() + ".");
		}
	}
	
	class Negative extends AccountState{
		
		public boolean payIn (double amount) {
			if(amount<=0) {
				throw new IllegalArgumentException("amount must be larger than zero");
			}
			if(getBalance()+amount>=0) {
				state=positive;
				balance=getBalance()+amount;
			}
			else {
				balance=getBalance()+amount;
			}
			return true;
		}
		
		public boolean payOff(double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException();
			}
			//payOff to Frozen
			else if(getBalance()-amount<0-lineOfCredit) {
				return false;
			}
			else if (getBalance()-amount==0-lineOfCredit) {
				state=frozen;
				balance=getBalance()-amount;
				return true;
			}
			else {
				balance=getBalance()-amount;
				return true;
			}
		}
		public void payInterest() {
			balance=getBalance()*1.03;
			if(balance<0-lineOfCredit) {
				state=frozen;
			}
		}
		public void printBalance() {
			System.out.println("Balance is NEGATIVE: " + getBalance() + ".");
		}
	}
	
	class Frozen extends AccountState {
		
		public boolean payIn (double amount) {
			if (amount<=0) {
				throw new IllegalArgumentException();
			}
			if(getBalance()+amount>=0) {
				state = positive;
				balance=getBalance()+amount;
			}
			else if (getBalance()+amount>0-lineOfCredit) {
				state=negative;
				balance=getBalance()+amount;
			}
			return true;
		}
		
		public boolean payOff(double amount) {
			return false;
		}
		public void payInterest() {
			balance=balance*1.05;
		}
		public void printBalance() {
			System.out.println("Balance is NEGATIVE: " + getBalance()+". You need to pay in money.");
		}
	}
	
	class Closed extends AccountState {
		
		public boolean payOff(double amount) {
			return false;
		}
		public void payInterest() {
			throw new IllegalStateException("bankAcc is closed.");
		}
		public void printBalance() {
			System.out.println("This account is CLOSED. The balance is 0.");
		}
	}

}