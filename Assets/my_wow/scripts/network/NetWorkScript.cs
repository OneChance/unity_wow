using UnityEngine;
using System.Collections;
using System.Net.Sockets;

public class NetWorkScript
{
		private static NetWorkScript instance;
		private Socket socket;
		private string ip = "127.0.0.1";
		private int port = 10100;

		public static NetWorkScript getInstance ()
		{
				if (instance == null) {
						instance = new NetWorkScript ();		
				}
				return instance;
		}

		public void init ()
		{
				socket = new Socket (AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
				socket.Connect (ip, port);
		}
}
