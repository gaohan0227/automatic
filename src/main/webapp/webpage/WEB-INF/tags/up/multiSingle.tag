<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入名称"%>
<%@ attribute name="accept" type="java.lang.String" required="false" description="文件类型"%>
<%@ attribute name="echo" type="java.lang.String" required="false" description="文件回显"%>
<%@ attribute name="index" type="java.lang.String" required="false" description="下标"%>
<%@ attribute name="required" type="java.lang.String" required="false" description="是否为空"%><%--设置为true则不允许为空--%>
<%@ attribute name="fileType" type="java.lang.String" required="false" description="文件类型"%><%--目前这个类看到只有图片和视频类型，那么普通文件呢？ 1：图片 2：视频 3：文件？？--%>
<%@ attribute name="ifNullTip" type="java.lang.String" required="false" description="为空时的提示"%>
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
		<!-- 删除的delIds,后台接收并删除响应的资源   -->
		<input type="hidden" name="delIds" id="delIds" value=""> 
		<i class="layui-icon iconFile" style="font-size: 30px; color: #009688;cursor: pointer;"  id="iconFile${index}">+</i>
		<input class="uploadFile no-v-emoji"     id="uploadFile${index}" type="file"  name="${name}"  accept="${accept == null? 'image/gif,image/jpeg,image/jpg,image/png,image/svg':accept}"   
		     style="height:0.5px; width:0px;border:0px; "/>
		<div id="filecontent${index}" class="${index}">
			<c:if test="${ not empty echo}">
			  	<c:choose>
		  			<c:when test="${fn:indexOf(echo,'png') != '-1' || fn:indexOf(echo,'jpg') != '-1'|| fn:indexOf(echo,'jpeg') != '-1'|| fn:indexOf(echo,'gif') != '-1'|| fn:indexOf(echo,'bmp') != '-1'}">
			              <div class="img-div">
								<p class="img-p" style="right:3px!important" onclick="singleUpload${index}.delImg(this,'${echo}')"></p>
				                <img  id="image${index}" src="${echo}" style="height: 100px;width: 100px;">
						  </div>
		  			</c:when>
		  			<c:when test="${fn:indexOf(echo,'mp4') != '-1' ||fn:indexOf(echo,'MP4') != '-1'}">
			              <div class="img-div">
								<p class="img-p" style="right:3px!important" onclick="singleUpload${index}.delImg(this,'${echo}')"></p> 
				                <video id="video${index}"  src="${echo}" controls controlsList='nofullscreen nodownload noremote footbar'></video>
						  </div>
		  			</c:when>
		  				<c:when test="${fn:indexOf(echo,'mp3') != '-1'}">
		  				
			              <div class="img-div">
								<p class="img-p" style="right:3px!important" onclick="singleUpload${index}.delImg(this,'${echo}')"></p> 
				                <video    controls >
				                <source id="audio${index}" src="${echo}" type="audio/mp3">
				                </video>
						  </div>
		  			</c:when>
		  			<c:otherwise>
		  					${echo}  
		  			</c:otherwise>
			  	</c:choose>
			</c:if>
		</div>
</div>
<script type="text/javascript">
;var singleUpload${index} = {};!function(){
	$("#iconFile${index}").unbind("click").click(function () {
		//alert('iconFile');
		if($('#uploadFile${index}-error')){
			$('#uploadFile${index}-error').remove()
		} 
		
		$(this).next("input[name='${name}']").click();
		//alert('iconFile-uploadFile.click');
    });
	
    $('input[name="${name}"]').unbind("change").on("change",function(e){
    	
    	var $this = $(this);
    	var files = e.target.files;
    	//alert("----------------:上传  :"+files.length);
    	if(files.length > 0){
	    	$.each(files,function(index,file){
	    		 
	    		var type = file.type;
	    		if(type.indexOf("png") > -1 || type.indexOf("jpg") > -1 || type.indexOf("jpeg") > -1 || type.indexOf("gif") > -1 || type.indexOf("bmp") > -1){
		    		var img = new Image(), url = img.src = URL.createObjectURL(file);
		            img.width = "100";
		            img.height = "100";
		            img.id = 'image${index}';
		            img.style.paddingRight='10px';
		            img.style.paddingBottom='5px';
		            var _div = document.createElement("div");
		            _div.setAttribute("class","img-div");
		            var node = document.createElement("p");
		            node.setAttribute("class","img-p")
		            
		            node.onclick=function(){
		            	
		            	singleUpload${index}.delImg(this)
		            }
		            _div.appendChild(node);
		            img.onload = function() {
		            	//alert("----${index}");
		                URL.revokeObjectURL(url)
		                _div.appendChild(img)
		                //alert("----1:"+_div);
		                console.log(_div);
		                $this.next("#filecontent${index}").empty().append(_div) 
		            }
	    		}else if(type.indexOf("mp4") > -1||type.indexOf("MP4") > -1){
	    			 
	    			var video =  document.createElement('video'), url =  URL.createObjectURL(file);
	    			var source = document.createElement('source');
	    			 $(video).attr('src', url);
	    			 $(video).attr('id', 'video${index}');
	    			 $(video).attr('controls', 'controls');
	    			 $(video).attr('width', "100");
	    			 $(video).attr('height', "100"); 
	    			 var _div = document.createElement("div");
			            _div.setAttribute("class","img-div");
			            var node = document.createElement("p");
			            node.setAttribute("class","img-p")
			            node.onclick=function(){
			            	
			            	singleUpload${index}.delImg(this)
			            }
			            _div.appendChild(node);
			            _div.appendChild(video);			           
			            $this.next("#filecontent${index}").empty().append(_div); 
			            //alert("video end");
	    		}else if(type.indexOf("mp3")>1){
	    			var video =  document.createElement('video'), url =  URL.createObjectURL(file);
	    			var source = document.createElement('source');
	    			 $(video).attr('src', url);
	    			 $(video).attr('id', 'video${index}');
	    			 $(video).attr('controls', 'controls');
	    			 $(video).attr('width', "100");
	    			 $(video).attr('height', "100"); 
	    			 var _div = document.createElement("div");
			            _div.setAttribute("class","img-div");
			            var node = document.createElement("p");
			            node.setAttribute("class","img-p")
			            node.onclick=function(){
			            	singleUpload${index}.delImg(this)
			            }
			            _div.appendChild(node);
			            _div.appendChild(video);			           
			            $this.next("#filecontent${index}").empty().append(_div); 
	    		}else{
	    			$this.next("#filecontent${index}").empty().append(file.name);
	    		}
			});
    	}
    });
	singleUpload${index}.delImg = function (args,ids){
	 	$(args).parents(".img-div").remove();
		if(ids != null && ids != ""){
		 	ids = ids.substring(ids.indexOf("image"));
		 	var val = $(args).parents("#delIds").val();
		 	ids = (val != null && val != "") ? (val + "," + ids) : ids;
		 	$(args).parents("#delIds").val(ids);
	 	}
		$('#uploadFile${index}').val('');
     	//$('.uploadFile').val('');//组件图片删除时的动作，修复了同名文件删除后再选择不显示问题   $('#uploadFile${index}').val('');
	 }
}();
$(document).ready(function() {
	var tempName='tip'+"${index}";
	window[tempName]="${ifNullTip}";
	if(window[tempName]==null||window[tempName]==''){
		
		if("${fileType}"=="1"){
			
			window[tempName]='请上传图片';
		}else if("${fileType}"=="2"){
			
			window[tempName]='请上传视频';
		}else{
			
			window[tempName]='这是必填字段';
		}
	}
	
	jQuery.validator.addMethod("imgUploadVal${index}", function(value, element) {
			//debugger;
		 
		  var imageinput = $("#image${index}");
		  var videoinput = $("#video${index}");  
		  var audioinput = $("#audio${index}");  
		  if( typeof(videoinput.attr("src"))!="undefined"||typeof(audioinput.attr("src"))!="undefined" || typeof(imageinput.attr("src"))!="undefined" ){
			  return true;  
		  }		
			
		 if(element.files.length>0 ){					 
			 return true;					 
		 }else{					 
			 return false;
		 }
		 
	  }, window[tempName]);

	  if("${required}"=="true"){
		  $("#uploadFile${index}").rules("add",{"imgUploadVal${index}":true});
	  }
     
});
</script>