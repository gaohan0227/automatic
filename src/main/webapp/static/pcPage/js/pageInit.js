// 初始化页面

window.onload = function() {
    checkUser();
    initPageHeader();
    initPageFooter();
    try {
        pageOnload && pageOnload();
    }catch(e) {}
}

// 页面头部
function initPageHeader() {
    var domStr = '<div id="page-header" data-flex="main:left cross:center box:justify">';
        domStr +=   '<div class="logo">';
        domStr +=       '<img src="../img/icon_logo.png">';
        domStr +=   '</div>';
        domStr +=   '<div class="tabs-btn" data-flex="main:left">';
        domStr +=       '<div>首页</div>';
        domStr +=       '<div>拼音秀</div>';
        domStr +=       '<div>阅读</div>';
        domStr +=       '<div>勇闯关</div>';
        domStr +=       '<div>商品展示</div>';
        domStr +=   '</div>'
        domStr +=   '<div class="mine-btn" data-flex="main:right">';
        domStr +=       '<div>我的</div>';
        domStr +=       '<div>消息</div>';
        domStr +=       '<div>日历</div>';
        domStr +=   '</div>';
        domStr += '</div>';
        domStr += '<div id="page-header-placeholder"></div>';
    $(domStr).insertBefore('#page-content');
}

// 页面底部
function initPageFooter() {
    var domStr = '<div id="page-footer">';
        domStr +=   '<div data-flex="main:left">';
        domStr +=       '<div class="logo">';
        domStr +=           '<img src="../img/icon_logo.png">';
        domStr +=       '</div>';
        domStr +=       '<div class="colunm">';
        domStr +=           '<p class="top">走向未来</p>';
        domStr +=           '<p>配音秀</p>';
        domStr +=           '<p>阅读</p>';
        domStr +=           '<p>勇闯关</p>';
        domStr +=       '</div>';
        domStr +=       '<div class="colunm">';
        domStr +=           '<p class="top">商品展示</p>';
        domStr +=           '<p>商品展示</p>';
        domStr +=       '</div>';
        domStr +=       '<div class="colunm">';
        domStr +=           '<p class="top">关于我们</p>';
        domStr +=           '<p>关于我们</p>';
        domStr +=           '<p>联系我们</p>';
        domStr +=       '</div>';
        domStr +=       '<div>';
        domStr +=           '<p>App手机下载</p>';
        domStr +=           '<div data-flex="main:left">';
        domStr +=               '<p>苹果</p>';
        domStr +=               '<p>安卓</p>';
        domStr +=           '</div>';
        domStr +=       '</div>';
        domStr +=   '</div>';
        domStr +=   '<div class="copyright">';
        domStr +=       '<p>Copyright ©2018.All Rights Reserved 京ICP备05027956号-1</p>';
        domStr +=       '<p>北京走向未来教育机构</p>';
        domStr +=   '</div>';
        domStr += '</div>';
        domStr += '<div id="page-footer-placeholder"></div>';
    $(domStr).insertAfter('#page-content');
}

// 验证用户登录
function checkUser() {

}