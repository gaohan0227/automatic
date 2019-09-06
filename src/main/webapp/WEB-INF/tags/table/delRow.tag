<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="dynamicAttributes" type="java.util.Map" required="false"%>
<%@ attribute name="id" type="java.lang.String" required="false"%><%--contentTable,即list页面中 table id="contentTable"  --%>
<%@ attribute name="url" type="java.lang.String" required="false"%>
<%@ attribute name="label" type="java.lang.String" required="false"%><%-- label没用，使用 <spring:message code="global.delete" />--%>
<%@ attribute name="confirmTip" type="java.lang.String" required="false"%><%-- 确认提示信息 --%>
<button class="btn btn-white btn-sm" onclick="deleteAll()" data-toggle="tooltip" data-placement="top">
	<i class="fa fa-trash-o"></i>
	<spring:message code="global.delete" />
</button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
$(document).ready(function() {
    $('#${id} thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	  $('#${id} tbody tr td input.i-checks').iCheck('check');
    	});

    $('#${id} thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	  $('#${id} tbody tr td input.i-checks').iCheck('uncheck');
    	});
    
});

	function deleteAll(){

		// var url = $(this).attr('data-url');
		  var str="";
		  var ids="";
		  $("#${id} tbody tr td input.i-checks:checkbox").each(function(){
		    if(true == $(this).is(':checked')){
		      str+=$(this).attr("id")+",";
		    }
		  });
		  if(str.substr(str.length-1)== ','){
		    ids = str.substr(0,str.length-1);
		  }
		  if(ids == ""){
			top.layer.alert('<spring:message code="global.edit.selectone"/>', {icon: 0, title:'<spring:message code="global.warn"/>'});
			return;
		  }
		/* 	top.layer.confirm('<spring:message code="global.edit.onlyone"/>', {icon: 3, title:'<spring:message code="global.warn"/>'}, function(index){
			window.location = "${url}?ids="+ids;
		    top.layer.close(index); */
			var title = " ${confirmTip==null?'':confirmTip}";
			top.layer.confirm('确认要批量删除'+title+'数据吗?', {icon: 3, title:'系统提示'}, function(index){
				var url = '${url}';
			    if(url.indexOf("?")>=0)
			    	url = url + "&ids="+ids;
			    else
			    	url = url+"?ids="+ids;
			window.location = url;
		    top.layer.close(index);
		});
	}
</script>