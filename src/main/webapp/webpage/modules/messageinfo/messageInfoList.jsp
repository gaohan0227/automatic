<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>消息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
	        laydate({
	            elem: '#CreateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
	        laydate({
	            elem: '#endCreateDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	        });
					
		
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>消息管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="messageInfo" action="${ctx}/messageInfo/messageInfo/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

			<span>消息标题：</span>
				<form:input path="title" htmlEscape="false" maxlength="64"  class=" form-control input-sm"/>


			<span>消息类型：</span>
				<form:select path="messageTypeId"  class="form-control m-b">
					<form:option value="" label=""/>
					<c:forEach items="${fns:callTableByCondList('messageType','id',messageInfo.messageTypeId)}" var="jtype">
					 <option  value="${jtype.id}"htmlEscape="false">${jtype.messageTypeName} </option>
					  </c:forEach>
					
				</form:select>


			<span>推送状态：</span>
				<form:select path="pushState"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getPushStateMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>是否全局消息：</span>
				<form:select path="ifGlobalMessage"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getIfGlobalMessageMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>创建时间：</span>
				<input id="createDate" name="createDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${messageInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/> - 
				<input id="endCreateDate" name="endCreateDate" type="text" maxlength="20" class="laydate-icon form-control layer-date input-sm"
					value="<fmt:formatDate value="${messageInfo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>

		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="messageInfo:messageInfo:add">
				<table:addRow url="${ctx}/messageInfo/messageInfo/form" title="消息管理"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="messageInfo:messageInfo:edit">
			    <table:editRow url="${ctx}/messageInfo/messageInfo/form" title="消息管理" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="messageInfo:messageInfo:del">
				<table:delRow url="${ctx}/messageInfo/messageInfo/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="messageInfo:messageInfo:import">
				<table:importExcel url="${ctx}/messageInfo/messageInfo/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="messageInfo:messageInfo:export">
	       		<table:exportExcel url="${ctx}/messageInfo/messageInfo/export"></table:exportExcel><!-- 导出按钮 -->
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
				<th  class="sort-column title">消息标题</th>
				<th  class="sort-column createDate">创建时间</th>
				<th  class="sort-column ifGlobalMessage">是否全局消息</th>
				<th  class="sort-column messageTypeName">类型名称</th>
				<th  class="sort-column pushState">推送状态</th>
				<th  class="sort-column hasRead">是否读</th>
				<th  class="sort-column receiveId">接收人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageInfo">
			<tr>
				<td> <input type="checkbox" id="${messageInfo.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看消息管理', '${ctx}/messageInfo/messageInfo/form?id=${messageInfo.id}','800px', '500px')">
					${messageInfo.title}
				</a></td>
				<td>
					<fmt:formatDate value="${messageInfo.createDate}"  pattern="yyyy-MM-dd"  />
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getIfGlobalMessageMap',messageType.ifGlobalMessage)}
				</td>
				<td>
					${messageInfo.messageTypeName}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getPushStateMap',messageInfo.pushState)}
				</td>
				<td>
					${messageInfo.hasRead}
				</td>
				<td>
					${messageInfo.receiveId}
				</td>
				<td>
					<shiro:hasPermission name="messageInfo:messageInfo:view">
						<a href="#" onclick="openDialogView('查看消息管理', '${ctx}/messageInfo/messageInfo/form?id=${messageInfo.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="messageInfo:messageInfo:edit">
    					<a href="#" onclick="openDialog('修改消息管理', '${ctx}/messageInfo/messageInfo/form?id=${messageInfo.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="messageInfo:messageInfo:del">
						<a href="${ctx}/messageInfo/messageInfo/delete?id=${messageInfo.id}" onclick="return confirmx('确认要删除该消息管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
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