<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入名称"%>
<%@ attribute name="ismultiple" type="java.lang.String" required="false" description="是否上传多个"%>
<%@ attribute name="file" type="java.lang.String" required="false" description="单个文件"%>
<%@ attribute name="files" type="java.util.List" required="false" description="文件集合"%>
<style>
.img-div {
	position: relative;
	display: inline-block;
}
.img-p{
	position: absolute;
	background: url("${ctxStatic}/common/img/close1.png");
	top: 1px;
    right: 11px;
    z-index: 100;
    width: 25px;
    height: 25px;
    background-size: 100%;
    background-repeat: no-repeat;
}
</style>
<div class="lightBoxGallery">
		<!-- 删除的delIds,后台接收并删除响应的资源 -->
		<input type="hidden" name="delIds" id="delIds" value=""> 
		<i class="layui-icon" style="font-size: 30px; color: #009688;cursor: pointer;" id="iconFile">+</i>
		<input id="uploadFile" type="file" <c:if test="${not empty ismultiple}">multiple="multiple" </c:if> name="${name}"  accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"  style="display: none"/>
		<div id="filecontent">
			<c:choose>
				<c:when test="${files != null && fn:length(files) > 0}">
					<c:forEach items="${files}" var="file">
						<div class="img-div">
							<p class="img-p" style="right:3px!important" onclick="delImg(this,'${file.url}')"></p>
			                <img src="${file.url}" style="height: 100px;width: 100px;">
						</div>
		            </c:forEach>
				</c:when>
				<c:otherwise>
					<c:if test="${ not empty file}">
		              <div class="img-div">
							<p class="img-p" style="right:3px!important" onclick="delImg(this,'${file}')"></p>
			                <img src="${file}" style="height: 100px;width: 100px;">
					  </div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
</div>
<script type="text/javascript">
;!function(){
	$("#iconFile").click(function () {
        $("#uploadFile").click();
    });
    $("#uploadFile").on("change",function(e){
    	var files = e.target.files;
    	if(files.length > 0){
	    	$.each(files,function(index,file){
	    		var img = new Image(), url = img.src = URL.createObjectURL(file);
	            img.width = "100";
	            img.height = "100";
	            img.style.paddingRight='10px';
	            img.style.paddingBottom='5px';
	            var _div = document.createElement("div");
	            _div.setAttribute("class","img-div");
	            var node = document.createElement("p");
	            node.setAttribute("class","img-p")
	            node.onclick=function(){
	            	delImg(this)
	            }
	            _div.appendChild(node);
	            img.onload = function() {
	                URL.revokeObjectURL(url)
	                _div.appendChild(img)
	                $('#filecontent').append(_div)
	            }
			});
    	}
    });
}();
 function delImg(args,ids){
 	var $this = $(args).parents(".img-div");
 	$this.remove();
 	if(ids != null && ids != ""){
	 	ids = ids.substring(ids.indexOf("image"));
 	}
 	var val = $("#delIds").val();
 	if(val != ""){
 		$("#delIds").val(val+","+ids);
 	}
 }
</script>