using UnityEngine;
using System;

public class LoginController: MonoBehaviour
{

		public GameObject regPanel;

		public void onMessage (SocketModel module)
		{
				Account account = JsonUtil<Account>.decode (module.message);

				if (account.userid != 0) {
						if (module.command == Protocal.LOGIN_RES) {
								Debug.Log("log ok!");
						} else if (module.command == Protocal.REG_RES) {
								regPanel.SetActive (false);
						}	
				} else {
						if (module.command == Protocal.LOGIN_RES) {
								alertConstants.alertList.Add (alertConstants.ACCOUNT_NOT_EXIST);
						} else if (module.command == Protocal.REG_RES) {
								alertConstants.alertList.Add (alertConstants.ACCOUNT_EXIST);
						}					
				}
		}			
}


