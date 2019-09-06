<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>国家管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>国家列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="country" action="${ctx}/country/country/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span><spring:message code='tbCountry.countryCode'/>：</span>
				<form:input path="countryCode" htmlEscape="false" maxlength="16"  class=" form-control input-sm"/>
			<span><spring:message code='tbCountry.countryName'/>：</span>
				<form:input path="countryName" htmlEscape="false" maxlength="16"  class=" form-control input-sm"/>
			<span><spring:message code='global.desc'/> ：</span>
				<form:input path="countryDesc" htmlEscape="false" maxlength="200"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="country:country:add">
				<table:addRow url="${ctx}/country/country/form" title="国家"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="country:country:edit">
			    <table:editRow url="${ctx}/country/country/form" title="国家" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="country:country:del">
				<table:delRow url="${ctx}/country/country/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="country:country:import">
				<table:importExcel url="${ctx}/country/country/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="country:country:export">
	       		<table:exportExcel url="${ctx}/country/country/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
	       <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="<spring:message code='global.refresh'/>"><i class="glyphicon glyphicon-repeat"></i> <spring:message code='global.refresh'/></button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> <spring:message code='global.query'/>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> <spring:message code='global.reset'/>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th  class="sort-column countryCode"><spring:message code='tbCountry.countryCode'/></th>
				<th  class="sort-column countryName"><spring:message code='tbCountry.countryName'/></th>
				<th  class="sort-column countryDesc"><spring:message code='tbCountry.validateFlag'/></th>
				<th  class="sort-column countryDesc"><spring:message code='global.desc'/> </th>
				<th  class="sort-column createBy.id"><spring:message code='global.create_by'/></th>
				<th  class="sort-column createDate"><spring:message code='tbCountry.createDate'/></th>
		
				<th><spring:message code='global.opt'/> </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="country">
			<tr>
				<td> <input type="checkbox" id="${country.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('<spring:message code='country.view'/>', '${ctx}/country/country/form?id=${country.id}','800px', '500px')">
					${country.countryCode}
				</a></td>
				<td>
					${country.countryName}
				</td>
				<td>
					${fns:getDictLabel(country.validateFlag, 'validate_flag', '')}
				</td>
				
				<td>
					${country.countryDesc}
				</td>
				<td>
					${fns:getUserById(country.createBy.id).name}
				</td>
				<td>
					<fmt:formatDate value="${country.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${country.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${country.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td>
					<shiro:hasPermission name="country:country:view">
						<a href="#" onclick="openDialogView('<spring:message code='country.view'/>', '${ctx}/country/country/form?id=${country.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> <spring:message code='global.view'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="country:country:edit">
    					<a href="#" onclick="openDialog('<spring:message code='country.update'/>', '${ctx}/country/country/form?id=${country.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> <spring:message code='global.update'/></a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="country:country:del">
						<a href="${ctx}/country/country/delete?id=${country.id}" onclick="return confirmx('确认要删除该国家吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> <spring:message code='global.delete'/></a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>
