using UnityEngine;
using System;
using System.Collections.Generic;

public class CenterController : MonoBehaviour
{
		private LoginController loginC;

		void Start ()
		{
				loginC = GetComponent <LoginController> ();
		}

		void Update ()
		{
				
				NetWorkScript nws = NetWorkScript.getInstance ();
				
				List<SocketModel> messageList = NetWorkScript.getInstance ().getMessageList ();

				for (int i=0; i<5; i++) {
						if (messageList.Count > 0) {
								SocketModel model = messageList [0];
								onMessage (model);
								messageList.RemoveAt (0);
						} else {
								break;
						}		
				}	
				
		}

		public void onMessage (SocketModel model)
		{
				switch (model.type) {
				case Module.LOGIN:
						loginC.onMessage (model);
						
						

						break;	
				default:
						break;
				}
		}
}


