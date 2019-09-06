var Tool = {};
/**
 * 发送ajax请求和服务器交互
 * @param {object} mySetting 配置ajax的配置
 */
Tool.ajax = function(mySetting) {
  var setting = {
    url: window.location.pathname, //默认ajax请求地址
    async: true, //true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false
    type: 'GET', //请求的方式
    data: {}, //发给服务器的数据
    dataType: 'json',
    success: function(text) {}, //请求成功执行方法
    error: function() {}, //请求失败执行方法
  };

  var aData = []; //存储数据
  var sData = ''; //拼接数据
  //属性覆盖
  for (var attr in mySetting) {
    setting[attr] = mySetting[attr];
  }
  for (var attr in setting.data) {
    aData.push(attr + '=' + filter(setting.data[attr]));
  }
  sData = aData.join('&');
  setting.type = setting.type.toUpperCase();
  // setting.url = window.TARGETWEBSITE + setting.url;
  var xhr = new XMLHttpRequest();
  try {
    if (setting.type == 'GET') {
      //get方式请求
      sData = setting.url + '?' + sData;
      xhr.open(setting.type, sData, setting.async);
      xhr.send();
    } else {
      //post方式请求
      xhr.open(setting.type, setting.url, setting.async);
      xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhr.send(sData);
    }
  } catch (e) {
    return httpEnd();
  }

  if (setting.async) {
    xhr.addEventListener('readystatechange', httpEnd, false);
  } else {
    httpEnd();
  }

  function httpEnd() {
    if (xhr.readyState == 4) {
      var head = xhr.getAllResponseHeaders();
      var response = xhr.responseText;
      //将服务器返回的数据，转换成json

      if (
        /application\/json/.test(head) ||
        (setting.dataType === 'json' &&
          /^(\{|\[)([\s\S])*?(\]|\})$/.test(response))
      ) {
        response = JSON.parse(response);
      }

      if (xhr.status == 200) {
        setting.success(response, setting, xhr);
      } else {
        setting.error(setting, xhr);
      }
    }
  }
  xhr.end = function() {
    xhr.removeEventListener('readystatechange', httpEnd, false);
  };

  function filter(str) {
    //特殊字符转义
    str += ''; //隐式转换
    str = str.replace(/%/g, '%25');
    str = str.replace(/\+/g, '%2B');
    str = str.replace(/ /g, '%20');
    str = str.replace(/\//g, '%2F');
    str = str.replace(/\?/g, '%3F');
    str = str.replace(/&/g, '%26');
    str = str.replace(/\=/g, '%3D');
    str = str.replace(/#/g, '%23');
    return str;
  }
  return xhr;
};
/**
 * 封装ajax post请求
 * @param {string} pathname 服务器请求地址
 * @param {object} data     发送给服务器的数据
 * @param {function} success  请求成功执行方法
 * @param {function} error    请求失败执行方法
 */
Tool.post = function(pathname, data, success, error) {
  var setting = {
    url: pathname, //默认ajax请求地址
    header: {
      custId: '1',
    },
    type: 'POST', //请求的方式
    data: data, //发给服务器的数据
    success: success || function() {}, //请求成功执行方法
    error: error || function() {}, //请求失败执行方法
  };
  return Tool.ajax(setting);
};
/**
 * 封装ajax get请求
 * @param {string} pathname 服务器请求地址
 * @param {object} data     发送给服务器的数据
 * @param {function} success  请求成功执行方法
 * @param {function} error    请求失败执行方法
 */
Tool.get = function(pathname, data, success, error) {
  var setting = {
    url: pathname, //默认ajax请求地址
    type: 'GET', //请求的方式
    data: data, //发给服务器的数据
    success: success || function() {}, //请求成功执行方法
    error: error || function() {}, //请求失败执行方法
  };
  return Tool.ajax(setting);
};

Tool.formatDateByFmt = function(DateObj, fmt) {
  var o = {
    'M+': DateObj.getMonth() + 1, //月份
    'd+': DateObj.getDate(), //日
    'h+': DateObj.getHours(), //小时
    'm+': DateObj.getMinutes(), //分
    's+': DateObj.getSeconds(), //秒
    'q+': Math.floor((DateObj.getMonth() + 3) / 3), //季度
    S: DateObj.getMilliseconds(), //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
      RegExp.$1,
      (DateObj.getFullYear() + '').substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp('(' + k + ')').test(fmt))
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)
      );
  return fmt;
};

/**
 * 距离今天{days}时间的日期
 *
 * @param {days} number
 * @returns
 */
Tool.formateDateZT = function(str) {
  if (!str) {
    return '刚刚';
  }
  str = str.replace(/\-/g, '/');
  var today = new Date().getTime();
  var before = new Date(str).getTime();
  var cha = today - before;
  if (cha <= 1000 * 60 * 60) {
    return '刚刚';
  } else {
    var hours = cha / (1000 * 60 * 60);
    if (hours < 24) {
      return parseInt(hours) + '小时前';
    } else if (hours < 24 * 30) {
      return parseInt(hours / 24) + '天前';
    } else {
      return parseInt(hours / (24 * 30)) + '月前';
    }
  }
};

/**
 * 距离今天{days}天数的日期
 *
 * @param {days} number
 * @returns
 */
Tool.timeSpace = function(days) {
  var date = new Date();
  var timestamp = Date.parse(date);
  var daySeconds = days * 60 * 60 * 24 * 1000;
  var needTimestap = timestamp - daySeconds;
  var needDate = new Date(needTimestap);
  var seperator1 = '-';
  var month = needDate.getMonth() + 1;
  var strDate = needDate.getDate();
  if (month >= 1 && month <= 9) {
    month = '0' + month;
  }
  if (strDate >= 0 && strDate <= 9) {
    strDate = '0' + strDate;
  }
  var currentdate =
    needDate.getFullYear() + seperator1 + month + seperator1 + strDate;
  return currentdate;
};

/**
 * 保留两位小数,直接截掉
 *
 * @param inter
 * @returns {*}
 */
Tool.fomatFloat = function(inter, length) {
  if (!inter) {
    return '0';
  }
  var income = Math.floor(inter * 100) / 100;
  if (length == 1) {
    income = Math.floor(inter * 10) / 10;
  } else if (length == 0) {
    income = Math.floor(inter * 1) / 1;
  }
  return income;
};

/**
 * 保留小数,四舍五入
 *
 * @param inter
 * @returns {*}
 */
Tool.formateNum = function(inter, length) {
  if (!inter) {
    return '0.00';
  }
  length = arguments.length === 1 ? 2 : length;
  inter = parseFloat(inter);
  return inter.toFixed(length);
};

/**
 * 本地数据存储或读取
 *
 * @param {any} key
 * @param {any} value
 * @returns
 */
Tool.localItem = function(key, value) {
  if (arguments.length == 1) {
    return localStorage.getItem(key);
  } else {
    return localStorage.setItem(key, value);
  }
};

/**
 * 删除本地数据
 *
 * @param {any} key
 * @returns
 */
Tool.removeLocalItem = function(key) {
  if (key) {
    return localStorage.removeItem(key);
  }
  return localStorage.removeItem();
};

/**
 * cookie存储或读取
 *
 * @param {any} key
 * @param {any} value
 * @returns
 */
Tool.cookieItem = function(key, value) {
  if (key === 'User') {
    if (arguments.length == 1) {
      if (window.USERSTR) {
        return decodeURIComponent(window.USERSTR);
      } else {
        return null;
      }
    } else {
      return (window.USERSTR = encodeURIComponent(value));
    }
  }
  if (arguments.length == 1) {
    var reg = new RegExp('(^| )' + key + '=([^;]*)(;|$)');
    var arr = document.cookie.match(reg);
    if (arr && arr[2] != '') {
      return decodeURIComponent(arr[2]);
    } else {
      return null;
    }
  } else {
    return (document.cookie = key + '=' + encodeURIComponent(value));
  }
};

/**
 * 删除Cookie
 *
 * @param {any} key
 * @returns
 */
Tool.clearCookie = function(key) {
  var d = new Date();
  d.setTime(d.getTime() + -1 * 24 * 60 * 60 * 1000);
  var expires = 'expires=' + d.toUTCString();
  document.cookie = key + '=; ' + expires;
};

/**
 * 数组是否包含某个元素
 *
 * @param obj
 * @return 元素在数组中的位置，-1为不包含
 */
Array.prototype.containObj = function(obj) {
  for (var i = 0; i < this.length; i++) {
    if (this[i] == obj) {
      return i;
    }
  }
  return -1;
};

/**
 * 字符串以什么结尾
 * */
String.prototype.endWith = function(endStr) {
  var d = this.length - endStr.length;
  return d >= 0 && this.lastIndexOf(endStr) == d;
};
//带有中文字符的字符串的长度--中文字符length=2
String.prototype.gblen = function() {
  var len = 0;
  for (var i = 0; i < this.length; i++) {
    if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
      len += 2;
    } else {
      len++;
    }
  }
  return len;
};

Tool.isEmptyObj = function(obj) {
  for (var t in obj) return false;
  return true;
};

/*
 * 统计talkingdata
 *
 * @params id 时间id
 * @params label 事件类型
 */
Tool.clickStat = function(id, label, params) {
  if (window.TDAPP) {
    TDAPP.onEvent(id, label, params);
  } else {
    console.warn('TDAPP is not exist！\n' + id + ' ' + JSON.stringify(label));
  }
};

//生成随即字符串 len 随机串的长度
Tool.randomString = function(len) {
  len = len || 8;
  var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
  /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/ var maxPos =
    $chars.length;
  var pwd = '';
  for (var i = 0; i < len; i++) {
    pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
  }
  return pwd;
};

//字符串长度，中文字符为2
Tool.byteLength = function(str) {
  str = str || '';
  var len = 0;
  for (var i = 0; i < str.length; i++) {
    var a = str.charAt(i);
    if (a.match(/[^\x00-\xff]/gi) != null) {
      len += 2;
    } else {
      len += 1;
    }
  }
  return len;
};

Tool.harfYear = function(endDate) {
  endDate = endDate.replace(/-/g, '/');
  var currentdate = Date.parse(new Date());
  endDate = Date.parse(new Date(endDate));
  var harfYear = (365 * 24 * 60 * 60 * 1000) / 2;
  var harfAgo = currentdate - harfYear - (currentdate - endDate);
  var date = new Date(harfAgo);
  return date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate();
};

/**
 * 获取URL参数
 * @param param 获取的value
 * @return {string}
 */
Tool.getQuery = function(param) {
  var result = window.location.hash.match(
    new RegExp('(\\?|&)' + param + '=([^&]*)(&|$)')
  );
  return result ? decodeURIComponent(result[2]) : '';
};

/**
 * [POST POST方法,不发送文件]
 * @param {[string]} api_name  [必填，部分请求路径]
 * @param {[object]} params   [选填，请求参数对象]
 * @param {[function]} callback [选填，回调函数]
 * @param {[function]} error [选填，是否统一异常处理]
 */
function POST(api_name, params, callback, error) {
  var _url = window.location.host;
  api_name = 'http://' + _url + '/FutureEducation/api/' + api_name;
  params = params || '';
  var postData = params != '' ? JSON.stringify(params) : '';
  var custId = localStorage.custId || '034f2d37b12e4aaf837d776512d9e4b5';
  $.ajax({
    type: 'POST',
    contentType: 'application/json;charset=utf-8',
    url: api_name,
    data: postData,
    dataType: 'json',
    beforeSend: function(XMLHttpRequest) {
      XMLHttpRequest.setRequestHeader('custId', custId);
    },
    success: function(ret) {
      var tStatus = ret.status;
      if ('object' !== typeof ret.data) {
        ret.data = {};
      }
      if (tStatus == '200' && ret.data.errorStatus) {
        ret.status = '';
        fnToast(ret.data.errorMessage);
      }
      callback(ret);
    },
    error: function(err) {
        error && error(err);
    }
  });
}
