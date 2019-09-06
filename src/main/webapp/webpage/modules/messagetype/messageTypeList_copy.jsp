<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息类型管理管理</title>
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
		<h5>消息类型管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="messageType" action="${ctx}/messagetype/messageType/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

			<span>类型名称：</span>
				<form:input path="messageTypeName" htmlEscape="false" maxlength="32"  class=" form-control input-sm"/>

		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">

				<table:addRow url="${ctx}/messagetype/messageType/form" title="消息类型管理"></table:addRow><!-- 增加按钮 -->
	
			    <table:editRow url="${ctx}/messagetype/messageType/form" title="消息类型管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->

				<table:delRow url="${ctx}/messagetype/messageType/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
	
			<shiro:hasPermission name="messageType:messageType:import">
				<table:importExcel url="${ctx}/messagetype/messageType/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="messageType:messageType:export">
	       		<table:exportExcel url="${ctx}/messagetype/messageType/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission>
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
				<th  class="sort-column messageTypeCode">类型代码</th>
				<th  class="sort-column messageTypeName">类型名称</th>
				<th  class="sort-column redirectUrl">跳转</th>
				<th  class="sort-column extendParamsDateIfTimestamp">扩展参数日期是否时分秒</th>
				<th  class="sort-column extendParams">扩展参数</th>
				<th  class="sort-column messageMaxLength">消息内容最大长度</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageType">
			<tr>
				<td> <input type="checkbox" id="${messageType.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看消息类型管理', '${ctx}/messagetype/messageType/form?id=${messageType.id}','800px', '500px')">
					${messageType.messageTypeCode}
				</a></td>
				<td>
					${messageType.messageTypeName}
				</td>
				<td>
					${messageType.redirectUrl}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getExtendParamsDateIfTimestampMap',messageType.extendParamsDateIfTimestamp)}
				</td>
				<td>
					${messageType.extendParams}
				</td>
				<td>
					${messageType.messageMaxLength}
				</td>
				<td>
	
						<a href="#" onclick="openDialogView('查看消息类型管理', '${ctx}/messagetype/messageType/form?id=${messageType.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
	
    					<a href="#" onclick="openDialog('修改消息类型管理', '${ctx}/messagetype/messageType/form?id=${messageType.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    			
						<a href="${ctx}/messagetype/messageType/delete?id=${messageType.id}" onclick="return confirmx('确认要删除该消息类型管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>

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