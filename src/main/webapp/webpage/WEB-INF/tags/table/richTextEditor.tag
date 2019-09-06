<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<script src="${ctxStatic}/ueditor/ueditor.config.js"></script>
<script src="${ctxStatic}/ueditor/ueditor.all.js"></script>
<script src="${ctxStatic}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${ctxStatic}/ueditor/ueditor.parse.js"></script>
<%--弹出窗口选择控件，注意，只是主页面部分，弹出窗口的内容需要自行处理，实际上本tag是无论是多选还是单选均会按照多选来处理  --%>
<%@ attribute name="dynamicAttributes" type="java.util.Map" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="false"%><%--存储富文本内容的字段名--%>
<%@ attribute name="value" type="java.lang.String" required="false"%><%--编辑时使用，富文本内容--%>
<%@ attribute name="atLeastChar" type="java.lang.Integer" required="false"%><%--编辑时使用，富文本内容--%>

<script type="text/plain" id="${name}" style="width: 100%; height: 400px;" name="${name}"></script>
<label id="articleContextHedden-error" style="color: #cc5965;display: none;">至少输入${atLeastChar}个字符</label> 
<input id="richTextContentTemp"  value='${value}' style="width:0px; height: 0px;" /></td>	

<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
$(document).ready(function() {
	//实例化富文本
	var ue = UE.getEditor('${name}');
	ue.addListener( 'contentChange', function( editor ) {
		validateRichTextEditor();	
	}); 
	
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
		if (action == 'uploadimage' || action == 'uploadscrawl'
				|| action == 'uploadvideo') {
			return '${ctx}/sp/ued/uploadFile';
		} else {
			return this._bkGetActionUrl.call(this, action);
		}
	};
	ue.ready(function() {
		//设置编辑器的内容
		ue.setContent($("#richTextContentTemp").val(), false);
		
	});
	//var tipwords="请至少输入"+${atLeastChar}+"个字符！"; 不需要加这个，因为在contentChange已经动态加了
	jQuery.validator.addMethod("validateCharNum", function(value, element) {
	if(validateRichTextEditor()==false){
			  return false;
	}
	 return true;
    }, '');//哪个元素$("#richTextContentTemp").rules，则提示语会跟在哪个元素后面

	
    if(parseInt(${atLeastChar})>parseInt(0)){
	  $("#richTextContentTemp").rules("add",{"validateCharNum":true});//注意：$("#richTextContentTemp")可以是任意【input】类型元素，因为实际验证的内容本身就不是针对这个字段，但是不能为type="hidden"的元素.rules("add"，会无任何效果
    }
  
});

function validateRichTextEditor(){
	var abc=UE.getEditor('${name}').body.innerText;
	if(abc && abc.length >=${atLeastChar}) {
		$("#articleContextHedden-error").css('display','none');
	}else {
		$('#articleContextHedden-error').css('display','block');
	}
	if(abc && abc.length <${atLeastChar}) {
		return false;
	}
	return true;
}
</script>