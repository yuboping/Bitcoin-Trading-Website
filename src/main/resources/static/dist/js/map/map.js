'use strict';

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }
var importData1 = [];
var importData2 = [];
$(function () {
     if(commonParams["areaCode"] == "86"){
        $(".areaName").html('全国');
        chinaMap();
    }else {
         $(".areaName").html('甘肃省');
        everyProvince();
    }
});

function everyProvince() {
    loadProvinceData();
}
function chinaMap() {
    loadMapChina();
}
function loadMapChina(){
    $(function () {
        //dom加载后执行
        mapChart('main_gansu');
    });
    /**
     * 根据Json里的数据构造Echarts地图所需要的数据
     * @param {} mapJson
     */
    function initMapData(mapJson) {
        var mapData = [];
        for (var i = 0; i < mapJson.features.length; i++) {
            if(mapJson.features[i].properties.name == '甘肃'){
                mapData.push({
                    name: mapJson.features[i].properties.name,
                    code: mapJson.features[i].id,
                    suspected: 29,
                    confirmed: 15,
                    value: 29
                });
            }else {
                mapData.push({
                    name: mapJson.features[i].properties.name,
                    code: mapJson.features[i].id,
                    suspected: Math.floor(0),
                    confirmed: Math.floor(0),
                    value: Math.floor(0)

                });
            }
        }
        return mapData;
    }
    function loadMapDatasz(myChart,cityId,param) {
        dataInterface.requestAjax("../ncovIndex/getMapData", commonParams,function (getData) {
            for(var i =0;i<getData.mapDataList.length;i++){
                var _item ;
                if (getData.mapDataList[i].isqzbl >= 1) {
                    _item = getData.mapDataList[i].isqzbl;
                } else if (getData.mapDataList[i].ispcysbl >= 1) {
                    _item = 0.5;
                } else {
                    _item = 0;
                }
                var _code;
                if(getData.mapDataList[i].code.length == 2){
                    _code = getData.mapDataList[i].code+'0000';
                }else if(getData.mapDataList[i].code.length == 4){
                    _code = getData.mapDataList[i].code+'00';
                }else {
                    _code = getData.mapDataList[i].code;
                }
                importData2.push({
                    name: getData.mapDataList[i].name,
                    code:_code,
                    suspected: getData.mapDataList[i].ispcysbl,
                    confirmed: getData.mapDataList[i].isqzbl,
                    value : _item
                });
            }
            $.get('../dist/js/lib/mapJson/' + cityId + '.json', function (mapJson) {
                registerAndsetOption(myChart, cityId, param.name, mapJson, true);
            });
        });
    }
    /*function initMapData(mapJson) {
        return importData2;
    }*/
    /**
     * 返回上一级地图
     */
    function back() {
        if (mapStack.length != 0) {
            //如果有上级目录则执行
            var map = mapStack.pop();
            $.get('../dist/js/lib/mapJson/' + map.mapId + '.json', function (mapJson) {
                registerAndsetOption(myChart, map.mapId, map.mapName, mapJson, false);
                //返回上一级后，父级的ID、Name随之改变
                parentId = map.mapId;
                parentName = map.mapName;
            });
        }
    }
    /**
     * Echarts地图
     * provinceId：省的code
     * provinceName：省的name
     * 说明：如果想要全国地图provinceId=100000 provinceName='china'
     */
//各省市地图（第一级地图）的ID、Name、Json数据
    var provinceId = 100000;
    var provinceName = 'china';
    var chinaJson = null;

//记录父级ID、Name
    var mapStack = [];
    var parentId = null;
    var parentName = null;

//Echarts地图全局变量，主要是在返回上级地图的方法中会用到
    var myChart = null;
    function mapChart(divid) {
        $.get('../dist/js/lib/mapJson/' + provinceId + '.json', function (mapJson) {
            chinaJson = mapJson;
            myChart = echarts.init(document.getElementById(divid));
            registerAndsetOption(myChart, provinceId, provinceName, mapJson, false);
            parentId = provinceId;
            parentName = 'gansu';
            myChart.on('click', function (param) {
                var cityId = cityMap[param.name];
                if (cityId) {
                    /*if(cityId = '620000'){
                        loadProvinceData();
                    }*/
                    /*$(".areaName").html(param.name);
                    //代表有下级地图
                    $.get('../dist/js/lib/mapJson/' + cityId + '.json', function (mapJson) {
                        registerAndsetOption(myChart, cityId, param.name, mapJson, true);
                    });*/
                    commonParams["areaCode"] = getSessionAreaCode(param.data.code);
                    firstPages();
                    secondPages();
                    $(".areaName").html(param.name);
                    //代表有下级地图
                    commonParams["areaCode"] = getSessionAreaCode(param.data.code);
                    loadMapDatasz(myChart,cityId,param);
                } else {
                    $(".areaName").html('全国');
                    //没有下级地图，回到一级中国地图，并将mapStack清空
                    registerAndsetOption(myChart, provinceId, provinceName, chinaJson, false);
                    mapStack = [];
                    parentId = provinceId;
                    parentName = provinceName;
                }
            });
        });
    }

    /**
     *
     * @param {*} myChart
     * @param {*} id        省市县Id
     * @param {*} name      省市县名称
     * @param {*} mapJson   地图Json数据
     * @param {*} flag      是否往mapStack里添加parentId，parentName
     */

    var option = {
        tooltip: {
            trigger: "item",
            borderColor: '#028ec9',
            borderWidth: 3,
            padding: [15, 18],
            formatter: function formatter(d) {
                var name = '<span style="color:#0bb5ff;font-size:24px;">' + d.name + '</span>';
                var html = '';
                var suspected = d.data && d.data.suspected ? d.data.suspected : 0;
                var confirmed = d.data && d.data.confirmed ? d.data.confirmed : 0;
                html += name + "</br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;margin-top:11px;'>疑似病例数：" + suspected + "</span></br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;'>确诊病例数：" + confirmed + "</span></br>";
                return html;
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
                color: '#00dbf2'
            },
            textStyle: {
                color: '#fff'
            },
            pieces: [{
                label: '疑似感染',
                min: 0.1,
                max: 0.9,
                color: '#f8db25'
            }, {
                label: '1-9人',
                min: 1,
                max: 9,
                color: '#fe8439'
            }, {
                label: '10-99人',
                min: 10,
                max: 99,
                color: '#fa5e2d'
            }, {
                label: '100-499人',
                min: 100,
                max: 499,
                color: '#fd4f51'
            }, {
                label: '500人以上',
                min: 500,
                color: '#be0000'
            }]
        }
    };
    function registerAndsetOption(myChart, id, name, mapJson, flag) {
        var _ref;

        echarts.registerMap(name, mapJson);
        option.series = [(_ref = {
            type: 'map',
            map: name,
            showLegendSymbol: true,
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
                    borderWidth: 1
                },

                emphasis: {
                    show: false
                }
            }
        }, _defineProperty(_ref, 'itemStyle', {
            normal: {
                areaColor: 'rgba(23, 27, 57,0)',
                borderColor: '#fff',
                borderWidth: 1
            }
        }), _defineProperty(_ref, 'data', initMapData(mapJson)), _ref), {
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
        }];
        myChart.setOption(option);
        if (flag) {
            //往mapStack里添加parentId，parentName,返回上一级使用
            mapStack.push({
                mapId: parentId,
                mapName: parentName
            });
            parentId = id;
            parentName = name;
        }
    }

    function getSessionAreaCode(xzqhCode) {
        if(xzqhCode.endsWith("0000")){
            xzqhCode = xzqhCode.substring(0, 2);
        }else  if(xzqhCode.endsWith("00")){
            xzqhCode = xzqhCode.substring(0, 4);
        }else {
            xzqhCode = xzqhCode;
        }
        return xzqhCode;
    }
}
function loadProvinceData() {
    $(function () {
        loadMapData();
    });
    /**
     * 根据Json里的数据构造Echarts地图所需要的数据
     * @param {} mapJson
     */
    function loadMapData() {
        dataInterface.requestAjax("../ncovIndex/getMapData", commonParams,function (getData) {
            for(var i =0;i<getData.mapDataList.length;i++){
                /*var szName = [];
                if(getData.mapDataList[i].name.indexOf('市')!=-1){
                    szName = getData.mapDataList[i].name.substring(0,getData.mapDataList[i].name.length-1)
                }
                if(getData.mapDataList[i].name == '临夏回族自治州'){
                    szName = '临夏'
                }
                if(getData.mapDataList[i].name == '甘南藏族自治州'){
                    szName = '甘南'
                }*/
                var _item ;
                if (getData.mapDataList[i].isqzbl >= 1) {
                    _item = getData.mapDataList[i].isqzbl;
                } else if (getData.mapDataList[i].ispcysbl >= 1) {
                    _item = 0.5;
                } else {
                    _item = 0;
                }
                var _code;
                if(getData.mapDataList[i].code.length == 2){
                    _code = getData.mapDataList[i].code+'0000';
                }else if(getData.mapDataList[i].code.length == 4){
                    _code = getData.mapDataList[i].code+'00';
                }else {
                    _code = getData.mapDataList[i].code;
                }
                importData1.push({
                    name: getData.mapDataList[i].name,
                    code:_code,
                    suspected: getData.mapDataList[i].ispcysbl,
                    confirmed: getData.mapDataList[i].isqzbl,
                    value : _item
                });
            }
            //dom加载后执行
            mapChart('main_gansu');
        });
    }
    function loadMapDatasz(myChart,cityId,param) {
        debugger
        dataInterface.requestAjax("../ncovIndex/getMapData", commonParams,function (getData) {
            for(var i =0;i<getData.mapDataList.length;i++){
                var _item ;
                if (getData.mapDataList[i].isqzbl >= 1) {
                    _item = getData.mapDataList[i].isqzbl;
                } else if (getData.mapDataList[i].ispcysbl >= 1) {
                    _item = 0.5;
                } else {
                    _item = 0;
                }
                var _code;
                if(getData.mapDataList[i].code.length == 2){
                    _code = getData.mapDataList[i].code+'0000';
                }else if(getData.mapDataList[i].code.length == 4){
                    _code = getData.mapDataList[i].code+'00';
                }else {
                    _code = getData.mapDataList[i].code;
                }
                importData1.push({
                    name: getData.mapDataList[i].name,
                    code:_code,
                    suspected: getData.mapDataList[i].ispcysbl,
                    confirmed: getData.mapDataList[i].isqzbl,
                    value : _item
                });
            }
            $.get('../dist/js/lib/mapJson/' + cityId + '.json', function (mapJson) {
                registerAndsetOption(myChart, cityId, param.name, mapJson, true);
            });
        });
    }
    function initMapData(mapJson) {
        return importData1;
    }

    /**
     * 返回上一级地图
     */
    function back() {
        if (mapStack.length != 0) {
            //如果有上级目录则执行
            var map = mapStack.pop();
            $(".areaName-back").hide();
            $(".areaName").html('甘肃省');
            commonParams["areaCode"] = getSessionAreaCode(map.mapId+"");
            firstPages();
            secondPages();
            $.get('../dist/js/lib/mapJson/' + map.mapId + '.json', function (mapJson) {
                registerAndsetOption(myChart, map.mapId, map.mapName, mapJson, false);
                //返回上一级后，父级的ID、Name随之改变
                parentId = map.mapId;
                parentName = map.mapName;
            });
        }
    }
    /**
     * Echarts地图
     * provinceId：省的code
     * provinceName：省的name
     * 说明：如果想要全国地图provinceId=100000 provinceName='china'
     */
//各省市地图（第一级地图）的ID、Name、Json数据
    var provinceId = 620000;
    var provinceName = 'gansu';
    var chinaJson = null;

//记录父级ID、Name
    var mapStack = [];
    var parentId = null;
    var parentName = null;

//Echarts地图全局变量，主要是在返回上级地图的方法中会用到
    var myChart = null;
    function mapChart(divid) {
        $.get('../dist/js/lib/mapJson/' + provinceId + '.json', function (mapJson) {
            chinaJson = mapJson;
            myChart = echarts.init(document.getElementById(divid));
            registerAndsetOption(myChart, provinceId, provinceName, mapJson, false);
            parentId = provinceId;
            parentName = 'gansu';
            myChart.on('click', function (param) {
                var cityId = cityMap[param.name];
                if (cityId) {
                    commonParams["areaCode"] = getSessionAreaCode(param.data.code);
                    firstPages();
                    secondPages();
                    $(".areaName-back").show();
                    $(".areaName").html(param.name);
                    //代表有下级地图
                    commonParams["areaCode"] = getSessionAreaCode(param.data.code);
                    loadMapDatasz(myChart,cityId,param);
                } /*else {
                $(".areaName-back").hide();
                //没有下级地图，回到一级中国地图，并将mapStack清空
                registerAndsetOption(myChart, provinceId, provinceName, chinaJson, false);
                mapStack = [];
                parentId = provinceId;
                parentName = provinceName;
            }*/
            });
        });
    }

    /**
     *
     * @param {*} myChart
     * @param {*} id        省市县Id
     * @param {*} name      省市县名称
     * @param {*} mapJson   地图Json数据
     * @param {*} flag      是否往mapStack里添加parentId，parentName
     */

    var option = {

        tooltip: {
            trigger: "item",
            borderColor: '#028ec9',
            borderWidth: 3,
            padding: [15, 18],
            formatter: function formatter(d) {
                var name = '<span style="color:#0bb5ff;font-size:24px;">' + d.name + '</span>';
                var html = '';
                var suspected = d.data && d.data.suspected ? d.data.suspected : 0;
                var confirmed = d.data && d.data.confirmed ? d.data.confirmed : 0;
                html += name + "</br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;margin-top:11px;'>疑似病例数：" + suspected + "</span></br><span style='display:inline-block;height: 40px;line-height: 40px;font-size:18px;'>确诊病例数：" + confirmed + "</span></br>";
                return html;
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
                color: '#00dbf2'
            },
            textStyle: {
                color: '#fff'
            },
            pieces: [{
                label: '疑似感染',
                min: 0.1,
                max: 0.9,
                color: '#f8db25'
            }, {
                label: '1-9人',
                min: 1,
                max: 9,
                color: '#fe8439'
            }, {
                label: '10-99人',
                min: 10,
                max: 99,
                color: '#fa5e2d'
            }, {
                label: '100-499人',
                min: 100,
                max: 499,
                color: '#fd4f51'
            }, {
                label: '500人以上',
                min: 500,
                color: '#be0000'
            }]
        }
    };
    function registerAndsetOption(myChart, id, name, mapJson, flag) {
        var _ref;

        echarts.registerMap(name, mapJson);
        option.series = [(_ref = {
            type: 'map',
            map: name,
            right: "2%",
            top: 10,
            height: "98%",
            width: "98%",
            showLegendSymbol: true,
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
                    borderWidth: 1
                },

                emphasis: {
                    show: false
                }
            }
        }, _defineProperty(_ref, 'itemStyle', {
            normal: {
                areaColor: 'rgba(23, 27, 57,0)',
                borderColor: '#fff',
                borderWidth: 1
            }
        }), _defineProperty(_ref, 'data', initMapData(mapJson)), _ref), {
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
        }];
        myChart.setOption(option);
        if (flag) {
            //往mapStack里添加parentId，parentName,返回上一级使用
            mapStack.push({
                mapId: parentId,
                mapName: parentName
            });
            parentId = id;
            parentName = name;
        }
    }

    function getSessionAreaCode(xzqhCode) {
        if(xzqhCode.endsWith("0000")){
            xzqhCode = xzqhCode.substring(0, 2);
        }else  if(xzqhCode.endsWith("00")){
            xzqhCode = xzqhCode.substring(0, 4);
        }else {
            xzqhCode = xzqhCode;
        }
        return xzqhCode;
    }

    $('#goBackBtn').on("click",function () {
        back();
    });
}

