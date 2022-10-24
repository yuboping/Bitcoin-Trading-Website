package com.gsww.qyws.gzbd.common;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseController {
	public final String SUCCESS = "1";
	public final String FAIL = "0";
	public final String ERROR = "2";
	public final String NULL = "3";
	
	public final String OPER_SUCCCESS = "操作成功";
	public final String OPER_FAIL = "操作失败";
	
	public final String GET_SUCCCESS = "获取成功";
	public final String GET_FAIL = "获取失败";
	
	public final String ADD_SUCCESS = "添加成功";
	public final String ADD_FAIL = "添加失败";
	
	public final String DELETE_SUCCESS = "删除成功";
	public final String DELETE_FAIL = "删除失败";
	public final String IS_NULL = "暂无数据";
	public final String TIME_OUT = "已超时";


	/**
	 * 请求封装map
	 * @param request
	 * @return
	 */
	protected Map getParameterMap(HttpServletRequest request) {
		Map properties = request.getParameterMap();

		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();

		String name = "";
		String value = "";
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (valueObj == null) {
				value = "";
			} else if ((valueObj instanceof String[])) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}
