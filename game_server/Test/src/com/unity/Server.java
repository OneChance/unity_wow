package com.unity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket {
	private static final int SERVER_PORT = 10100;

	public Server() throws IOException {
		super(SERVER_PORT);

		try {
			while (true) {
				Socket socket = accept();
				new CreateServerThread(socket);
			}
		} catch (IOException e) {
		} finally {
			close();
		}
	}

	class CreateServerThread extends Thread {
		private Socket client;
		private DataInputStream in;

		public CreateServerThread(Socket s) throws IOException {
			client = s;

			in = new DataInputStream(client.getInputStream());

			start();
		}

		public void run() {
			try {

				int type = in.readInt();
				int area = in.readInt();
				int protocal = in.readInt();
				int messageLength = in.readInt();
				String message = in.readUTF();

				System.out.println("--------" + type);
				System.out.println("--------" + area);
				System.out.println("--------" + protocal);
				System.out.println("--------" + messageLength);
				System.out.println("--------" + message);

				// client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}