package com.gsww.qyws.gzbd.service.impl;

import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.HosBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HosBasicInfoServiceImpl  implements HosBasicInfoService {

    @Autowired
    private JdbcTemplate yypjJdbcTemplate;
    @Override
    public Map<String, Object> getHoaBasicInfoByAreaCodeAndDate(String areaCode, String bgsj,String state) {
        String sql=" SELECT IFNULL(sum(t.n95kzs),0) n95kzs, IFNULL(sum(t.fhyj),0) fhyj, IFNULL(sum(t.gly),0) gly, IFNULL(sum(t.qmxhxfhq),0) qmxhxfhq, IFNULL(sum(t.fswq),0) fswq, IFNULL(sum(t.sxy),0) sxy, IFNULL(sum(t.jhc),0) jhc, IFNULL(sum(t.fybf),0) fybf,IFNULL(sum(t.xdypsfdq),0) xdypsfdq ,IFNULL(sum(t.ylcws),0) ylcws , IFNULL(sum(t.fyzyc),0) fyzyc, IFNULL(sum(t.fydj),0)  fydj FROM hos_basic_info_daily t where t.area_code like  concat(?,'%')  and t.bgsj like concat(?,'%') and  t.state=?  ";
        return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj,state});
    }

    @Override
    public Map<String, Object> getHoaBasicInfoLgByAreaCodeAndDate(String areaCode, String bgsj,String lastBgsj,String state) {
       String sql=" select sum(drfrmzlgrc) drfrmzlgrc ,sum(ljrc) ljlgrc from  (  SELECT	sum(t.drfrmzlgrc) drfrmzlgrc,	0 AS ljrc FROM	hos_basic_info_daily t WHERE	t.area_code LIKE concat(?,'%')  AND t.bgsj LIKE concat(?,'%') and  t.state=?   UNION ALL	SELECT		0 AS drfrmzlgrc,		sum(t.drfrmzlgrc) AS ljrc	FROM		hos_basic_info_daily t	WHERE		t.area_code LIKE concat(?,'%')  	AND t.bgsj <=?  and  t.state=?  ) m ";
        return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj,state,areaCode ,lastBgsj,state });
    }


    @Override
    public Map<String, Object> getHoaBasicInfoQzblDtByAreaCodeAndDate(String areaCode, String bgsj,String state,String isqzbl) {
        String sql="SELECT count(DISTINCT  CASE WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' THEN t.sfzh  END   ) isqzbl, IFNULL( count( DISTINCT  CASE  WHEN t.isdoctor = '1' THEN  t.sfzh  END ), 0 ) AS qzblwywry, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' and t.iszz = '1' THEN   t.sfzh  END ), 0 ) AS qzbliszz, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' and  t.iswzz = '1' THEN   t.sfzh  END ), 0 ) AS qzbliswzz, IFNULL( count( DISTINCT  CASE  WHEN t.issw = '1' THEN  t.sfzh  END ), 0 ) AS qzblissw, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1'and t.zljg = ? THEN   t.sfzh  END ), 0 ) AS qzbliscy FROM patient_info t WHERE t.xz_xian_bm LIKE concat(?, '%') AND t.tbsj LIKE concat(?, '%') AND t.state =? AND t.isqzbl =? ";
        return yypjJdbcTemplate.queryForMap(sql,new Object[]{Constant.cure_result_success,areaCode , bgsj ,state,isqzbl});
    }

    @Override
    public Map<String, Object> getHoaBasicInfoQzblLjByAreaCodeAndDate(String areaCode, String bgsj,String state,String isqzbl) {
        String sql="SELECT count(DISTINCT CASE WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' THEN t.sfzh  END ) isqzbl, IFNULL( count( DISTINCT  CASE  WHEN t.isdoctor = '1' THEN  t.sfzh  END ), 0 ) AS qzblwywry, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' and t.iszz = '1' THEN   t.sfzh  END ), 0 ) AS qzbliszz, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1' and t.issw != '1' and t.iscy != '1' and t.iswzz = '1' THEN   t.sfzh  END ), 0 ) AS qzbliswzz, IFNULL( count( DISTINCT  CASE  WHEN t.issw = '1' THEN  t.sfzh  END ), 0 ) AS qzblissw, IFNULL( count( DISTINCT  CASE  WHEN t.isqzbl = '1'and  t.zljg = ? THEN   t.sfzh  END ), 0 ) AS qzbliscy FROM patient_info t WHERE t.xz_xian_bm LIKE concat(?, '%')AND t.tbsj <=? AND t.state =? AND t.isqzbl =? ";
        return yypjJdbcTemplate.queryForMap(sql,new Object[]{Constant.cure_result_success,areaCode , bgsj ,state,isqzbl});
    }

    @Override
    public Map<String, Object> getHoaBasicInfoYsblByAreaCodeAndDate(String areaCode, String bgsj, String state, String isdoctor) {
        String  sql=" SELECT  count(DISTINCT t.sfzh) rc FROM patient_info t   WHERE  t.xz_xian_bm LIKE concat(?,'%') AND t.tbsj LIKE concat(?,'%')  AND t.state =?   and t.isqzbl!='1' and t.ispcysbl!='1'  ";
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+="  and t.isdoctor=?";
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state,isdoctor});
        } else{
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state});
        }

    }

    @Override
    public Map<String, Object> getHoaBasicInfoYsblPcByAreaCodeAndDate(String areaCode, String bgsj, String state, String isdoctor) {
        String  sql="SELECT   count(t.sfzh) rc  FROM  patient_info t WHERE t.xz_xian_bm LIKE concat(?,'%')  AND t.tbsj <=? AND t.state =? and t.isqzbl!='1' and t.ispcysbl='1' ";
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+="  and t.isdoctor=?";
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state,isdoctor});
        }else{
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state});
        }

    }

    @Override
    public Map<String, Object> getHoaBasicInfoYsblAllByAreaCodeAndDate(String areaCode, String bgsj, String state, String isdoctor) {
        String  sql="SELECT   count(distinct  t.sfzh) rc  FROM  patient_info t WHERE t.xz_xian_bm LIKE concat(?,'%')  AND t.tbsj <=? AND t.state =? and t.isqzbl!='1' and t.ispcysbl!='1' ";
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+="  and t.isdoctor=?";
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state,isdoctor});
        }else{
            return yypjJdbcTemplate.queryForMap(sql,new Object[]{areaCode , bgsj ,state});
        }

    }




}
