using UnityEngine;
using System.Collections;

public class reg : MonoBehaviour
{

		public UILabel acc_label;
		public UILabel pwd_label;

		void OnClick ()
		{
				if (acc_label.text != string.Empty && pwd_label.text != string.Empty) {		
						//Debug.Log ("send account info to server");							
				} else {	
						alertConstants.alertList.Add(alertConstants.INPUT_ERROR);	
				}
		}
}
