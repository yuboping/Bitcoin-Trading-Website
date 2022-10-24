package com.gsww.qyws.gzbd.service;


import java.util.List;
import java.util.Map;

public interface PatientInfoService {

    //疑似病人 不分人群
    public List<Map<String, Object>> querySuspectPatient(String areaCode) ;
    // 确诊病人 不分人群
    public List<Map<String, Object>> queryConfirmPatient(String areaCode,String isDtLj,String isdoctor) ;
    //治愈病人
    public List<Map<String, Object>> queryCurePatient(String areaCode) ;
    //死亡病人
    public List<Map<String, Object>> queryDeathPatient(String areaCode) ;

    /**
     * 确认患者
     * 重症、危重症、死亡、治愈
     * @param areaCode
     * @param isDtLj
     * @return
     */
    public List<Map<String, Object>> queryConfirmZzAndWzzPatient(String areaCode,String isDtLj,String  zzState,String isdoctor);

    /**
     * 疑似患者
     * @param areaCode
     * @param isDtLj  取值 dt、lj、jc
     * @param isdoctor
     * @return
     */
    public List<Map<String, Object>> querySuspectOtherPatient(String areaCode,String isDtLj,String  isdoctor,String isJc);

    /**
     * 获取患者信息合并信息
     * @param areaCode
     * @param isqzbl
     * @param isDtLj
     * @param isDoctor
     * @param zzState  或者状态
     * @param   isJc
     * @return
     */
    public List<Map<String,Object>> queryPatientInfoNew(String areaCode, String isqzbl,String isDtLj,String isDoctor,String zzState,String isJc);

    public List<Map<String,Object>> queryPatientInfoTw(String orgCode, String sfzh);

    public List<Map<String,Object>> queryPatientInfoXy(String orgCode, String sfzh);

    public List<Map<String,Object>> queryPatientInfoXq(String orgCode, String sfzh);

    /**
     * 根据机构编码和身份证号获取患者基本就诊信息
     * @param params
     * @return
     * @throws Exception
     */
    public  Map<String, Object>  getPatientInfoList(Map params) throws Exception;
}

