package com.gsww.qyws.gzbd.entity;

public class Constant {

    /**
     * 分页条数
     */
    public static final int pageSize = 10;

    /**
     *治疗结果
     * 治愈
     */

    public static  final String cure_result_success="治愈";

    /**
     * 返回状态值
     */
    public enum RESULT {
        /**
         * 成功
         */
        CODE_YES("200"),
        /**
         * 失败
         */
        CODE_NO("999"),
        /**
         * 失败msg
         */
        MSG_YES("操作成功"),
        /**
         * 失败msg
         */
        MSG_NO("操作失败");
        private String value;

        RESULT(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
