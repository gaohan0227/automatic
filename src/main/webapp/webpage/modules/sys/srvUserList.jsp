<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(function(){
		//$("#loginFlag option:eq(1)").removeAttr("selected");
		$("#loginFlag").select2('val','');
	})
	</script>
</head>
<body>
	<div class="wrapper wrapper-content">
    <sys:message content="${message}"/>
		<!-- 查询条件 -->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/srvList" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<c:if test="${fns:getUser().platform == '2' }">
				<span>服务商编号：</span><form:input path="srvProInfo.servicerNo" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
				<span>服务商名称：</span><form:input path="srvProInfo.servicerName" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
			</c:if>
			<span>登&nbsp;录&nbsp;名：</span><form:input path="loginName" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
			<span>姓&nbsp;&nbsp;&nbsp;名：</span><form:input path="name" htmlEscape="false" maxlength="50" class=" form-control input-sm"/>
			<span><spring:message code='global.isdelete'/> ：</span>
				<form:select path="loginFlag"  class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
			<shiro:hasPermission name="srv:user:add">
				<c:if test="${fns:getUser().platform == '1' }">
					<table:addRow url="${ctx}/sys/user/srvForm" title="用户" width="800px" height="625px" target="officeContent" label="添加"></table:addRow><!-- 增加按钮 -->
				</c:if>
			</shiro:hasPermission>
			<shiro:hasPermission name="srv:user:edit">
			    <table:editRow url="${ctx}/sys/user/srvForm" id="contentTable"  title="用户" width="800px" height="680px" target="officeContent" label="修改"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="srv:user:del">
				<table:delRow url="${ctx}/sys/user/srvDeleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="srv:user:import">
				<table:importExcel url="${ctx}/sys/user/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="srv:user:export">
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
				<c:if test="${fns:getUser().platform == '2' }">
					<th class="sort-column srvProInfo.servicerNo">服务商编号</th>
					<th class="sort-column srvProInfo.servicerName">服务商名称</th>
				</c:if>
				<th class="sort-column login_name"><spring:message code='sysUser.loginName'/></th>
				<th class="sort-column name"><spring:message code='clientUser.name'/></th>
				<th class="sort-column no"><spring:message code='sysUser.no'/></th>
				<th class="sort-column userType">账户类型</th>
				<th class="sort-column phone"><spring:message code='sysUser.phone'/></th>
				<th class="sort-column mobile">手机</th>
				<th class="sort-column loginFlag"><spring:message code='global.isdelete'/> </th>
				<th class="sort-column createDate">创建时间</th>
				<th class="sort-column updateDate">更新时间</th>
				<th><spring:message code='global.opt'/> </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td> <input type="checkbox" id="${user.id}" class="i-checks"></td>
				<c:if test="${fns:getUser().platform == '2' }">
					<td>${user.srvProInfo.servicerNo}</td>
					<td>${user.srvProInfo.servicerName}</td>
				</c:if>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.no}</td>
				<td>${fns:getDictLabel(user.userType, 'user_type', '')}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<td>${fns:getDictLabel(user.loginFlag, 'yes_no', '')}</td>
				<td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${user.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<shiro:hasPermission name="srv:user:view">
						<a href="#" onclick="openDialogView('查看用户', '${ctx}/sys/user/srvForm?id=${user.id}','800px', '680px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> <spring:message code='global.view'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="srv:user:edit">
						<a href="#" onclick="openDialog('修改用户', '${ctx}/sys/user/srvForm?id=${user.id}','800px', '700px', 'officeContent')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> <spring:message code='global.update'/></a>
					</shiro:hasPermission>
					<%-- <shiro:hasPermission name="srv:user:del">
						<a href="${ctx}/sys/user/srvDelete?id=${user.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> <spring:message code='global.delete'/></a>
					</shiro:hasPermission> --%>
					<shiro:hasPermission name="srv:user:frozen">
						<c:if test="${user.loginFlag == '0'}"> <!-- 0:<spring:message code='global.fail'/>  -->
							<a href="${ctx}/sys/user/srvEnable?id=${user.id}" onclick="return confirmx('确认要<spring:message code='global.effective'/>吗？', this.href)" class="btn btn-info btn-xs"><i class="fa fa-power-off"></i> <spring:message code='global.effective'/></a>
						</c:if>
						<c:if test="${user.loginFlag == '1'}"> <!-- 1:<spring:message code='global.effective'/>  -->
							<a href="${ctx}/sys/user/srvDisable?id=${user.id}" onclick="return confirmx('确认要<spring:message code='global.fail'/>吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-power-off"></i> <spring:message code='global.fail'/></a>
						</c:if>
					</shiro:hasPermission>
					<shiro:hasPermission name="orgip:orgIp:list">
						<c:if test="${fns:getUser().platform == '2' }"> <!-- 2运营人员配置IP  -->
							<a href="#" style="color:#fff" onclick="top.openOneTab('${ctx}/orgip/orgIp/list?serviceId=${user.srvProInfo.servicerNo}','白名单',false)" class="btn label-inverse btn-xs" >
							<i class="fa fa-location-arrow"></i> 白名单</a>
						</c:if>
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