<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ include file="/webpage/include/head.jsp" %>
<%@page import="priv.bigant.aotomatic.common.JspTool" %>
<c:if test="${dynamicJspListConfigVO.extendDynamicJsFileName!=null}">
    <script src="${ctxStatic}/dynamicjs/${dynamicJspListConfigVO.extendDynamicJsFileName}.js" type="text/javascript"></script>
</c:if>
<html>
<head>
    <title>${dynamicManageName}</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:forEach items="${dynamicJspListConfigVO.laydateJsList}" var="laydateJs">
            ${laydateJs}
            </c:forEach>
        });

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
                    <li>
                        <a href="#">选项1</a>
                    </li>
                    <li>
                        <a href="#">选项2</a>
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
                    <%--@elvariable id="${dynamicJspListConfigVO.dynamicModelName}" type="apple"--%>
                    <form:form id="searchForm" modelAttribute="${dynamicJspListConfigVO.dynamicModelName}" action="${ctx}/${dynamicJspListConfigVO.controllerQueryPath}" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.current}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.size}"/>
                        <c:forEach items="${dynamicJspListConfigVO.hiddenList}" var="hiddenEle">
                            <form:hidden path="${hiddenEle.attribute['path'].attrCovertValue}"/>
                        </c:forEach>
                        <%--<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/>--%><!-- 支持排序 -->
                        <div class="form-group">

                            <c:forEach items="${dynamicJspListConfigVO.queryCondList}" var="queryCondEle">
                                <c:choose>
                                    <c:when test="${queryCondEle.ifFormElement == true && queryCondEle.elementCode eq JspTool.getLsc().getElementInput()}"><!-- 如果 -->
                                        <form:input path="${queryCondEle.attribute['path'].attrCovertValue}" htmlEscape="${queryCondEle.attribute['htmlEscape'].attrCovertValue}"
                                                    maxlength="${queryCondEle.attribute['maxlength'].attrCovertValue}" class="${queryCondEle.attribute['class'].attrCovertValue}"/>
                                    </c:when>
                                    <c:when test="${queryCondEle.ifFormElement == true && queryCondEle.elementCode eq JspTool.getLsc().getElementSelect()}"><!-- 如果 -->

                                        <form:select path="${queryCondEle.attribute['path'].attrCovertValue}" class="form-control m-b">
                                            <c:forEach items="${queryCondEle.childList}" var="selectchild">
                                                <c:if test="${selectchild.attribute['items']==null}">
                                                    <form:option value="" label="请选择"/>
                                                </c:if>
                                                <c:if test="${selectchild.attribute['items'].selectDataCollection!=null}">
                                                    <form:options items="${selectchild.attribute['items'].selectDataCollection}" htmlEscape="false"/>
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
                        <c:if test="${dynamicJspListConfigVO.toolButtonTagHtml!=null}">
                            ${dynamicJspListConfigVO.toolButtonTagHtml}
                        </c:if>
                        <c:forEach items="${dynamicJspListConfigVO.toolButtonList}" var="toolButton">
                            <c:choose>
                                <c:when test="${toolButton.elementCode eq JspTool.getLsc().getElementTagAddrow()}">
                                    <table:addRow dynamicAttributes="${toolButton.attribute}"/><!-- 增加按钮 -->
                                </c:when>
                                <c:when test="${toolButton.elementCode eq JspTool.getLsc().getElementTagDelrow()}">
                                    <table:delRow dynamicAttributes="${toolButton.attribute}"/><!-- 增加按钮 -->
                                </c:when>
                                <c:otherwise>
                                    ${toolButton.html}
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>

                    <div class="pull-right">
                        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()"><i class="fa fa-search"></i> 查询</button>
                        <button class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="reset()"><i class="fa fa-refresh"></i> 重置</button>
                    </div>
                </div>
            </div>

            <!-- 表格 -->
            <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <c:if test="${dynamicJspListConfigVO.ifShowCheckBox}">
                        <th><input type="checkbox" class="i-checks"/></th>
                    </c:if>
                    <c:forEach items="${dynamicJspListConfigVO.tableDataList}" var="tableThEle">
                        <c:choose>
                            <c:when test="${tableThEle.ifFormElement == false}">
                                <th>
                                        ${tableThEle.getFieldVal()}
                                </th>
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

                <%--<tengjie:tjforEach items="${page.records}" var="${dynamicJspListConfigVO.dynamicModelName}" varStatus="status1">--%>
                <c:forEach items="${page.records}" var="entity">

                    <tr>
                        <c:if test="${dynamicJspListConfigVO.ifShowCheckBox}">
                            <c:set var="dynamicModelNameEval"><spring:eval expression="${dynamicJspListConfigVO.dynamicModelName}.id"/></c:set>
                            <td><input type="checkbox" id="${dynamicModelNameEval}" class="i-checks"></td>
                        </c:if>
                        <c:forEach items="${dynamicJspListConfigVO.tableDataList}" var="tableDataEle">
                            <c:choose>
                                <c:when test="${tableDataEle.elementCode eq x.getLsc().getElementAtagButton()}">
                                    <td>
                                        <spring:eval expression="JspTool.parseATagUrl(tableDataEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"/><!-- 这里不在程序中解析url是因为，这个每行记录的url必须依靠每行的数据来解析 -->
                                    </td>
                                </c:when>
                                <c:when test="${tableDataEle.attribute['tjText'].ifStatic }"><!-- ifStatic=true，表明是手工输入的静态字符串 -->
                                    <td>
                                        <spring:eval expression="${tableDataEle.attribute['tjText'].attrCovertValue}"/><!-- 这里的attrCovertValue实际已经在后台解析完结果传过来的 -->
                                    </td>
                                </c:when>

                                <c:when test="${tableDataEle.attribute['tjText'].ifContainElExpress }">
                                    <td>
                                        <spring:eval expression="${tableDataEle.attribute['tjText'].attrCovertValue}"/>
                                    </td>
                                </c:when>
                                <c:when test="${tableDataEle.elementCode ne JspTool.getLsc().getElementTd()}"><!-- 不是td类型，都是要简单的数据展示，而是控件，为可编辑表格做准备 -->
                                    <td>
                                        <c:choose>
                                            <c:when test="${tableDataEle.ifFormElement == false}"><!-- 如果 -->
                                                <a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i
                                                        class="fa fa-edit"></i>修改</a>
                                                <%--<spring:eval expression="JspTool.parseElForEle(tableDataEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"/>--%>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </c:when>

                                <c:when test="${tableDataEle.attribute['tjText'].attrName eq 'createBy'||tableDataEle.attribute['tjText'].attrName eq 'updateBy' }"><!-- 对于这两个createby和updateBy 是baseUser类型，需要根据其id查找项目对应user对象并返回name的值 -->
                                    <td>
                                        <a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i class="fa fa-edit"></i>修改</a>
                                            <%--
                                                                                    <spring:eval expression="JspTool.getNameByUser(${dynamicJspListConfigVO.dynamicModelName}[tableDataEle.attrMap['tjText'].attrName])"/>
                                            --%>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                            ${tableDataEle.getVal(entity)}
                                    </td>
                                    <%--                                    <td>
                                                                            <spring:eval expression="${dynamicJspListConfigVO.dynamicModelName}[tableDataEle.attrMap['tjText'].attrName]"/>
                                                                        </td>--%>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${fn:length(dynamicJspListConfigVO.tableButtonList) > 0}">
                            <td>
                                <c:forEach items="${dynamicJspListConfigVO.tableButtonList}" var="tableButtonEle">
                                    <c:choose>
                                        <c:when test="${not empty tableButtonEle.showHideElExpress}">
                                            <c:set var="showOrHide"><spring:eval expression='${tableButtonEle.showHideElExpress}'/></c:set>
                                            <c:if test="${showOrHide}"><!-- 判断元素是否隐藏 -->
                                                <c:if test="${not empty tableButtonEle.shiroHasPermission}">
                                                    <shiro:hasPermission name="tableButtonEle.shiroHasPermission">
                                                        <a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i
                                                                class="fa fa-edit"></i>修改</a>
                                                        <%--
                                                                                                                <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
                                                        --%>
                                                    </shiro:hasPermission>
                                                </c:if>
                                                <c:if test="${ empty tableButtonEle.shiroHasPermission}">
                                                    <a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i
                                                            class="fa fa-edit"></i>修改</a>
                                                    <%--
                                                                                                        <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
                                                    --%>
                                                </c:if>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${not empty tableButtonEle.shiroHasPermission}">
                                                <shiro:hasPermission name="tableButtonEle.shiroHasPermission">
                                                    <%--<a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i
                                                            class="fa fa-edit"></i>修改</a>--%>
                                                    ${tableButtonEle.html}
                                                    <%--
                                                                                                        <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
                                                    --%>
                                                </shiro:hasPermission>
                                            </c:if>
                                            <c:if test="${ empty tableButtonEle.shiroHasPermission}">
                                                <%--<a href="#" htmlEscape="false" onclick="openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')" class="btn btn-info btn-xs"><i
                                                        class="fa fa-edit"></i>修改</a>--%>
                                                ${tableButtonEle.html}

                                                <%--
                                                                                                <spring:eval expression="JspTool.parseATagUrl(tableButtonEle.html, ${dynamicJspListConfigVO.dynamicModelName},pageContext.request)"></spring:eval>
                                                --%>
                                            </c:if>

                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <%--
                                </tengjie:tjforEach>
                --%>
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