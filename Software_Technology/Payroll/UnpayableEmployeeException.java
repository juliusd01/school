package Payroll;

public class UnpayableEmployeeException extends java.lang.Exception {

	private String message;

	public UnpayableEmployeeException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
}
