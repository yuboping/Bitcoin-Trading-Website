'use strict';

var clientWidth = document.documentElement.scrollWidth; //获取屏幕尺寸
var scale = clientWidth / 1920; //缩放比例
var optionLine1 = {
    grid: {
        left: '15%',
        right: '15%',
        top: '15%',
        bottom: '15%'
    },
    legend: {
        show: true,
        orient: 'horizontal',
        left: 'center',
        top: '1%',
        data: ['体温', '呼吸', '心率'],
        textStyle: {
            color: '#fff',
            fontSize: 15 * scale
        },
        icon: 'circle',
        itemWidth: 14 * scale,
        itemHeight: 14 * scale
    },
    xAxis: {
        type: 'category',
        data: ['01-24', '01-25', '01-26', '01-27', '01-28'],
        boundaryGap: false,
        axisLine: {
            show: true,
            lineStyle: {
                color: '#1a2950',
                type: 'solid'
            }
        },
        axisLabel: {
            show: true,
            color: '#fff'
        },
        axisTick: {
            show: false
        }
    },
    yAxis: [{
        type: 'value',
        min: 0,
        max: 50,
        splitNumber: 5,
        axisLine: {
            show: false
        },
        name: '体温(℃)',
        nameTextStyle: {
            color: "#fff",
            fontSize: 14 * scale,
            padding: [0, 30 * scale, 0, 0]
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            show: true,
            color: '#fff'
        },
        splitLine: {
            show: true,
            lineStyle: {
                color: '#456299',
                type: 'dashed'
            }
        }
    }, {
        type: 'value',
        min: 0,
        max: 150,
        splitNumber: 5,
        axisLine: {
            show: false
        },
        name: 'bpm',
        nameTextStyle: {
            color: "#fff",
            fontSize: 14 * scale,
            padding: [0, 30 * scale, 0, 0]
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            show: true,
            color: '#fff'
        },
        splitLine: {
            show: true,
            lineStyle: {
                color: '#456299',
                type: 'dashed'
            }
        }
    }],
    series: [{
        type: 'line',
        name: '体温',
        data: [35, 38, 43, 39, 42],
        symbol: 'circle',
        symbolSize: 8 * scale,
        itemStyle: {
            color: '#ff8657'
        },
        lineStyle: {
            width: 2 * scale
        }
    }, {
        type: 'line',
        name: '呼吸',
        data: [29, 30, 30, 31, 36],
        symbol: 'circle',
        symbolSize: 8 * scale,
        itemStyle: {
            color: '#55d6fb'
        },
        lineStyle: {
            width: 2 * scale
        }
    }, {
        type: 'line',
        name: '心率',
        data: [18, 19, 17, 18, 22],
        symbol: 'circle',
        symbolSize: 8 * scale,
        itemStyle: {
            color: '#f4ee2c'
        },
        lineStyle: {
            width: 2 * scale
        }
    }]
};
var optionLine2 = {
    grid: {
        left: '15%',
        right: '15%',
        top: '15%',
        bottom: '15%'
    },
    legend: {
        show: true,
        orient: 'horizontal',
        left: 'center',
        top: '1%',
        data: ['舒张压', '收缩压'],
        textStyle: {
            color: '#fff',
            fontSize: 15 * scale
        },
        icon: 'circle',
        itemWidth: 14 * scale,
        itemHeight: 14 * scale
    },
    xAxis: {
        type: 'category',
        data: ['01-24', '01-25', '01-26', '01-27', '01-28'],
        boundaryGap: false,
        axisLine: {
            show: true,
            lineStyle: {
                color: '#1a2950',
                type: 'solid'
            }
        },
        axisLabel: {
            show: true,
            color: '#fff'
        },
        axisTick: {
            show: false
        }
    },
    yAxis: {
        type: 'value',
        min: 0,
        max: 200,
        splitNumber: 5,
        axisLine: {
            show: false
        },
        name: '血压/mmhg',
        nameTextStyle: {
            color: "#fff",
            fontSize: 14 * scale,
            padding: [0, 30 * scale, 0, 0]
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            show: true,
            color: '#fff'
        },
        splitLine: {
            show: true,
            lineStyle: {
                color: '#456299',
                type: 'dashed'
            }
        }
    },
    series: [{
        type: 'line',
        name: '舒张压',
        data: [35, 38, 43, 39, 42],
        symbol: 'circle',
        symbolSize: 8 * scale,
        itemStyle: {
            color: '#ff8657'
        },
        lineStyle: {
            width: 2 * scale
        }
    }, {
        type: 'line',
        name: '收缩压',
        data: [29, 30, 30, 31, 36],
        symbol: 'circle',
        symbolSize: 8 * scale,
        itemStyle: {
            color: '#55d6fb'
        },
        lineStyle: {
            width: 2 * scale
        }
    }]
};