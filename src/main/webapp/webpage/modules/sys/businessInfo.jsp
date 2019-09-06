<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
				width='auto';
				height='auto';
			}else{//如果是PC端，根据用户设置的width和height显示。
				width='700px';
				height='500px';
			}

			$("#userPassWordBtn").click(function(){
				top.layer.open({
				    type: 2, 
				    area: [width, height],
				    title:"<spring:message code="global.updatePwd"></spring:message>",
				    content: "${ctx}/sys/user/modifyPwd" ,
					btn: ['<spring:message code="global.save"></spring:message>', '<spring:message code="global.cancel"></spring:message>'],
				    yes: function(index, layero){
				    	 var body = top.layer.getChildFrame('body', index);
				         var inputForm = $(body).find('#inputForm');
				         var btn = body.find('#btnSubmit');
				         var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				         inputForm.validate({
								rules: {
								},
								messages: {
									confirmNewPassword: {equalTo: "输入与上面相同的密码"}
								},
								submitHandler: function(form){
									loading('正在提交，请稍等...');
									form.submit();
									
								},
								errorContainer: "#messageBox",
								errorPlacement: function(error, element) {
									$("#messageBox").text("输入有误，请先更正。");
									if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
										error.appendTo(element.parent().parent());
									} else {
										error.insertAfter(element);
									}
								}
							});
					     if(inputForm.valid()){
				        	  loading("正在提交，请稍等...");
				        	  inputForm.submit();
				        	  top.layer.close(index);//关闭对话框。
				          }else{
					          return;
				          }
						
						
					  },
					  cancel: function(index){ 
		    	       }
				}); 
			});
			
			$("#userInfoBtn").click(function(){
				top.layer.open({
				    type: 2,  
				    area: [width, height],
				    title:"个人信息编辑",
				    content: "${ctx}/sys/user/infoEdit" ,
				    btn: ['确定', '关闭'],
				    yes: function(index, layero){
				    	 var body = top.layer.getChildFrame('body', index);
				         var inputForm =  $(body).find('#inputForm');
				         var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				         inputForm.validate();
				         if(inputForm.valid()){
				        	  loading("正在提交，请稍等...");
				        	  inputForm.submit();
				          }else{
					          return;
				          }
				        
						 top.layer.close(index);//关闭对话框。
						
					  },
					  cancel: function(index){ 
		    	       }
				}); 
			});

			$("#userImageBtn").click(function(){
				top.layer.open({
				    type: 2,  
				    area: [width, height],
				    title:"上传头像",
				    content: "${ctx}/sys/user/imageEdit" ,
				  //  btn: ['确定', '关闭'],
				    yes: function(index, layero){
				    	 var body = top.layer.getChildFrame('body', index);
				         var inputForm = body.find('#inputForm');
				         var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe 
				         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
				         inputForm.validate();
				         if(inputForm.valid()){
				        	  loading("正在提交，请稍等...");
				        	  inputForm.submit();
				          }else{
					          return;
				          }
				        
						 top.layer.close(index);//关闭对话框。
						
					  },
					  cancel: function(index){ 
		    	       }
				}); 
			});
			
		});
	</script>
<style type="text/css">
	.content {
		margin: 0 auto;
		max-width: 1600px;
		width:90%;
		min-height: 500px;
	}
	.content tr:even {
		text-align: center!important;
		
	}
</style>
</head>
<body>

	<body class="gray-bg">
		<div class="wrapper wrapper-content">
			<div class="row animated fadeInRight">
				<sys:message hideType="1" content="${message}"/>
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>企业信息</h5>
							<!-- <div class="ibox-tools">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									编辑<i class="fa fa-wrench"></i>
								</a>
								<ul class="dropdown-menu dropdown-user">
									<li><a id="userImageBtn" data-toggle="modal" data-target="#register">更换头像</a>
									</li>
									<li><a id="userInfoBtn" data-toggle="modal" data-target="#register">编辑资料</a>
									</li>
								</ul>
							</div> -->
						</div>
						<div class="ibox-content">
							<div class="row">
								<%-- <div class="col-sm-4" style="margin-bottom: 10px;">
									<img alt="image" class="img-responsive" src="${user.photo }" />
								</div> --%>
								<div class="content">
									<div class="table-responsive">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<td><strong>服务商编号</strong></td>
													<td>${info.servicerNo }</td>
												</tr>
												<tr>
													<td><strong>服务商账号</strong></td>
													<td>${info.servicerAccount}</td>
												</tr>
												<tr>
													<td><strong>服务商名称</strong></td>
													<td>
													<a class="clituo" href="javascript:;"  str="${info.servicerName}">
													   ${fns:decrypt3Des(info.servicerName,0,1)}
													</a>
													</td>
												</tr>
												<tr>
													<td><strong>接入运营商名称</strong></td>
													<td>${fns:getOpeName(info.serviceProviderAbility.carryOperator)}</td>
												</tr>
											 
											<%-- 	<tr>
													<td><strong>运营商授权证明</strong></td>
													<td>${info.serviceProviderAbility.operatorAuthProve}</td>
												</tr>
												<tr>
													<td><strong>资质审核状态</strong></td>
													<td>${info.serviceProviderAbility.apitudeCheckStatus}</td> <!--0:正常 1:不通过 2:审核中  -->
												</tr>
												 --%>
												<tr>
													<td><strong>管辖省份</strong></td>
													<td>${fns:getProvinceName(info.serviceProviderAbility.effectAddressProvince)}</td>
												</tr>
												<tr>
													<td><strong>管辖市区</strong></td>
													<td>${fns:getCityName(info.serviceProviderAbility.effectAddressCity)}</td>
												</tr>
												<tr>
													<td><strong>管辖区县</strong></td>
													<td>${fns:getCountyList(info.serviceProviderAbility.effectAddressCounty)}</td>
												</tr>
												<tr>
													<td><strong>账号状态</strong></td>
													<td>${fns:getDictLabel(info.servicerStatus, 'servicer_status', '')}</td>
												</tr>
												<tr>
													<td><strong>工商注册号</strong></td>
													<td>
													<a class="clituo" href="javascript:;"  str="${info.commercialRegistrationNo}">
													  ${fns:decrypt3Des(info.commercialRegistrationNo,2,1)}
													 </a>
													</td>
												</tr>
												<tr>
													<td><strong>税务登记证号</strong></td>
													<td>${info.businessCertificateImg }</td>
												</tr>
												<tr>
													<td><strong>保证金</strong></td>
													<td>${info.prepaid }</td>
												</tr>
												<tr>
													<td><strong>审核日期</strong></td>
													<td><fmt:formatDate value="${info.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												</tr>
												<tr>
													<td><strong>服务商地址</strong></td>
													<td>
													<a class="clituo" href="javascript:;"  str="${info.servicerAddress}">
												    	${fns:decrypt3Des(info.servicerAddress,3,1)}
												    </a>
													</td>
												</tr>
												<tr>
													<td><strong>联系电话</strong></td>
													<td>
												   <a class="clituo" href="javascript:;"  str="${info.contactMobile}">
													${fns:decrypt3Des(info.contactMobile,1,1)}
													</a>
													</td>
													
												</tr>
												<tr>
													<td><strong>联系邮箱</strong></td>
													<td>${info.contactEmail}</td>
												</tr>
												<tr>
													<td><strong>备    注</strong></td>
													<td>${info.remarks }</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="col-sm-5">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>注册信息</h5>
							<div class="ibox-tools">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									编辑<i class="fa fa-wrench"></i>
								</a>
								<ul class="dropdown-menu dropdown-user">
									<li><a id="userPassWordBtn" data-toggle="modal" data-target="#register">更换密码</a>
									</li>
									<li><a href="#" data-toggle="modal" data-target="#register">更换手机号</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="ibox-content">
							<div class="row">
								<div class="col-sm-8">
									<div class="table-responsive">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<td><strong>用户名</strong></td>
													<td>${user.loginName}</td>
												</tr>
												<tr>
													<td><strong>注册手机号码</strong></td>
													<td>${user.mobile}</td>
												</tr>
												<tr>
													<td><strong>用户角色</strong></td>
													<td>${user.roleNames}</td>
												</tr>
												<tr>
													<td><strong>用户类型</strong></td>
													<td>${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</td>
												</tr>
											</tbody>
										</table>
									</div>
								
								</div>
								<div class="col-sm-4">
									<img width="100%" style="max-width:264px;" src="${user.qrCode}">
								</div>
							</div>
						</div>
					</div>

				</div> --%>

			</div>
		</div>
</body>
</html>