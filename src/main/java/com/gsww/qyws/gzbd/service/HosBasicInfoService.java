package com.gsww.qyws.gzbd.service;

import java.util.Map;

public interface HosBasicInfoService {

    /**
     * 首页-右侧
     * 物资储备情况
     */
    public Map<String,Object> getHoaBasicInfoByAreaCodeAndDate(String areaCode,String bgsj,String state);

    /**
     *
     * 首页-左侧
     * 发热留观人次
     * @param areaCode
     * @param bgsj
     * @param lastBgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoLgByAreaCodeAndDate(String areaCode,String bgsj,String lastBgsj,String state);

    /**
     * 首页-左侧-病历统计情况
     * 确诊病历当天数
     * @param areaCode
     * @param bgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoQzblDtByAreaCodeAndDate(String areaCode,String bgsj,String state,String isqzbl);

    /**
     * 首页-左侧-病历统计情况
     * 确诊病历累计
     * @param areaCode
     * @param bgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoQzblLjByAreaCodeAndDate(String areaCode,String bgsj,String state,String isqzbl);

    /**
     * 首页-左侧-疑似病历情况统计
     * 累计疑似病历人数
     * @param areaCode
     * @param bgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoYsblAllByAreaCodeAndDate(String areaCode,String bgsj,String state,String isdoctor);
    /**
     * 首页-左侧-疑似病历情况统计
     * 昨日新增
     * @param areaCode
     * @param bgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoYsblByAreaCodeAndDate(String areaCode,String bgsj,String state,String isdoctor);

    /**
     * 首页-左侧-疑似病历情况统计
     * 累计解除疑似病历
     * @param areaCode
     * @param bgsj
     * @return
     */
    public Map<String,Object> getHoaBasicInfoYsblPcByAreaCodeAndDate(String areaCode,String bgsj,String state,String isdoctor);





}
