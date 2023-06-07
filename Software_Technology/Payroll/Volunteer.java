package Payroll;

public class Volunteer extends Employee{

	public Volunteer (String id) {
		super(id);
	}

	@Override
	public boolean isPayday(int dayOfMonth) {
		if (dayOfMonth<1 || dayOfMonth>30) {
			throw new IllegalArgumentException("day of month must contain value between 1 and 31");
		}
		return false;
	}

	@Override
	public double calculatePay() throws UnpayableEmployeeException {
		throw new UnpayableEmployeeException("A volunteer does not receive any salary");
	}

	@Override
	public double calculateDeductions() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
