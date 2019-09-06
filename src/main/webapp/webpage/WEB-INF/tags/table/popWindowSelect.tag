<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%--弹出窗口选择控件，注意，只是主页面部分，弹出窗口的内容需要自行处理，实际上本tag是无论是多选还是单选均会按照多选来处理  --%>
<%@ attribute name="dynamicAttributes" type="java.util.Map" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%><%--存储所选内容的name名称，通常为属性字段名或者虚拟字段名，如果一个页面有多个弹窗，记得不要重名--%>
<%@ attribute name="value" type="java.lang.String" required="false"%><%--更新时使用，为所选选项的多个id，单个id也当多个处理，多个以逗号分割--%>
<%@ attribute name="entityName" type="java.lang.String" required="false"%><%--整个弹窗所涉及的表的实体名称--%>
<%@ attribute name="showFields" type="java.lang.String" required="false"%><%-- 要显示的name字段名字，多个以逗号分割 />--%>
<%@ attribute name="windowsTitle" type="java.lang.String" required="false"%><%-- 弹出窗口title--%>
<%@ attribute name="url" type="java.lang.String" required="false"%><%-- 弹出的url--%>
<%@ attribute name="callBackMethodName" type="java.lang.String" required="false"%><%-- 回调主窗口的函数名称--%>
<%@ attribute name="mutilSelect" type="java.lang.String"  required="false"%><%-- 为true表示是多选--%>
<%@ attribute name="parentName" type="java.lang.String"  required="false"%><%-- 父级 name--%>
<%@ attribute name="childName" type="java.lang.String"  required="false"%><%-- 子集 name--%>
<%@ attribute name="required" type="java.lang.Boolean"  required="false"%><%-- true 表示必填--%>
<%@ attribute name="errorMessage" type="java.lang.String"  required="false"%><%-- true 表示必填--%>

<a href="#"  style="width: 100px" onclick="gotoSelectWindow${name}()" class="btn btn-white btn-sm">选择</a>
<form:input  path="${name}" style="height:0px; width:0px;border:0px; " message="${errorMessage}" htmlEscape="false"  class="form-control"/>
<div id="${name}List" style="display:inline;">
</div>

<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
function gotoSelectWindow${name}(){
    $('#${name}-error').remove();
    if("${parentName}" == ""){
        openDialogSelect('${windowsTitle}', '${url}','800px', '500px');
    }else{
        if($("#${parentName}").val()!="undefined" && $("#${parentName}").val()!=""){
            var vale = $("#${parentName}").val();
            openDialogSelect('${windowsTitle}', addURLArg('${url}','${parentName}',vale),'800px', '500px');
        }else{
            var errorMes =  $("#${parentName}").attr("message");
            $("#${name}List").before('<label id="${name}-error" style="color:#cc5965;">请选择'+errorMes+'</label>');
        }
    }
}

top.${callBackMethodName} = function(ids) {//固定只传id或者id集合
	$('${name}-error').remove();
	var val = $("#${name}").val();
	var idArrays=ids.split(",");
    if(${mutilSelect==true}){//多选
    }else{
        $("#${name}").val('');
        val="";
        $("#${name}List").empty();
    }
    appendHtml${name}(val,idArrays)
    emList${name}();
}

function emList${name}() {
    if("${childName}" != ""){
        $("#${childName}").val('');
        $("#${childName}List").empty();
    }
}

function getFields(resultBean, allKeys){
    allKeys = allKeys || '';
    allKeys = allKeys.split(',');
    var showFieldsStr = "";
    allKeys.map(function (item) {
    	if(item.indexOf("\.") != -1 ){
    		item=item.substring(item.indexOf("\."));
    	}
		showFieldsStr += resultBean[item];
    });
	return showFieldsStr;
}

function validates${name}() {
    var val = $("#${name}").val();
    if(val == ''){
        return false;
    }else{
        return true;
    }
}

$(document).ready(function() {
    var ids = $("#${name}").val();
    var idArrays=ids.split(",");
    appendHtml${name}('',idArrays)
    jQuery.validator.addMethod("validateDosubmit${name}", function(value, element) {
        //debugger;
        return validates${name}();
    }, "请选择${errorMessage}");
    if("${required}"=="true"){
        $("#${name}").rules("add",{"validateDosubmit${name}":true});
    }
});

//删除标签
function delLabel${name}(id){
    var hiddenId = $("#${name}").val();
    $("#${name}").val('');
    var ids = hiddenId.split(",");
    var values = "";
    for (var i in ids){
        if(ids[i] != id){
            values = ids[i]+","+values;
        }
    }
    $("#" + id).remove();
    var isa = values.substring(0,values.length-1);
    $("#${name}").val(isa);
    emList${name}();
}

function appendHtml${name}(val,idArrays) {
    for(var i in idArrays){
        if(val.indexOf(idArrays[i]) == -1){
            if(val==''||val==null){
                val = idArrays[i] ;
            }else{
                val = idArrays[i] +"," + val;
            }
            $("#${name}").val(val);
            var tempId=idArrays[i].replace(/\'/g,"");
            var elexpress="JspTool.getEntityByEntityId('${entityName}','"+idArrays[i]+"','"+"${showFields}"+"')";
            var resultBean=parseElByAjax(elexpress);
            var html = '<span class="list_info" id ="'+ tempId +'">'+ getFields(resultBean, "${showFields}") +
                '		<i class="fa fa-times-circle" onclick="delLabel${name}(\''+ tempId +'\')"></i>'+
                '</span>';
            $("#${name}List").append(html);
        }
    }
}

</script>