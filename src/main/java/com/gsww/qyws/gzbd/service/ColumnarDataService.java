package com.gsww.qyws.gzbd.service;


import java.util.List;
import java.util.Map;

public interface ColumnarDataService {


    /**
     * 按照行政区划进行查询数据
     *
     * @return
     */
    List<Map<String, Object>> getSuspectList(Map params) throws Exception;


}

