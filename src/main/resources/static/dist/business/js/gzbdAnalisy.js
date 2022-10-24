$(function () {
    secondPages();
});
function secondPages() {
    //各市州新型冠状病毒肺炎疫情监控情况
    szGzbdAnalisy();
    getTopTenHos();
    getszDatas();
}
function szGzbdAnalisy() {
    dataInterface.requestAjax("../ncovIndex/getDetailByszInfo", commonParams,function (data) {
        $('#szgzjbDiv').empty();
        $('#szgzjbDiv').append('<div class="table-head font-size1">\n' +
            '                                            <span class="col col1">地区</span>\n' +
            '                                            <span class="col col3">确诊</span>\n' +
            '                                            <span class="col col2">疑似</span>\n' +
            '                                            <span class="col col5">治愈</span>\n' +
            '                                            <span class="col col4">死亡</span>\n' +
            '\n' +
            '                                        </div>');
        if (data.code != '999') {
            var html = '<div class="row-wrap font-size2" id="quansheng">\n' +
                '<span style="display: none">'+data.detailcountByszInfo[0].code+'</span>'+
                '                        <span class="col col1">'+data.detailcountByszInfo[0].name+'</span>\n' +
                '                        <span class="col col3 font-fcdb0f">'+data.detailcountByszInfo[0].isqzbl+'</span>\n' +
                '                        <span class="col col2 font-ff8440">'+data.detailcountByszInfo[0].ispcysbl+'</span>\n' +
                '                        <span class="col col5 font-32e9c8">'+data.detailcountByszInfo[0].iscy+'</span>\n' +
                '                        <span class="col col4">'+data.detailcountByszInfo[0].issw+'</span>\n' +

                '                    </div>\n';
            $('#szgzjbDiv').append(html);
            var html1 = '<div class="city-state-data">\n';
            var html3 = '';
            for(var i=1;i<data.detailcountByszInfo.length;i++){
                if(data.detailcountByszInfo[i].name == '临夏回族自治州'){
                    data.detailcountByszInfo[i].name = '临夏州'
                }
                if(data.detailcountByszInfo[i].name == '甘南藏族自治州'){
                    data.detailcountByszInfo[i].name = '甘南州'
                }
                var html2 = '    <div class="area-li">\n' +
                    '                            <div class="first-box">\n' +
                    '<span style="display: none">'+data.detailcountByszInfo[i].code+'</span>'+
                    '                                <span class="col col1"><i class="triangle-icon"></i><span class="area-name">'+data.detailcountByszInfo[i].name+'</span>\n' +
                    '                                </span>\n' +
                    '                                <span class="col col2 yellow-color">'+data.detailcountByszInfo[i].isqzbl+'</span>\n' +
                    '                                <span class="col col2 orange-color">'+data.detailcountByszInfo[i].ispcysbl+'</span>\n' +
                    '                                <span class="col col2 green-color">'+data.detailcountByszInfo[i].iscy+'</span>\n' +
                    '                                <span class="col col2 white-color">'+data.detailcountByszInfo[i].issw+'</span>\n' +
                    '                            </div>\n' +
                    '                        </div>\n';
                html3 = html3+html2;
            }
            var html4 = '                    </div>';
            $('#szgzjbDiv').append(html1+html3+html4);
            $(".city-state-data .first-box .triangle-icon").on('click',function () {
                var $fb = $(this).parent().parent();
                var areaCode = $fb.find('span').eq(0).text();
                var $li = $fb.parent();
                var params1 = {
                    "areaCode" : areaCode
                };
                dataInterface.requestAjax("../ncovIndex/getDetailByszInfo", params1,function (data) {
                    if (data.code != '999') {
                        var htmls1 = '<div class="second-box fn-clear">\n'+
                                     '                                <dl>\n';
                        $('.second-box').html('');
                        var htmls3 = '';
                        for(var i=0;i<data.detailcountByszInfo.length;i++){
                            var htmls2 =
                                '                                    <dt class="sec-dt">\n' +
                                '<span style="display: none">'+data.detailcountByszInfo[i].code+'</span>'+
                                '                                            <span class="col col1"><span>'+data.detailcountByszInfo[i].name+'</span>\n' +
                                '                                            </span>\n' +
                                '                                        <span class="col col2 yellow-color">'+data.detailcountByszInfo[i].isqzbl+'</span>\n' +
                                '                                        <span class="col col2 orange-color">'+data.detailcountByszInfo[i].ispcysbl+'</span>\n' +
                                '                                        <span class="col col2 green-color">'+data.detailcountByszInfo[i].iscy+'</span>\n' +
                                '                                        <span class="col col2 white-color">'+data.detailcountByszInfo[i].issw+'</span>\n' +
                                '                                    </dt>\n';
                             htmls3 = htmls3+htmls2;
                        }
                        var htmls4 = '                                </dl>\n' +
                                    '                            </div>';
                        $li.append(htmls1+htmls3+html4);
                    }

                    $(".city-state-data  .second-box span").on('click',function () {
                        var _indexstate=0;
                        var $index = $(this).index();
                        if($index == '1'){
                            return;
                        }
                        var areaCode = $(this).parent().find('span').eq(0).text();
                        if($index == '2'){
                            _indexstate='2'
                        }
                        if($index == '3'){
                            _indexstate='1';
                        }
                        if($index == '4'){
                            _indexstate='4';
                        }
                        if($index == '5'){
                            _indexstate='3';
                        }
                        showPatientList(areaCode,_indexstate);
                    });
                });
                if (!$li.hasClass('active')) {
                   // $(".city-state-data li.active").removeClass('active');
                    $li.addClass('active');
                }else {
                    $li.removeClass('active');

                }
            });
            $(".city-state-data  .first-box span").on('click',function () {
                var _indexstate=0;
                var $index = $(this).index();
                if($index == '1'){
                    return;
                }
                var areaCode = $(this).parent().find('span').eq(0).text();
                if($index == '2'){
                    _indexstate='2'
                }
                if($index == '3'){
                    _indexstate='1';
                }
                if($index == '4'){
                    _indexstate='4';
                }
                if($index == '5'){
                    _indexstate='3';
                }
                showPatientList(areaCode,_indexstate);
            });
        }
        $('#quansheng span').on('click',function () {
            var _indexstate=0;
            var $index = $(this).index();
            if($index == '1'){
                return;
            }
            var areaCode = $(this).parent().find('span').eq(0).text();
            if($index == '2'){
                _indexstate='2'
            }
            if($index == '3'){
                _indexstate='1';
            }
            if($index == '4'){
                _indexstate='4';
            }
            if($index == '5'){
                _indexstate='3';
            }
            showPatientList(areaCode,_indexstate);
        });
    });
}

//获取定点前十机构数据
function getTopTenHos(){

    dataInterface.requestAjax("../fixedPointHospital/getFixedPointHostop10", commonParams,function (data) {
        var legend_data = new Array ;
        var series_duspect = new Array;
        var series_diagnosee = new Array;
        var series_death = new Array;
        var series_cure = new Array;


        if(data.allList){
            for(var i in data.allList){
                var rowData = data.allList[i];
                legend_data[i]=rowData.org_name;
                series_duspect[i]=rowData.isys;
                series_diagnosee[i]=rowData.isqzbl;
                series_death[i]=rowData.iscy;
                series_cure[i]=rowData.issw;

            }
        }else{
            legend_data=['兰州大学第一人民医院', '兰州大学第二人民医院', '甘肃省中医院', '甘肃省妇幼保健医院', '甘肃省人民医院', '甘肃省疾控中心', '甘肃省第二人民医院', '甘肃省肿瘤医院', '甘肃省中医药大学附属医院', '甘肃省中医药大学附属医院'];
            series_duspect=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_diagnosee=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_death=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_cure=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        }
        var opt_bar = {
            echars_id:'chart_bar2',//容器id
            legend_data:legend_data,//代表的名称(数组)
            series_duspect:series_duspect,   //疑似病例数据
            series_diagnosee:series_diagnosee,   //确诊数据
            series_death:series_death,   //死亡数据
            series_cure:series_cure   //治愈数据
        }
        new CreatPieChart(opt_bar);
    });


}

//获取各市州数据
function getszDatas(){
    $.ajax({
        type: 'POST',
        url: '../columnar/getColumnarList',
        dataType: 'json',
        data : commonParams,
        success:function(getData){
            var legend_data = [] ;
            var series_duspect = [];
            var series_diagnosee = [];
            var series_death = [];
            var series_cure = [];
            if(getData.code="200"){
                if(commonParams['areaCode'] == '86'){
                    for(var i = 0;i<getData.suspectList.length;i++){
                        legend_data.push(getData.suspectList[i].name);
                        series_duspect.push(getData.suspectList[i].ispcysbl);
                        series_diagnosee.push(getData.suspectList[i].isqzbl);
                        series_death.push(getData.suspectList[i].issw);
                        series_cure.push(getData.suspectList[i].iscy);
                    }
                } else if(commonParams['areaCode'] == '62') {
                    for(var i = 0;i<getData.suspectList.length;i++){
                        var szName = [];
                        if(getData.suspectList[i].name.indexOf('市')!=-1){
                            szName = getData.suspectList[i].name.substring(0,getData.suspectList[i].name.length-1)
                        }
                        if(getData.suspectList[i].name == '临夏回族自治州'){
                            szName = '临夏'
                        }
                        if(getData.suspectList[i].name == '甘南藏族自治州'){
                            szName = '甘南'
                        }
                        legend_data.push(szName);
                        series_duspect.push(getData.suspectList[i].ispcysbl);
                        series_diagnosee.push(getData.suspectList[i].isqzbl);
                        series_death.push(getData.suspectList[i].issw);
                        series_cure.push(getData.suspectList[i].iscy);
                    }
                } else {
                    for(var i = 0;i<getData.suspectList.length;i++){
                        legend_data.push(getData.suspectList[i].name);
                        series_duspect.push(getData.suspectList[i].ispcysbl);
                        series_diagnosee.push(getData.suspectList[i].isqzbl);
                        series_death.push(getData.suspectList[i].issw);
                        series_cure.push(getData.suspectList[i].iscy);
                    }
                }
            }else{
                legend_data=['兰州', '嘉峪关', '金昌', '白银', '天水', '张掖', '酒泉', '武威', '定西', '平凉'];
                series_duspect=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                series_diagnosee=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                series_death=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                series_cure=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            }
            var opt_bar = {
                echars_id:'chart_bar1',//容器id
                legend_data:legend_data,//代表的名称(数组)
                series_duspect:series_duspect,   //疑似病例数据
                series_diagnosee:series_diagnosee,   //确诊数据
                series_death:series_death,   //死亡数据
                series_cure:series_cure   //治愈数据
            }
            new CreatColumnarChart(opt_bar);
        }
    });
}