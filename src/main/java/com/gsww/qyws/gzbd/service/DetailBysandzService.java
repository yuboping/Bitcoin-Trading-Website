package com.gsww.qyws.gzbd.service;

import java.util.List;
import java.util.Map;

public interface DetailBysandzService {
        /**
         * 各市县疫情统计
         */
        public List<Map<String,Object>> getTotalBysz(Map params);

}
