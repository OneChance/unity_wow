using UnityEngine;
using System.Collections;
using System.Net.Sockets;
using System;
using System.IO;

public class NetWorkScript
{
		private static NetWorkScript instance;
		private static Socket socket;
		private static string ip = "127.0.0.1";
		private static int port = 10100;
		private byte[] buff = new byte[1024];

		public static NetWorkScript getInstance ()
		{
				if (instance == null) {
						instance = new NetWorkScript ();					
				}
				return instance;
		}

		public void init ()
		{
				try {
						socket = new Socket (AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
						socket.Connect (ip, port);
						
						Debug.Log ("connect success");	
				
						socket.BeginReceive (buff, 0, 1024, SocketFlags.None, ReceiveCallBack, this);

				} catch {
						Debug.Log ("connect error");
				}
		}

		private void ReceiveCallBack (IAsyncResult ar)
		{
				try {
						//获取消息体长度
						int readCount = 0;
						readCount = socket.EndReceive (ar);
						byte[] temp = new byte[readCount];
						//将服务器返回的缓存内容读取到temp中
						Buffer.BlockCopy (buff, 0, temp, 0, readCount);					
				} catch {
						socket.Close ();
						Debug.Log ("net work error");		
				}

				socket.BeginReceive (buff, 0, 1024, SocketFlags.None, ReceiveCallBack, this);	
		}

		private void readMessage (byte[] message)
		{
				MemoryStream ms = new MemoryStream (message, 0, message.Length);
				ByteArray ba = new ByteArray (ms);
				SocketModel model = new SocketModel ();
				model.type = ba.ReadInt ();
				model.area = ba.ReadInt ();
				model.command = ba.ReadInt ();
				int length = ba.ReadInt ();
				model.message = ba.ReadUTFBytes ((uint)length);
		}
}
