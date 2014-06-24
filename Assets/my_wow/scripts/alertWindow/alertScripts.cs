using UnityEngine;
using System.Collections;

public class alertScripts : MonoBehaviour {

	public UILabel messageLabel;

	public void setMessage(string message){
		messageLabel.text = message;
	}

}
