package com.gsww.qyws.gzbd.service;


import java.util.List;
import java.util.Map;

public interface BrokenLineDataService {


    /**
     * 获取折线图数据
     *
     * @return
     */
    List<Map<String, Object>> getSuspectList(String indexTime,String endTime,String xzqh) throws Exception;


}

