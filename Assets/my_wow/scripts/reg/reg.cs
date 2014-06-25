using UnityEngine;
using System.Collections;

public class reg : MonoBehaviour
{

		public UILabel acc_label;
		public UILabel pwd_label;

		void OnClick ()
		{
				if (acc_label.text != string.Empty && pwd_label.text != string.Empty) {		
						Account account = new Account ();
						account.userName = acc_label.text;
						account.password = pwd_label.text;
			
						NetWorkScript nws = NetWorkScript.getInstance ();

						SocketModel model = new SocketModel ();
			
						model.type = Module.LOGIN;
						model.area = 0;
						model.command = Protocal.REG_REQ;
						model.message = JsonUtil<Account>.encode (account);
			
						nws.sendMessage (model);				
				} else {	
						alertConstants.alertList.Add (alertConstants.INPUT_ERROR);	
				}
		}
}
