package com.gsww.qyws.gzbd.service.impl;



import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.FixedPointHospitalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FixedPointHospitalDataServiceImpl implements FixedPointHospitalDataService {

    @Autowired
    private JdbcTemplate yypjJdbcTemplate;

    @Override
    public List<Map<String, Object>> getHosAllTop10(String  areaCode ,String state) throws Exception {

        /**
         * 查询数据中字段的含义
         * org_name:医疗机构名称
         * isqzbl：确诊
         * isys：疑似
         * iscy：治愈
         * issw：死亡
         *
         */
        String sql ="SELECT * FROM(SELECT t.org_code,t.org_name,IFNULL( count( DISTINCT CASE WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' THEN  sfzh END ), 0 ) AS isqzbl,IFNULL( count( DISTINCT CASE WHEN t.isqzbl != '1'  and t.ispcysbl !='1' THEN sfzh  END ), 0 ) AS isys,\n" +
                "IFNULL( count( DISTINCT CASE WHEN t.isqzbl = '1' and  t.zljg = ?  THEN sfzh  END ), 0 ) AS iscy,IFNULL( count( DISTINCT CASE WHEN t.issw = '1' THEN sfzh  END ), 0 ) AS issw  \n" +
                "FROM patient_info t WHERE t.org_code LIKE concat(?, '%') AND t.state = ? GROUP BY t.org_code,t.org_name) k order by k.isqzbl  DESC,k.isys DESC,k.iscy desc LIMIT 10";

        return  yypjJdbcTemplate.queryForList(sql,new Object[]{Constant.cure_result_success,areaCode , state});
    }
}
