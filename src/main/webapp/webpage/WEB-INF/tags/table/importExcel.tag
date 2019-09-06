<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="index" type="java.lang.String" required="false"%>
<%@ attribute name="params" type="java.lang.String" required="false"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<%@ attribute name="target" type="java.lang.String" required="false"%>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入controller的url --%>
<button id="btnImport${index==null?'':index}" class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" title="<spring:message code='template.import'/>"><i class="fa fa-folder-open-o"></i> ${label==null?"导入":label}</button>
<div id="importBox${index==null?'':index}" class="hide">
		<form id="importForm${index==null?'':index}" action="${url}${params==null?'':params}" method="post" enctype="multipart/form-data"
			 style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile${index==null?'':index}" name="file${index==null?'':index}" type="file" style="width:330px"/>导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！<br/>　　
			
			
		</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#btnImport${index==null?'':index}").click(function(){
		top.layer.open({
		    type: 1, 
		    area: [500, 300],
		    title:"导入数据",
		    content:$("#importBox${index==null?'':index}").html() ,
		    btn: ['下载模板','确定', '关闭'],
			    btn1: function(index, layero){
				  window.location.href='${url}/template${params==null?'':params}';
			  },
		    btn2: function(index, layero){
			        var inputForm =top.$("#importForm${index==null?'':index}");
			        var top_iframe;
			        if(null!='${target}'&&'${target}'!=''){
			        	 top_iframe = '${target}';//如果指定了iframe，则在改frame中跳转
			         }else{
			        	 top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
			         }
			        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
    	       		top.$("#importForm${index==null?'':index}").submit();
				    top.layer.close(index);
			  },
			 
			  btn3: function(index){ 
				  top.layer.close(index);
    	       }
		}); 
	});
    
});

</script>