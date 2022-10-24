package com.gsww.qyws.gzbd.service;

import com.gsww.qyws.gzbd.entity.HisFpjcXqyyzh;

/**
 * Created by weix on 2019/4/28.
 */
public interface LoginService {
    String getLoginUserByAccountAndPsw(String account, String passwrd) throws Exception;
    
}
