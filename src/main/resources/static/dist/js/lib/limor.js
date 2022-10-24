function randomData() {
  return Math.round(Math.random()*1000);
}

var Map_option = {
  title: {
      text: 'iphone销量',
      subtext: '纯属虚构',
      left: 'center'
  },
  tooltip: {
      trigger: 'item'
  },
  legend: {
      orient: 'vertical',
      left: 'left',
      data:['iphone3','iphone4','iphone5']
  },
  visualMap: {
      min: 0,
      max: 2500,
      left: 'left',
      top: 'bottom',
      text: ['高','低'],           // 文本，默认为数值文本
      calculable: true
  },
  toolbox: {
      show: false,
  },
  geo:{
    zoom: 1.6,
  },
  series: [
      {
          name: 'iphone3',
          type: 'map',
          zoom: 0.8,
          left: '24%',
          top: '24%',
          mapType: 'gansu',
          roam: false,
          label: {
              normal: {
                  show: true
              },
              emphasis: {
                  show: true
              }
          },
          data:[
              {name: '兰州市',value: randomData() },
              {name: '庆阳市',value: randomData() },
              {name: '天水市',value: randomData() },
              {name: '陇南市',value: randomData() },
              {name: '嘉峪关市',value: randomData() },
              {name: '金昌市',value: randomData() },
              {name: '武威市',value: randomData() },
          ]
      }/* ,
      {
          name: 'iphone4',
          type: 'map',
          zoom: 0.8,
          mapType: 'gansu',
          label: {
              normal: {
                  show: true
              },
              emphasis: {
                  show: true
              }
          },
          data:[
            {name: '兰州市',value: randomData() },
            {name: '庆阳市',value: randomData() },
            {name: '天水市',value: randomData() },
            {name: '陇南市',value: randomData() },
            {name: '嘉峪关市',value: randomData() },
            {name: '金昌市',value: randomData() },
            {name: '武威市',value: randomData() },
          ]
      },
      {
          name: 'iphone5',
          type: 'map',
          mapType: 'gansu',
          zoom: 0.8,
          label: {
              normal: {
                  show: true
              },
              emphasis: {
                  show: true
              }
          },
          data:[
            {name: '兰州市',value: randomData() },
            {name: '庆阳市',value: randomData() },
            {name: '天水市',value: randomData() },
            {name: '陇南市',value: randomData() },
            {name: '嘉峪关市',value: randomData() },
            {name: '金昌市',value: randomData() },
            {name: '武威市',value: randomData() },
          ]
      } */
  ]
};