package com.gsww.qyws.gzbd.service.impl;

import com.gsww.qyws.gzbd.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by weix on 2019/4/28.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JdbcTemplate yypjJdbcTemplate;
    @Override
    public String getLoginUserByAccountAndPsw(String account, String passwrd) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from his_fpjc_xqyyzh t where t.account=? and t.password = ? ");

        List<Map<String, Object>> list =  yypjJdbcTemplate.queryForList(sql.toString(),new Object[]{account,passwrd});
        if(CollectionUtils.isEmpty(list)){
            return "0";
        }else {
            return "1";
        }
    }

}
