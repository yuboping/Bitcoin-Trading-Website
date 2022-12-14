(function (doc, win) {
  var dpr = window.devicePixelRatio || 1;
  var docEl = doc.documentElement,
    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
    recalc = function () {
      var clientWidth = docEl.clientWidth;
      if (!clientWidth) return;
      docEl.style.fontSize = 40 * (clientWidth / 1920) + 'px';
      // 设置data-dpr属性，留作的css hack之用
      docEl.setAttribute('data-dpr', dpr);
    };
  if (!doc.addEventListener) return;
  win.addEventListener(resizeEvt, recalc, false);
  doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
