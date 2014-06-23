package com.unity;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		new Server().startServer();
	}

	public void startServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(10100);
			System.out.println("start server");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("connect...");
			}

		} catch (Exception e) {
			System.out.println("server error" + e);
		}
	}

}
