<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="输入ID"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="图片地址"%>
<style>
.layui-upload-img{width: 92px; height: 92px; margin: 0 10px 10px 0;}
</style>
<div class="layui-upload">
  <button type="button" class="layui-btn" id="layui-btn">上传图片</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img"  src="${value}">
    <p id="demoText"></p>
  </div>
</div>
<script type="text/javascript">
;!function(){
	  //无需再执行layui.use()方法加载模块，直接使用即可
	  var form = layui.form
	  ,layer = layui.layer,
	  $ = layui.jquery
	  ,upload = layui.upload;
	  
	  //普通图片上传
	  var uploadInst = upload.render({
	    elem: '#layui-btn'
	    ,url: '${ctx}/util/upload/'
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('.layui-upload-img').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	    	debugger
	    	console.log(res);
	      //如果上传失败
	      if(res.code > 0){
	        return layer.msg('上传失败');
	      }
	      $("#${id}").val(res.imgurl);
	      //上传成功
	    }
	    ,error:function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      });
	    }
	  })
	}();
</script>