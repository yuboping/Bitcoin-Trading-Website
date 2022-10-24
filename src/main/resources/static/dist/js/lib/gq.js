clientWidth = document.documentElement.scrollWidth; //获取屏幕尺寸
scale = clientWidth / 1920; //缩放比例
// 折线图
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
        data: ['疑似', '确诊', '治愈', '死亡'],
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
        data: ['1月15', '1月16', '1月17', '1月18', '1月19', '1月20', '1月21', '1月22', '1月23', '1月24', '1月25', '1月26']
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
        name: '疑似',
        type: 'line',
        normal: {
            symbolSize: 0 * scale,
        },
        emphasis: {
            symbolSize: 6 * scale,
        },
        data: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210],
        lineStyle: {
            color: '#fcdb0f',
            width: 3 * scale
        },
        itemStyle: {
            color: '#fcdb0f',
            borderWidth: 2 * scale,
        }
    }, {
        name: '确诊',
        type: 'line',
        symbolSize: 6 * scale,
        data: [220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310],
        lineStyle: {
            color: '#ff8440',
            width: 3 * scale
        },
        itemStyle: {
            color: '#ff8440',
            borderWidth: 2 * scale
        }
    }, {
        name: '治愈',
        type: 'line',
        symbolSize: 6 * scale,
        data: [320, 332, 301, 334, 390, 330, 320, 301, 334, 390, 330, 320],
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
        data: [150, 232, 201, 154, 190, 330, 410, 201, 154, 190, 330, 410],
        lineStyle: {
            color: '#ff4b50',
            width: 3 * scale
        },
        itemStyle: {
            color: '#ff4b50',
            borderWidth: 2 * scale
        }
    }]
};
// 柱状图
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
        data: ['兰州', '嘉峪关', '金昌', '白银', '天水', '张掖', '酒泉', '武威', '定西', '平凉', '陇南', '庆阳', '临夏', '甘南']
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
        data: [220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310, 230, 210],
        itemStyle: {
            color: '#ff4b50',
        }
    },{
        name: '疑似',
        type: 'bar',
        barGap: 0,
        barWidth: 14 * scale,
        data: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210, 230, 210],
        itemStyle: {
            color: '#fcdb0f',
        }
    },   {
        name: '治愈',
        type: 'bar',
        barGap: 0,
        barWidth: 14 * scale,
        data: [320, 332, 301, 334, 390, 330, 320, 301, 334, 390, 330, 320, 230, 210],
        itemStyle: {
            color: '#00d578',
        }
    },{
        name: '死亡',
        type: 'bar',
        barGap: 0,
        barWidth: 14 * scale,
        data: [150, 232, 201, 154, 190, 330, 410, 201, 154, 190, 330, 410, 230, 210],
        itemStyle: {
            color: '#dfdcdc',
        }
    }]
};
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
            formatter: function(params) {
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
        data: ['兰州大学第一人民医院', '兰州大学第二人民医院', '甘肃省中医院', '甘肃省妇幼保健医院', '甘肃省人民医院', '甘肃省疾控中心', '甘肃省第二人民医院', '甘肃省肿瘤医院', '甘肃省中医药大学附属医院', '甘肃省中医药大学附属医院']
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
        type: 'bar',
        barWidth: 14 * scale,
        barGap: 0,
        data: [220, 182, 191, 234, 290, 330, 310, 191, 234, 290],
        itemStyle: {
            color: '#ff4b50',
        }
    }, {
        name: '疑似',
        type: 'bar',
        barWidth: 14 * scale,
        barGap: 0,
        data: [120, 132, 101, 134, 90, 230, 210, 101, 134, 90],
        itemStyle: {
            color: '#fcdb0f',
        }
    },{
        name: '治愈',
        type: 'bar',
        barWidth: 14 * scale,
        barGap: 0,
        data: [320, 332, 301, 334, 390, 330, 320, 301, 334, 390],
        itemStyle: {
            color: '#00d578',
        }
    }, {
        name: '死亡',
        type: 'bar',
        barWidth: 14 * scale,
        barGap: 0,
        data: [150, 232, 201, 154, 190, 330, 410, 201, 154, 190],
        itemStyle: {
            color: '#dfdcdc',
        }
    }]
};