using UnityEngine;
using System.Collections;

public class login : MonoBehaviour
{

		public UILabel acc_label;
		public UILabel pwd_label;

		void OnClick ()
		{
				if (acc_label.text != string.Empty && pwd_label.text != string.Empty) {
						
						NetWorkScript nws = NetWorkScript.getInstance ();
						
						nws.init ();

				} else {
						Debug.Log ("login error");		
				}
						
		}
}
