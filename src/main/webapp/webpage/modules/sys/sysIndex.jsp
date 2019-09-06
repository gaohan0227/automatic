<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>海尔手机</title>

	<%@ include file="/webpage/include/head.jsp"%>
	<script src="${ctxStatic}/common/inspinia.js?v=3.2.0"></script>
	<script src="${ctxStatic}/common/contabs.js"></script> 
    <meta name="keywords" content="">
    <meta name="description" content="">
    <script type="text/javascript">
	$(document).ready(function() {
		 if('${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}' == '天蓝主题'){
			    // 蓝色主题
			        $("body").removeClass("skin-2");
			        $("body").removeClass("skin-3");
			        $("body").addClass("skin-1");
		 }else  if('${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}' == '橙色主题'){
			    // 黄色主题
			        $("body").removeClass("skin-1");
			        $("body").removeClass("skin-2");
			        $("body").addClass("skin-3");
		 }else {
			 // 默认主题
			        $("body").removeClass("skin-2");
			        $("body").removeClass("skin-3");
			        $("body").removeClass("skin-1");
		 };
	 });
	
	function loadFrame(obj){
		obj.contentDocument.onclick = function () {
			$('#side-menu').click(); 
		 }
	}
	</script>

</head>

<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" style="height:64px;width:64px;" src="${fns:getUser().photo }" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${fns:getUser().name}</strong></span>
                               <span class="text-muted text-xs block">${fns:getUser().roleNames}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="${ctx}/sys/user/imageEdit">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="${ctx }/sys/user/info">个人资料</a>
                                </li>
                               <%--  <li><a class="J_menuItem" href="${ctx }/iim/contact/index">我的通讯录</a>
                                </li>
                                <li><a class="J_menuItem" href="${ctx }/iim/mailBox/list">信箱</a>
                                </li> 
                                 <li class="divider"></li>
                                <li><a onclick="changeStyle()" href="#">切换到ACE模式</a>
                                </li>  --%>
                                 
                                <li class="divider"></li>
                                <li><a href="${ctx}/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">JP
                        </div>
                    </li>
     
                  <t:menu  menu="${fns:getTopMenu()}"></t:menu>
            
                 
             

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                 <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                      <!-- 国际化功能预留接口 -->
                        <li class="dropdown">
							<a id="lang-switch" class="lang-selector dropdown-toggle" href="#" data-toggle="dropdown" aria-expanded="true">
								<span class="lang-selected">
										<img  class="lang-flag" src="${ctxStatic}/common/img/china.png" alt="中国">
										<span class="lang-id">中国</span>
										<span class="lang-name">中文</span>
									</span>
							</a>

							<!--Language selector menu-->
							<ul class="head-list dropdown-menu with-arrow">
								<li>
                                    <!--中文-->
                                       <a class="lang-select" href="${ctx}/index?lang=zh_CN">
                                        <img class="lang-flag" src="${ctxStatic}/common/img/china.png" alt="中国">
                                        <span class="lang-id">中国</span>
                                        <span class="lang-name">中文</span>
                                    </a>
								</li>
								<li>
                                    <!--English-->
                                    <a class="lang-select" href="${ctx}/index?lang=en_US">
                                        <img class="lang-flag" src="${ctxStatic}/common/img/united-kingdom.png" alt="English">
                                        <span class="lang-id">EN</span>
                                        <span class="lang-name">English</span>
                                    </a>
								</li>
								
								 
								
								
							</ul>
						</li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/home">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose"  data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx}/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" onload="loadFrame(this)"  src="${ctx}/home" frameborder="0" data-id="${ctx}/home" seamless></iframe>
            </div>
            <!-- <div class="footer">
                <div class="pull-left"><a href="http://www.liuliangqb.com">http://www.liuliangqb.com</a> &copy; 2015-2025</div>
            </div> -->
        </div>
        <!--右侧部分结束-->
       
       
    </div>
</body>

<!-- 语言切换插件，为国际化功能预留插件 -->
<script type="text/javascript">

$(document).ready(function(){

	$("a.lang-select").click(function(){
		$(".lang-selected").find(".lang-flag").attr("src",$(this).find(".lang-flag").attr("src"));
		$(".lang-selected").find(".lang-flag").attr("alt",$(this).find(".lang-flag").attr("alt"));
		$(".lang-selected").find(".lang-id").text($(this).find(".lang-id").text());
		$(".lang-selected").find(".lang-name").text($(this).find(".lang-name").text());
	});
	var lang = getUrlParam("lang");
	console.log(lang)
	if(lang != null && lang != '' && lang != ""){
        var _select = $(".lang-select[href *= '"+lang+"']");
        $(".lang-selected").find(".lang-flag").attr("src",$(_select).find(".lang-flag").attr("src"));
        $(".lang-selected").find(".lang-flag").attr("alt",$(_select).find(".lang-flag").attr("alt"));
        $(".lang-selected").find(".lang-id").text($(_select).find(".lang-id").html());
        $(".lang-selected").find(".lang-name").text($(_select).find(".lang-name").html());
    }
});
//获取url的参数
function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}
function changeStyle(){
   $.get('${pageContext.request.contextPath}/theme/ace?url='+window.top.location.href,function(result){   window.location.reload();});
}
var IframeOnClick = {  
	    resolution: 200,  
	    iframes: [],  
	    interval: null,  
	    Iframe: function() {  
	        this.element = arguments[0];  
	        this.cb = arguments[1];   
	        this.hasTracked = false;  
	    },  
	    track: function(element, cb) {  
	        this.iframes.push(new this.Iframe(element, cb));  
	        if (!this.interval) {  
	            var _this = this;  
	            this.interval = setInterval(function() { _this.checkClick(); }, this.resolution);  
	        }  
	    },  
	    checkClick: function() {  
	        if (document.activeElement) {  
	            var activeElement = document.activeElement;  
	            for (var i in this.iframes) {  
	                if (activeElement === this.iframes[i].element) { // user is in this Iframe  
	                    if (this.iframes[i].hasTracked == false) {   
	                        this.iframes[i].cb.apply(window, []);   
	                        this.iframes[i].hasTracked = true;  
	                    }  
	                } else {  
	                    this.iframes[i].hasTracked = false;  
	                }  
	            }  
	        }  
	    }  
	}; 

</script>
</html>