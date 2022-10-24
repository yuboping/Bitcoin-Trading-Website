//各市州新型冠状病毒肺炎疫情监控情况
$(function () {
    firstPages();
    initFullScreen();
    initNiceScolle();
    initSilder();
    // $('#iframe').css('height', scroll_height);
    //页面每隔2分重刷新一次
    setInterval(function () {
        firstPages();
        secondPages();
    }, 60000);
});
function firstPages() {
    getRightIndex();
    getFrlgrc();
    getQrblsjtj();
    getYsbltj();
    getbrokenLineList();
    getditushangfangData();
    $(".tab_item").on("click",function() {
        $(this).addClass("tab_active").siblings().removeClass("tab_active")
        var index = $(this).index();

        if($(this).attr("value")=='ywry'){

            commonParams["isdoctor"]="1";
            getYsbltj();
        }
        if($(this).attr("value")=='hz'){

            commonParams["isdoctor"]="2";
            getYsbltj();
        }

        if($(this).attr("value")=='dt'){
            commonParams["dtlj"]="dt";
            getQrblsjtj();
        }
        if($(this).attr("value")=='lj')
        {
            commonParams["dtlj"]="lj";
            getQrblsjtj();
        }

        $(".cr_content").removeClass("crc_active");
        $(".cr_content").eq(index).addClass("crc_active");

    })
}
//全省应急物资储备情况
function getRightIndex() {
    dataInterface.requestAjax("../rightIndex/getWzcbData", commonParams,function (data) {
        if (data.code != '999') {
          $("#n95kzs").html(data.mapData.n95kzs+'<span>个</span>');
            $("#fhyj").html(data.mapData.fhyj+'<span>个</span>');
            $("#gly").html(data.mapData.gly+'<span>件</span>');
            $("#qmxhxfhq").html(data.mapData.qmxhxfhq+'<span>个</span>');
            $("#fswq").html(data.mapData.fswq+'<span>个</span>');
            $("#sxy").html(data.mapData.sxy+'<span>瓶</span>');
          //  $("#xdypsfdq").html(data.mapData.xdypsfdq+'<span>个</span>');
            $("#jhc").html(data.mapData.jhc+'<span>辆</span>');
            $("#ylcws").html(data.mapData.ylcws+'<span>个</span>');
            $("#fybf").html(data.mapData.fybf+'<span>个</span>');
            $("#fyzyc").html(data.mapData.fyzyc+'<span>个</span>');
            $("#fydj").html(data.mapData.fydj+'<span>件</span>');
        }
    });
}
//发热留观人数情况
function getFrlgrc() {
    dataInterface.requestAjax("../leftIndex/getFrlgrc", commonParams,function (data) {
        if (data.code != '999') {
            $("#ljlgrc").html(data.mapData.ljlgrc+'<span>人</span>');
            $("#drfrmzlgrc").html(data.mapData.drfrmzlgrc+'<span>人</span>');
        }
    });
}
//确诊病例数统计情况
function getQrblsjtj() {
    dataInterface.requestAjax("../leftIndex/getQrblsjtj", commonParams,function (data) {
        if (data.code != '999') {
            $("#isqzbl").html(data.mapData.isqzbl+'<span>人</span>');
            $("#qzblwywry").html(data.mapData.qzblwywry+'<span>人</span>');
            $("#qzbliszz").html(data.mapData.qzbliszz+'<span>人</span>');
            $("#qzbliswzz").html(data.mapData.qzbliswzz+'<span>人</span>');
            $("#qzblissw").html(data.mapData.qzblissw+'<span>人</span>');
            $("#qzbliscy").html(data.mapData.qzbliscy+'<span>人</span>');
        }
    });
}
//疑似病例数统计情况
function getYsbltj() {
    dataInterface.requestAjax("../leftIndex/getYsbltj", commonParams,function (data) {
        if (data.code != '999') {
            $("#ljysData").html(data.ljysData.rc+'<span>人</span>');
            $("#zrysData").html(data.zrysData.rc+'<span>人</span>');
            $("#ljysData").html(data.ljysData.rc+'<span>人</span>');

        }
    });
}
//全省新型冠状病毒肺炎疫情监控趋势分析
function getbrokenLineList(){
    dataInterface.requestAjax("../brokenLine/getbrokenLineList", commonParams,function (getData) {
        var legend_data = new Array ;
        var series_duspect = new Array;
        var series_diagnosee = new Array;
        var series_death = new Array;
        var series_cure = new Array;
        if(getData.code=="200"){
            if(getData.resultMap.length<=0){
                var date=new Date();
                day1.setDate(day1.getDate());
                var day2 = new Date();
                day2.setDate(day2.getDate()-1);
                var day3 = new Date();
                day3.setDate(day3.getDate()-2);
                var day4 = new Date();
                day4.setDate(day4.getDate()-3);
                var day5 = new Date();
                day5.setDate(day5.getDate()-4);
                var day6 = new Date();
                day6.setDate(day6.getDate()-5);
                legend_data=[DateUtil.format(day6,"MM-dd"), DateUtil.format(day5,"MM-dd"), DateUtil.format(day4,"MM-dd"),DateUtil.format(day3,"MM-dd"), DateUtil.format(day2,"MM-dd"), DateUtil.format(day1,"MM-dd")];
                series_duspect=[0, 0, 0, 0, 0, 0];
                series_diagnosee=[0, 0, 0, 0, 0, 0];
                series_death=[0, 0, 0, 0, 0, 0];
                series_cure=[0, 0, 0, 0, 0, 0];

            }else {
                for (var i = 0; i < getData.resultMap.length; i++) {
                    legend_data.push(getData.resultMap[i].timedate);
                    series_duspect.push(getData.resultMap[i].isys);
                    series_diagnosee.push(getData.resultMap[i].isqzbl);
                    series_death.push(getData.resultMap[i].issw);
                    series_cure.push(getData.resultMap[i].iscy);

                }
            }
        }else{
            legend_data=['1月17', '1月18', '1月19', '1月20', '1月21', '1月22', '1月23', '1月24', '1月25', '1月26'];
            series_duspect=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_diagnosee=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_death=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
            series_cure=[0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        }
        var opt_bar = {
            echars_id:'chart_line',//容器id
            legend_data:legend_data,//代表的名称(数组)
            series_duspect:series_duspect,   //疑似病例数据
            series_diagnosee:series_diagnosee,   //确诊数据
            series_death:series_death,   //死亡数据
            series_cure:series_cure   //治愈数据
        }
        new CreatBrokenLineChart(opt_bar);
    });
}

//获取地图上方数据
function getditushangfangData() {
    dataInterface.requestAjax("../ncovIndex/getMapCountInfo", commonParams,function (getData) {
        if(getData.code !="999"){
            $('#drfrmzjzrc_count').html(getData.mapCountInfo.drfrmzjzrc_count);
            $('#drfrmzlgrc_count').html(getData.mapCountInfo.drfrmzlgrc_count);
            $('#ysbl_count').html(getData.mapCountInfo.ysbl_count);
            $('#qzbl_count').html(getData.mapCountInfo.qzbl_count);
            $('#cy_count').html(getData.mapCountInfo.cy_count);
            $('#sw_count').html(getData.mapCountInfo.sw_count);

            $('#drfrmzjzrc_count').countUp();
            $('#drfrmzlgrc_count').countUp();
            $('#ysbl_count').countUp();
            $('#qzbl_count').countUp();
            $('#cy_count').countUp();
            $('#sw_count').countUp();
        }
    });
}

function initNiceScolle() {
    $('body').niceScroll({
        touchbehavior: false,
        cursorcolor: "#189bbb",
        cursoropacitymax: 1,
        cursorwidth: 4,
        cursorborder: "none",
        cursorborderradius: "4px",
        autohidemode: false
    });
}

function initFullScreen() {
    $("#icon_wholeScreenspan").click(function () {
        if ($(this).hasClass('active')) {
            toggleFullScreen(document.body);
            $(this).removeClass("active");
            $(this).find('span').text("全屏显示")
        } else {
            toggleFullScreen(document.body);
            $(this).addClass("active");
            $(this).find('span').text("退出全屏");
        }

    });
}

// 全屏切换
function toggleFullScreen(elem) {
    // ## The below if statement seems to work better ## if ((document.fullScreenElement && document.fullScreenElement !== null) || (document.msfullscreenElement && document.msfullscreenElement !== null) || (!document.mozFullScreen && !document.webkitIsFullScreen)) {
    if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
        if (elem.requestFullScreen) {
            elem.requestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
            // elem.webkitRequestFullScreen();
        } else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
    } else {
        if (document.cancelFullScreen) {
            document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
}

function initSilder() {
    $(".content").slide({
        mainCell: ".bd ul",
        effect: "top",
        autoPlay: true,
        trigger: "click",
        pnLoop: true,
        interTime: 15000,
        mouseOverStop: false
    });
}