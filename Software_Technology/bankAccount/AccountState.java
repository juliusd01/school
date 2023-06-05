package bankAccount;

else if (state==frozen) {
	return false;
}
else if(getBalance()-amount<0-lineOfCredit) {
	double currentBalance=getBalance();
	balance=0-lineOfCredit;
	state=frozen;
	return state.payOff(currentBalance+lineOfCredit);
}
else if (getBalance()-amount<0) {
	state=negative;
}
balance=-amount;
