<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>卡券管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		
	     function shanchu(){
	     }
	     
	     function tianjia(){
	     }
	     
	     function daochu(){
	     }
	     
	     function daoru(){
	     }
	     
	     function shanchu(){
	     }
	     
	     function xiugai(){
	     }
	     
	     function chakan(){
	     }
	     
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>卡券管理列表 </h5>
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
	<form:form id="searchForm" modelAttribute="coupon" action="${ctx}/coupon/coupon/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

			<span>卡券名称：</span>
				<form:input path="couponName" htmlEscape="false" maxlength="100"  class=" form-control input-sm"/>


			<span>卡券状态：</span>
				<form:select path="couponStatus"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getCouponStatusMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>卡券类型：</span>
				<form:select path="couponType"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getCouponTypeMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>发放类型：</span>
				<form:select path="handOutType"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getHandOutTypeMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>使用有效期类型：</span>
				<form:select path="useIndateType"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getUseIndateTypeMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>发放周期：</span>
				<form:select path="handOutCyclicity"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getHandOutCyclicityMap','')}"  htmlEscape="false"/>
				</form:select>


			<span>不可用商品规则：</span>
				<form:select path="unavailableGoodsRule"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:callGlobalStaticDict('getUnavailableGoodsRuleMap','')}"  htmlEscape="false"/>
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
               <table:delRow url="${ctx}/coupon/coupon/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
               <table:addRow url="${ctx}/coupon/coupon/form" title="卡券管理"></table:addRow><!-- 增加按钮 --> 
               <table:exportExcel url="${ctx}/coupon/coupon/export"></table:exportExcel><!-- 导出按钮 -->
               <table:importExcel url="${ctx}/coupon/coupon/import"></table:importExcel><!-- 导入按钮 -->
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
				<th  >卡券名称</th>
				<th  >卡券状态</th>
				<th  >卡券类型</th>
				<th  >发放周期</th>
				<th  >发放类型</th>
				<th  >不可用商品规则</th>
				<th  >使用有效期类型</th>
				<th  >使用结束时间</th>
				<th  >天有效</th>
				<th  >满多少</th>
				<th  >减少多少</th>
				<th  >优惠券图片</th>
				<th  >最高折扣金额</th>
				<th  >抵扣金额</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coupon">
			<tr>
			    <td> <input type="checkbox" id="${coupon.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看卡券管理', '${ctx}/coupon/coupon/form?id=${coupon.id}','800px', '500px')">
				    ${coupon.couponName}
				</a></td>
				<td>
				    ${fns:callGlobalStaticDict('getCouponStatusMap',coupon.couponStatus)}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getCouponTypeMap',coupon.couponType)}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getHandOutCyclicityMap',coupon.handOutCyclicity)}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getHandOutTypeMap',coupon.handOutType)}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getUnavailableGoodsRuleMap',coupon.unavailableGoodsRule)}
				</td>
				<td>
				    ${fns:callGlobalStaticDict('getUseIndateTypeMap',coupon.useIndateType)}
				</td>
				<td>
					<fmt:formatDate value="${coupon.useEndDate}"  pattern="yyyy-MM-dd"  />
				</td>
				<td>
				    ${coupon.ndayIndate}
				</td>
				<td>
				    ${coupon.totalMoney}
				</td>
				<td>
				    ${coupon.paybackMoney}
				</td>
				<td>
				     <img  src="${coupon.picUrl}" height="30px" />
					
				</td>
				<td>
				    ${coupon.maxDiscountMoney}
				</td>
				<td>
				    ${coupon.deductionMoney}
				</td>
				<td>
               <a href="${ctx}/coupon/coupon/delete?id=${coupon.id}" onclick="return confirmx('确认要删除该卡券管理吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
               <a href="#" onclick="openDialog('修改卡券管理', '${ctx}/coupon/coupon/form?id=${coupon.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
               <a href="#" onclick="openDialogView('查看卡券管理', '${ctx}/coupon/coupon/form?id=${coupon.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
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