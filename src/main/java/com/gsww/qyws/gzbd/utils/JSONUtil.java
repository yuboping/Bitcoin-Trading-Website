package com.gsww.qyws.gzbd.utils;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class JSONUtil {

	/**  
	 * 将map转换成json字符串  
	 */ 
	public static String writeMapJSON(Map<String, Object> map) { 
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(map); 
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return jsonString;
	} 
	
	/**  
	 * 将map转换成json字符串  
	 */ 
	public  String writeMapSJSON(Map<String, String> map) { 
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(map); 
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return jsonString;
	} 
	
	/**  
	 * 将list转换成json字符串  
	 */ 
	public  static String writeListJSON(List<String> list) {
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(list);  
		} catch (IOException e) {  
			e.printStackTrace();  
		} 
		return jsonString;
	} 
	
	/**  
	 * 将list<Map>转换成json字符串  
	 */ 
	public static  String writeListMapJSON(List<Map<String,String>> listMap) {
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(listMap);  
		} catch (IOException e) {  
			e.printStackTrace();  
		} 
		return jsonString;
	} 
	/**  
	 * 将list<Map>转换成json字符串  
	 */ 
	public static String writeListMapJSONMap(List<Map<String,Object>> listMap) {
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(listMap);  
		} catch (IOException e) {  
			e.printStackTrace();  
		} 
		return jsonString;
	} 
	/**
	 * 将数组对象转换成json字符串
	 */
	public  String writeArrayJSON(String[] array) {
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
	    try {
	    	jsonString = objectMapper.writeValueAsString(array);    
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return jsonString;
	}
	
	/**  
	 * json字符串转换成Map  
	 */ 
	@SuppressWarnings("unchecked")
	public  Map<String,String> readJsonMap(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> map = new LinkedHashMap<String,String>();
		try{
			map = objectMapper.readValue(jsonString, Map.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return map;
	}
	
	/**  
	 * json字符串转换成Map  
	 */ 
	@SuppressWarnings("unchecked")
	public static  Map<String,Object> readJsonMapObject(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		try{
			map = objectMapper.readValue(jsonString, Map.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return map;
	}
	
	/**  
	 * json字符串转换成list  
	 */ 
	@SuppressWarnings("unchecked")
	public  List<String> readJsonList(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		List<String> list = new ArrayList<String>();
		try{
			list = objectMapper.readValue(jsonString, List.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public  List<Object> readJsonListObject(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> list = new ArrayList<Object>();
		try{
			list = objectMapper.readValue(jsonString, List.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return list;
	}
	
	/**  
	 * json字符串转换成list<map>  
	 */ 
	@SuppressWarnings("unchecked")
	public  List<Map<String, String>> readJsonListMap(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try{
			list = objectMapper.readValue(jsonString, List.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return list;
	}
	public static List<Map<String, Object>> readJsonListMapObj(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try{
			list = objectMapper.readValue(jsonString, List.class);  
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return list;
	}
	public static Map<String, Map<String, Object>> readJsonToMapObj(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Map<String, Object>> list = null;
		try{
			list = objectMapper.readValue(jsonString,Map.class); 
		}catch (JsonParseException e) {  
			e.printStackTrace();  
		} catch (JsonMappingException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return list;
	}
	/**
	 * json字符串转换成Array
	 */
	public static String[] readJsonArray(String jsonString) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    String[] array = null;
	    try {
	        array = objectMapper.readValue(jsonString, String[].class);
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return array;
	}
	
	public static String appendJsonString(String jsonStr,Map<String,String> map){
		String jsonString = "";
		String jsonTemp = "";
		try{
			if(!map.isEmpty()){
				Set<String> key = map.keySet();
		        Iterator<String> iter = key.iterator();
		        while (iter.hasNext()) {
		            String field = iter.next();
		        	jsonTemp += "\""+field+"\":"+"\""+map.get(field)+"\",";
		        }
		        jsonTemp = jsonTemp.substring(0, jsonTemp.lastIndexOf(","));
			}
			if(StringUtils.isNotBlank(jsonStr)){
				jsonString = jsonStr.substring(0, jsonStr.lastIndexOf("}"))+","+jsonTemp+"}";
			}else{
				jsonString = jsonTemp;
			}
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		return jsonString;
	}
	

	@SuppressWarnings("rawtypes")
	public static void main(String args[])throws Exception{
		String jsonString  = "{\"app_code\":\"mrgs\",\"fucn_code\":\"NEWS\",\"page_size\":\"6\",\"page_num\":\"14\",\"param_list\":[{\"param_code\":\"lyxl\",\"param_value\":\"兰州到北京\"},{\"param_code\":\"lyxl\",\"param_value\":\"兰州到北京\"}]}";
		//String ss = "{\"931\":[\"TOURISM\",\"MEDICAL\",\"NEWS_INFO\"],\"934\":[\"TOURISM\",\"MEDICAL\",\"NEWS_INFO\"]}";
		Map<String,Object> maplist = JSONUtil.readJsonMapObject(jsonString);
		System.out.println(maplist);
		System.out.println(maplist.get("param_list"));
		for(int i=0;i<((List)maplist.get("param_list")).size();i++){
			System.out.println(((List)maplist.get("param_list")).get(i));
			System.out.println(((Map)((List)maplist.get("param_list")).get(i)).get("param_code"));
			System.out.println(((Map)((List)maplist.get("param_list")).get(i)).get("param_value"));
			//List<Map<String,String>> ssList  = (List)JSONUtil.readJsonListMap(maplist.get("param_list"));
		}
		//List maplist1 = (List)maplist.get("param_list").;
		//List<Map<String,String>> ssList  = JSONUtil.readJsonListMap(maplist.get("param_list"));
		//System.out.println(ssList);
		/*for(String coder:maplist.keySet()){
			List appList = (List)maplist.get(coder);
			for(int i=0; i<appList.size(); i++){
				System.out.println(appList.get(i));
			}
		}*/
		
	}
	
	public String writeListMapSJSON(List<Map<String, Object>> firstList) {
		// TODO Auto-generated method stub
		String jsonString ="";
		ObjectMapper objectMapper = new ObjectMapper();
		try {  
			jsonString = objectMapper.writeValueAsString(firstList);  
		} catch (IOException e) {  
			e.printStackTrace();  
		} 
		return jsonString;
	}
}