package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.ColumnarDataService;
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
 * 柱状图控制器
 */
@Controller
@RequestMapping("/columnar")
public class ColumnarController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ColumnarController.class);

    @Autowired
    private ColumnarDataService  columnarDataService;


    /**
     * 柱状图数据封装
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getColumnarList", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity getMedInsertInfo(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try{
            List<Map<String, Object>> suspectList = columnarDataService.getSuspectList(params);
            resultEntity.put("suspectList", suspectList);

        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取柱状图数据出错");
        }
        return resultEntity;
    }
}