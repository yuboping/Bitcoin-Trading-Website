package com.gsww.qyws.gzbd.rest;

import com.gsww.qyws.gzbd.common.BaseController;
import com.gsww.qyws.gzbd.entity.ResultEntity;
import com.gsww.qyws.gzbd.service.DetailBysandzService;
import com.gsww.qyws.gzbd.service.NcovIndexDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 新型肺炎监控大屏首页地图数据接口
 */
@Controller
@RequestMapping(value = "/ncovIndex")
public class NcovIndexController extends BaseController {

    @Autowired
    private
    NcovIndexDataService ncovIndexDataService;
    @Autowired
    private DetailBysandzService detailBysandzService;

    private static Logger logger = LoggerFactory.getLogger(NcovIndexController.class);

    /**
     * 获取首页地图数据接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMapData")
    @ResponseBody
    public ResultEntity getMapData(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try {
            List<Map<String, Object>> mapDataList = ncovIndexDataService.mapDataList(params);

            resultEntity.put("mapDataList", mapDataList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取地图数据出错");
        }
        return resultEntity;
    }

    /**
     * 获取地图上方统计数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getMapCountInfo")
    @ResponseBody
    public ResultEntity getMapCountInfo(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try {
            Map<String, Object> mapCountInfo = ncovIndexDataService.getMapCountInfo(params);

            resultEntity.put("mapCountInfo", mapCountInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取地图上方统计数据出错");
        }
        return resultEntity;
    }

    /**
     * 获取地图下方疑似病例数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getYsblCountInfo")
    @ResponseBody
    public ResultEntity getYsblCountInfo(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try {
            Map<String, Object> ysblCountInfo = ncovIndexDataService.getYsblCountInfo(params);

            resultEntity.put("ysblCountInfo", ysblCountInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取地图下方疑似病例数据出错");
        }
        return resultEntity;
    }

    /**
     * 获取各市县疫情统计情况
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDetailByszInfo")
    @ResponseBody
    public ResultEntity getDetailByszInfo(HttpServletRequest request, HttpServletResponse response) {
        Map params = getParameterMap(request);
        //封装返回数据
        ResultEntity resultEntity = ResultEntity.ok("获取成功");
        try {
            List<Map<String, Object>> detailcountByszInfo = detailBysandzService.getTotalBysz(params);

            resultEntity.put("detailcountByszInfo", detailcountByszInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultEntity = ResultEntity.error("获取失败");
        }
        return resultEntity;
    }

}
