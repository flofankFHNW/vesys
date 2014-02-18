package bank.client.socket;

import java.io.IOException;
import java.net.Socket;

import bank.Bank;
import bank.BankDriver;

public class SocketDriver implements BankDriver {
	private Bank bank;
	private Socket socket;
	
	@Override
	public void connect(String[] args) throws IOException {
		socket = new Socket(args[0], Integer.parseInt(args[1]));
		bank = new SocketBank(socket);
		System.out.println("...Client connected with " + socket);
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		socket.close();
		System.out.println("...disconnected");
	}

	@Override
	public Bank getBank() {
		return bank;
	}

}
