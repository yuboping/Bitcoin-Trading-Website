package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.HosBasicInfoService;
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
 * 首页右侧
 * 物资储备情况
 */
@Controller
@RequestMapping(value = "/rightIndex")
public class RightIndexController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(RightIndexController.class);
    @Autowired
    private HosBasicInfoService hosBasicInfoService;

    /**
     * 获取首页右右侧数据物资储备
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getWzcbData")
    @ResponseBody
    public ResultEntity getWzcbData(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        String areaCode=String.valueOf(params.get("areaCode"));
        String bgsj=String.valueOf(params.get("bgsj"));
        //上报数据状态
        String state=String.valueOf(params.get("hostState"));
        try {
            Map<String, Object> mapData = hosBasicInfoService.getHoaBasicInfoByAreaCodeAndDate(areaCode,bgsj,state);

            resultEntity.put("mapData", mapData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取物资储备数据错误");
        }
        return resultEntity;
    }
}

