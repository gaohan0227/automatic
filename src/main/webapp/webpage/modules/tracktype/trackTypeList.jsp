<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>足迹类型定义管理</title>
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
		<h5>足迹类型定义列表 </h5>
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
	<form:form id="searchForm" modelAttribute="trackType" action="${ctx}/tracktype/trackType/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

			<span>类型代码：</span>
				<form:input path="typeCode" htmlEscape="false" maxlength="2"  class=" form-control input-sm"/>


			<span>类型名称：</span>
				<form:input path="typeName" htmlEscape="false" maxlength="20"  class=" form-control input-sm"/>

		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		        <table:addRow url="${ctx}/tracktype/trackType/form" title="添加足迹类型定义"></table:addRow><!-- 增加按钮 -->
	
			    <table:editRow url="${ctx}/tracktype/trackType/form" title="修改足迹类型定义" id="contentTable"></table:editRow><!-- 编辑按钮 -->

				<table:delRow url="${ctx}/tracktype/trackType/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
	
        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>
		
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()" ><i class="fa fa-refresh"></i> 重置</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
			    <th> <input type="checkbox" class="i-checks"></th>
				<th  >类型名称</th>
				<th  >类型代码</th>
				<th  >业务表字段列表</th>
				<th  >多表关联字段映射</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trackType">
			<tr>
			    <td> <input type="checkbox" id="${trackType.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看足迹类型定义', '${ctx}/tracktype/trackType/form?id=${trackType.id}','800px', '500px')">
				    ${trackType.typeName}
				</a></td>
				<td>
				    ${trackType.typeCode}
				</td>
				<td>
				    ${trackType.bussFieldNames}
				</td>
				<td>
				    ${trackType.bussRelationMapped}
				</td>
				<td>

				<a href="#" onclick="openDialogView('查看足迹类型定义', '${ctx}/tracktype/trackType/form?id=${trackType.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
	
    			<a href="#" onclick="openDialog('修改足迹类型定义', '${ctx}/tracktype/trackType/form?id=${trackType.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    			
				<a href="${ctx}/tracktype/trackType/delete?id=${trackType.id}" onclick="return confirmx('确认要删除该足迹类型定义吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
	
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