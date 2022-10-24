package com.gsww.qyws.gzbd.service;


import java.util.List;
import java.util.Map;

public interface NcovIndexDataService {
    /**
     * 获取地图数据列表
     * @param params
     * @return
     */
    List<Map<String, Object>> mapDataList(Map params);
    /**
     * 地图上方汇总数据
     * @param params
     * @return
     */
    Map<String, Object> getMapCountInfo(Map params);
    /**
     * 地图下方疑似病例汇总数据
     * @param params
     * @return
     */
    Map<String, Object> getYsblCountInfo(Map params);
}

