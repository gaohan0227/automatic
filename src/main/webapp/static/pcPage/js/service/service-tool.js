function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

/*
 * 格式化时间为 2018/07/30 12:08:08
 */
function formatTime(date, type) {
    if (!date) return '';
    if (typeof date == 'string' && (date.indexOf('-') != -1 || date.indexOf('.') != -1)) {
        date = date.replace(/-/g, '/');
        date = date.replace(/\./g, '/');
    }
    if (type == 'nextYear') {
        date = new Date();
    }
    if (date && parseInt(date) == date) {
        date = parseInt(date);
    }
    date = date ? new Date(date) : new Date();
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()
    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds();
    if (type == 'day') {
        return day;
    }
    if (type == 'month') {
        return month;
    }
    if (type == 'year') {
        return year;
    }
    if (type == 'date') {
        return [year, month, day].map(formatNumber).join('-');
    }
    if (type == 'report') {
        return [year, month, day].map(formatNumber).join('.');
    }
    if (type == "order_data") {
        return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute].map(formatNumber).join(':')
    }
    if (type == 'nextYear') {
        year = year * 1 + 1;
    }
    if (type == 'dateC') {
        return year + "年" + formatNumber(month) + "月" + formatNumber(day) + "日";
    }
    if (type == 'date') {
        return [year, month, day].map(formatNumber).join('-');
    }
    return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}


var code_timer;

function fnVerifyTime(time, el) {
    //time 倒计时的时间,当前的vue， sendBtnText 文本字段 isSendCode 发送按钮是否可用字段
    console.log("开启倒计时")
    var _time;
    if (time && time != "") {
        _time = time;
    } else {
        _time = 60;
    }
    if (code_timer) {
        clearInterval(code_timer);
        el.sendBtnText = _time+"s";
    }
    code_timer = setInterval(function() {
        if (_time > 0) {
            _time--;
            el.sendBtnText = _time;
        } else {
            clearInterval(code_timer);
            el.isSendCode = true;
            el.sendBtnText = '重新获取';
        }
    }, "1000");
}

//移除数组中的某个元素
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};



var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function(obj) {
    return typeof obj;
} : function(obj) {
    return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
};

//判断是否为空
function isEmpty(data) {
    if (isEmpty1(data) || isEmpty2(data)) {
        return true;
    }
    return false;
};

function isEmpty1(data) {
    if (data == undefined || data == null || data == "" || data == 'NULL' || data == false || data == 'false') {
        return true;
    }
    return false;
};

function isEmpty2(v) {
    switch (typeof v === "undefined" ? "undefined" : _typeof(v)) {
        case 'undefined':
            return true;
        case 'boolean':
            if (!v) return true;
            break;
        case 'number':
            if (0 === v) return true;
            break;
        case 'object':
            if (null === v) return true;
            if (undefined !== v.length && v.length == 0) return true;
            for (var k in v) {
                return false;
            }
            return true;
            break;
    }
    return false;
};
//本地存储
function fnWhenLoginSucess(data){
    if (!data) {
        console.log('错误：用户信息保存失败');
        return;
    }
    for(var key in data){
        localStorage[key]=data[key];
    }
}

function fnLoadJS(url) {
    var list=document.getElementsByTagName('script');
    if(list.length>0){
        for (var i = 0; i < list.length; i++) {
            var oldNum = list[i].src.indexOf('/js/');  
            var oldJs = list[i].src.substring(oldNum+1,list[i].src.length);
            var newNum = url.indexOf('/js/');  
            var newJs = url.substring(newNum+1,url.length);
            if(oldJs==newJs){
                var removeJs = document.getElementsByTagName('script')[i];
                removeJs.parentNode.removeChild(removeJs);
            }
        }

    }
    var script= document.createElement('script'); 
    script.type= 'text/javascript'; 
    script.src= url; 
    document.getElementsByTagName('head')[0].appendChild(script); 
}
//修改单元的名称
function fnSetPublicUnit(uId, uName) {
    var _uId = uId||localStorage.unitId|| "";
    var _uName = uName || localStorage.unitName || "";
    if (_uId == localStorage.unitId) {
        localStorage.unitName = _uName;
    }
}