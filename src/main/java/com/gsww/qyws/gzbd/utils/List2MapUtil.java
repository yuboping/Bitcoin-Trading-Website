package com.gsww.qyws.gzbd.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈庆勇
 * @version 2015-01-21 11:23:40
 */
public class List2MapUtil {
	/**
	 * 把List<Map<String, String>> 转成 Map<String, String>，不改变原来的list
	 * 如[{"id":"0", "text":"否"},{"id":"1", "text":"是"}] 》》 {"0":"否", "1":"是"}
	 * @param list 列表对象
	 * @param keyColumn 列表数据Map的列名，作为返回数据key值，
	 * @param valueColumn 列表数据Map的列名，作为返回数据的value值，
	 * @return
	 */
	public static Map<String, Object> list2Map(List<Map<String, Object>> list, String keyColumn, String valueColumn){
		Map<String, Object> map = null;
		if(list != null && keyColumn != null && valueColumn != null){
			map = new HashMap();
			for(Map<String, Object> data : list){
				Object tempKey = data.get(keyColumn);
				String key = String.valueOf(tempKey);
				Object value = data.get(valueColumn);
				map.put(key, value);
			}
		}
		return map;
	}
	/**
	 * 把List<Map<String, String>> 转成 Map<String, List<Map<String, String>>>，不改变原来的list
	 * 如[{"id":"0", "text":"否"},{"id":"1", "text":"是"}] 》》 {"0":{"id":"0", "text":"否"}, "1":{"id":"1", "text":"是"}}
	 * @param list 列表对象
	 * @param keyColumn 列表数据Map的列名，作为返回数据key值，
	 * @return
	 */
	public static Map<String, Map<String, Object>> list2MapMap(List<Map<String, Object>> list, String keyColumn){
		Map<String, Map<String, Object>> map = null;
		if(list != null && keyColumn != null){
			map = new HashMap<String, Map<String, Object>>();
			for(Map<String, Object> data : list){
				String key = (String) data.get(keyColumn);
				map.put(key, data);
			}
		}
		return map;
	}
	public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("id", "0");
		map.put("text", "否");
		list.add(map);
		map = new HashMap();
		map.put("id", "1");
		map.put("text", "是");
		list.add(map);

		System.out.println("转换前list数据：" + list);
		System.out.println("转换后的map数据" + list2Map(list, "id", "text"));
	}
}
