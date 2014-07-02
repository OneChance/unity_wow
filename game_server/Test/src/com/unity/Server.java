package com.unity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.unity.bean.Account;
import com.unity.bean.Protocal;
import com.unity.bean.SocketMessage;
import com.unity.util.ConvertType;
import com.unity.util.DBUtil;
import com.unity.util.JsonUtil;

public class Server extends ServerSocket {
	private static final int SERVER_PORT = 10100;
	private static DBUtil dbUtil;

	public Server() throws IOException {
		super(SERVER_PORT);

		dbUtil = new DBUtil();

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
		private DataOutputStream out;

		public CreateServerThread(Socket s) throws IOException {
			client = s;

			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());

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

					in.read(temp);
					messageLength = ConvertType.getInt(temp, true);

					byte[] messageBytes = new byte[messageLength];
					in.read(messageBytes);
					message = new String(messageBytes);

					switch (protocal) {
					case Protocal.LOGIN_REQ:// 登陆请求

						sm.setCommand(Protocal.LOGIN_RES);

						if (messageLength > 0) {
							Account account = JsonUtil.decode(message,
									Account.class);
							sm.setMessage(JsonUtil.encode(dbUtil
									.checkAccount(account)));
						}

						break;
					case Protocal.REG_REQ:// 注册请求

						sm.setCommand(Protocal.REG_RES);

						if (messageLength > 0) {
							Account account = JsonUtil.decode(message,
									Account.class);
							sm.setMessage(JsonUtil.encode(dbUtil
									.checkReg(account)));
						}

						break;
					default:
						break;
					}

					// send message to client
					byte[] type_bytes = ConvertType.getBytes(sm.getType(),
							false);
					out.write(type_bytes, 0, type_bytes.length);

					byte[] area_bytes = ConvertType.getBytes(sm.getArea(),
							false);
					out.write(area_bytes, 0, area_bytes.length);

					byte[] commond_bytes = ConvertType.getBytes(
							sm.getCommand(), false);
					out.write(commond_bytes, 0, commond_bytes.length);

					byte[] length_bytes = ConvertType.getBytes(sm.getMessage()
							.length(), false);
					out.write(length_bytes, 0, length_bytes.length);

					out.write(sm.getMessage().getBytes(), 0, sm.getMessage()
							.getBytes().length);

					out.flush();

				}

				// client.close();
			} catch (Exception e) {
				System.out.println("连接断开..........");
				try {
					out.close();
					in.close();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}