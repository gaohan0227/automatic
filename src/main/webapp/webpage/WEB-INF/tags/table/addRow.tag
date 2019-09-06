<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<%@ attribute name="dynamicAttributes" type="java.util.Map" required="false" %>
<%@ attribute name="url" type="java.lang.String" required="false" %><%--默认：rtexprvalue="true" --%>
<%@ attribute name="title" type="java.lang.String" required="false" %>
<%@ attribute name="width" type="java.lang.String" required="false" %>
<%@ attribute name="height" type="java.lang.String" required="false" %>
<%@ attribute name="target" type="java.lang.String" required="false" %>
<%@ attribute name="label" type="java.lang.String" required="false" %>
<%@ attribute name="needTarget" type="java.lang.String" required="false" %>
<%@ attribute name="ifSelect" type="java.lang.String" required="false" %>
<!-- target通常不用传，即默认指向最底层的frame中的窗口，但是一旦指定needTarget="true"，则会找当前弹出窗口的上一级别窗口，他可能也是一个弹出窗口，而不是底层窗口 -->
<button class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="left" onclick="add()">
    <i class="fa fa-plus">
    </i>
    <spring:message code="global.delete"/>
</button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>


<script type="text/javascript">
    function add() {
        var destTraget = "${target}";
        if ("true" == "${needTarget}") {
            destTraget = findCurrPopDialogIframeId();
        }
        if ("true" == "${ifSelect}") {
            openDialogSelect("<spring:message code='global.add'/>" + '${title}', "${url}", "${width==null?'800px':width}", "${height==null?'500px':height}", destTraget);
        } else {
            openDialog("<spring:message code='global.add'/>" + '${title}', "${url}", "${width==null?'800px':width}", "${height==null?'500px':height}", destTraget);

        }
    }
</script>