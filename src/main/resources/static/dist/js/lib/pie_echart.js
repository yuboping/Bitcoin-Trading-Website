;(function(win,$){
	/**
	opt = {
			echars_id:echars_id,//容器id
			legend_data:legend_data,//代表的名称(数组)
			series_duspect:series_duspect,   //疑似病例数据
			series_diagnosee:series_diagnosee,   //确诊数据
			series_death:series_death,   //死亡数据
			series_cure:series_cure   //治愈数据
		}
	*/

	 //定点前十医疗机构柱状图
	 var CreatPieChart =	function (opt) {
            var myChart = echarts.init(document.getElementById(opt.echars_id));
            option_bar2 = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        lineStyle: {
                            type: 'dashed',
                            color: '#029f9d'
                        }
                    }
                },
                legend: {
                    data: ['确诊', '疑似', '治愈', '死亡'],
                    right: 30 * scale,
                    icon: 'rect',
                    textStyle: {
                        fontSize: 16 * scale,
                        color: '#fff'
                    }
                },
                grid: {
                    top: '10%',
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    axisLabel: {
                        color: '#fff',
                        fontSize: 12 * scale,
                        formatter: function (params) {
                            var newParamsName = "";
                            var paramsNameNumber = params.length;
                            var provideNumber = 4;
                            var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
                            if (paramsNameNumber > provideNumber) {
                                for (var p = 0; p < rowNumber; p++) {
                                    var tempStr = "";
                                    var start = p * provideNumber;
                                    var end = start + provideNumber;
                                    if (p == rowNumber - 1) {
                                        tempStr = params.substring(start, paramsNameNumber);
                                    } else {
                                        tempStr = params.substring(start, end) + "\n";
                                    }
                                    newParamsName += tempStr;
                                }
                            } else {
                                newParamsName = params;
                            }
                            return newParamsName;
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#147fa2'
                        }
                    },
                    splitLine: {
                        show: false
                    },
                    data:opt.legend_data
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        color: '#fff',
                        fontSize: 12 * scale
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#147fa2'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                },
                series: [{
                    name: '确诊',
                    type: 'bar',
                    barWidth: 14 * scale,
                    barGap: 0,
                    data: opt.series_diagnosee,
                    itemStyle: {
                        color: '#ff4b50',
                    }
                }, {
                    name: '疑似',
                    type: 'bar',
                    barWidth: 14 * scale,
                    barGap: 0,
                    data: opt.series_duspect,
                    itemStyle: {
                        color: '#fcdb0f',
                    }
                }, {
                    name: '治愈',
                    type: 'bar',
                    barWidth: 14 * scale,
                    barGap: 0,
                    data: opt.series_cure,
                    itemStyle: {
                        color: '#00d578',
                    }
                }, {
                    name: '死亡',
                    type: 'bar',
                    barWidth: 14 * scale,
                    barGap: 0,
                    data: opt.series_death,
                    itemStyle: {
                        color: '#dfdcdc',
                    }
                }]
            }

            myChart.setOption(option_bar2);

        }

  win.CreatPieChart = CreatPieChart;

//各市州柱状图
var CreatColumnarChart = function(opt){
    var myColumnarChart = echarts.init(document.getElementById(opt.echars_id));
    option_bar1 = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    type: 'dashed',
                    color: '#029f9d'
                }
            }
        },
        legend: {
            data: ['确诊', '疑似', '治愈', '死亡'],
            right: 30 * scale,
            icon: 'rect',
            textStyle: {
                fontSize: 16 * scale,
                color: '#fff'
            }
        },
        grid: {
            top: '10%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                color: '#fff',
                fontSize: 12 * scale
            },
            axisLine: {
                lineStyle: {
                    color: '#147fa2'
                }
            },
            splitLine: {
                show: false
            },
            data: opt.legend_data
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                color: '#fff',
                fontSize: 12 * scale
            },
            axisLine: {
                lineStyle: {
                    color: '#147fa2'
                }
            },
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '确诊',
            type: 'bar',
            barGap: 0,
            barWidth: 14 * scale,
            data: opt.series_diagnosee,
            itemStyle: {
                color: '#ff4b50',
            }
        },{
            name: '疑似',
            type: 'bar',
            barGap: 0,
            barWidth: 14 * scale,
            data: opt.series_duspect,
            itemStyle: {
                color: '#fcdb0f',
            }
        },   {
            name: '治愈',
            type: 'bar',
            barGap: 0,
            barWidth: 14 * scale,
            data: opt.series_cure,
            itemStyle: {
                color: '#00d578',
            }
        },{
            name: '死亡',
            type: 'bar',
            barGap: 0,
            barWidth: 14 * scale,
            data: opt.series_death,
            itemStyle: {
                color: '#dfdcdc',
            }
        }]
    }
    myColumnarChart.setOption(option_bar1);
}

    win.CreatColumnarChart = CreatColumnarChart;

//折线图

    var CreatBrokenLineChart = function(opt){
        var myBrokenLineChart = echarts.init(document.getElementById(opt.echars_id));
        option_line = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    lineStyle: {
                        type: 'dashed',
                        color: '#029f9d'
                    }
                }
            },
            legend: {
                data: [ '确诊','疑似', '治愈', '死亡'],
                right: 30 * scale,
                textStyle: {
                    fontSize: 16 * scale,
                    color: '#fff'
                },
                // icon: 'path://M10.981,7.000 C10.981,4.929 12.614,3.250 14.630,3.250 C16.645,3.250 18.279,4.929 18.279,7.000 C18.279,9.071 16.645,10.750 14.630,10.750 C12.614,10.750 10.981,9.071 10.981,7.000 Z'
            },
            grid: {
                top: '10%',
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                axisLabel: {
                    color: '#fff',
                    fontSize: 12 * scale
                },
                axisLine: {
                    lineStyle: {
                        color: '#147fa2'
                    }
                },
                splitLine: {
                    show: false
                },
                data: opt.legend_data
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    color: '#fff',
                    fontSize: 12 * scale
                },
                axisLine: {
                    lineStyle: {
                        color: '#147fa2'
                    }
                },
                splitLine: {
                    show: false
                }
            },
            series: [ {
                name: '确诊',
                type: 'line',
                symbolSize: 6 * scale,
                data: opt.series_diagnosee,
                lineStyle: {
                    color: '#ff8440',
                    width: 3 * scale
                },
                itemStyle: {
                    color: '#ff8440',
                    borderWidth: 2 * scale
                }
            },{
                name: '疑似',
                type: 'line',
                normal: {
                    symbolSize: 0 * scale,
                },
                emphasis: {
                    symbolSize: 6 * scale,
                },
                data: opt.series_duspect,
                lineStyle: {
                    color: '#fcdb0f',
                    width: 3 * scale
                },
                itemStyle: {
                    color: '#fcdb0f',
                    borderWidth: 2 * scale,
                }
            },
                {
                name: '治愈',
                type: 'line',
                symbolSize: 6 * scale,
                data: opt.series_cure,
                lineStyle: {
                    color: '#44f9d8',
                    width: 3 * scale
                },
                itemStyle: {
                    color: '#44f9d8',
                    borderWidth: 2 * scale
                }
            },{
                name: '死亡',
                type: 'line',
                symbolSize: 6 * scale,
                data: opt.series_death,
                lineStyle: {
                    color: '#FFFFFF',
                    width: 3 * scale
                },
                itemStyle: {
                    color: '#FFFFFF',
                    borderWidth: 2 * scale
                }
            }]
        }
        myBrokenLineChart.setOption(option_line);
    }

    win.CreatBrokenLineChart = CreatBrokenLineChart;

})(window,jQuery);
