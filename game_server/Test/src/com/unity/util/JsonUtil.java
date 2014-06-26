package com.unity.util;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static <T> String encode(T bean){
		return JSONObject.fromObject(bean).toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T decode(String jsonValue){
		return (T)JSONObject.toBean(JSONObject.fromObject(jsonValue));
	}
}
