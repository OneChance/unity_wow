using UnityEngine;
using System.Collections;

public class close : MonoBehaviour
{

		public GameObject regPanel;

		void OnClick ()
		{
				regPanel.SetActive (false);
		}
}
