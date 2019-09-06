<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>卡券管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
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
			func();
		});
		     function func(action){
		    	 if(action=="1"){
		    		 //获取被选中的option标签
				    	var couponType=$("#couponType option:selected"); 
				    	var couponTypeValue=couponType.val();
				    	if("0" == couponTypeValue){
							$("#couponType1").show();
							$("#couponType2").hide();
							$("#couponType3").hide();
						}else if("1" == couponTypeValue){
							$("#couponType1").hide();
							$("#couponType2").show();
							$("#couponType3").hide();
						}else if("2" == couponTypeValue){
							$("#couponType1").hide();
							$("#couponType2").hide();
							$("#couponType3").show();
						}
		    		}else if(action=="2"){
		    			//获取被选中的option标签
				    	var useIndateType=$("#useIndateType option:selected"); 
				    	var useIndateTypeValue=useIndateType.val();
				    	if("0" == useIndateTypeValue){
							$("#useIndateType1").show();
							$("#useIndateType2").hide();
							$("#useIndateType3").hide();
						}else if("1" == useIndateTypeValue){
							$("#useIndateType1").hide();
							$("#useIndateType2").show();
							$("#useIndateType3").hide();
						}else if("2" == useIndateTypeValue){
							$("#useIndateType1").hide();
							$("#useIndateType2").hide();
							$("#useIndateType3").show();
						}  
		    		}else if(action=="3"){
		    			//获取被选中的option标签
				    	var handOutType=$("#handOutType option:selected"); 
				    	var handOutTypeValue=handOutType.val();
				    	if("1" == handOutTypeValue){
							$("#handOutCyclicityValue").show();
						}else {
							$("#handOutCyclicityValue").hide();
						}
		    		}
		     }
	</script>
</head>

<body class="hideScroll">
		<form:form id="inputForm" enctype="multipart/form-data"  modelAttribute="coupon" action="${ctx}/coupon/coupon/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody id="tbody">
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>卡券名称：</label></td>
					<td class="width-35">
						<form:input path="couponName" htmlEscape="false" maxlength="100"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">规则描述：</label></td>
					<td class="width-35">
						<form:input path="ruleDesc" htmlEscape="false" maxlength="100"    class="form-control "/>
					</td>					
				</tr>
				<tr>
				   <td class="width-15 active"><label class="pull-right"><font color="red">*</font>卡券类型：</label></td>
					<td class="width-35">
			            <form:select path="couponType"  class="form-control m-b required" onchange="func('1')">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getCouponTypeMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>使用有效期类型：</label></td>
					<td class="width-35">
			            <form:select path="useIndateType"  class="form-control m-b required" onchange="func('2')">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getUseIndateTypeMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
				</tr>
				

				<tr id="couponType1" style="display: none;">
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>满多少：</label></td>
					<td class="width-35">
						<form:input path="totalMoney" htmlEscape="false"    class="form-control required number"/>
					</td>

					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>减少多少：</label></td>
					<td class="width-35">
						<form:input path="paybackMoney" htmlEscape="false"    class="form-control required number"/>
					</td>
				</tr>

				<tr id="couponType2" style="display: none;">
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>折扣：</label></td>
					<td class="width-35">
						<form:input path="couponDiscount" htmlEscape="false"    class="form-control required number"/>
					</td>

					<td class="width-15 active"><label class="pull-right">最高折扣金额11：</label></td>
					<td class="width-35">
						<form:input path="maxDiscountMoney" htmlEscape="false"    class="form-control  number"/>
					</td>
				</tr>

				<tr id="couponType3" style="display: none;">
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>抵扣金额：</label></td>
					<td class="width-35">
						<form:input path="deductionMoney" htmlEscape="false"    class="form-control required number"/>
					</td>

					
				</tr>
				
				<%-- <tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>使用有效期类型：</label></td>
					<td class="width-35">
			            <form:select path="useIndateType"  class="form-control m-b required" onchange="funcc()">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getUseIndateTypeMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
				</tr> --%>
                <tr id="useIndateType1" style="display: none;">
                   <td class="width-15 active"><label class="pull-right"><font color="red">*</font>天有效：</label></td>
					<td class="width-35">
						<form:input path="ndayIndate" htmlEscape="false" maxlength="3"    class="form-control required number"/>
					</td>
                
                </tr>
				<tr id="useIndateType2" style="display: none;">
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>使用开始时间：</label></td>
					<td class="width-35">
						<input id="useBeginDate" name="useBeginDate" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
							value="<fmt:formatDate value="${coupon.useBeginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>

					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>使用结束时间：</label></td>
					<td class="width-35">
						<input id="useEndDate" name="useEndDate" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
							value="<fmt:formatDate value="${coupon.useEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				</tr>
				
				<tr id="useIndateType3" style="display: none;">
				    <td class="width-15 active"><label class="pull-right"><font color="red">*</font>使用结束时间：</label></td>
					<td class="width-35">
						<input id="useEndDate" name="useEndDate" type="text" maxlength="20" class="laydate-icon form-control layer-date required"
							value="<fmt:formatDate value="${coupon.useEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				
				</tr>
				<tr>
				   <td class="width-15 active"><label class="pull-right"><font color="red">*</font>发放类型：</label></td>
					<td class="width-35">
			            <form:select path="handOutType"  class="form-control m-b required" onchange="func('3')">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getHandOutTypeMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>单用户领取张数：</label></td>
					<td class="width-35">
						<form:input path="receiveNumLimit" htmlEscape="false" maxlength="1000"    class="form-control required number"/>
					</td>
				</tr>
				   
				<tr id="handOutCyclicityValue" style="display: none;">
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>发放周期：</label></td>
					<td class="width-35">
			            <form:select path="handOutCyclicity"  class="form-control m-b required">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getHandOutCyclicityMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>不可用商品规则：</label></td>
					<td class="width-35">
			            <form:select path="unavailableGoodsRule"  class="form-control m-b required">
					     <form:option value="" label=""/>
					     <form:options items="${fns:callGlobalStaticDict('getUnavailableGoodsRuleMap','')}"  htmlEscape="false"/>
				        </form:select>
				       
					</td>
				</tr>

				<tr>
					<td class="width-15 active"><label class="pull-right">优惠券图片：</label></td>
					<td class="width-35">
                          <up:single name="single" file=""  accept="image/*"></up:single> 
					</td>
 
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>发券券总数量：</label></td>
					<td class="width-35">
						<form:input path="totalCouponNum" htmlEscape="false" maxlength="11"    class="form-control required number"/>
					</td>
				</tr>

		 	</tbody>
		</table>
	</form:form>
</body>
</html>