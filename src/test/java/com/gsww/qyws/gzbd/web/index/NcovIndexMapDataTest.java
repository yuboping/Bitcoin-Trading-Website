package com.gsww.qyws.gzbd.web.index;

import com.gsww.qyws.gzbd.rest.NcovIndexController;
import com.gsww.qyws.gzbd.service.NcovIndexDataService;
import com.gsww.qyws.gzbd.utils.JSONUtil;
import com.gsww.qyws.gzbd.web.BaseUnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: NcovIndexMapDataTest
 * @Package com.gsww.qyws.gzbd.web.index
 * @Description:
 * @author: zhangjianyuan
 * @create: 2020-01-26 16:22
 **/
public class NcovIndexMapDataTest extends BaseUnitTest {
    @Autowired
    NcovIndexDataService ncovIndexDataService;

    private static Logger log = LoggerFactory.getLogger(NcovIndexMapDataTest.class);

    @Before
    public void before() {
        log.info("=======NcovIndexMapDataTest start test========");
    }

    @Test
    public void getMapData() {
        Map params = new HashMap();
        params.put("areaCode", "62");
        List list = ncovIndexDataService.mapDataList(params);
        log.info(JSONUtil.writeListJSON(list));
        Assert.assertNotNull(list);
    }

    @Test
    public void getMapCountInfo() {
        Map params = new HashMap();
        params.put("areaCode", "62");
        Map data = ncovIndexDataService.getMapCountInfo(params);
        log.info(data.toString());
        Assert.assertNotNull(data);
    }

    @Test
    public void getYsblCountInfo() {
        Map params = new HashMap();
        params.put("areaCode", "62");
        Map data = ncovIndexDataService.getYsblCountInfo(params);
        log.info(data.toString());
        Assert.assertNotNull(data);
    }
}
