package Payroll;

public class Payroll {
	
	private int payday;
	private PayrollDisposition disposition;
	
	public Payroll (PayrollDisposition disposition, int payday) {
		if(disposition==null) {
			throw new NullPointerException("disposition must not be null");
		}
		else if (payday<1 || payday>30) {
			throw new IllegalArgumentException("payday must contain value between 1 and 31");
		}
		this.disposition=disposition;
		this.payday=payday;
	}
	
	public void doPayroll(PayrollDB db) {
		for (Employee e : db.getEmployeeList()){
			if(e.isPayday(payday)) {
			try {
				disposition.sendPayment(e, e.calculatePay()*0.6);
			} catch (Exception exp) {
			}
		}
		}
}

}
