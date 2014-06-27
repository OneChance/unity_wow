using UnityEngine;
using System;

public class LoginController: MonoBehaviour
{
	public void onMessage(SocketModel module){
		Account account = JsonUtil<Account>.decode (module.message);
		Debug.Log ("read ok");
		//Debug.Log (account.userid);
	}			
}


