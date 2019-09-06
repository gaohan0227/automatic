<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>国家管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/ueditor/ueditor.config.js"></script>
<script src="${ctxStatic}/ueditor/ueditor.all.js"></script>
<script src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${ctxStatic}/ueditor/ueditor.parse.js"></script>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
					laydate({
			            elem: '#createDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
					laydate({
			            elem: '#updateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
		});
		
		
		//实例化富文本
	    var ue = UE.getEditor('descpition');
	    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;	    
	    UE.Editor.prototype.getActionUrl = function (action) {
	        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {
	            return '${ctx}/sp/ued/uploadFile';
	        }else{
	            return this._bkGetActionUrl.call(this, action);
	        }
	    };
	    
	$(document).ready(function() {
			
			ue.ready(function() {
				var htmlStr = $("#descpitionHedden").val(); //数据回显；
	            //设置编辑器的内容				 
	            ue.setContent(htmlStr, false); 
				
	           
	        });
			 
		
		}); 
		
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="country" action="${ctx}/country/country/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><spring:message code='tbCountry.countryCode'/>：</label></td>
					<td class="width-35">
						<form:input path="countryCode" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><spring:message code='tbCountry.countryName'/>：</label></td>
					<td class="width-35">
						<form:input path="countryName" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><spring:message code='global.desc'/> ：</label></td>
					<td class="width-35">
						<form:input path="countryDesc" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right"><spring:message code='tbCountry.validateFlag'/>：</label></td>
					<td class="width-35">
						<form:select path="validateFlag" class="form-control required">
							<form:options items="${fns:getDictList('validate_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font  color="red">*</font>描述：</label></td>
					<td class="width-35" colspan="3">
					  <!--style给定宽度可以影响编辑器的最终宽度-->
                      <script type="text/plain" id="descpition"  style="width:100%;height:600px;" name="descpition">

                      </script>  
                     <input id="descpitionHedden"  value="" type="hidden" />  
                      <label id="descpitionHedden-error">至少输入5个字符</label>
                      <label id="descpitionHedden-long-error">最多输入400个字符</label>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>
