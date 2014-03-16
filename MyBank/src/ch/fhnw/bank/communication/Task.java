package ch.fhnw.bank.communication;

import java.io.Serializable;

import ch.fhnw.bank.Bank;

public abstract class Task implements Serializable {
	private static final long serialVersionUID = 7591468067248228207L;
	protected Exception exception;
	
	public abstract void execute(Bank bank);
	
	public Exception getException() {
		return exception;
	}
	
	public boolean hasException() {
		return exception != null;
	}
}
