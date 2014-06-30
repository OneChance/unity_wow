using UnityEngine;
using System.Collections;

public class alertManager : MonoBehaviour
{

		public GameObject alertPanel;
		public alertScripts aScript;

		// Use this for initialization
		void Start ()
		{
				aScript = alertPanel.GetComponent<alertScripts> ();
		}

		void Update ()
		{	
				if (alertConstants.alertList.Count > 0) {
						int type = alertConstants.alertList [0];
						onWindow (type);
						alertConstants.alertList.RemoveAt (0);
				}
		}

		public void onWindow (int type)
		{
				switch (type) {
				case alertConstants.INPUT_ERROR:
						aScript.setMessage ("Input Error!");
						break;		
				case alertConstants.SERVER_ERROR:
						aScript.setMessage ("Server Error!");
						break;
				case alertConstants.MODULE_ERROR:
						aScript.setMessage ("Unknown module!");
						break;
				case alertConstants.ACCOUNT_ERROR:
						aScript.setMessage ("Account Error!");
						break;
				default:
						aScript.setMessage ("Unknown Error!");
						break;
				}	

				alertPanel.SetActive (true);
		}

}
