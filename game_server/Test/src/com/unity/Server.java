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
import java.util.List;
import java.util.Map;

import com.unity.bean.Account;
import com.unity.bean.Protocal;
import com.unity.bean.SocketMessage;
import com.unity.util.ConvertType;
import com.unity.util.JsonUtil;

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

				byte[] temp = new byte[4];

				while (true) {
					
					SocketMessage sm = new SocketMessage();

					int type = 0;
					in.read(temp);
					type = ConvertType.getInt(temp, true);
					
					sm.setType(type);

					int area = 0;
					in.read(temp);
					area = ConvertType.getInt(temp, true);
					sm.setArea(area);

					int protocal = 0;
					in.read(temp);
					protocal = ConvertType.getInt(temp, true);

					int messageLength = 0;
					String message = "";
					
					switch (protocal) {
					case Protocal.LOGIN_REQ:// µÇÂ½ÇëÇó

						sm.setCommand(Protocal.LOGIN_RES);
						
						in.read(temp);
						messageLength = ConvertType.getInt(temp, true);

						if (messageLength > 0) {
							byte[] messageBytes = new byte[messageLength];
							in.read(messageBytes);
							message = new String(messageBytes);
							
							Account account = JsonUtil.decode(message,Account.class);
							
						}

						break;
					case Protocal.REG_REQ:// ×¢²áÇëÇó

						in.read(temp);
						messageLength = ConvertType.getInt(temp, true);

						if (messageLength > 0) {
							byte[] messageBytes = new byte[messageLength];
							in.read(messageBytes);
							message = new String(messageBytes);
						}

						break;
					default:
						break;
					}

				}

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