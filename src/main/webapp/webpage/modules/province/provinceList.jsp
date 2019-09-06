<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>省管理</title>
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
		<h5>省列表 </h5>
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
	<form:form id="searchForm" modelAttribute="province" action="${ctx}/province/province/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span><spring:message code='tbProvince.provinceCode'/>：</span>
				<form:input path="provinceCode" htmlEscape="false" maxlength="16"  class=" form-control input-sm"/>
			<span><spring:message code='tbProvince.provinceName'/>：</span>
				<form:input path="provinceName" htmlEscape="false" maxlength="16"  class=" form-control input-sm"/>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="province:province:add">
				<table:addRow url="${ctx}/province/province/form" title="省"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="province:province:edit">
			    <table:editRow url="${ctx}/province/province/form" title="省" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="province:province:del">
				<table:delRow url="${ctx}/province/province/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="province:province:import">
				<table:importExcel url="${ctx}/province/province/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="province:province:export">
	       		<table:exportExcel url="${ctx}/province/province/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column provinceCode">ʡ����</th>
				<th  class="sort-column provinceName">ʡ���</th>
				<th  class="sort-column parent">�ϼ�����</th>
				<th  class="sort-column a.create_by"><spring:message code='global.create_by'/></th>
				<th  class="sort-column a.create_date"><spring:message code='tbProvince.createDate'/></th>
				<th  class="sort-column updateBy.id">�޸���</th>
				<th  class="sort-column updateDate">�޸�ʱ��</th>
				<th><spring:message code='global.opt'/> </th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="province">
			<tr>
				<td> <input type="checkbox" id="${province.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('<spring:message code='province'/>', '${ctx}/province/province/form?id=${province.id}','800px', '500px')">
					${province.provinceCode}
				</a></td>
				<td>
					${province.provinceName}
				</td>
				<td>
					${province.parent}
				</td>
				<td>
					${province.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${province.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${province.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${province.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="province:province:view">
						<a href="#" onclick="openDialogView('<spring:message code='province'/>', '${ctx}/province/province/form?id=${province.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> <spring:message code='global.view'/></a>
					</shiro:hasPermission>
					<shiro:hasPermission name="province:province:edit">
    					<a href="#" onclick="openDialog('修改省', '${ctx}/province/province/form?id=${province.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> <spring:message code='global.update'/></a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="province:province:del">
						<a href="${ctx}/province/province/delete?id=${province.id}" onclick="return confirmx('确认要删除该省吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> <spring:message code='global.delete'/></a>
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
