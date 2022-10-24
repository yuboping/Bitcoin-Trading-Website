package com.gsww.qyws.gzbd.service.impl;

import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.DetailBysandzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DetailBysandzServiceImpl implements DetailBysandzService {
        @Autowired
        private JdbcTemplate yypjJdbcTemplate;
        @Override
        public List<Map<String, Object>> getTotalBysz(Map params) {
            String areaCode = (String)params.get("areaCode");
            StringBuffer sql = new StringBuffer();
            String areaCode1 = areaCode+"%";
            List<Map<String, Object>> list = new ArrayList<>();
            if(areaCode.length() == 2){
                sql.append(" select s.code,s.name,case when m.ispcysbl is not null then m.ispcysbl else 0 end as ispcysbl,case when m.isqzbl is not null then m.isqzbl else 0 end as isqzbl,case when m.issw is not null then m.issw else 0 end as issw,case when m.iscy is not null then m.iscy else 0 end as iscy from area_qg_2019 s left join (SELECT SUBSTR(t.xz_xian_bm, 1, 2) area_code,count(DISTINCT CASE WHEN isqzbl != '1' and ispcysbl!='1' THEN sfzh   END) AS ispcysbl,count(DISTINCT CASE WHEN isqzbl = '1'  and issw != '1' and iscy != '1' THEN sfzh END) AS isqzbl,count(DISTINCT CASE WHEN issw = '1' THEN sfzh END) AS issw,count(DISTINCT CASE WHEN isqzbl = '1' and  zljg = ? THEN sfzh  END) AS iscy FROM patient_info t WHERE t.state = '1' AND t.xz_xian_bm LIKE ? group  by  SUBSTR(t.xz_xian_bm, 1, 2)) m on s.code= m.area_code where s.code =? union all select s.code,s.name,case when m.ispcysbl is not null then m.ispcysbl else 0 end as ispcysbl,case when m.isqzbl is not null then m.isqzbl else 0 end as isqzbl,case when m.issw is not null then m.issw else 0 end as issw,case when m.iscy is not null then m.iscy else 0 end as iscy from area_qg_2019 s left join (SELECT SUBSTR(t.xz_xian_bm, 1, 4) area_code,count( DISTINCT CASE WHEN isqzbl != '1'  and ispcysbl!='1' THEN sfzh  END) AS ispcysbl,count(DISTINCT CASE WHEN isqzbl = '1' and issw != '1' and iscy != '1' THEN sfzh END) AS isqzbl,count( DISTINCT CASE WHEN issw = '1' THEN sfzh END) AS issw,count( DISTINCT CASE WHEN isqzbl = '1' and zljg = ? THEN sfzh END) AS iscy FROM patient_info t WHERE t.state = '1' AND t.xz_xian_bm LIKE ? group  by  SUBSTR(t.xz_xian_bm, 1, 4)) m on s.code= m.area_code where s.parent_code =? ");
                list = yypjJdbcTemplate.queryForList(sql.toString(),new Object[]{Constant.cure_result_success,areaCode1,areaCode, Constant.cure_result_success,areaCode1,areaCode});
            }else {
                sql.append(" select s.code,s.name,case when m.ispcysbl is not null then m.ispcysbl else 0 end as ispcysbl,case when m.isqzbl is not null then m.isqzbl else 0 end as isqzbl,case when m.issw is not null then m.issw else 0 end as issw,case when m.iscy is not null then m.iscy else 0 end as iscy from area_qg_2019 s left join (SELECT SUBSTR(t.xz_xian_bm, 1, 6) area_code,count(DISTINCT CASE WHEN isqzbl != '1' and ispcysbl!='1' THEN sfzh  END) AS ispcysbl,count(DISTINCT CASE WHEN isqzbl = '1' and issw != '1' and iscy != '1' THEN sfzh END) AS isqzbl,count( DISTINCT CASE WHEN issw = '1' THEN sfzh END) AS issw,count( DISTINCT CASE WHEN isqzbl = '1' and  zljg = ? THEN sfzh  END) AS iscy FROM patient_info t WHERE t.state = '1' AND t.xz_xian_bm LIKE ? group  by  SUBSTR(t.xz_xian_bm, 1, 6)) m on s.code= m.area_code where s.parent_code =? ");
                list = yypjJdbcTemplate.queryForList(sql.toString(),new Object[]{Constant.cure_result_success,areaCode1,areaCode});
            }
            return list;
        }

}
