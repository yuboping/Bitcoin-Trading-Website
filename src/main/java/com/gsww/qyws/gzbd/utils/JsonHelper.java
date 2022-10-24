package com.gsww.qyws.gzbd.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

public class JsonHelper {
	private static ObjectMapper mapper;

	private static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			
		}
			return mapper;
		
	}

	public static Map<String, Object> parserJson(String json) throws Exception {
		try {
			return getMapper().readValue(json, Map.class);
		} catch (Exception e) {
			throw new Exception("解析相应的json出错", e);
		}
	}
	
	public static String parserMap(Map<String, Object> reqMap) throws Exception {
		try {
			return getMapper().writeValueAsString(reqMap);
		} catch (Exception e) {
			throw new Exception("解析相应的Map出错", e);
		}
	}
}
