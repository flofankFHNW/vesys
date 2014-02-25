package bank.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bank.Bank;
import bank.BankDriver;
import bank.InactiveException;
import bank.OverdrawException;

public class SocketDriver implements BankDriver {
	private Bank bank;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	@Override
	public void connect(String[] args) throws IOException {
		socket = new Socket(args[0], Integer.parseInt(args[1]));
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		bank = new SocketBank(this);
		System.out.println("...Client connected with " + socket);
	}

	@Override
	public void disconnect() throws IOException {
		bank = null;
		socket.close();
		System.out.println("...disconnected");
	}

	public String[] invoke(String param) throws IllegalArgumentException, OverdrawException, InactiveException, IOException, NoSuchMethodException {
		out.println(param);
		out.flush();
		String result = in.readLine();
		String[] results = result.split(",");
		System.out.println("Got return value: " + result);
		switch(Integer.parseInt(results[0])) { // Statuscode
			case 1:
				throw new IllegalArgumentException();
			case 2:
				throw new OverdrawException();
			case 3:
				throw new InactiveException();
			case 4: 
				throw new NoSuchMethodException();
			default:
				return results;
		}
	}
	
	@Override
	public Bank getBank() {
		return bank;
	}

}
