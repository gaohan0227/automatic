<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='user.function'/></title>
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
			$("#no").focus();
			validateForm = $("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}//设置了远程验证，在初始化时必须预先调用一次。
				},
				messages: {
					loginName: {remote: "用户<spring:message code='sysUser.loginName'/>已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的<spring:message code='user.password'/>"}
				},
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

			//在ready函数中预先调用一次远程校验函数，是一个无奈的回避案。(刘高峰）
			//否则打开修改对话框，不做任何更改直接submit,这时再触发远程校验，耗时较长，
			//submit函数在等待远程校验结果然后再提交，而layer对话框不会阻塞会直接关闭同时会销毁表单，因此submit没有提交就被销毁了导致提交表单失败。
			$("#inputForm").validate().element($("#loginName"));
		});

	
	</script>
</head>
<body class="hideScroll">
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
		      <tr>
		        <!-- <td class="width-15 active">	<label class="pull-right"> <font color="red">*</font> ：</label> --></td>
		         <%-- <td class="width-35"><form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
						<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/></td>
		     --%>     <%-- <td  class="width-15"  class="active">	<label class="pull-right"><font color="red">*</font><spring:message code='sysUser.company'/>:</label></td>
		         <td class="width-35"><sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
						title="公司" url="/sys/office/treeData?type=1" cssClass="form-control required"/></td>
		      </tr>
		      
		      <tr>
		         <td class="active"><label class="pull-right"><font color="red">*</font><spring:message code='sysUser.dep'/>:</label></td>
		         <td><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="form-control required" notAllowSelectParent="true"/></td>
		         <td class="active"><label class="pull-right"><font color="red">*</font><spring:message code='sysUser.no'/>:</label></td>
		         <td><form:input path="no" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		      </tr> --%>
		      
		      <tr>
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font><spring:message code='clientUser.name'/>:</label></td>
		         <td style="width: 160px;"><form:input path="name" htmlEscape="false" maxlength="50" class="form-control required"/></td>
		         
		         <td class="width-15 active"><label class="pull-right"><font color="red">*</font><spring:message code='sysUser.loginName'/>:</label></td>
		          <td class="width-35"><input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
		          <form:input path="loginName" htmlEscape="false" maxlength="10" class="form-control required"/></td>
		      </tr>
		      
		      
		      <tr>
		         <td class="active"><label class="pull-right"><c:if test="${empty user.id}"><font color="red">*</font></c:if><spring:message code='user.password'/>:</label></td>
		         <td><input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="form-control ${empty user.id?'required':''}"/>
					<c:if test="${not empty user.id}"><span class="help-inline"><spring:message code='user.password.help'/></span></c:if></td>
		         <td class="active"><label class="pull-right"><c:if test="${empty user.id}"><font color="red">*</font></c:if><spring:message code='user.pwdAgain'/>:</label></td>
		         <td><input id="confirmNewPassword" name="confirmNewPassword" type="password"  class="form-control ${empty user.id?'required':''}" value="" maxlength="50" minlength="3" equalTo="#newPassword"/></td>
		      </tr>
		      
		       <tr>
		         <td class="active"><label class="pull-right"><spring:message code='sysOffice.email'/>:</label></td>
		         <td><form:input path="email" htmlEscape="false" maxlength="100" class="form-control email"/></td>
		         <td class="active"><label class="pull-right"><spring:message code='sysUser.phone'/>:</label></td>
		         <td><form:input path="phone" htmlEscape="false" maxlength="100" class="form-control digits"/></td>
		      </tr>
		      
		  
		      
		      <tr>
		         
		         <td class="active"><label class="pull-right"><font color="red">*</font><spring:message code='sysOffice.user.role'/>:</label></td>
		         <td>
		         	<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false" cssClass="i-checks required"/>
		         	<label id="roleIdList-error" class="error" for="roleIdList"></label>
		         </td>
		         <td class="active"><label class="pull-right"><spring:message code='sysOffice.user.allow.login'/>:</label></td>
		         <td><form:select path="loginFlag"  class="form-control">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
		      </tr>
		      
		       <tr>
		         <td class="active"><label class="pull-right"><spring:message code='sysOffice.phone'/>:</label></td>
		         <td><form:input path="mobile" htmlEscape="false" maxlength="100" class="form-control digits"/></td>
		           	<td class=" active"><label class="pull-right"><spring:message code='sysOffice.distribution.area'/>：</label></td>
					<td><form:select path="company.id" class="form-control ">
						<form:option value="" label=""/>
						<form:options items="${fns:findAllCountryList()}" itemLabel="countryName" itemValue="countryCode" htmlEscape="false"/>
					</form:select> </td>
				    
		      </tr>
	</form:form>
</body>
</html>
