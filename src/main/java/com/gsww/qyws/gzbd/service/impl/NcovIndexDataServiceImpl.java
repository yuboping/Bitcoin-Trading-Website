package com.gsww.qyws.gzbd.service.impl;


import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.NcovIndexDataService;
import com.gsww.qyws.gzbd.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class NcovIndexDataServiceImpl implements NcovIndexDataService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 获取地图数据
     *
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> mapDataList(Map params) {
        String areaCode = (String) params.get("areaCode");
        List<Map<String, Object>> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        if(areaCode.length() == 2){
            sql.append(" select s.code,s.name,case when m.ispcysbl is not null then m.ispcysbl else 0 end as ispcysbl,case when m.isqzbl is not null then m.isqzbl else 0 end as isqzbl from area_qg_2019 s left join (SELECT SUBSTR(t.xz_xian_bm, 1, 4) area_code,count( DISTINCT CASE WHEN isqzbl != '1'and ispcysbl!='1' THEN sfzh  END) AS ispcysbl,count(DISTINCT CASE WHEN isqzbl = '1' and issw != '1' and iscy != '1' THEN sfzh  END) AS isqzbl FROM patient_info t WHERE t.state = '1' AND t.xz_xian_bm LIKE concat(?,'%')  group  by  SUBSTR(t.xz_xian_bm, 1, 4)) m on s.code= m.area_code where s.parent_code =? ");
            list = jdbcTemplate.queryForList(sql.toString(), new Object[]{areaCode,areaCode});
        }else {
            sql.append(" select s.code,s.name,case when m.ispcysbl is not null then m.ispcysbl else 0 end as ispcysbl,case when m.isqzbl is not null then m.isqzbl else 0 end as isqzbl from area_qg_2019 s left join (SELECT SUBSTR(t.xz_xian_bm, 1, 6) area_code,count( DISTINCT CASE WHEN isqzbl != '1' and ispcysbl!='1' THEN sfzh  END) AS ispcysbl,count( DISTINCT CASE WHEN isqzbl = '1'  and issw != '1' and iscy != '1' THEN sfzh END) AS isqzbl FROM patient_info t WHERE t.state = '1' AND t.xz_xian_bm LIKE concat(?,'%')  group  by  SUBSTR(t.xz_xian_bm, 1, 6)) m on s.code= m.area_code where s.parent_code =? ");
            list = jdbcTemplate.queryForList(sql.toString(), new Object[]{areaCode,areaCode});
        }
        return list;
    }

    /**
     * 地图上方汇总数据统计
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> getMapCountInfo(Map params) {
        String areaCode = (String) params.get("areaCode");
        String bgsj = (String) params.get("bgsj");
//        Calendar rightNow = Calendar.getInstance();
//       int hour = rightNow.get(Calendar.HOUR_OF_DAY);
//        String upDate = TimeHelper.getYesterdayDate();//默认查询日期为昨天
//        //如果大于晚上20时则查询今天的日期
//        if (hour >= 20) {
//            upDate = TimeHelper.getCurrentDate();
//        }
        String  time=TimeHelper.getCurrentTime();
        String sql = "select t1.drfrmzjzrc_count,t1.drfrmzlgrc_count, t2.ysbl_count,t2.qzbl_count,t2.cy_count,t2.sw_count from";
        String sql_t1 = "(select IFNULL(sum(h.drfrmzjzrc),0) as drfrmzjzrc_count,IFNULL(sum(h.drfrmzlgrc),0) as drfrmzlgrc_count from hos_basic_info_daily h where h.bgsj<=? and h.state=?  and h.area_code like concat(?,'%')) t1,";
        String sql_t2 = "(select count(distinct  case when p.isqzbl = '1' and p.issw != '1' and p.iscy != '1' then p.sfzh end)  as qzbl_count," +
                "             count(distinct case when p.isqzbl != '1' and p.ispcysbl != '1'  then sfzh end) as ysbl_count," +
                "             count(distinct case when p.issw = '1' then sfzh end) as sw_count," +
                "             count(distinct case when p.isqzbl = '1' and  p.zljg = ?  then sfzh end) as cy_count" +
                "      from patient_info p where p.xz_xian_bm  like concat(?,'%') and p.state=? )  t2";
        return jdbcTemplate.queryForMap(sql + sql_t1 + sql_t2, new Object[]{time,"2",areaCode, Constant.cure_result_success, areaCode,"1"});
    }

    /**
     * 地图下方疑似病例统计
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> getYsblCountInfo(Map params) {
        String areaCode = (String) params.get("areaCode");
        String sql = "select * from (" +
                " select sum(case when p.isqzbl != '1' then 1 else 0 end) as ysbl_today_count," +
                "       sum(case when p.isqzbl != '1' and p.isdoctor='1' then 1 else 0 end) as ysbl_today_doc_count" +
                " from patient_info p where substr(p.tbsj, 1, 10) = date_format(now(), '%Y-%m-%d') and p.area_code like concat(?,'%')) t1," +
                "              ( select sum(case when p.isqzbl != '1' then 1 else 0 end) as ysbl_count," +
                "                         sum(case when p.isqzbl != '1' and p.isdoctor='1' then 1 else 0 end) as ysbl_doc_count" +
                "                  from patient_info p where   p.area_code like concat(?,'%')) t2," +
                "              ( select sum(case when p.ispcysbl = '1' then 1 else 0 end) as pcysbl_count" +
                "                from patient_info p where   p.area_code like concat(?,'%')) t3";
        return jdbcTemplate.queryForMap(sql, new Object[]{areaCode, areaCode, areaCode});
    }
}
