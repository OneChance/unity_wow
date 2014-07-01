using UnityEngine;
using System;

public class LoginController: MonoBehaviour
{
		public void onMessage (SocketModel module)
		{
				Account account = JsonUtil<Account>.decode (module.message);
				
				if (account.userid != 0) {
						Debug.Log ("login ok!");
				} else {
						alertConstants.alertList.Add (alertConstants.ACCOUNT_ERROR);
				}
		}			
}


