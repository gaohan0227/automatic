<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>收藏类型管理管理</title>
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
		<h5>收藏类型管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="favoriteType" action="${ctx}/favoritetype/favoriteType/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		        <table:addRow url="${ctx}/favoritetype/favoriteType/form" title="收藏类型管理"></table:addRow><!-- 增加按钮 -->
	
			    <table:editRow url="${ctx}/favoritetype/favoriteType/form" title="收藏类型管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->

				<table:delRow url="${ctx}/favoritetype/favoriteType/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
	
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
				<th  >类型代码</th>
				<th  >类型名称</th>
				<th  >业务表名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="favoriteType">
			<tr>
			    <td> <input type="checkbox" id="${favoriteType.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看收藏类型管理', '${ctx}/favoritetype/favoriteType/form?id=${favoriteType.id}','800px', '500px')">
				    ${favoriteType.typeCode}
				</a></td>
				<td>
				    ${favoriteType.typeName}
				</td>
				<td>
				    ${favoriteType.bussTableNames}
				</td>
				<td>

				<a href="#" onclick="openDialogView('查看收藏类型管理', '${ctx}/favoritetype/favoriteType/form?id=${favoriteType.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
	
    			<a href="#" onclick="openDialog('修改收藏类型管理', '${ctx}/favoritetype/favoriteType/form?id=${favoriteType.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    			
				<a href="${ctx}/favoritetype/favoriteType/delete?id=${favoriteType.id}" onclick="return confirmx('确认要删除该收藏类型管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
	
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