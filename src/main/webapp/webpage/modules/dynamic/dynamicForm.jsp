<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<c:if test="${dynamicJspListConfigVO.extendDynamicJsFileName!=null}">
	<script src="${ctxStatic}/dynamicjs/${dynamicJspListConfigVO.extendDynamicJsFileName}.js" type="text/javascript"></script>
</c:if>
<html>
<head>
	<title>学校平台管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        var validateForm;
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if(validateForm.form()){

                if(isExitsFunction('preDoSubmit')){//preDoSubmits是自定义的扩展js中用来拦截doSubmit的自定义函数，如果需要就可以定义，如果只是输入input等验证，
                    //完全可以参照richTextEidtor、single等tag中 $("#richTextContentTemp").rules("add",{"validateCharNum":true})这种形式来添加验证，就不需要在这定义这个方法
                    if(preDoSubmit()==false)return false;
                }

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
            <c:forEach items="${dynamicJspFormConfigVO.laydateJsList}" var="laydateJs">
            ${laydateJs}
            </c:forEach>
        });
	</script>
</head>

<body class="hideScroll">

<form:form id="inputForm" enctype="${dynamicJspFormConfigVO.enctype}"  modelAttribute="${dynamicJspFormConfigVO.dynamicModelName}" action="${ctx}/${dynamicJspFormConfigVO.controllerSavePath}" method="post" class="form-horizontal">

	<c:forEach items="${dynamicJspFormConfigVO.hiddenList}" var="hiddenEle">
		<form:hidden path="${hiddenEle.attrMap['path'].attrCovertValue}"/>
	</c:forEach>
	<sys:message content="${message}"/>
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		<tbody>
		<c:forEach items="${dynamicJspFormConfigVO.labelTdList}" var="labelTd"  varStatus="labelTdStatus" >

			<c:if test="${labelTdStatus.index%dynamicJspFormConfigVO.columnNum==0}"><tr></c:if>
			${labelTd.html}
			<c:set var="dataTdListGroup" value="${dynamicJspFormConfigVO.findDataTdListByLabelTdIndex(labelTdStatus.index)}"/><!-- dataTdListGroup是一个list对象 -->
			<td class="width-35"  <c:if test="${dynamicJspFormConfigVO.columnNum==1}">colspan="3"</c:if>>
				<c:forEach items="${dataTdListGroup}" var="dataTd"  >
					<c:choose>
						<c:when test="${dataTd.ifFormElement == true && dataTd.elementCode eq JspTool.getLsc().getElementInput()}"><!-- 如果 -->
							<form:input path="${dataTd.attrMap['path'].attrCovertValue}" htmlEscape="${dataTd.attrMap['htmlEscape'].attrCovertValue}" maxlength="${dataTd.attrMap['maxlength'].attrCovertValue}" class="${dataTd.attrMap['class'].attrCovertValue}" />
						</c:when>
						<c:when test="${dataTd.ifFormElement == true && dataTd.elementCode eq JspTool.getLsc().getElementTextarea()}"><!-- 如果 -->
							<form:textarea path="${dataTd.attrMap['path'].attrCovertValue}" htmlEscape="${dataTd.attrMap['htmlEscape'].attrCovertValue}" maxlength="${dataTd.attrMap['maxlength'].attrCovertValue}" class="${dataTd.attrMap['class'].attrCovertValue}" />
						</c:when>
						<c:when test="${dataTd.ifFormElement == true && dataTd.elementCode eq JspTool.getLsc().getElementSelect()}"><!-- 如果 -->

							<form:select path="${dataTd.attrMap['path'].attrCovertValue}"  class="${dataTd.attrMap['class'].attrCovertValue}">
								<c:forEach items="${dataTd.childList}" var="selectchild">
									<c:if test="${selectchild.attrMap['items']==null}">
										<form:option value="" label="请选择"/>
									</c:if>
									<c:if test="${selectchild.attrMap['items'].selectDataCollection!=null}">
										<form:options items="${selectchild.attrMap['items'].selectDataCollection}"  htmlEscape="false"/>
									</c:if>
								</c:forEach>
							</form:select>

						</c:when>
						<c:when test="${dataTd.ifFormElement == false && dataTd.elementCode eq JspTool.getLsc().getElementSingle()}"><!-- 单图片上传 -->
							<up:single  dynamicAttributes="${dataTd.attrMap}"></up:single>
						</c:when>
						<c:when test="${dataTd.ifFormElement == false && dataTd.elementCode eq JspTool.getLsc().getElementMultiple()}"><!-- 多图片上传 -->
							<up:multiple  dynamicAttributes="${dataTd.attrMap}"></up:multiple>
						</c:when>
						<c:when test="${dataTd.ifFormElement == false && dataTd.elementCode eq JspTool.getLsc().getElementTagRichtexteditor()}"><!-- 富文本编辑器-->
							<table:richTextEditor  dynamicAttributes="${dataTd.attrMap}"></table:richTextEditor>
						</c:when>
						<c:when test="${dataTd.ifFormElement == false && dataTd.elementCode eq JspTool.getLsc().getElementTagPopwindowselect()}"><!-- 弹窗选取-->
							<table:popWindowSelect  dynamicAttributes="${dataTd.attrMap}"></table:popWindowSelect>
						</c:when>
						<c:when test="${dataTd.ifFormElement == false}">
							${dataTd.html}
						</c:when>
					</c:choose>
				</c:forEach>
			</td>
			<c:if test="${labelTdStatus.index%dynamicJspFormConfigVO.columnNum==dynamicJspFormConfigVO.columnNum-1}"></tr></c:if>
		</c:forEach>
		</tbody>
	</table>
</form:form>
</body>
</html>