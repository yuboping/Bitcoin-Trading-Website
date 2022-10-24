/** 获取公共参数 */
var commonParams = {
    "areaCode" : "",
    "bgsj" : "",
    "hostState" : "2",
    "patientState" : "1",
    "lastBgsj" : "",
    "dtlj" : 'dt',
    "isqzbl" : '1',
    "isdoctor" : '2',
    "endTime" : "",
    "indexTime" : ""
};
var date=new Date();
function dateFormat(date) {     //时间格式
    var y=date.getFullYear(),
        month=date.getMonth() + 1,
        m=month<10 ? "0"+month : month,
        d=(date.getDate()<10) ? "0"+date.getDate() : date.getDate();
    var sendDate=y + "-" +m + "-" +d ;
    return sendDate;
}
function dateFormatAll(date) {     //时间格式
    var y=date.getFullYear(),
        month=date.getMonth() + 1,
        m=month<10 ? "0"+month : month,
        d=(date.getDate()<10) ? "0"+date.getDate() : date.getDate(),
      h=(date.getHours()<10) ? "0"+date.getHours() : date.getHours(),
        mi=(date.getMinutes()<10) ? "0"+date.getMinutes() : date.getMinutes(),
        s=(date.getSeconds()<10) ? "0"+date.getSeconds() : date.getSeconds();
    var allDate=y + "-" +m + "-" +d + " " +h + ":" +mi + ":" +s;
    return allDate;
}

var day1 = new Date();
day1.setDate(day1.getDate());
var day2 = new Date();
day2.setDate(day2.getDate()-12);
//commonParams["areaCode"] = $('#xzqhCode').val();
commonParams["areaCode"] = "62";
commonParams["bgsj"] = date.getHours()>=20?dateFormat(date):dateFormat(new Date(date.getTime() - 86400000));
commonParams["state"] = "1";
commonParams["lastBgsj"] = date.getHours()>=20?dateFormatAll(date):dateFormat(new Date(date.getTime() - 86400000))+' 23:59:59';
commonParams["endTime"] = DateUtil.format(day1,"yyyy-MM-dd");
commonParams["indexTime"] ='2020-01-22';