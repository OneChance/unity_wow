package com.unity.util;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static String encode(Object bean){
		return JSONObject.fromObject(bean).toString();
	}

	public static <T> T decode(String jsonValue,Class<T> beanType){
		return beanType.cast(JSONObject.toBean(JSONObject.fromObject(jsonValue),beanType));
	}
}
