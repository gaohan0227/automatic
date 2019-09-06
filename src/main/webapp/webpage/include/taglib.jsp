<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="tengjie" uri="/WEB-INF/tlds/tengjie.tld" %>
<%@ taglib prefix="echarts" uri="/WEB-INF/tlds/echarts.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="table" tagdir="/WEB-INF/tags/table" %>
<%@ taglib prefix="up" tagdir="/WEB-INF/tags/up" %>
<%@ taglib prefix="t" uri="/menu-tags"%>

<%--<c:set var="ctx" value="${pageContext.request.contextPath}"/>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<%--ä¸è¿°æ¹æ³ä¸»è¦ä¸ºaddRow.tag\up:singleç­è¿ç§èªå®ä¹å½¢æåå¤çï¼ä¸ºäºèªå®ä¹å±æ§èªå¨æ å°å¹¶ä¸éè¿setèµå¼å°è¿äºtagæéè¦çå±æ§ä¸­--%>
<c:if test="${not empty dynamicAttributes}" >
  <c:forEach items="${dynamicAttributes}" var="dynamicAttribute">
    <c:if test="${StringUtils.isNotEmpty(dynamicAttribute.value.attrCovertValue)}" >
      <c:choose>
        <c:when test="${dynamicAttribute.key eq 'url'}">
           <tengjie:tjset var="${dynamicAttribute.key}" value="${JspTool.transferPageUrlELExpress(dynamicAttribute.value.attrCovertValue,null,pageContext.request)}"/>
        </c:when>
        <c:otherwise>
        <%--script>
        alert("${dynamicAttribute.key}"+"===="+"${dynamicAttribute.value.attrCovertValue}");
        </script--%>
           <tengjie:tjset var="${dynamicAttribute.key}" value="${dynamicAttribute.value.attrCovertValue}"/>
        </c:otherwise>	
      </c:choose>
     
    </c:if>
  </c:forEach>
</c:if>
<script>


function findLanguageMap(){
	return "${languageMap}"
}
//通过ajax方式在服务端调用el表达式，由于采用同步方式，在程序中可直接调用如下：
//var elexpress="JspTool.getEntityByEntityId('${entityName}','"+idArrays[i]+"')";
//var resultBean=parseElByAjax(elexpress);
function parseElByAjax(elExpress){
	var dataText=$.ajax({
	    url: "${ctx}/fnscall/parseElByAjax",
	    data: {elExpressStr: elExpress},
	    type: "POST",
	    async: false,
	    dataType: "json",
	}).responseText;
	var data = jQuery.parseJSON(dataText);
	if(data.status=="200"){
		if($.isEmptyObject(data.data)){
			alert("执行表达式"+elExpress+"获得的数据为空");
		}
		return data.data;
	}else{
		alert("执行表达式"+elExpress+"发生错误！");
		return {};
	}
	
}
function fnRequestFns(mymethodInfo,pCallBack){//
    var tUrl = '${ctx}/fnscall/callFnsMethod';
    var tParam = {
  	  methodInfo:mymethodInfo
    };
    $.ajax({
        url:tUrl,
        type:"post",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify(tParam),
        success:function(response){
      	
      	  if(pCallBack){

            pCallBack(response);
      	  }
        },
        error:function() {
            console.log('Ã¨Â°ÂÃ§ÂÂ¨fnsÃ¤Â¿Â¡Ã¦ÂÂ¯Ã¥Â¤Â±Ã¨Â´Â¥')
        }
    });
}
function addURLArg(url,arg,arg_val){
    var pattern=arg+'=([^&]*)';
    var replaceText=arg+'='+arg_val; 
    if(url.match(pattern)){
        var tmp='/('+ arg+'=)([^&]*)/gi';
        tmp=url.replace(eval(tmp),replaceText);
        return tmp;
    }else{ 
        if(url.match('[\?]')){ 
            return url+'&'+replaceText; 
        }else{ 
            return url+'?'+replaceText; 
        } 
    }
}
//判断函数是否存在
function isExitsFunction(funcName) {
	  try {
	    if (typeof(eval(funcName)) == "function") {
	      return true;
	    }
	  } catch(e) {}
	  return false;
}
</script>