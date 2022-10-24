/** 时间工具类 */
var DateUtil = function() {
    return {
        /**
         * 格式化日期格式
         * @param date Date对象
         * @param format 时间格式
         * @returns 指定格式的时间字符串
         */
        format : function (date, format) {
            if (!date) return;
            date = DateUtil.getDateObj(date);
            if (date == null) return;
            if (!date instanceof Date) return;
            var paddNum = function(num){
                num += "";
                return num.replace(/^(\d)$/,"0$1");
            };
            //指定格式字符
            var cfg = {
                yyyy : date.getFullYear() //年 : 4位
                ,yy : date.getFullYear().toString().substring(2)//年 : 2位
                ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
                ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
                ,d  : date.getDate()   //日 : 如果1位的时候不补0
                ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
                ,hh : date.getHours()  //时
                ,mm : date.getMinutes() //分
                ,ss : date.getSeconds() //秒
            };
            format || (format = "yyyy-MM-dd hh:mm:ss");
            return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
        },
        /**
         * 比较时间大小 要包括月日以上  只包括时分请使用 Util.DateUtil.compareTime
         */
        compareDate : function(beginDate, endDate){
            var dateA = DateUtil.getDateObj(beginDate);
            var dateB = DateUtil.getDateObj(endDate);
            if(dateA != null && dateB!= null && dateA <= dateB){
                return true;
            }
            return false;
        },

        /**
         * 获取时间对象
         */
        getDateObj : function(date){
            switch(typeof date) {
                case 'string':
                    if (date.length >= 20) {
                        date = date.substring(0, 19);
                    }
                    date = new Date(date.replace(/-/ig, "/"));
                    break;
                case 'number':
                    date = new Date(date);
                    break;
            }
            return date instanceof Date && date != 'Invalid Date'? date : null;
        },
        /**
         * 限定时间
         * sDate 源时间
         * changeType 改变方式 1 为推后 -1 为提前
         * changeValue 改变值 数字
         */
        getValueDate : function(sDate,changeType,changeValue){
            if(isNaN(changeType)) return;
            if(isNaN(changeValue)) return;
            var sDate = DateUtil.getDateObj(sDate);
            if (sDate == null) return;
            var dateTemp = sDate.valueOf();
            var trueChangeValue = changeValue * 24 * 60 * 60 * 1000;
            return new Date(dateTemp + Number(changeType) * trueChangeValue);
        },

        dateToJson: function (sDate) {
            var date = null;
            if (sDate.length > 0 && sDate != "请选择") {
                date = DateUtil.getDateObj(sDate);
            } else {
                date = new Date();
            }
            var month = date.getMonth() + 1;
            var j_date = '{ "year": "' + date.getFullYear() + '", "month": "' + month + '", "day": "' + date.getDate() + '", "hour": "' + date.getHours() + '", "min": "' + date.getMinutes() + '" }';
            return j_date;
        },

        /** 获取月初日期 */
        getMonthStartEndDay : function(sDateStr, format){
            var sDate = DateUtil.getDateObj(sDateStr);
            if (sDate == null) return;

            var year = sDate.getFullYear();
            var month = sDate.getMonth();
            var startDay = new Date(year,month,1);
            return DateUtil.format(startDay, format);
        }
    }
}();