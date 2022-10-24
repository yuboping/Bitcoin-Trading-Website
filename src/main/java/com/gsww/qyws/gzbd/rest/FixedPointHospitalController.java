package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.FixedPointHospitalDataService;
import com.gsww.qyws.gzbd.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指标控制器
 */
@Controller
@RequestMapping("/fixedPointHospital")
public class FixedPointHospitalController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FixedPointHospitalController.class);

    @Autowired
    private FixedPointHospitalDataService fixedPointHospitalDataService;

    /**
     * 柱状图数据封装
     * 前十的数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getFixedPointHostop10", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity getFixedPointHostop10(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //String areaCode=String.valueOf(params.get("areaCode"));
        String areaCode="62";//默认全省
        String state=String.valueOf(params.get("patientState"));
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try{

            List<Map<String, Object>> allList= fixedPointHospitalDataService.getHosAllTop10(areaCode,state);
         resultEntity.put("allList", allList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取定点医院前十数据出错");
        }
        return resultEntity;
    }


}