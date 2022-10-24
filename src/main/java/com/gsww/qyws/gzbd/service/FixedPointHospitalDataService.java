package com.gsww.qyws.gzbd.service;


import java.util.List;
import java.util.Map;

public interface FixedPointHospitalDataService {

    /**
     * 获取定点机构
     *   前十的数据
     * @return
     */
    List<Map<String, Object>> getHosAllTop10(String  areaCode ,String state) throws Exception;


}

