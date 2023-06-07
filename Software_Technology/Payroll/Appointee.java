package Payroll;

public class Appointee extends Employee{

	private int payday;
	private int hoursPerMonth;
	private double payPerHour;
	
	public Appointee (String id, int payday, int hoursPerMonth, double payPerHour) {
		super(id);
		if (payday<1 || payday>30) {
			throw new IllegalArgumentException("payday must contain value between 0 and 31");
		}
		else if (id==null) {
			throw new NullPointerException("id must not be null");
		}
		else if (id.isEmpty() || hoursPerMonth<=0 || payPerHour<=0) {
			throw new IllegalArgumentException();
		}
		this.payday=payday;
		this.hoursPerMonth=hoursPerMonth;
		this.payPerHour=payPerHour;
	}

	@Override
	public boolean isPayday(int dayOfMonth) {
		if (dayOfMonth<1 || dayOfMonth>30) {
			throw new IllegalArgumentException("day of month must contain value between 1 and 31!");
		}
		if (dayOfMonth==payday) {
			return true;
		}
		return false;
	}

	@Override
	public double calculatePay() {
		// TODO Auto-generated method stub
		return payPerHour*hoursPerMonth;
	}

	@Override
	public double calculateDeductions() {
		// TODO Auto-generated method stub
		return payPerHour*hoursPerMonth*0.4;
	}
}
