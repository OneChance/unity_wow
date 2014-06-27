using UnityEngine;
using System.Collections;
using System.Net.Sockets;
using System;
using System.IO;
using System.Collections.Generic;

public class NetWorkScript
{
		private static NetWorkScript instance;
		private static Socket socket;
		private static string ip = "127.0.0.1";
		private static int port = 10100;
		private static byte[] buff = new byte[1024];
		private static List<SocketModel> messageList = new List<SocketModel> ();

		public static NetWorkScript getInstance ()
		{
				if (instance == null) {
						instance = new NetWorkScript ();	
						init ();
				}
				return instance;
		}

		public List<SocketModel> getMessageList ()
		{
				return messageList;
		}

		public static void init ()
		{
				try {
						socket = new Socket (AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
						socket.Connect (ip, port);
						socket.BeginReceive (buff, 0, 1024, SocketFlags.None, ReceiveCallBack, null);
			
				} catch {
						alertConstants.alertList.Add (alertConstants.SERVER_ERROR);
				}
		}

		private static void ReceiveCallBack (IAsyncResult ar)
		{
				try {
						//获取消息体长度
						int readCount = 0;
						readCount = socket.EndReceive (ar);

						Debug.Log ("readCount:" + readCount);
						Debug.Log ("buff length:" + buff.Length);

						byte[] temp = new byte[readCount];
						//将服务器返回的缓存内容读取到temp中
						Buffer.BlockCopy (buff, 0, temp, 0, readCount);	

						readMessage (temp);

				} catch {
						socket.Close ();
						Debug.Log ("net work error when receive");		
				}

				socket.BeginReceive (buff, 0, 1024, SocketFlags.None, ReceiveCallBack, null);	
		}

		public void sendMessage (SocketModel model)
		{
				ByteArray ba = new ByteArray ();
				ba.WriteInt (model.type);
				ba.WriteInt (model.area);
				ba.WriteInt (model.command);
				if (model.message != null && model.message != string.Empty) {
						ba.WriteInt (model.message.Length);
						ba.WriteUTFBytes (model.message);
				} else {
						ba.WriteInt (0);		
				}

				try {
						socket.Send (ba.Buffer, ba.Length, SocketFlags.None);
				} catch {
						alertConstants.alertList.Add (alertConstants.SERVER_ERROR);
				}
		}

		public static void readMessage (byte[] message)
		{
				MemoryStream ms = new MemoryStream (message, 0, message.Length);
				ByteArray ba = new ByteArray (ms);
				SocketModel model = new SocketModel ();

				model.type = ba.ReadInt ();

				Debug.Log ("type:" + model.type);

				model.area = ba.ReadInt ();
				
				Debug.Log ("area:" + model.area);

				model.command = ba.ReadInt ();

				Debug.Log ("command:" + model.command);

				int length = ba.ReadInt ();

				Debug.Log ("message length:" + length);

				if (length > 0) {
						model.message = ba.ReadUTFBytes ((uint)length);
				}

				Debug.Log ("message:" + model.message);	

				messageList.Add (model);
		}
}
