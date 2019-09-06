<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>${dynamicManageName}</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			<c:forEach items="${dynamicJspListConfigVO.laydateJsList}" var="laydateJs">
			   ${laydateJs}
			 </c:forEach>
		});
		 
		 <c:forEach items="${dynamicJspListConfigVO.jsFunctionList}" var="jsContent">
		   ${jsContent}
		 </c:forEach>
	</script>

	<style type="text/css">
	 <c:forEach items="${dynamicJspListConfigVO.cssClassList}" var="cssClass">
	   ${cssClass}
	 </c:forEach>
		
	</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>${dynamicManageName}列表 </h5>
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
	<form:form id="searchForm"  modelAttribute="${dynamicJspListConfigVO.dynamicModelName}" action="${ctx}/${dynamicJspListConfigVO.controllerQueryPath}" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">

	    <c:forEach items="${dynamicJspListConfigVO.queryCondList}" var="queryCondEle">
              <c:choose>
	               	<c:when test="${queryCondEle.ifFormElement == true && queryCondEle.elementCode eq JspTool.getLsc().getElementInput()}"><!-- 如果 -->
	               		<form:input path="${queryCondEle.attrMap['path'].attrCovertValue}" htmlEscape="${queryCondEle.attrMap['htmlEscape'].attrCovertValue}" maxlength="${queryCondEle.attrMap['maxlength'].attrCovertValue}" class="${queryCondEle.attrMap['class'].attrCovertValue}" />
	               	</c:when>
	                <c:when test="${queryCondEle.ifFormElement == true && queryCondEle.elementCode eq JspTool.getLsc().getElementSelect()}"><!-- 如果 -->
	               	  
	               	   <form:select path="${queryCondEle.attrMap['path'].attrCovertValue}"  class="form-control m-b">
					     <c:forEach items="${queryCondEle.childList}" var="selectchild">
					      <c:if test="${selectchild.attrMap['items']==null}">
					        <form:option value="" label="请选择"/>
					      </c:if>
					      <c:if test="${selectchild.attrMap['items'].selectDataCollection!=null}">
					        <form:options items="${selectchild.attrMap['items'].selectDataCollection}"  htmlEscape="false"/>
					      </c:if>
					      </c:forEach>
			      	    </form:select>
	            
	               	</c:when>
	               	<c:when test="${queryCondEle.ifFormElement == false}">
	               		${queryCondEle.html}
	               	</c:when>	              
               </c:choose>          
         </c:forEach>
             
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
		     <c:forEach items="${dynamicJspListConfigVO.toolButtonList}" var="toolButton">
		       <c:choose>
	             <c:when test="${toolButton.elementCode eq JspTool.getLsc().getElementTagAddrow()}">
	               	<table:addRow  dynamicAttributes="${toolButton.attrMap}"></table:addRow><!-- 增加按钮 -->${tableThEle.html} 
	             </c:when>
	             <c:when test="${toolButton.elementCode eq JspTool.getLsc().getElementTagDelrow()}">
	               	<table:delRow  dynamicAttributes="${toolButton.attrMap}"></table:delRow><!-- 增加按钮 -->${tableThEle.html} 
	             </c:when>
	             <c:otherwise>
	               		${toolButton.html} 
	             </c:otherwise>	
               </c:choose>      
		     </c:forEach>
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
			 <c:if test="${dynamicJspListConfigVO.ifShowCheckBox}">
			  <th> <input type="checkbox" class="i-checks"></th>
			 </c:if>
              <c:forEach items="${dynamicJspListConfigVO.tableThList}" var="tableThEle">
               <c:choose>
	               	<c:when test="${tableThEle.ifFormElement == false}">
	               		${tableThEle.html} 
	               	</c:when>
               </c:choose>          
              </c:forEach>
               <c:if test="${fn:length(dynamicJspListConfigVO.tableButtonList) > 0}">
               <th>
               操作
               </th>
               </c:if>
			</tr>
		</thead>
		<tbody>
		
		<tengjie:tjforEach items="${page.list}"  var="${dynamicJspListConfigVO.dynamicModelName}"  varStatus="status1">
		<tr>
		    <c:if test="${dynamicJspListConfigVO.ifShowCheckBox}">
		      <c:set var="dynamicModelNameEval"><spring:eval expression="${dynamicJspListConfigVO.dynamicModelName}.id"></spring:eval></c:set>
		      <td> <input type="checkbox" id="${dynamicModelNameEval}" class="i-checks"></td>
			</c:if>
		    <c:forEach items="${dynamicJspListConfigVO.tableDataList}" var="tableDataEle">
              <c:choose>
                	<c:when test="${tableDataEle.elementCode eq JspTool.getLsc().getElementAtagButton()}">
	               		<td>
	               		  <spring:eval expression="JspTool.parseATagUrl(tableDataEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
                        </td>
	               	</c:when>
	               	<c:when test="${tableDataEle.attrMap['tjText'].ifStatic }"><!-- ifStatic=true，表明是手工输入的静态字符串 -->
	               		<td>
	               		   <spring:eval expression="${tableDataEle.attrMap['tjText'].attrCovertValue}"></spring:eval><!-- 这里的attrCovertValue实际已经在后台解析完结果传过来的 -->
                        </td>
	               	</c:when>
	               	
	               <c:when test="${tableDataEle.attrMap['tjText'].ifContainElExpress }">
	               		<td>
	               		   <spring:eval expression="${tableDataEle.attrMap['tjText'].attrCovertValue}"></spring:eval>
		                </td>
	               	</c:when>
	               	<c:when test="${tableDataEle.elementCode ne JspTool.getLsc().getElementTd()}"><!-- 不是td类型，都是要简单的数据展示，而是控件，为可编辑表格做准备 -->
	               		<td>
	               		    <c:choose>
	               	          <c:when test="${tableDataEle.ifFormElement == false}"><!-- 如果 -->
	               		         <spring:eval expression="JspTool.parseElForEle(tableDataEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
	                      	  </c:when>
	                      	</c:choose>
		                </td>
	               	</c:when>
	               
	               	<c:when test="${tableDataEle.attrMap['tjText'].attrName eq 'createBy'||tableDataEle.attrMap['tjText'].attrName eq 'updateBy' }"><!-- 对于这两个createby和updateBy 是baseUser类型，需要根据其id查找项目对应user对象并返回name的值 -->
	               		<td>
	               		  <spring:eval expression="JspTool.getNameByUser(${dynamicJspListConfigVO.dynamicModelName}[tableDataEle.attrMap['tjText'].attrName])"></spring:eval>
		                </td>
	               	</c:when>
	               	<c:otherwise>
	               	    <td>
                           <spring:eval expression="${dynamicJspListConfigVO.dynamicModelName}[tableDataEle.attrMap['tjText'].attrName]"></spring:eval>
                        </td>
                    </c:otherwise>               
               </c:choose>
             </c:forEach>
             
             <c:if test="${fn:length(dynamicJspListConfigVO.tableButtonList) > 0}">
               <td>
                <c:forEach items="${dynamicJspListConfigVO.tableButtonList}" var="tableButtonEle">
                    <c:choose>
	                  <c:when test="${not empty tableButtonEle.showHideElExpress}">
	                     <c:set var="showOrHide"><spring:eval expression='${tableButtonEle.showHideElExpress}'></spring:eval></c:set>
	                     <c:if test="${showOrHide}"><!-- 判断元素是否隐藏 -->
	                         <c:if test="${not empty tableButtonEle.shiroHasPermission}">
	                           <shiro:hasPermission name="tableButtonEle.shiroHasPermission">
				                 <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
				               </shiro:hasPermission>
				             </c:if>
                             <c:if test="${ empty tableButtonEle.shiroHasPermission}">
				                 <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval> 
				              </c:if>
                         </c:if>
	                  </c:when>
	                  <c:otherwise>
	                         <c:if test="${not empty tableButtonEle.shiroHasPermission}">
	                           <shiro:hasPermission name="tableButtonEle.shiroHasPermission">
				                 <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
				               </shiro:hasPermission>
				              </c:if>
                             <c:if test="${ empty tableButtonEle.shiroHasPermission}">
				                 <spring:eval expression="xJspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
				             </c:if>
	               	     
                      </c:otherwise>
	                </c:choose>
                </c:forEach>
               </td>
             </c:if>
        </tr>
        </tengjie:tjforEach>
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