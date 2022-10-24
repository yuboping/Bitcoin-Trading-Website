$(function () {
    pages();
});
function pages() {
    var pareantParams = {
        'sfzh' : $('#sfzhs').val(),
        'orgCode' : $('#orgCode').val()
    }
    patient_info_list(pareantParams);
    //护理记录体温
    hljlTw(pareantParams);
    //护理记录血压
    hljlXy(pareantParams);
    //护理记录详情
    hljlList(pareantParams);
}
function patient_info_list(pareantParams) {

    dataInterface.requestAjax("../patientInfo/patientInfoList", pareantParams,function (data) {
        if (data.code != '999') {
            $("#xm").html(data.patientInfoList.name);
            $("#sfzh").html(data.patientInfoList.sfzh);
            $("#xb").html(data.patientInfoList.sex);
            $("#age").html(data.patientInfoList.age);
            $("#lxdh").html(data.patientInfoList.hzlxdh);
            $("#rqfl").html(data.patientInfoList.rqfl);
            $("#sfywry").html(data.patientInfoList.isdoctor);
            $("#gzdw").html(data.patientInfoList.workunit);
            $("#lzsfzwh").html(data.patientInfoList.iszwh);
            $("#lzsfzqts").html(data.patientInfoList.isqtsf);
            $("#ddqtsmc").html(data.patientInfoList.sfmc);
            $("#jg").html(data.patientInfoList.jiguan);
            $("#xzz").html(data.patientInfoList.xzz);
            $("#sfszyy").html(data.patientInfoList.sfszyy);
            $("#szyymc").html(data.patientInfoList.szyymc);
            $("#fbrq").html(data.patientInfoList.fbrq);
            $("#zdsj").html(data.patientInfoList.zdsj);
            $("#qryssj").html(data.patientInfoList.qryssj);
            $("#bah").html(data.patientInfoList.bah);
            $("#zs").html(data.patientInfoList.zs);
            $("#sfyjcjb").html(data.patientInfoList.isjcjb);
            $("#jcjbqk").html(data.patientInfoList.jcjbsm);
            $("#sfhsjc").html(data.patientInfoList.ishsjc);
            $("#jcjb").html(data.patientInfoList.jcjb);
            $("#hsjcjg").html(data.patientInfoList.hsjcjg);
            $("#sfjycx").html(data.patientInfoList.isjycx);
            $("#sfjc2019").html(data.patientInfoList.isncov);
            $("#sfqzbl").html(data.patientInfoList.isqzbl);
            $("#qzsj").html(data.patientInfoList.qzsj);
            $("#sfzz").html(data.patientInfoList.iszz);
            $("#sfwzz").html(data.patientInfoList.iswzz);
            $("#sfpcysbl").html(data.patientInfoList.ispcysbl);
            $("#sfcy").html(data.patientInfoList.iscy);
            $("#cysj").html(data.patientInfoList.cysj);
            $("#sfsw").html(data.patientInfoList.issw);
            $("#swsj").html(data.patientInfoList.swsj);
            $("#sfkzswbytl").html(data.patientInfoList.iskzswbytl);
            $("#sfsyxxgzbdgr").html(data.patientInfoList.issyxxgzbdgrdfy);

        }
    });

}
function hljlTw(pareantParams) {
    dataInterface.requestAjax("../patientInfo/queryPatientInfoTw", pareantParams,function (data) {
        debugger
        if(data.code != '999'){
            optionLine1.xAxis.data = [];
            optionLine1.series[0].data = [];
            optionLine1.series[1].data = [];
            optionLine1.series[2].data = [];
            for(var i = 0;i<data.queryPatientInfoTw.length;i++){
                optionLine1.xAxis.data.push(data.queryPatientInfoTw[i].hlsj);
                optionLine1.series[0].data.push(data.queryPatientInfoTw[i].tw);
                optionLine1.series[1].data.push(data.queryPatientInfoTw[i].hxcs);
                optionLine1.series[2].data.push(data.queryPatientInfoTw[i].xl);
            }
            // 第二屏图表
            var myChart2_1 = echarts.init(document.getElementById("screen_line1"));
            myChart2_1.setOption(optionLine1);
        }
    });
}
function hljlXy(pareantParams) {
    dataInterface.requestAjax("../patientInfo/queryPatientInfoXy", pareantParams,function (data) {
        if(data.code != '999'){
            // 第二屏图表
            optionLine2.xAxis.data = [];
            optionLine2.series[0].data = [];
            optionLine2.series[1].data = [];
            for (var i =0;i<data.queryPatientInfoXy.length;i++){
                optionLine2.xAxis.data.push(data.queryPatientInfoXy[i].hlsj);
                optionLine2.series[0].data.push(data.queryPatientInfoXy[i].xy_szy);
                optionLine2.series[1].data.push(data.queryPatientInfoXy[i].xy_ssy);
            }
            var myChart2_2 = echarts.init(document.getElementById("screen_line2"));
            myChart2_2.setOption(optionLine2);
        }
    });
}
function hljlList(pareantParams) {
    dataInterface.requestAjax("../patientInfo/queryPatientInfoXq", pareantParams,function (data) {
        if(data.code != '999'){
            for(var i=0;i<data.queryPatientInfoXq.length;i++){
                var html = '<tr>\n' +
                    '                            <td>'+(i+1)+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].hlsj+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].tw+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].hxcs+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].xy_szy+'/'+data.queryPatientInfoXq[i].xy_ssy+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].xl+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].iszz+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].iswzz+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].ispcysbl+'</td>\n' +
                    '                            <td>'+data.queryPatientInfoXq[i].zljg+'</td>\n' +
                    '                        </tr>';
                $('#huliTable').append(html);
            }
        }
    });
}