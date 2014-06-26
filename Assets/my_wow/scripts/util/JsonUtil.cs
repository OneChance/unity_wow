using LitJson;
using System;

public class JsonUtil<T>
{
	public static string encode(T bean){
		return JsonMapper.ToJson (bean);
	}

	public static T decode(string jsonValue){
		return JsonMapper.ToObject<T> (jsonValue);
	}
}


