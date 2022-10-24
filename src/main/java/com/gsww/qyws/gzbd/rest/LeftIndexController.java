package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.HosBasicInfoService;
import com.gsww.qyws.gzbd.utils.TimeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 首页左侧
 * 物资储备情况
 */
@Controller
@RequestMapping(value = "/leftIndex")
public class LeftIndexController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(LeftIndexController.class);
    @Autowired
    private HosBasicInfoService hosBasicInfoService;
    /**
     * 获取首页左侧
     *发热留观人次
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getFrlgrc")
    @ResponseBody
    public ResultEntity getWzcbData(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        String areaCode=String.valueOf(params.get("areaCode"));
        String bgsj=String.valueOf(params.get("bgsj"));
        String lastBgsj= TimeHelper.getCurrentTime();
        String state=String.valueOf(params.get("hostState"));
        try {
            Map<String, Object> mapData = hosBasicInfoService.getHoaBasicInfoLgByAreaCodeAndDate(areaCode,bgsj,lastBgsj,state);

            resultEntity.put("mapData", mapData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取发热留观人次异常");
        }
        return resultEntity;
    }

    /**
     * 获取首页左侧
     *确诊病例数统计情况
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getQrblsjtj")
    @ResponseBody
    public ResultEntity getQrblsjtj(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        String areaCode=String.valueOf(params.get("areaCode"));
        String bgsj=String.valueOf(params.get("bgsj"));
        String dtlj=String.valueOf(params.get("dtlj"));
        String state=String.valueOf(params.get("patientState"));
        String isqzbl=String.valueOf(params.get("isqzbl"));
        Map<String, Object> mapData=null;
        try {
           if(dtlj!=null && dtlj.equals("dt")){
               mapData  =  mapData = hosBasicInfoService.getHoaBasicInfoQzblDtByAreaCodeAndDate(areaCode,bgsj,state,isqzbl);
           }else{
               mapData  =  mapData = hosBasicInfoService.getHoaBasicInfoQzblLjByAreaCodeAndDate(areaCode,TimeHelper.getCurrentTime(),state,isqzbl);
           }

            resultEntity.put("mapData", mapData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取确诊病例数据异常");
        }
        return resultEntity;
    }
    /**
     * 获取首页左侧
     *疑似病历统计
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getYsbltj")
    @ResponseBody
    public ResultEntity getYsbltj(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        String areaCode=String.valueOf(params.get("areaCode"));
        String bgsj=String.valueOf(params.get("bgsj"));
        String state=String.valueOf(params.get("patientState"));
        String isdoctor=String.valueOf(params.get("isdoctor"));
        //累计疑似病历
        Map<String, Object> ljysData=null;
        //昨日新增疑似
        Map<String, Object> zrysData=null;
        //累计解除疑似
        Map<String, Object> ysjcData=null;
        try {
               ljysData=hosBasicInfoService.getHoaBasicInfoYsblAllByAreaCodeAndDate(areaCode,TimeHelper.getCurrentTime(),state,isdoctor);
               zrysData=hosBasicInfoService.getHoaBasicInfoYsblByAreaCodeAndDate(areaCode,bgsj,state,isdoctor);
               ysjcData=hosBasicInfoService.getHoaBasicInfoYsblPcByAreaCodeAndDate(areaCode,TimeHelper.getCurrentTime(),state,isdoctor);
            resultEntity.put("ljysData", ljysData);
            resultEntity.put("zrysData", zrysData);
            resultEntity.put("ysjcData", ysjcData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取确诊病例数据异常");
        }
        return resultEntity;
    }
}
