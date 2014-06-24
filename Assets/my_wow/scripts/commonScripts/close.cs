using UnityEngine;
using System.Collections;

public class close : MonoBehaviour
{

		public GameObject closeObject;
	
		void OnClick ()
		{
			closeObject.SetActive (false);
		}
}
