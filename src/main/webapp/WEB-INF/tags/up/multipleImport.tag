<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="dynamicAttributes" type="java.util.Map" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入名称"%>
<%@ attribute name="name1" type="java.lang.String" required="true" description="输入名称"%>
<%@ attribute name="files" type="java.util.List" required="false" description="文件集合"%>
<%@ attribute name="required" type="java.lang.String" required="false" description="是否验证数量"%>
<%@ attribute name="equcnt" type="java.lang.Integer" required="false" description="定值数量"%><!-- equcnt maxcnt二选择一，一个是固定值，一个是最多数量 -->
<%@ attribute name="maxcnt" type="java.lang.Integer" required="false" description="最大数量"%>
<%@ attribute name="upFileType" type="java.lang.String" required="false" description="上传类型"%><!-- 1：阿里云  6：本地 -->
 
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
		<i class="layui-icon" style="font-size: 30px; color: #009688;cursor: pointer;" id="iconFile${name1}">+</i>
		<input id="uploadFile${name1}1" type="file"    class="no-v-emoji"  onchange="upload${name1}.showImg(event)"   name="${name}"  accept="image/gif,image/jpeg,image/jpg,image/png,image/svg,video/mp4,video/x-m4v,video/*,audio/*"   
		    style="height:0.5px; width:0px;border:0px; "   />
		<div id="filecontent${name1}">
			<c:forEach items="${files}" var="file">
				<div class="img-div">
					<p class="img-p" style="right:3px!important" onclick="upload${name1}.delImg(this,'${file.url}')"></p>
	                <img src="${file.url}" style="height: 100px;width: 100px;">
				</div>
            </c:forEach>
		</div>
</div>
<script type="text/javascript">
var numse=${equcnt}
	$('#titleError').hide()
	
var maxcntnum=${maxcnt}
	$('#lengthError').hide()
	
	
;var upload${name1} = {};!function(){
	var _numIndex = 1;
	$("#iconFile${name1}").click(function () {
		var imglength=$("#filecontentmultiple").find('.img-div').length
		
		if(numse&&imglength==numse){
			$('#titleError').show()
			$('#lengthError').hide()
			return;
		}else if(maxcntnum&&imglength==maxcntnum){
			$('#titleError').hide()
			$('#lengthError').show()
			return;
		}else{
			$('#titleError').hide()
			$('#lengthError').hide()
		}
		
		
		if($('#uploadFilemultiple1-error')){
			$('#uploadFilemultiple1-error').remove()
		} 
		//alert("avc:"+_numIndex)
		//alert($("#uploadFile${name}"+ _numIndex));
		//console.log($("#uploadFile${name}"+ _numIndex));
		
		 //if($("#uploadFile${name}"+ _numIndex).length > 0){
			//alert('---存在fileinput---'+_numIndex);
			//alert("弹出：uploadFile${name}"+_numIndex);
			 $("#uploadFile${name1}"+ _numIndex).click();
		 //}else{
			//alert('---不存在fileinput---'+_numIndex);
			// $("#uploadFile${name}1").click();
		 //}
		
       
    });
    
     upload${name1}.delImg = function(args,ids){
	 	$(args).parents(".img-div").remove();
	 	if(ids != null && ids != ""){
		 	ids = ids.substring(ids.indexOf("image"));
	 	}
	 	var val = $("#delIds").val();
	 	if(val != ""&&val!='undefined'){
	 		val += ","+ ids;
			$("#delIds").val(val);
	 	}else{
			$("#delIds").val(ids);
	 	}
	 	
	 	//alert($(args).attr("fileid"));
	 	
	 	
	 	
	 	var delfilename=$(args).attr("fileid");
	 	alert(delfilename);
	 	$('#'+delfilename).val(''); 
	 	alert($('#'+delfilename).val());
	 	alert($("#delIds").val());
	 	//alert($('#'+delfilename).attr("value"));
	 	 
	 	
	 	//console.log($(args));
	 	//alert(this.attr("id"));
	 	//var imgcnt=$('#filecontent${name}').find('img').length;
	 	
	 	
	 	
        //if(imgcnt==0){
        	//alert('删除第一个');
        	//$('#uploadFile${name}1').val('');
        //} 
	 	  //$('#uploadFile${name}1').val('');
	 	 
	 }
     
    upload${name1}.showImg = function(e){
    	//alert('select img');;
    	e = e ||event;
	 	var files = e.target.files;
	 	if(files.length > 0){
		    	$.each(files,function(index,file){
		    		 
		    		num=++_numIndex;
		    		var type = file.type;
		    		if(type.indexOf("png") > -1 || type.indexOf("jpg") > -1 || type.indexOf("jpeg") > -1 || type.indexOf("gif") > -1 || type.indexOf("bmp") > -1){
			    	
		    		var img = new Image(), url = img.src = URL.createObjectURL(file);
		            img.width = "100";
		            img.height = "100";
		            img.style.paddingRight='10px';
		            img.style.paddingBottom='5px';
		            //alert(num-1)
		           
		            var _div = document.createElement("div");
		            _div.setAttribute("class","img-div");
		           
		            var node = document.createElement("p");
		            node.setAttribute("class","img-p")
		            node.setAttribute("fileid","uploadFile${name1}"+ (num-1));
		            node.onclick=function(){
		            	upload${name1}.delImg(this,url)
		            }
		            _div.appendChild(node);
		            img.onload = function() {
		                URL.revokeObjectURL(url)
		                _div.appendChild(img)
		                $('#filecontent${name1}').append(_div)
		            }
		    		}else if(type.indexOf("mp4") > -1||type.indexOf("MP4") > -1){
		    			 
		    			var video =  document.createElement('video'), url =  URL.createObjectURL(file);
		    			var source = document.createElement('source');
		    			 $(video).attr('src', url);
		    			 $(video).attr('id', 'video${index}');
		    			 $(video).attr('controls', 'controls');
		    			 $(video).attr('width', "13%");
		    			 $(video).attr('height', "100"); 
		    			 var _div = document.createElement("div");
				            _div.setAttribute("class","img-div");
				            var node = document.createElement("p");
				            node.setAttribute("class","img-p")
				            node.onclick=function(){
				            	
				            	upload${name1}.delImg(this,url)
				            }
				            _div.appendChild(node);
				            _div.appendChild(video);			           
				            $("#filecontent${name1}").append(_div); 
				            $("#filecontent${name1}").append("\n"); 
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
				            	upload${name1}.delImg(this,url)
				            }
				          
				            _div.appendChild(node);
				            _div.appendChild(video);			           
				            $("#filecontent${name1}").append(_div); 
		    		}else{
		    			   $('#filecontent${name1}').append(file.name+"\n");
		    		}
		         
				});
		    	//创建inputFile +1
		    	 var input = document.createElement("input");
		    	 //alert(num)
		    	input.setAttribute("id","uploadFile${name1}"+ (num));
		    	input.setAttribute("type","file");
		    	//input.setAttribute("multiple","multiple");
		    	input.setAttribute("name","${name}");
		    	input.style.display = "none";
		    	input.onchange=function(event){
		    		upload${name1}.showImg(event);
		    	}
		    	 
		    	$("#filecontent${name1}").before(input); 
		    	//_div.appendChild(input)
		    	
		    	
	 	}else{
	 		 //alert('files=0');
	 	}
 	}
}(window);


$(document).ready(function() {
	var temp="请上传图片 ";
    eq='${equcnt}';
    ma='${maxcnt}'; 
    
    if(eq!=''){
      temp=temp+",数量为"+eq+"张";
	  
	}
	 
	if(ma!=''){
	  temp=temp+",不超过"+ma+"张";		
	}
    
    
    jQuery.validator.addMethod("imgUploadVal${name1}", function(value, element) {
     	  
	 len=$("#filecontent${name1} img").length;
	  //alert("len"+len);
	 if(eq!=''){
		 if(len==eq){
			 return true;
		 }else{
			  
			 return false;
		 } 
	 }
	 //debugger;
	 if(ma!=''){
		 //alert(len<=ma)
		 if(len<=ma){
			 return true;
		 }else{
			 
			 return false;
		 } 
	 }
	 
	  
	 return false;
	 
	  
	 
 },temp);

 if("${required}"=="true"){
	$("#uploadFile${name1}1").rules("add",{"imgUploadVal${name1}":true});
 }

})



</script>