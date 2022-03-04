import java.util.ArrayList;

public class CustomerCurrentAccount extends CustomerAccount 
{
	ATMCard atm;
	double overDraft;
	
public CustomerCurrentAccount()
{
	super();
	this.atm = null;
	this.overDraft = 0.0;
	
}



public CustomerCurrentAccount(ATMCard atm, double overDraft) {
	super();
	this.atm = atm;
	this.overDraft = overDraft;
}



public ATMCard getAtm() {
	return atm;
}

public void setAtm(ATMCard atm) {
	this.atm = atm;
}

public double getOverDraft() {
	return overDraft;
}

public void setOverDraft(double overDraft) {
	this.overDraft = overDraft;
}



}