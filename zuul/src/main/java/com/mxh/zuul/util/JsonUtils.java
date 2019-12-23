package com.mxh.zuul.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON帮助类
 *
 * @ClassName: JsonUtils   
 * @author chenting
 * @date 2017年5月26日 
 *
 */
public class JsonUtils {
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	/**
	 * 将JSON对象格式的字符串转换成Object对象
	 * @param jsonStr
	 * @return Object
	 * @author chenting    
	 * @date 2017年5月26日
	 */
	public static Object toObject(String jsonStr) {
		logger.info("要转换为OBJECT的JSON字符串为["+jsonStr+"]");
		Object result = null;
		if(StringUtils.isNull(jsonStr)){
			return result;
		}
		try {
			result = JSONObject.parse(jsonStr);
		} catch (Exception e) {
			logger.error("JSON字符串["+jsonStr+"]转换为OBJECT对象失败", e);
		}
		return result;
	}
	
	/**
	 * 将JSON格式的字符串转换成JSONObject对象
	 * @param jsonStr
	 * @return Object
	 * @author chenting    
	 * @date 2017年5月26日
	 */
	public static JSONObject toJSONObject(String jsonStr) {
		logger.info("要转换为JSONObject的JSON字符串为["+jsonStr+"]");
		JSONObject result = null;
		if(StringUtils.isNull(jsonStr)){
			return result;
		}
		try {
			result = JSONObject.parseObject(jsonStr);
		} catch (Exception e) {
			logger.error("JSON字符串["+jsonStr+"]转换为JSONObject对象失败", e);
		}
		return result;
	}
	/**
	 * 将JSON格式的字符串转换成JSONObject对象
	 * @param jsonStr
	 * @return Object
	 * @author chenting
	 * @date 2017年5月26日
	 */
	public static <T> T toJavaObject(String jsonStr, Class<T> clazz) {
		logger.info("要转换为JavaObject的JSON字符串为["+jsonStr+"]");
		T result = null;
		if(StringUtils.isNull(jsonStr)){
			return result;
		}
		try {
			JSONObject a = JSONObject.parseObject(jsonStr);
			if(a != null) {
				return JSONObject.toJavaObject(a, clazz);
			}
		} catch (Exception e) {
			logger.error("JSON字符串["+jsonStr+"]转换为JSONObject对象失败", e);
		}
		return result;
	}
	
	/**
	 * 将Object对象转换成JSON字符串
	 * @param Object
	 * @return Object
	 * @author chenting    
	 * @date 2017年5月26日
	 */
	public static String toJSONString(Object obj) {
		String result = null;
		if(obj == null){
			return result;
		}
		try {
			result = JSONObject.toJSONString(obj);
		} catch (Exception e) {
			logger.error("Object对象转换为JSON字符串失败", e);
		}
		return result;
	}
	/**
	 * 将List转成json数组
	 * @param configList
	 * @return
	 */
	public static <T> JSONArray toJSONArray(List<T> configList) {
		JSONArray jsonArray = null;
		if(configList == null){
			return jsonArray;
		}
		try {
			jsonArray = (JSONArray) JSONArray.toJSON(configList);
		} catch (Exception e) {
			logger.error("将List转成json数组失败", e);
		}
		
		return jsonArray;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		JSONArray jsonArray = toJSONArray(list);
		System.out.println(jsonArray.toJSONString());
	}

	public static <T> List<T> toJSONArray(String str, Class<T> clazz) {
		List<T> result = null;
		try {
			result = JSONArray.parseArray(str, clazz);
		} catch (Exception e) {
			logger.error("将字符串转成json数组失败", e);
		}
		return result;
	}

	/**
	 * 将JSON字符串转成MAP
	 * @Description TODO
	 * @Author chenting
	 * @Date 2019/11/14 下午3:17
	 * @param jsonString
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	public static Map jsonStr2Map(String jsonString) {
		if(jsonString == null || "".equalsIgnoreCase(jsonString)) {
			return null;
		}
		Map json = JSON.parseObject(jsonString);
		return json;
	}
}
