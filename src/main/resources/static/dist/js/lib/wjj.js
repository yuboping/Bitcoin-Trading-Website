/*
// $(".tab_item").mouseover(function() {
//     $(this).addClass("tab_active").siblings().removeClass("tab_active")
//     var index = $(this).index()
//     $(".cr_content").removeClass("crc_active")
//     $(".cr_content").eq(index).addClass("crc_active")
// })



//  甘肃地图
/!*$(function() {
    MapBusiness();
})*!/

function MapBusiness(importData1) {
    var mapchart = echarts.init(document.getElementById('main_gansu'));
    var city = {
        '兰州': 'lanzhou',
        '嘉峪关': 'jiayuguan',
        '金昌': 'jinchang',
        '白银': 'baiyin',
        '天水': 'tianshui',
        '武威': 'wuwei',
        '张掖': 'zhangye',
        '平凉': 'pingliang',
        '酒泉': 'jiuquan',
        '庆阳': 'qingyang',
        '定西': 'dingxi',
        '甘南': 'gannan',
        '临夏': 'linxia',
        '陇南': 'longnan'
    }
    var geoCoordMap = {
        '兰州': [103.823557, 36.058039],
        '嘉峪关': [98.277304, 39.786529],
        '金昌': [102.187888, 38.514238],
        '白银': [111.173606, 37.64568],
        '天水': [105.724998, 34.578529],
        '武威': [102.634697, 37.929996],
        '张掖': [100.455472, 38.932897],
        '平凉': [106.684691, 35.54279],
        '酒泉': [96, 40.744023],
        '庆阳': [107.638372, 35.734218],
        '定西': [104.626294, 35.579578],
        '甘南': [102.911008, 34.986354],
        '临夏': [103.212006, 35.599446],
        '陇南': [104.929379, 33.388598]
    }
    //以下为本地实验数据 value反应的是地区颜色  info里反应可疑和已确认感染人数量
    /!*importData = [{
        name: '兰州',
        code:6201,
        suspected: 1,
        confirmed: 2,
    },
        {
            name: '嘉峪关',
            code:6202,
            suspected: 1,
            confirmed: 0,
        },
        {
            name: '金昌',
            code:6203,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '白银',
            code:6204,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '天水',
            code:6205,
            suspected: 0,
            confirmed: 0,
        },
        {
            name: '武威',
            code:6206,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '张掖',
            code:6207,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '平凉',
            code:6208,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '酒泉',
            code:6209,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '庆阳',
            code:6210,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '定西',
            code:6211,
            suspected: 3,
            confirmed: 6,
        },
        {
            name: '甘南',
            code:6230,
            suspected: 1,
            confirmed: 0,
        },
        {
            name: '临夏',
            code:6229,
            suspected: 1,
            confirmed: 2,
        },
        {
            name: '陇南',
            code:6212,
            suspected: 1,
            confirmed: 2,
        },
    ]*!/
    //所有的ajax需要处理跨域问题
    $.getJSON("../dist/js/lib/echarts/gansu.json", function(data) {
        console.log("加载全省");
        //注册地图
        echarts.registerMap("甘肃", data);
        // 绘制地图
        renderMap("甘肃", importData1);
    });
    var option = {
        tooltip: {
            trigger: "item",
            borderColor: '#028ec9',
            borderWidth: 3,
            padding: [15, 18],
            formatter: function(d) {
                var name = '<span style="color:#0bb5ff;font-size:24px;">' + d.name + '</span>'
                var html = ''
                var suspected = (d.data && d.data.suspected) ? d.data.suspected : 0
                var confirmed = (d.data && d.data.confirmed) ? d.data.confirmed : 0
                html += name +
                    "</br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;margin-top:11px;'>疑似病例数：" +
                    suspected +
                    "</span></br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;'>确诊病例数：" +
                    confirmed +
                    "</span></br>"
                return html
            },
            textStyle: {
                lineHeight: 40
            }
        },
        visualMap: {
            show: true,
            type: 'piecewise',
            splitNumber: 5,
            inverse: true,
            itemSymbol: "rect",
            outOfRange: {
                color: '#00dbf2',
            },
            textStyle: {
                color: '#fff'
            },
            pieces: [{
                label: '疑似感染',
                min: 0.1,
                max: 0.9,
                color: '#f8db25',
            },
                {
                    label: '1-9人',
                    min: 1,
                    max: 9,
                    color: '#fe8439',
                },
                {
                    label: '10-99人',
                    min: 10,
                    max: 99,
                    color: '#fa5e2d'
                },
                {
                    label: '100-499人',
                    min: 100,
                    max: 499,
                    color: '#fd4f51'
                },
                {
                    label: '500人以上',
                    min: 500,
                    color: '#be0000'
                }
            ]
        }
    };

    function renderMap(map, data) {
        debugger
        //数组，name为地区名称，value为值
        for (var i = 0, _len = data.length; i < _len; i++) {
            var _item = data[i];
            if (_item.confirmed >= 1) {
                _item.value = _item.confirmed;
            } else if (_item.suspected >= 1) {
                _item.value = 0.5;
            } else {
                _item.value = 0;
            }
        }
        option.series = [{
            z: 1,
            name: map,
            type: "map",
            map: map,
            right: "2%",
            top: 10,
            height: "98%",
            width: "98%",
            zoom: 1,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color: "#fff",
                        fontSize: 12
                    }
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        color: "#fff",
                        fontSize: 16
                    }
                }
            },
            itemStyle: {
                normal: {
                    borderColor: "#fff",
                    borderWidth: 1,
                },

                emphasis: {
                    show: false
                }
            },
            //roam: true,
            data: data
        },
            {
                type: 'effectScatter',
                coordinateSystem: 'geo',
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ddb926',
                        position: 'right',
                        show: true
                    }
                }
            }
        ];
        //渲染地图
        debugger
        mapchart.setOption(option);
    }
    var cityArea = ''
    mapchart.on('click', function(params) {
        $(".areaName-back").show();
        $(".areaName").html(params.name);
        commonParams["areaCode"] = params.data.code;
        firstPages();
        //secondPages();
        if (params.name in city) {
            if (city[params.name] === 'lanzhou') {
                cityArea = "../dist/js/lib/echarts/620100.json";
            }
            if (city[params.name] === 'longnan') {
                cityArea = "../dist/js/lib/echarts/621200.json";
            }
            if (city[params.name] === 'dingxi') {
                cityArea = "../dist/js/lib/echarts/621100.json";
            }
            if (city[params.name] === 'gannan') {
                cityArea = "../dist/js/lib/echarts/623000.json";
            }
            if (city[params.name] === 'tianshui') {
                cityArea = "../dist/js/lib/echarts/620500.json";
            }
            if (city[params.name] === 'jiayuguan') {
                cityArea = "../dist/js/lib/echarts/620200.json";
            }

            if (city[params.name] === 'jinchang') {
                cityArea = "../dist/js/lib/echarts/620300.json";
            }
            if (city[params.name] === 'baiyin') {
                cityArea = "../dist/js/lib/echarts/620400.json";
            }
            if (city[params.name] === 'wuwei') {
                cityArea = "../dist/js/lib/echarts/620600.json";
            }
            if (city[params.name] === 'zhangye') {
                cityArea = "../dist/js/lib/echarts/620700.json";
            }
            if (city[params.name] === 'pingliang') {
                cityArea = "../dist/js/lib/echarts/620800.json";
            }
            if (city[params.name] === 'jiuquan') {
                cityArea = "../dist/js/lib/echarts/620900.json";
            }
            if (city[params.name] === 'qingyang') {
                cityArea = "../dist/js/lib/echarts/621000.json";
            }
            if (city[params.name] === 'linxia') {
                cityArea = "../dist/js/lib/echarts/622900.json";
            }

            $.getJSON(cityArea, function(data) {
                echarts.registerMap(params.name, data);
                var importData = [];
                dataInterface.requestAjax("../ncovIndex/getMapData", commonParams,function (getData) {
                    debugger
                    for(var i =0;i<getData.mapDataList.length;i++){
                        if(getData.mapDataList[i].code != '620171'){
                            importData.push({
                                name: getData.mapDataList[i].name,
                                code:getData.mapDataList[i].code,
                                suspected: getData.mapDataList[i].ispcysbl,
                                confirmed: getData.mapDataList[i].isqzbl,
                            });
                        }
                    }
                    renderMap(params.name, importData);
                });
            });
        }
        /!*$(".areaName-back").click(function(){

            commonParams["areaCode"] = '62';
            var importData1 = [];
            dataInterface.requestAjax("../ncovIndex/getMapData", commonParams,function (getData) {
                debugger
                for(var i =0;i<getData.mapDataList.length;i++){
                    var szName = [];
                    if(getData.mapDataList[i].name.indexOf('市')!=-1){
                        szName = getData.mapDataList[i].name.substring(0,getData.mapDataList[i].name.length-1)
                    }
                    if(getData.mapDataList[i].name == '临夏回族自治州'){
                        szName = '临夏'
                    }
                    if(getData.mapDataList[i].name == '甘南藏族自治州'){
                        szName = '甘南'
                    }
                    importData1.push({
                        name: szName,
                        code:getData.mapDataList[i].code,
                        suspected: getData.mapDataList[i].ispcysbl,
                        confirmed: getData.mapDataList[i].isqzbl,
                    });
                }
                //renderMap("甘肃", importData);
                MapBusiness(importData1);
            });
            $.getJSON("../dist/js/lib/echarts/gansu.json", function(data) {
                console.log("加载全省");
                debugger;
                //注册地图
                echarts.registerMap("甘肃", data);
                // 绘制地图
                renderMap("甘肃", importData1);

            });

            $(".areaName-back").hide()
            $(".areaName").html("甘肃")
            firstPages();
            secondPages();
        })*!/
    })
}

// echarts水球图
/!*
var myChart = echarts.init(document.getElementById('jcys'));
var option = {
    backgroundColor: 'none',
    title: {
        text: '已解除疑似病例数', // 主标题文本，支持使用 \n 换行
        bottom: 15, // 定位 值: 'top', 'middle', 'bottom' 也可以是具体的值或者百分比
        left: 'center', // 值: 'left', 'center', 'right' 同上
        textStyle: { // 文本样式
            fontSize: 21,
            fontWeight: 600,
            color: '#fff'
        }
    },
    series: [{
        type: 'liquidFill',
        data: [0.32, 0.25],
        color: ['#096fa9'],
        itemStyle: {
            opacity: 0.5
        },
        radius: '50%',
        backgroundStyle: {
            color: 'none'
        },
        outline: {
            show: false
        },
        label: {
            normal: {
                position: ['50%', '40%'],
                formatter: '32',
                textStyle: {
                    fontSize: 40,
                    fontWeight: 400,
                    color: '#3fe5ca',
                    fontFamily: 'degital'
                }
            }
        }
    }]
};
myChart.setOption(option)*!/
*/
