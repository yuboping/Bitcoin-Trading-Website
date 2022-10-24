package com.gsww.qyws.gzbd.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类名: AgreementJsonParser <br/>
 * 功能: TODO ADD FUNCTION. <br/>
 * 创建时间: 2014-3-18 下午9:23:09 <br/>
 * 
 * @author jiangy
 * @version
 * @since Jdk 1.6
 */
public class JsonParser {

    /**
     * TODO将对象转换为json字符串
     * 
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
	ObjectMapper mapper = new ObjectMapper();
	try {
	    return mapper.writeValueAsString(obj);
	} catch (JsonGenerationException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "";
    }

    public static Object stringToObject(String obj, Class cls) {
	ObjectMapper mapper = new ObjectMapper();
	try {
	    return mapper.readValue(obj, cls);
	} catch (JsonGenerationException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "";
    }

    public static <T> Object stringToObject(String obj, TypeReference<T> t) {
	ObjectMapper mapper = new ObjectMapper();
	try {
	    return mapper.readValue(obj, t);
	} catch (JsonParseException e) {
	    e.printStackTrace();
	} catch (JsonMappingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "";
    }
    
    
    
    private static final ObjectMapper MAPPER   = new ObjectMapper();
    private static JsonParser           jsonUtil = null;

    private JsonParser() {
    }

    public static JsonParser getInstance() {
        synchronized (JsonParser.class) {
            if (jsonUtil == null) {
                jsonUtil = new JsonParser();
            }
        }

        return jsonUtil;
    }

    public static String fromObject(Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        StringWriter stringWriter = new StringWriter();
        MAPPER.writeValue(stringWriter, obj);
        return stringWriter.toString();
    }

    public static String fromListForData(List<?> list) throws IOException, JsonGenerationException,
                                                      JsonMappingException {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write("{data:[");
        for (int i = 0; i < list.size(); i++) {
            stringWriter.write(fromObject(list.get(i)));
            if (i != list.size() - 1) {
                stringWriter.write(",");
            }
        }
        stringWriter.write("]}");
        return stringWriter.toString();
    }

    public static List<?> toList(String json) throws IOException, JsonGenerationException, JsonMappingException {

        MAPPER.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        return MAPPER.readValue(json, List.class);
    }

    public static Map<?, ?> toMap(String json) throws IOException, JsonGenerationException, JsonMappingException {
        MAPPER.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        return MAPPER.readValue(json, Map.class);
    }

    public static Map<?, ?> toMap1(String json) throws Exception {
        MAPPER.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        return MAPPER.readValue(json, Map.class);
    }

    /**
     * 从Json字串中得到指定属性值 
     * @param jsonStr
     * @param proertyName
     * @return
     */
    public static Object getFromJson(String jsonStr, String proertyName) {
        Map map = new HashMap();
        try {
            map = JsonParser.getInstance().toMap(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Object) map.get(proertyName);
    }

    public Object json2Object(String json, Class clazz) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
}
