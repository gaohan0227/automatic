// 页面公共方法 --打开，返回等

var Page = {};

// 重新打开一个页面
Page.open = function(_link, _params) {
    Tool.localItem('_Page_Params', _params);
    window.location.href = _link;
};

// 重定向当前页面 
Page.redirect = function(_link, _params) {
    Tool.localItem('_Page_Params', _params);
    window.location.replace(_link);
};

// 返回 不传或者-1为返回一次
Page.back = function(_level) {
    _level = _level || -1;
    window.history.go(_level);
};

// 获取页面传参
Page.params = function() {
    Tool.localItem('_Page_Params');
}
