/*!
 * Copyright &copy; 2015-2020 <a href="http://http://www.liuliangqb.com/">llqb</a> All rights reserved.
 * 
 * 通用公共方法
 * @author 
 * @version 2014-4-29
 */
$(document).ready(function() {
	try{
		// 链接去掉虚框
		$("a").bind("focus",function() {
			if(this.blur) {this.blur()};
		});
		//所有下拉框使用select2
		$("select").select2();
	}catch(e){
		// blank
	}
});

// 引入js和css文件
function include(id, path, file){
	if (document.getElementById(id)==null){
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++){
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + path + name + "'";
            document.write("<" + tag + (i==0?" id="+id:"") + attr + link + "></" + tag + ">");
        }
	}
}

// 获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == ""){
	    url = window.location.search;
    }else{	
    	url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]); return null;
}

//获取字典标签
function getDictLabel(data, value, defaultValue){
	for (var i=0; i<data.length; i++){
		var row = data[i];
		if (row.value == value){
			return row.label;
		}
	}
	return defaultValue;
}

// 打开一个窗体
function windowOpen(url, name, width, height){
	var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
		options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
		"resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
	window.open(url ,name , options);
}

// 恢复提示框显示
function resetTip(){
	top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip(){
	top.$.jBox.closeTip();
}

//显示提示框
function showTip(mess, type, timeout, lazytime){
	resetTip();
	setTimeout(function(){
		top.$.jBox.tip(mess, (type == undefined || type == '' ? 'info' : type), {opacity:0, 
			timeout:  timeout == undefined ? 2000 : timeout});
	}, lazytime == undefined ? 500 : lazytime);
}

// 显示加载框
function loading(mess){
	if (mess == undefined || mess == ""){
		mess = "正在提交，请稍等...";
	}
	resetTip();
	top.$.jBox.tip(mess,'loading',{opacity:0});
}

// 警告对话框
function alertx(mess, closed){
	top.$.jBox.info(mess, '提示', {closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
	top.$('.jbox-body .jbox-icon').css('top','55px');
}

// 确认对话框
function confirmx(mess, href, closed){
	
	top.layer.confirm(mess, {icon: 3, title:'系统提示'}, function(index){
	    //do something
		if (typeof href == 'function') {
			href();
		}else{
			resetTip(); //loading();
			location = href;
		}
	    top.layer.close(index);
	});
	
//	top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
//		if(v=='ok'){
//			if (typeof href == 'function') {
//				href();
//			}else{
//				resetTip(); //loading();
//				location = href;
//			}
//		}
//	},{buttonsFocus:1, closed:function(){
//		if (typeof closed == 'function') {
//			closed();
//		}
//	}});
//	top.$('.jbox-body .jbox-icon').css('top','55px');
	return false;
}

// 提示输入对话框
function promptx(title,  href){

	 var index = top.layer.prompt({title: title, formType: 2}, function(text){
		 if (typeof href == 'function') {
				href();
			}else{
				resetTip(); //loading();
				location = href + encodeURIComponent(text);
			}
		 
		 top.layer.close(index);
		  });
	return false;
}


// cookie操作
function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

// 数值前补零
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}

// 转换为日期
function strToDate(date){
	return new Date(date.replace(/-/g,"/"));
}

// 日期加减
function addDate(date, dadd){  
	date = date.valueOf();
	date = date + dadd * 24 * 60 * 60 * 1000;
	return new Date(date);  
}

//截取字符串，区别汉字和英文
function abbr(name, maxLength){  
 if(!maxLength){  
     maxLength = 20;  
 }  
 if(name==null||name.length<1){  
     return "";  
 }  
 var w = 0;//字符串长度，一个汉字长度为2   
 var s = 0;//汉字个数   
 var p = false;//判断字符串当前循环的前一个字符是否为汉字   
 var b = false;//判断字符串当前循环的字符是否为汉字   
 var nameSub;  
 for (var i=0; i<name.length; i++) {  
    if(i>1 && b==false){  
         p = false;  
    }  
    if(i>1 && b==true){  
         p = true;  
    }  
    var c = name.charCodeAt(i);  
    //单字节加1   
    if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {  
         w++;  
         b = false;  
    }else {  
         w+=2;  
         s++;  
         b = true;  
    }  
    if(w>maxLength && i<=name.length-1){  
         if(b==true && p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(b==false && p==false){  
             nameSub = name.substring(0,i-3)+"...";  
         }  
         if(b==true && p==false){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         if(p==true){  
             nameSub = name.substring(0,i-2)+"...";  
         }  
         break;  
    }  
 }  
 if(w<=maxLength){  
     return name;  
 }  
 return nameSub;  
}

var key=true;
function openDialogViewForLF(title,url,width,height){
	
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['关闭'],
	    success: function(layero, index){
			  var body = top.layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         var lffe= iframeWin.contentWindow.messLanuageFlagForEdit;
		         debugger;
		         var destTable=iframeWin.contentDocument.getElementsByClassName("table-bordered");
		         appendLanguageInput($(destTable).children(),lffe);
		         
		    }  ,
	    cancel: function(index){ 
	       }
	}); 
	
}
//打开对话框(添加修改),为了处理语言问题，在所有form前添加一个选择语言框
function openDialogForLF(title,url,width,height,target){
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	
	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['确定', '关闭'],
	  success: function(layero, index){
		  var body = top.layer.getChildFrame('body', index);
	         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
	         var lffe= iframeWin.contentWindow.messLanuageFlagForEdit;
	         var destTable=iframeWin.contentDocument.getElementsByClassName("table-bordered");
	         appendLanguageInput($(destTable).children(),lffe);

	      
	         iframeWin.contentWindow.refreshStyle();
	    }  ,
	    yes: function(index, layero){
	    	
	         if(key){	        
	        	 key=false;
	        	 var body = top.layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         var inputForm = body.find('#inputForm');
		         var top_iframe;
		         if(target){
		        	 top_iframe = target;//如果指定了iframe，则在改frame中跳转
		         }else{
		        	 top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
		         }
		         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
	        }
	        if(iframeWin.contentWindow.doSubmit()){
	        	// top.layer.close(index);//关闭对话框。
	        	  setTimeout(function(){
	        		  top.layer.close(index)
	        		
	        		  key=true;
	        		  }, 100);//延时0.1秒，对应360 7.1版本bug
	         }else{
	       
	        	 key=true;
	        	 
	         }
	        
		  },
		  cancel: function(index){ 
			 
	       }
	}); 	
	
}
/**
 * 获得当前弹出窗口所属的iframe的id，主要是为了两级弹窗时，第二级的提交能够刷新第一级弹窗，
 * 因此需要将这个id传递给openDialog，而不是刷新最底层的tab窗口
 */
function findCurrPopDialogIframeId(){
	var url = location.href;
	var _url = url.split("/");
	var destEle;
	var eles = top.document.getElementsByTagName("iframe");
	for (var i = 0 ; i < eles.length; i++) {
	       var ele = eles[i];
	       var iframeUrl = ele.src;
	       var _iframeUrl = iframeUrl.split("/");
	//获取当前页面的iframe
	       if (_url[_url.length - 1] == _iframeUrl[_iframeUrl.length - 1]) {
	       	debugger;
	       	destEle=ele;
	       	return destEle.id;
	       }
	 }
}


//打开对话框(添加修改)
function openDialog(title,url,width,height,target){
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
	
	}
	
	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['确定', '关闭'],
	    yes: function(index, layero){
	    	
	         if(key){	        
	        	
	        	 debugger;
	        	 key=false;
	        	 var body = top.layer.getChildFrame('body', index);
		         var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		         var inputForm = body.find('#inputForm');
		         var top_iframe;
		         if(target){
		        	 console.log( target+"----1---------");
		        	 top_iframe = target;//如果指定了iframe，则在改frame中跳转
		         }else{
		        	 console.log( top.getActiveTab().attr("name")+"-------------");
		        	 top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
		         }
		         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
	        }
	        if(iframeWin.contentWindow.doSubmit()){
	        	// top.layer.close(index);//关闭对话框。
	        	
	        	  setTimeout(function(){
	        		  top.layer.close(index)
	        		
	        		  key=true;
	        		  }, 100);//延时0.1秒，对应360 7.1版本bug
	         }else{
	       
	        	 key=true;
	        	 
	         }
	        
		  },
		  cancel: function(index){ 
			 
	       }
	}); 	
	
}
//打开对话框(添加修改)
function openDialog2(title,url,width,height,target){
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
		
	}
	
	top.layer.open({
		type: 2,  
		area: [width, height],
		title: title,
		maxmin: true, //开启最大化最小化按钮
		content: url ,
		btn: ['通过审核', '拒绝','关闭'],
		yes: function(index, layero){
	
			if(key){	        
				key=false;
				var body = top.layer.getChildFrame('body', index);
				var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				var inputForm = body.find('#inputForm');
				body.find('#option').val("1");
				/*var opt = body.find('#option').val();
				console.log(opt)
				alert(opt);*/
				var top_iframe;
				if(target){
					top_iframe = target;//如果指定了iframe，则在改frame中跳转
				}else{
					top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				}
				inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
			}
			if(iframeWin.contentWindow.doSubmit()){
				// top.layer.close(index);//关闭对话框。
				
				setTimeout(function(){
					top.layer.close(index)
					
					key=true;
				}, 100);//延时0.1秒，对应360 7.1版本bug
			}else{
				
				key=true;
				
			}
			
		},
		btn2: function(index, layero){
			if(key){	        
				key=false;
				var body = top.layer.getChildFrame('body', index);
				var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				var inputForm = body.find('#inputForm');
				body.find('#option').val("2");
				var top_iframe;
				if(target){
					top_iframe = target;//如果指定了iframe，则在改frame中跳转
				}else{
					top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				}
				inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
			}
			if(iframeWin.contentWindow.doSubmit()){
				// top.layer.close(index);//关闭对话框。
				
				setTimeout(function(){
					top.layer.close(index)
					
					key=true;
				}, 100);//延时0.1秒，对应360 7.1版本bug
			}else{
				
				key=true;
				
			}
		},
		cancel: function(index){ 
			
		}
	}); 	
	
}

//打开对话框(查看)
function openDialogView(title,url,width,height){
	
	
	if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
		width='auto';
		height='auto';
	}else{//如果是PC端，根据用户设置的width和height显示。
		
	}
	top.layer.open({
	    type: 2,  
	    area: [width, height],
	    title: title,
        maxmin: true, //开启最大化最小化按钮
	    content: url ,
	    btn: ['关闭'],
	    cancel: function(index){ 
	       },
	    success: function(layero, index){
	    	var iframeWin = layero.find('iframe')[0]; 
	    	var $iframeWin= $(iframeWin); 

			$(iframeWin).contents().find("input").attr("disabled",
					"disabled");
			$(iframeWin).contents().find("select").attr("disabled",
					"disabled");
			$(iframeWin).contents().find("select").attr("disabled",
					"disabled");
			$(iframeWin).contents().find("tbody *").css(
					'pointer-events', 'none')

			$(iframeWin).contents().find("iframe").css('pointer-events', 'auto')
			$(iframeWin).contents().find("video").css('pointer-events', 'auto')
//			$(iframeWin).contents().find("textarea").css(
//					'pointer-events', 'none')

			

			$(iframeWin).contents().find("textarea").attr("disabled", "disabled");
//			$(iframeWin).contents().find("iframe").contents().find(
//					"body").attr('contenteditable', 'false')
			$(iframeWin).contents().find("iframe").contents().find(".view").attr('contenteditable', 'false')
			
	    	
	    	
	    	$iframeWin.ready(function ($) {

	    	});
	    }
	}); 
	
}

function appendLanguageInput(destTbody,lffe){

	destTbody.after(get_html(lffe));
	 
}

function get_html(lffe){
	var _html = "<tr>"+
	    "                    <td><label class=\"pull-right\">语言类型：</label></td> "+
	    "                    <td>"+
	    "                        <select id=\"languageFlag\"  name=\"languageFlag\" class=\"required input-medium\" style=\"width:85px;*width:75px\">"+getLanguageDataMap(lffe)+
	    "                        </select>"+
	    "                    </td>"+
	    "                <td colspan=\"2\"></td> "+
	    "       </tr>";
	  return _html;
}

function handlerCss() {
    $("select").select2();
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });
    
}
function getLanguageDataMap(lffe) {

	   var joinTypeList =findLanguageMap(),opt = "";
	   if(joinTypeList != null && joinTypeList != ""){

		 joinTypeList = joinTypeList.replace(/=/g,":");
		  joinTypeList= eval('(' + joinTypeList + ')');
	        $.each(joinTypeList,function (key,value) {
	        	if(lffe==key){
	        		 opt +="<option value='"+key+"' selected title='"+ value + "'>"+ value+"</option>";
	        	}else{
	        		 opt +="<option value='"+key+"' title='"+ value + "'>"+ value+"</option>";
	        	}
	           
	        })
	   }
	   return opt;
	}

function search(){//查询，页码清零
	$("#pageNo").val(0);
	$("#searchForm").submit();
		return false;
}

function reset(){//重置，页码清零
	$("#pageNo").val(0);
	$("#searchForm div.form-group input").val("");
	$("#searchForm div.form-group select").val("");
	$("#searchForm").submit();
		return false;
	 }
function sortOrRefresh(){//刷新或者排序，页码不清零
	
	$("#searchForm").submit();
		return false;	
}
function page(n,s){//翻页
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	$("span.page-size").text(s);
	return false;
}

function callAjax(destUrl,pCallBack){
	$.ajax({
		processData:false,
		contentType : false,
		cache: false,	
		type: "POST",			 
		url:destUrl, 
		async: false,			 
		error: function(request, textStatus, errorThrown) {	
		  alert("ajax调用异常！"+request.status+","+request.readyState+","+textStatus);	 		 
	    },			 
	    success: function(data) {
	    	if(pCallBack){
	            pCallBack(data);
	      	  }
	    }			 
	  });	
}
function fnRequestFns(mymethodInfo,pCallBack){//
    var tUrl = '${ctx}/fnscall/fnsCall';
    var tParam = {
  	  methodInfo:mymethodInfo
    };
    $.ajax({
        url:tUrl,
        type:"post",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify(tParam),
        success:function(response){
      	
      	  if(pCallBack){

            pCallBack(response);
      	  }
        },
        error:function() {
            console.log('调用fns信息失败')
        }
    });
}

/*
 * 本方法主要是为了可更新表格使用，表单中的单元格是可编辑的，本方法是获得表格的编辑数据并以json格式传递到后端
 * 后端的获取方式为：
 * String sortInfoJsonArray = request.getParameter("batchJsonArray");
 * List<PageFieldList> problemList = MyJSONUtils.parseArray(sortInfoJsonArray, PageFieldList.class);
 * actionUrl：提交到后端的路径，即标准的controller
 * tBodyId：表格table中的tbody 的id，必须 要给tbody定义ID
 * someDestField：默认获取全部的列，可以以逗号分隔指定只获取某些列，如：'userName,address'
 */
    function batchUpdateEditableTable(actionUrl,tBodyId,someDestField,myindex){
    
   	 top.layer.confirm('确定要更新数据么?', {icon: 3, title:'系统提示'}, function(index){
				var myForm = document.forms['searchForm'];  //表单的name  
   		
   		      
				myForm.action=actionUrl;   
				var input = document.createElement('input');
				input.type = 'hidden';
				input.name = 'batchJsonArray';
				input.value = JSON.stringify(getTbodyJsonArray(tBodyId,someDestField));
				myForm.appendChild(input);
				myForm.method= 'POST';
				myForm.submit();
				/*if(myindex!=null){
					top.layer.close(myindex) 
				}esle{*/
					
					top.layer.close(index);
				/*}*/
			});
   }
	//可编辑table时获得数据
	function getTbodyJsonArray(editTableTBody,someDestField){
		var oTableCont = document.getElementById(editTableTBody);
		debugger;
		var rows=oTableCont.rows;
		var array=[];
		var destfArray=[];
		if(someDestField&&someDestField!=''){
			var destfArray=someDestField.split(",");
			var hasid="1";
			for(var h=0;h<destfArray.length;h++){
				if(destfArray[h]=='id'){
					hasid="2";
				}
			}
			if(hasid=="1"){//必须要传递id到后台，如果手工设置的目标属性集不包括id，要给自动加上去
				destfArray.push("id");
			}
		}
	     for(var i=0;i<rows.length;i++){    //--循环所有的行
	         var cells=rows[i].cells;
	         var item={};
	         for(var j=0;j<cells.length;j++){   //--循环所有的列
	  			var children = $(cells[j]).children();
	  			if(children[0] && children[0].id && children[0].id != '') {
	  				if(destfArray.length>0){
	  					for (var b=0;b<destfArray.length;b++){
	  						if(children[0].id==destfArray[b]){
	  							item[children[0].id]=children[0].value;
	  						}
	  						
	  					}
	  				}else{
	  					if(item[children[0].id]==destfArray[b]){
 							item[children[0].id]=children[0].value;
 						}
	  				}
	  				
	  			}
	         }
	         if(Object.getOwnPropertyNames(item).length>0)
	         array.push(item);
	     }
		return array;
	}
	//*******************************************以下是表格调整行顺序的js代码****************
	var oTableCont ;
	function initMoveListener(editTableTBody){
		oTableCont = document.getElementById(editTableTBody);
	    oTableCont.addEventListener('click', function(e) {
	        var event = e || window.event;
	        var target = event.target || event.srcElement;
	        switch (target.className) {
	            case 'move-up':
					moveUp(target)
	                break;
	            case 'move-down':
					moveDown(target)
	                break;
	            case 'move-top':
	            	moveTop(target)
	                break;
	            case 'stick':
					stick(target)
	                break;
	            default:

	        }

	    });
	}
	function findTopSiblingNode(otr){
		var temp = $('#editTableTBody tr')[0];
		return temp;
	}
	function moveTop(param){
  	  var otr = param.parentNode.parentNode;
  	  //获得第一行元素tr
  	  var nodetr = findTopSiblingNode(otr);
  	  if(nodetr){
  		moveNodeToTop(otr,nodetr);
  	  }
  	}
	function moveNodeToTop(node1,node2){//node1当前元素 node2是第一个元素
  	  //获取两个结点的相对位置
  	  var oneNode = node1.nextSibling;
  	  var twoNode = node2.nextSibling;
  
   	  var node1Value=node1.getElementsByTagName("input")[0].value;
  	  var node2Value=node2.getElementsByTagName("input")[0].value;

  	  //将node1插入到node2之前
  	  alert(node1Value+"--"+node2Value)
  	  if(node1&&node2){
  		oTableCont.insertBefore(node1,node2)
  	  }
  	  node1.getElementsByTagName("input")[0].value=node2Value-1;	  
  	}
	function moveUp(param){
    	  var otr = param.parentNode.parentNode;
    	  //如果不是第一行，则与上一行交换顺序
    	  
    	  var nodetr = otr.previousSibling;
    	  var otr=param.parentNode.parentNode
    	  var thisValue=otr.querySelector('input').value;
    	  var prevVal=null;
    	  
    	  if(otr.previousElementSibling){
    		  var prevVal=otr.previousElementSibling.querySelector('input').value;
    	  }else{
    		  return;
    	  }
    	  
    	  

    	  
    	  if(prevVal-thisValue>100000){
    		  return;
    	  }
    	  while(nodetr && nodetr.nodeType != 1){
    	    nodetr = nodetr.previousSibling;
    	  }
    	  if(nodetr){
    	    swapNode(otr,nodetr);
    	  }
    	}
	function moveDown(param){
    	  var otr = param.parentNode.parentNode;
    	  //如果不是最后一行，则与下一行交换顺序
    	  var nodetr = otr.previousSibling;
    	  var otr=param.parentNode.parentNode
    	  var thisValue=otr.querySelector('input').value;	    	  
    	  var nextVal=otr.nextElementSibling.querySelector('input').value;
    	  if(thisValue-nextVal>100000){
    		  return;
    	  }
    	  var nodetr = otr.nextSibling;
    	  
    	  console.log(nodetr)
    	  while(nodetr && nodetr.nodeType != 1){
    	    nodetr = nodetr.nextSibling;
    	  }
    	  if(nodetr){
    	    swapNode(otr,nodetr);
    	  }
    	}


    	function stick(param){
    		var otr = param.parentNode.parentNode;
    		oTableCont.insertBefore(otr,oTableCont.childNodes[0])
    	}


    	function swapNode(node1,node2){
    	  //获取两个结点的相对位置
    	  var oneNode = node1.nextSibling;
    	  var twoNode = node2.nextSibling;
    
     	  var node1Value=node1.getElementsByTagName("input")[0].value;
    	  var node2Value=node2.getElementsByTagName("input")[0].value;

    	  //将node2插入到原来node1的位置
    	  if(oneNode){
    	  	oTableCont.insertBefore(node2,oneNode)
    	  }else{
    	  	oTableCont.appendChild(node2)
    	  };
    	 
    	  //将node1插入到原来node2的位置
    	  if(twoNode){
    	  	oTableCont.insertBefore(node1,twoNode)
    	  }else{
    	  	oTableCont.appendChild(node1)
    	  };
    	  node1.getElementsByTagName("input")[0].value=node2Value;
    	  node2.getElementsByTagName("input")[0].value=node1Value;
    	  
    	}
    	
    	function addDialogBtn(btnName, callback, index, iframeWin, mylayer) {

    		return mylayer.layerAddBtn(btnName, callback, index, iframeWin);

    	}
    	//打开对话框(添加修改),自定义按钮
    	function openDialogCustomBtn(title, url, width, height, target) {
    		layer.layerAddBtn = function(name, callback, index, iframeWin) {
    			// var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    			var layerBtnOutdiv_ = parent.$("#layui-layer" + index);
    			var layerBtnDiv_ = layerBtnOutdiv_.find(".layui-layer-btn");
    			var button_ = $('<a></a>', {
    				text : name,
    				"class" : "layui-layer-btn0"
    			});
    			debugger;
    			button_.click(function() {
    				iframeWin.contentWindow.eval(callback(index))
    			});

    			if (layerBtnDiv_.length == 0) {
    				var s = "<div class=\"layui-layer-btn\"></div>";
    				layerBtnOutdiv_.append(s);
    				layerBtnDiv_ = layerBtnOutdiv_.find(".layui-layer-btn");
    				var iframe = layerBtnOutdiv_.find("iframe");
    				var oldhigh = $(iframe).height();
    				$(iframe).height(oldhigh - 50);// 调整iframe窗体的大小，以把按钮条露出
    			}
    			button_.appendTo(layerBtnDiv_);
    		}

    		if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {// 如果是移动端，就使用自适应大小弹窗
    			width = 'auto';
    			height = 'auto';
    		} else {// 如果是PC端，根据用户设置的width和height显示。

    		}

    		top.layer
    				.open({
    					type : 2,
    					area : [ width, height ],
    					title : title,
    					maxmin : true, // 开启最大化最小化按钮
    					content : url,
    					success : function(layero, index) {
    						var body = top.layer.getChildFrame('body', index);
    						var iframeWin = layero.find('iframe')[0]; // 得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
    						iframeWin.contentWindow.addBtn(index, iframeWin, layer);

    						var inputForm = body.find('#inputForm');
    						var top_iframe;
    						if (target) {
    							top_iframe = target;// 如果指定了iframe，则在改frame中跳转
    						} else {
    							top_iframe = top.getActiveTab().attr("name");// 获取当前active的tab的iframe
    						}
    						inputForm.attr("target", top_iframe);// 表单提交成功后，从服务器返回的url在当前tab中展示

    						var $iframeWin = $(iframeWin);

    						$(iframeWin).contents().find("input").attr("disabled",
    								"disabled");
    						$(iframeWin).contents().find("select").attr("disabled",
    								"disabled");
    						$(iframeWin).contents().find("select").attr("disabled",
    								"disabled");
    						$(iframeWin).contents().find("tbody").css('pointer-events',
    								'none')

    						$(iframeWin).contents().find("iframe").css(
    								'pointer-events', 'auto')
    						$(iframeWin).contents().find("textarea").css(
    								'pointer-events', 'auto')
    					$(iframeWin).contents().find("img").css(
    								'pointer-events', 'auto')
    						$(iframeWin).contents().find("textarea").attr("disabled",
    								"disabled");
    						$(iframeWin).contents().find("iframe").contents().find(
    								"body").attr('contenteditable', 'false')

    					}

    				});

    	}
	
