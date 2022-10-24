package com.gsww.qyws.gzbd.service.impl;


import com.gsww.qyws.gzbd.entity.Constant;
import com.gsww.qyws.gzbd.service.BrokenLineDataService;
import com.gsww.qyws.gzbd.service.PatientInfoService;
import com.gsww.qyws.gzbd.utils.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    @Autowired
    private JdbcTemplate yypjJdbcTemplate;

    @Override
    public List<Map<String, Object>> querySuspectPatient(String areaCode) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor  ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " +
                "  and a.isqzbl !='1' and a.ispcysbl !='1'  and a.xz_xian_bm like ? order by a.qzsj  ,a.create_time desc" ;

        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryConfirmPatient(String areaCode,String isDtLj,String isdoctor) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor  ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " +
                "  and a.isqzbl = '1'  and a.issw != '1' and a.iscy != '1'  ";
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+=" and a.isdoctor='1'  ";
        }
        if(isDtLj!=null && isDtLj.equals("dt")){
            sql+=" and  a.tbsj  like '"+TimeHelper.getHours8() +"%'  ";

        }
        sql+= " and a.xz_xian_bm like ? order by qzsj  ,a.create_time desc ";

        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryCurePatient(String areaCode) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor  ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " +
                "  and a.zljg = ? " +
                " and a.xz_xian_bm like ? order by a.qzsj  ,a.create_time desc ";
        return yypjJdbcTemplate.queryForList(sql, new Object[]{Constant.cure_result_success,"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryDeathPatient(String areaCode) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor  ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " +
                "  and a.issw = '1' and a.isqzbl = '1'  " +
                " and a.xz_xian_bm like ? order by a.qzsj  ,a.create_time desc ";
        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryConfirmZzAndWzzPatient(String areaCode, String isDtLj,String  zzState,String isdoctor) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " +
                "  and a.isqzbl = '1'  and a.issw != '1' and a.iscy != '1'  ";
        /**
         * 当天还是累计
         */
        if(isDtLj!=null && isDtLj.equals("dt")){
            sql+=" and  a.tbsj  like '"+TimeHelper.getHours8() +"%'  ";

        }
        /**
         * 是否重症
         */
        if(zzState!=null && zzState.equals("zz")){
            sql+="   and iszz='1'  ";
        }
        /**
         * 是否危重症
         */
        if(zzState!=null && zzState.equals("wzz")){
            sql+="   and iswzz='1'  ";
        }

        /**
         * 此处有歧义，确认的中是排除死亡的，做条件置换
         * 是否死亡
         */
        if(zzState!=null && zzState.equals("sw")){
            sql=sql.replace("and a.issw != '1'"," and issw='1' ");

        }
        /**
         * 是否治愈
         */

        if(zzState!=null && zzState.equals("zy")){
            sql+=" and zljg='"+Constant.cure_result_success+"'";
        }
        /**
         * s是否医务人员
         */
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+=" and a.isdoctor='1'  ";
        }
        sql+= " and a.xz_xian_bm like ? order by a.qzsj,a.create_time desc";
        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});

    }

    @Override
    public List<Map<String, Object>> querySuspectOtherPatient(String areaCode, String isDtLj, String  isdoctor,String isJc) {
        String sql = "select distinct a.`name`,b.JGID, b.jgmc,CONCAT(a.xz_sheng,a.xz_shi,xz_xian) as jg,a.sfzh as sfzhs , if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,a.qzsj,if(a.iscy = '1','是','否') as iscy,if(a.issw = '1','是','否') as issw, if(a.isdoctor = '1','是','否') as isdoctor ,a.create_time " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID " ;

        /**
         * 当天还是累计
         */
        if(isDtLj!=null && isDtLj.equals("dt")){
            sql+=" and  a.tbsj  like '"+TimeHelper.getHours8() +"%'  ";

        }
        if(isdoctor!=null && isdoctor.equals("1")){
            sql+=" and a.isdoctor='1'  ";
        }
        /**
         * 解除疑似病历
         */
        if(isJc!=null && isJc.equals("jc")){
            sql+=" and a.ispcysbl ='1'  ";
        }else{
            sql+=" and a.ispcysbl !='1'  ";
        }
        sql+="  and a.isqzbl !='1'  and a.xz_xian_bm like ? order by a.qzsj,a.create_time desc " ;

        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryPatientInfoNew(String areaCode, String isqzbl, String isDtLj, String isDoctor, String zzState, String isJc) {
        String sql = "select distinct a.`name`, b.JGID, b.jgmc,CONCAT(a.jg_sheng,a.jg_shi) as jg,a.sfzh as sfzhs ,if(a.sex = '1','男','女') as sex,a.age,concat(left(a.sfzh,6),'******',right(a.sfzh,6)) as sfzh,if(a.isqzbl = '1','是','否') as isqzbl ,if(a.isdoctor = '1','是','否') as isdoctor " +
                "  from patient_info a,his_fpjc_xqyyzh b " +
                "where a.jgid = b.JGID ";
        //1.当天-累计
        if(isDtLj!=null && isDtLj.equals("dt")){
            sql+=" and  a.tbsj  like '"+TimeHelper.getHours8() +"%'  ";
        }
        //2.医务人员
        if(isDoctor!=null && isDoctor.equals("1")){
            sql+=" and a.isdoctor='1'  ";
        }
        /**
         * 是否重症
         */
        if(zzState!=null && zzState.equals("zz")){
            sql+="   and iszz='1'  ";
        }
        /**
         * 是否危重症
         */
        if(zzState!=null && zzState.equals("wzz")){
            sql+="   and iswzz='1'  ";
        }
        /**
         * 是否死亡
         */
        if(zzState!=null && zzState.equals("sw")){
            sql+="   and issw='1'  ";
        }
        //已确认的病历
        if(isqzbl!=null && isqzbl.equals("1")){

            /**
             * 是否治愈
             */
            if(zzState!=null && zzState.equals("zy")){
                sql+=" and  a.isqzbl='1' and a.issw != '1' and a.iscy != '1'  and zljg='"+Constant.cure_result_success+"'";
            }
            /**
             * 解除疑似病历
             */
            if(isJc!=null && isJc.equals("jc")){
                sql+=" and  a.isqzbl='1' and a.ispcysbl ='1'  ";
            };
        }else{
            sql+="and a.isqzbl!='1' and a.ispcysbl !='1'  ";
        }
        sql+= "order by a.qzsj,a.create_time desc";
        return yypjJdbcTemplate.queryForList(sql, new Object[]{"%"+areaCode+"%"});
    }

    @Override
    public List<Map<String, Object>> queryPatientInfoTw(String orgCode, String sfzh) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select LEFT (t.hlsj, 10) hlsj,sum(t.tw) tw,sum(t.hxcs) hxcs,sum(t.xl) xl from nurse_info t where t.patient_id in \n" +
                "(select id from patient_info p where p.sfzh = ? and p.org_code = ?) and t.hlsj is not null group by LEFT (t.hlsj, 10)) t order by t.hlsj limit 5");
        return yypjJdbcTemplate.queryForList(sql.toString(), new Object[]{sfzh,orgCode});
    }

    @Override
    public List<Map<String, Object>> queryPatientInfoXy(String orgCode, String sfzh) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select LEFT (t.hlsj, 10) hlsj,sum(t.xy_szy) xy_szy,sum(t.xy_ssy) xy_ssy from nurse_info t where t.patient_id in \n" +
                "(select id from patient_info p where p.sfzh = ? and p.org_code = ?) and t.hlsj is not null group by LEFT (t.hlsj, 10)) t order by t.hlsj limit 5 ");
        return yypjJdbcTemplate.queryForList(sql.toString(), new Object[]{sfzh,orgCode});
    }

    @Override
    public List<Map<String, Object>> queryPatientInfoXq(String orgCode, String sfzh) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select t.hlsj,t.tw,t.hxcs,t.xy_szy,t.xy_ssy,t.xl,case when t.iszz='0' then '否' else '是' end as iszz,case when t.iswzz='0' then '否' else '是' end as iswzz,case when t.ispcysbl='0' then '否' else '是' end as ispcysbl,t.zljg from nurse_info t where t.patient_id in \n" +
                "(select id from patient_info p where p.sfzh = ? and p.org_code = ?) and t.hlsj is not null order by t.hlsj desc ");
        return yypjJdbcTemplate.queryForList(sql.toString(), new Object[]{sfzh,orgCode});
    }


    @Override
    public Map<String, Object> getPatientInfoList(Map params) throws Exception {
        String orgCode =params.get("orgCode").toString();
        String sfzh=    params.get("sfzh").toString();
        String sql = "SELECT name,(case  when sex='0' then '未知的性别' when sex='1' then '男性' when sex='2' then '女性'  when sex='5' then '女性改（变）为男性'  when sex='6' then '男性改（变）为女性'  when sex='9' then '未说明的性别' END ) as sex,\n" +
                "                age,sfzh,hzlxdh,(case when rqfl='1' then '工人' when rqfl='2' then '商业服务' when rqfl='3' then '家务及待业' when rqfl='4' then '离退人员' when rqfl='5' then '散居儿童' when rqfl='6' then '其他' ELSE '其他' END ) as rqfl,(case when isdoctor='0' then '否' when isdoctor='1' then '是' END ) as isdoctor,\n" +
                "                workunit,(case when iszwh='0' then '否' when iszwh='1' then '是' END ) as iszwh,(case when isqtsf='0' then '否' when isqtsf='1' then '是' END ) as isqtsf,(case when sfmc<>'' then sfmc else '--' end ) as sfmc,concat_ws('',t.jg_sheng,t.jg_shi,t.jg_xian) AS jiguan,concat_ws('',xz_sheng,xz_shi,xz_xian,xz_xiang) AS xzz,\n" +
                "                 (case when sfszyy='0' then '否' when sfszyy='1' then '是' END ) as sfszyy,(case when szyymc<>'' then szyymc else '--' end ) as szyymc,fbrq,zdsj,qryssj,bah,zs,(case when isjcjb='0' then '否' when isjcjb='1' then '是' END ) as isjcjb,(case when jcjbsm<>'' then jcjbsm else '--' end ) as jcjbsm,(case when ishsjc='0' then '否' when ishsjc='1' then '是' END ) as ishsjc,\n" +
                "                 (case when jcjb<>'' then jcjb else '--' end ) as  jcjb,(case when hsjcjg='0' then '阴性' when hsjcjg='1' then '阳性'  ELSE '--' END) as hsjcjg,(case when isjycx='0' then '否' when isjycx='1' then '是' END ) as  isjycx,(case when isncov='0' then '否' when isncov='1' then '是' ELSE '--' END ) as  isncov,(case when isqzbl='0' then '否' when isqzbl='1' then '是' END ) as  isqzbl,\n" +
                "                (case when qzsj<>'' then qzsj else '--' end ) as  qzsj,(case when iszz='0' then '否' when iszz='1' then '是' END ) as iszz,(case when iswzz='0' then '否' when iswzz='1' then '是' END ) as iswzz,\n" +
                "                (case when ispcysbl='0' then '否' when ispcysbl='1' then '是' END )  as ispcysbl,(case when iscy='0' then '否' when iscy='1' then '是' END )  as iscy,(case when cysj<>'' then cysj else '--' end ) as  cysj,(case when issw='0' then '否' when issw='1' then '是' END )  as issw,(case when swsj<>'' then swsj else '--' end ) as swsj,(case when iskzswbytl='0' then '否' when iskzswbytl='1' then '是' else '--' END )  as iskzswbytl,(case when issyxxgzbdgrdfy='0' then '否' when issyxxgzbdgrdfy='1' then '是' else '--' END )  as issyxxgzbdgrdfy FROM patient_info t  \n" +
                "where org_code = ? AND sfzh = ? LIMIT 1";
        return yypjJdbcTemplate.queryForMap(sql,new Object[]{orgCode,sfzh});
    }
}
