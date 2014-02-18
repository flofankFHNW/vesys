package bank.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import bank.Bank;
import bank.BankDriver;
import bank.gui.BankGUI;
import bank.server.ServerBank;

public class SocketServer {
	/** Utility class which is only used to start the server */
	private static Bank bank;
	private static final List<SocketThread> threads = new ArrayList<SocketThread>();

	private SocketServer() {
		try {
			ServerSocket server = new ServerSocket(6789);
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
		private Bank bank;

		public SocketThread(Socket socket) {
			System.out.println("New Client registered on: " + socket);
			this.socket = socket;
			bank = ServerBank.getInstance();
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				System.out.println("Reader and Writer registerd - Listening");
				String input = in.readLine();
				while (input != null && !"".equals(input)) {
					System.out.println(input);
					out.write("012345");
					out.flush();
					input = in.readLine();
				}
				System.out.println("done serving " + socket);
				socket.close();
			} catch (IOException e) {
				System.out.println("Client closed Conenction");
			}
		}

	}

}
