package Payroll;

import java.util.HashMap;
import java.util.Map;

public class PayrollDispositionImpl implements PayrollDisposition{
	
	private Map<Employee, Double> payments;
	
	public PayrollDispositionImpl() {
		payments = new HashMap<Employee, Double>();
	}
	
	public double getTotal () {
		double total = 0;
		for (Employee e : payments.keySet()) {
			total += payments.get(e);
		}
		return total;
	}
	
	public double getAverage() {
		double avg = 0;
		for (Employee e : payments.keySet()) {
			avg += payments.get(e);
		}
		avg = avg/payments.size();
		if (payments.size()==0) {
			return 0;
		}
		return avg;
	}
	
	public Map<Employee, Double> getPayments(){
		return payments;
	}

	@Override
	public void sendPayment(Employee empl, double payment) {
		if (empl==null) {
			throw new NullPointerException("enter a valid employee");
		}
		else if (payment<=0) {
			throw new IllegalArgumentException("payments must be greater than or equal to zero");
		}
		if(empl!=null && payment>0) {
		payments.put(empl, payment);
		}
	}

}
