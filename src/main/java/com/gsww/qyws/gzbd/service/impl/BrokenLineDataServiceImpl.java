package com.gsww.qyws.gzbd.service.impl;


import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.BrokenLineDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrokenLineDataServiceImpl implements BrokenLineDataService {

    @Autowired
    private JdbcTemplate yypjJdbcTemplate;

    @Override
    public List<Map<String, Object>> getSuspectList(String indexTime,String endTime,String xzqh) throws Exception {
        String sql ="SELECT tjsj as timedate FROM gzbdjk_jsdate t WHERE tjsj >=? And tjsj <=? ORDER BY tjsj ASC";

        List<Map<String, Object>> list = yypjJdbcTemplate.queryForList(sql,new Object[]{ indexTime,endTime});
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(Map map:list){
           // String  sumsql = "SELECT IFNULL(count(0),0 )AS totalnum FROM patient_info t WHERE t.isqzbl != '1' and t.ispcysbl != '1' AND LEFT (tbsj, 10) IS NOT NULL AND LEFT (tbsj, 10) <=? AND state = '1' and xz_xian_bm like  concat(?,'%')";
            String sumsql="SELECT ? as timedate,IFNULL(count(DISTINCT CASE WHEN t.isqzbl = '1' AND t.issw != '1' AND t.iscy != '1' THEN sfzh END ),0) AS isqzbl,\n" +
                    "IFNULL(count(DISTINCT CASE WHEN t.isqzbl != '1' AND t.ispcysbl != '1' THEN sfzh END),0) AS isys,\n" +
                    "IFNULL(count(DISTINCT CASE WHEN t.isqzbl = '1' and  t.zljg =? THEN sfzh END),0) AS iscy,\n" +
                    "IFNULL(count(DISTINCT CASE WHEN t.issw = '1' THEN sfzh END),0) AS issw   \n" +
                    "FROM patient_info t WHERE t.xz_xian_bm LIKE concat(?, '%') AND t.state ='1' AND LEFT (tbsj, 10) <=?";
            List<Map<String, Object>> countmap = yypjJdbcTemplate.queryForList(sumsql,new Object[]{map.get("timedate").toString(),Constant.cure_result_success,xzqh,map.get("timedate").toString()} );
            if(countmap.size()>0){
            resultList.add(countmap.get(0));
            }else{
               Map<String, Object> map0 = new HashMap<String, Object>();
                map0.put("timedate",map.get("timedate").toString());
                map0.put("isqzbl",0);
                map0.put("isys",0);
                map0.put("iscy",0);
                map0.put("issw",0);
                resultList.add(map0);
            }
        }

        return resultList;
    }


}
