package ch.fhnw.bank.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import ch.fhnw.bank.Bank;
import ch.fhnw.bank.BankDriver;
import ch.fhnw.bank.InactiveException;
import ch.fhnw.bank.OverdrawException;
import ch.fhnw.bank.gui.BankGUI;
import ch.fhnw.bank.server.ServerBank;

public class SocketServer {
	/** Utility class which is only used to start the server */
	private static Bank bank;
	private static final List<SocketThread> threads = new ArrayList<SocketThread>();

	private SocketServer() {
		try {
			ServerSocket server = new ServerSocket(6789);
			bank = ServerBank.getInstance();
			System.out.println("Server Listening on Port 6789");
			while (true) {
				SocketThread thread = new SocketThread(server.accept());
				threads.add(thread);
				thread.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SocketServer server = new SocketServer();
	}

	private class SocketThread implements Runnable {
		private Socket socket;

		public SocketThread(Socket socket) {
			System.out.println("New Client registered on: " + socket);
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				System.out.println("Reader and Writer registered - Listening");
				String input = in.readLine();
				while (input != null && !"".equals(input)) {
					out.println(handle(input));
					out.flush();
					input = in.readLine();
				}
				System.out.println("done serving " + socket);
				socket.close();
			} catch (IOException e) {
				System.out.println("Client closed Connection");
			}
		}
	}

	private String handle(String param) throws IOException {
		System.out.println("About to handle: " + param);
		String[] params = param.split(",");
		try {
			switch (params[0]) {
			case "createAccount":
				return "0," + bank.createAccount(params[1]);
			case "closeAccount":
				return "0," + bank.closeAccount(params[1]);
			case "getAccountNumbers":
				Set<String> numbers = bank.getAccountNumbers();
				StringBuilder sb = new StringBuilder();
				for (String number : numbers) {
					sb.append(",");
					sb.append(number);
				}
				return "0" + sb.toString();
			case "getAccountOwner":
				return "0," + bank.getAccount(params[1]).getOwner();
			case "getAccountStatus":
				return "0," + bank.getAccount(params[1]).isActive();
			case "depositOnAccount":
				bank.getAccount(params[1]).deposit(Double.parseDouble(params[2]));
				return "0";
			case "withdrawFromAccount":
				bank.getAccount(params[1]).withdraw(Double.parseDouble(params[2]));
				return "0";
			case "getAccountBalance":
				return "0," + bank.getAccount(params[1]).getBalance();
			default:
				// NoSuchMethodException
				return "4";
			}
		} catch (IllegalArgumentException e) {
			return "1";
		} catch (OverdrawException ex) {
			return "2";
		} catch (InactiveException exc) {
			return "3";
		}
	}

}
