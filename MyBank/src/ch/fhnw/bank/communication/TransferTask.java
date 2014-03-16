package ch.fhnw.bank.communication;

import java.io.IOException;

import ch.fhnw.bank.Bank;
import ch.fhnw.bank.InactiveException;
import ch.fhnw.bank.OverdrawException;

public class TransferTask extends Task {
	private static final long serialVersionUID = -8330245439478567427L;
	private String numberA, numberB;
	private double amount;
	
	public TransferTask(String numberA, String numberB, double amount) {
		this.numberA = numberA;
		this.numberB = numberB;
		this.amount = amount;
	}

	public void execute(Bank bank) {
		try {
			bank.transfer(bank.getAccount(numberA), bank.getAccount(numberB), amount);
		} catch (IllegalArgumentException | IOException | OverdrawException
				| InactiveException e) {
			exception = e;
		}
	}
	
	public Object getResult() {
		return null;
	}
}
