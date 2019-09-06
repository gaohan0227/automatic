<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title><spring:message code='user.function'/></title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div class="wrapper wrapper-content">
    <sys:message content="${message}"/>
		<!-- 查询条件 -->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<%-- <span><spring:message code='sysUser.company'/>：</span>
				<sys:treeselect id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"  
				title="公司" url="/sys/office/treeData?type=1" cssClass=" form-control input-sm" allowClear="true"/>--%>
			<%-- <span><spring:message code='sysUser.dep'/>：</span>
				<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass=" form-control input-sm" allowClear="true" notAllowSelectParent="true"/>
	 --%>		
	 
	           <%-- <span>姓&nbsp;&nbsp;&nbsp;名：</span>
				<form:input path="name" htmlEscape="false" maxlength="50" class=" form-control input-sm"/> --%>
		     <span><spring:message code='global.belongCountry'/> ：</span>
				<form:select path="countryCode"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:findAllCountryList()}" itemLabel="countryName" itemValue="countryCode" htmlEscape="false"/>
				</form:select>
			<span><spring:message code='global.city'/> ：</span>
			     <form:select style="width:300px" path="cityCode"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options  itemLabel="cityName"   itemValue="cityCode" htmlEscape="false"/>
				</form:select>
		
		
		
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="sys:user:add">
					<table:addRow url="${ctx}/sys/user/form" title="用户" width="800px" height="625px" target="officeContent"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:edit">
			    <table:editRow url="${ctx}/sys/user/form" id="contentTable"  title="用户" width="800px" height="680px" target="officeContent"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:del">
				<table:delRow url="${ctx}/sys/user/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
		<%-- 需求上暂时不做--%>
			<shiro:hasPermission name="sys:user:import">
				<table:importExcel url="${ctx}/sys/user/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:export">
	       		<table:exportExcel url="${ctx}/sys/user/export"></table:exportExcel><!-- 导出按钮 -->
	       </shiro:hasPermission> 
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="<spring:message code='global.refresh'/>"><i class="glyphicon glyphicon-repeat"></i> <spring:message code='global.refresh'/></button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> <spring:message code='global.query'/>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> <spring:message code='global.reset'/>
		</div>
	</div>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th><input type="checkbox" class="i-checks"></th>
				<!-- <th class="sort-column login_name"><spring:message code='sysUser.loginName'/></th> -->
				<th class="sort-column name"><spring:message code='clientUser.name'/></th>
				<th class="sort-column roleName"><spring:message code='Administrator.category'/></th>
				<th class="sort-column countryCode"><spring:message code='sysOffice.distribution.area'/></th>
				<th class="sort-column phone"><spring:message code='sysOffice.phone'/></th>
<!-- 				<th class="sort-column c.name"><spring:message code='sysUser.company'/></th>
				<th class="sort-column o.name"><spring:message code='sysUser.dep'/></th> -->
				<th><spring:message code='global.opt'/> </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td> <input type="checkbox" id="${user.id}" class="i-checks"></td>
				<td>${user.name}</td>
				<td>${user.roleName}</td>
				<td>${user.countryName}</td>
				
				<td>${user.phone}</td>
				
<%-- 				<td>${user.company.name}</td>
				<td>${user.office.name}</td> --%>
				<td>
					<shiro:hasPermission name="sys:user:view">
						<a href="#" onclick="openDialogView('<spring:message code='user.view'/>', '${ctx}/sys/user/form?id=${user.id}','900px', '600px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> <spring:message code='global.view'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:user:edit">
						<a href="#" onclick="openDialog('<spring:message code='user.update'/>', '${ctx}/sys/user/form?id=${user.id}','900px', '600px', 'officeContent')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> <spring:message code='global.update'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:user:del">
						<a href="${ctx}/sys/user/delete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> <spring:message code='global.delete'/></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table:page page="${page}"></table:page>
	</div>
</body>
</html>
