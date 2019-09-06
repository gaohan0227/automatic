<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息类型管理管理</title>
	<meta name="decorator" content="default"/>
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
			
		});
	</script>
</head>
<body class="hideScroll">
		<form:form id="inputForm" modelAttribute="messageType" action="${ctx}/messagetype/messageType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
	
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>扩展参数日期是否时分秒：</label></td>
					<td class="width-35">
			            <form:select path="extendParamsDateIfTimestamp"  class="form-control m-b required">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getExtendParamsDateIfTimestampMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>

	
					<td class="width-15 active"><label class="pull-right">扩展参数：</label></td>
					<td class="width-35">
						<form:input path="extendParams" htmlEscape="false" maxlength="50"    class="form-control "/>
					</td>
				</tr>

	
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>跳转：</label></td>
					<td class="width-35">
						<form:input path="redirectUrl" htmlEscape="false" maxlength="200"    class="form-control required"/>
					</td>

	
					<td class="width-15 active"><label class="pull-right">对应业务表名：</label></td>
					<td class="width-35">
						<form:input path="bussTableName" htmlEscape="false" maxlength="50"    class="form-control "/>
					</td>
				</tr>

	
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>消息内容最大长度：</label></td>
					<td class="width-35">
						<form:input path="messageMaxLength" htmlEscape="false" maxlength="11"    class="form-control required number"/>
					</td>

	
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>类型代码：</label></td>
					<td class="width-35">
						<form:input path="messageTypeCode" htmlEscape="false" maxlength="2"    class="form-control required"/>
					</td>
				</tr>

	
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>类型名称：</label></td>
					<td class="width-35">
						<form:input path="messageTypeName" htmlEscape="false" maxlength="32"    class="form-control required"/>
					</td>

					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>