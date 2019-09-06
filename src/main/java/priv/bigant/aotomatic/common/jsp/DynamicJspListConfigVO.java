package priv.bigant.aotomatic.common.jsp;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import priv.bigant.aotomatic.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import priv.bigant.aotomatic.common.*;
import priv.bigant.aotomatic.common.jsp.child.*;
import priv.bigant.aotomatic.common.jsp.child.JspElementAtagButton.ButtonType;
import priv.bigant.aotomatic.common.table.JoinTableFinderFactory;

import java.util.*;

public class DynamicJspListConfigVO extends DynamicJspBaseConfigVO {
    protected String toolButtonTagHtml;

    private TjList<JspElement> queryCondList = new TjList<>();
    protected TjList<JspElement> toolButtonList = new TjList<>();
    private TjList<JspElement> tableDataList = new TjList<>();
    private TjList<JspElement> tableThList = new TjList<>();
    protected TjList<JspElement> tableButtonList = new TjList<>();

    protected boolean ifShowCheckBox;

    public void tableButtonAddAtagButtonBasic(ButtonType... buttonType) {
        for (ButtonType type : buttonType) {
            this.tableButtonAddAtagButtonBasic(type, null, null);
        }
    }

    public JspElementAtagButton tableButtonAddAtagButtonBasic(ButtonType buttonType, String custUrl, Map<String, String> urlParams) {
        return this.tableButtonAddAtagButtonBasic(buttonType, custUrl, null, urlParams);
    }

    public JspElementAtagButton tableButtonAddAtagButtonBasic(ButtonType buttonType, String custUrl, String target, Map<String, String> urlParams) {
        if (urlParams != null) {
            this.idParamsMap.putAll(urlParams);
        }

        JspElementAtagButton jab = null;
        if (StringUtils.isNotEmpty(custUrl)) {
            custUrl = this.controllerBasePath + custUrl;
        }

        if ("1".equals(buttonType.getVal())) {
            if (StringUtils.isEmpty(target)) {
                target = "this.href";
            }

            jab = JspElementAtagButton.newInstance(true, StringUtils.isEmpty(custUrl) ? this.controllerDeletePath : custUrl, "0", "1", null, target, this.idParamsMap);
            jab.getChildList().add(JspElement.findMatchPureI("2"));
        }

        if ("0".equals(buttonType.getVal())) {
            jab = JspElementAtagButton.newInstance(false, StringUtils.isEmpty(custUrl) ? this.controllerFormPath : custUrl, "0", "0", "0", target, this.idParamsMap);
            jab.getChildList().add(JspElement.findMatchPureI("1"));
        }

        if ("2".equals(buttonType.getVal())) {
            jab = JspElementAtagButton.newInstance(false, StringUtils.isEmpty(custUrl) ? this.controllerFormPath : custUrl, "0", "2", "2", target, this.idParamsMap);
            jab.getChildList().add(JspElement.findMatchPureI("0"));
        }

        jab.genCode();
        this.tableButtonList.add(jab);
        return jab;
    }

    public JspElementAtagButton tableButtonAddAtagButtonCustomHref(String urlPath, String classContent, String operationDescContent, String target, Map<String, String> urlParams) {
        return this.tableButtonAddAtagButtonHref(urlPath, classContent, operationDescContent, target, urlParams);
    }

    public JspElementAtagButton tableButtonAddAtagButtonHref(String urlPath, String classContent, String operationDescContent, String target, Map<String, String> urlParams) {
        urlPath = this.controllerBasePath + urlPath;
        JspElementAtagButton jab = JspElementAtagButton.newInstance(true, urlPath, classContent, operationDescContent, (String) null, target, urlParams);
        jab.genCode();
        this.tableButtonList.add(jab);
        return jab;
    }

    public JspElementAtagButton tableButtonAddAtagButtonDialogClick(String urlPath, String classContent, String operationDescContent, String target, String openDialogType, Map<String, String> urlParams) {
        urlPath = this.controllerBasePath + urlPath;
        JspElementAtagButton jab = JspElementAtagButton.newInstance(false, urlPath, classContent, operationDescContent, openDialogType, target, urlParams);
        jab.genCode();
        this.tableButtonList.add(jab);
        return jab;
    }

    public JspElementAtagButton tableButtonQButtonAudit(String urlPath, Map<String, String> urlParams) {
        return this.tableButtonQCommon(urlPath, "通过审核", urlParams, 1, 4);
    }

    public JspElementAtagButton tableButtonQButtonReject(String rejectReasonSaveUrl, String rejectReasonFieldName, Map<String, String> urlParams) {
        if (urlParams == null) {
            urlParams = new HashMap<>();
        }
        String needTranParamsName = ArrayUtils.toString(urlParams.keySet().toArray(), ",");
        needTranParamsName = needTranParamsName.substring(0, needTranParamsName.length() - 1);
        urlParams.put("rejectReasonFieldNamesss", rejectReasonFieldName);
        urlParams.put("rejectReasonSaveUrl", rejectReasonSaveUrl);
        urlParams.put("needTranParamsName", needTranParamsName.substring(1));
        String commonRejectFormUrl = "commonRejectForm";
        JspElementAtagButton clickBtn = this.tableButtonAddAtagButtonDialogClick(commonRejectFormUrl, "1", "审核驳回", (String) null, "0", (Map) urlParams);
        clickBtn.getChildList().add(JspElement.findMatchPureI("5"));
        return clickBtn;
    }

    public JspElementAtagButton tableButtonQButtonPublish(String urlPath, Map<String, String> urlParams) {
        return this.tableButtonQCommon(urlPath, "发布", urlParams, 1, 3);
    }

    public JspElementAtagButton tableButtonQButtonUp(String urlPath, Map<String, String> urlParams) {
        return this.tableButtonQCommon(urlPath, "上架", urlParams, 1, 6);
    }

    public JspElementAtagButton tableButtonQButtonDown(String urlPath, Map<String, String> urlParams) {
        return this.tableButtonQCommon(urlPath, "下架", urlParams, 1, 7);
    }

    private JspElementAtagButton tableButtonQCommon(String urlPath, String operDesc, Map<String, String> urlParams, int cssClassbtn, int cssClassIcon) {
        JspElementAtagButton jab1 = this.tableButtonAddAtagButtonHref(urlPath, String.valueOf(cssClassbtn), operDesc, (String) null, urlParams);
        jab1.addConfirmx("确认要" + operDesc + "吗？");
        jab1.getChildList().add(JspElement.findMatchPureI(String.valueOf(cssClassIcon)));
        return jab1;
    }

    private void autoJoinTableAndInitMap(Class<BaseEntity> mainTable, Class<BaseEntity> childTableName, SFunction<? extends BaseBean, ?>... fieldNames) {
        if (mainTable != this.mainTableClass) {
            try {
                JoinTableBean destJtb = this.autoJoinTable(mainTable, childTableName);
                String mainEntityName = JspElement.getEntityNameByClass(this.mainTableClass, new boolean[0]);
                /*List<Field> allFields = TableInfoHelper.getAllFields(mainTable);

                Map<String, TableColumn> columnMap = DBTool.findTableFieldMap(mainTable);
                Map<String, Class> allVirtualPropMap = new HashMap<>();
                String[] var11 = fieldNames;
                int var10 = fieldNames.length;

                for (int var9 = 0; var9 < var10; ++var9) {
                    String field = var11[var9];
                    TableColumn tc = (TableColumn) columnMap.get(field);
                    if (tc == null) {
                    }

                    allVirtualPropMap.put(field, mainTable.getDeclaredField(field).getType());
                    if ("String".equals(tc.getJavaType())) {
                        destJtb.putWhereLike(field, null);
                    } else {
                        destJtb.putWhere(field);
                    }
                }

                this.addLackProps(mainEntityName, allVirtualPropMap);*/
                this.addLackProps(mainEntityName, null);
            } catch (Exception var13) {
                var13.printStackTrace();
            }

        }
    }

    /*private JoinTableBean autoJoinTable(Class<BaseEntity> mainTable, Class<BaseEntity> childTable) throws Exception {
        if (mainTable == this.mainTableClass) {
            return null;
        } else {
            JoinTableFinderFactory joinTableFinderFactory = JoinTableFinderFactory.finderIns(false);
            Map<Class<? extends BaseEntity>, LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean>> joinPath =
                    joinTableFinderFactory.findJTBByDirectRelation(mainTable, false, childTable);
            LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean> allJoinTable = joinPath.get(mainTable);
            JoinTableBean destJtb = null;
            Iterator var8 = allJoinTable.values().iterator();

            while (var8.hasNext()) {
                JoinTableBean jtb = (JoinTableBean) var8.next();
                JoinTableBean exist = this.currEntityObj.findExistSameJtb(jtb);
                if (exist == null) {
                    jtb.joinInto(this.currEntityObj);
                    destJtb = jtb;
                } else {
                    destJtb = exist;
                }
            }

            return destJtb;
        }
    }*/
    private JoinTableBean autoJoinTable(Class<BaseEntity> mainTable, Class<BaseEntity> childTable) throws Exception {
        if (mainTable == this.mainTableClass) {
            return null;
        } else {
            JoinTableFinderFactory joinTableFinderFactory = JoinTableFinderFactory.finderIns(false);
            return joinTableFinderFactory.findJTBByDirectRelation(mainTable, childTable);
        }
    }

    private void addLackProps(String mainEntityName, Map<String, Class> allVirtualPropMap) {
        //this.currEntityObj = (BaseEntity) this.currEntityObj.initDynaMap(allVirtualPropMap);
        this.elParse.getContext().addContext(mainEntityName, this.currEntityObj);
    }

    private List<String> judgeIfLackProp(String... fields) {
        List<String> noContainProp = new ArrayList<>();
        String[] var6 = fields;
        int var5 = fields.length;

        /*for (int var4 = 0; var4 < var5; ++var4) {
            String temp = var6[var4];
            if (!this.currEntityObj.hasProperty(temp)) {
                noContainProp.add(temp);
            }
        }*/

        return noContainProp;
    }

    public void queryCondSmart(Class<BaseEntity> tableClass, String... fieldNames) {
        /*this.autoJoinTableAndInitMap(tableClass, null, fieldNames);
        (new SmartJspGen(tableClass, this)).queryCondGen(fieldNames);*/
    }

    public void queryCondSmart(String... fieldNames) {
        //(new SmartJspGen(this.mainTableClass, this)).queryCondGen(fieldNames);
    }

    public JspElement findQueryCondEleByName(String fieldName) {
        return this.findElementByName(this.queryCondList, fieldName);
    }

    public JspElementFormHidden queryCondAddHidden(String fieldName) {
        JspElementFormHidden temp = (JspElementFormHidden) JspElementFormHidden.newInstance(this.mainTableClass, fieldName);
        temp.genCode();
        this.hiddenList.add(temp);
        return temp;
    }

    public boolean queryCondInsertEle(List<JspElement> jeList, String fieldName, boolean... beforeOrAfters) throws Exception {
        if (jeList != null && jeList.size() == 2) {
            boolean bl = false;
            JspElement dest = (JspElement) this.findQueryCondEleByName(fieldName);
            boolean beforeOrAfter = false;
            if (beforeOrAfters.length > 0) {
                beforeOrAfter = beforeOrAfters[0];
            }

            if (dest != null) {
                int destindex = this.queryCondList.indexOf(dest);
                if (beforeOrAfter) {
                    if (destindex == 1) {
                        destindex = 0;
                    } else {
                        --destindex;
                    }
                }

                this.queryCondList.addAll(destindex, jeList);
            }

            return false;
        } else {
            throw new Exception("要添加元素必须为2个，一个标题，一个内容，请重新构建");
        }
    }

    public void queryCondAddFormInput(String... fieldNames) {
        String[] var5 = fieldNames;
        int var4 = fieldNames.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            String fieldName = var5[var3];
            this.queryCondAddFormInput(this.mainTableClass, fieldName);
        }

    }

    public JspElementFormInput queryCondAddFormInputForVirField(String fieldName, String fieldChineseName) {
        JspElementFormInput temp = (JspElementFormInput) JspElementFormInput.newInstance(this.mainTableClass, fieldName);
        temp.setQueryCondPrintPrefix("queryCondEle");
        temp.genCode();
        fieldChineseName = StringUtils.isEmpty((CharSequence) this.currFieldChineseNameMap.get(fieldName)) ? fieldChineseName : (String) this.currFieldChineseNameMap.get(fieldName);
        JspElementSpan span = JspElementFormInput.findMatchPureSpan(fieldChineseName);
        this.queryCondList.add(span);
        temp.setCorrespondJspElement(span);
        this.queryCondList.add(temp);
        return temp;
    }

    public JspElementFormInput queryCondAddFormInput(Class tableClass, String fieldName) {
        JspElementFormInput temp = (JspElementFormInput) JspElementFormInput.newInstance(tableClass, fieldName);
        temp.setQueryCondPrintPrefix("queryCondEle");
        temp.genCode();
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementSpan span = JspElementFormInput.findMatchPureSpan(fieldChineseName);
        this.queryCondList.add(span);
        temp.setCorrespondJspElement(span);
        this.queryCondList.add(temp);
        return temp;
    }

    public JspElementInputDate queryCondAddInputDate(String fieldName, String... formatPattern) {
        String formatPatternnew = formatPattern.length > 0 ? formatPattern[0] : null;
        JspElementInputDate temp = this.queryCondAddInputDate(this.mainTableClass, fieldName, formatPatternnew, false);
        return temp;
    }

    public JspElementInputDate queryCondAddInputDateBetween(String fieldName, String... formatPattern) {
        String formatPatternnew = formatPattern.length > 0 ? formatPattern[0] : null;
        JspElementInputDate temp = this.queryCondAddInputDate(this.mainTableClass, fieldName, formatPatternnew, true);
        return temp;
    }

    public JspElementInputDate queryCondAddInputDate(Class tableClass, String fieldName, String formatPattern, boolean ifbetween) {
        JspElementInputDate temp = JspElementInputDate.newInstance(tableClass, fieldName, formatPattern);
        temp.parseEl(this.elParse);
        this.addLaydateJs(fieldName);
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementSpan span = JspElementInputDate.findMatchPureSpan(fieldChineseName);
        this.queryCondList.add(span);
        temp.setCorrespondJspElement(span);
        this.queryCondList.add(temp);
        if (ifbetween) {
            this.queryCondList.add(JspElementPureText.newInstance("--"));
            String destClassName = JspElement.getEntityNameByClass(tableClass, new boolean[]{false});
            String endDateName = "end" + StringUtils.firstToUpper(fieldName);
            BaseEntity dest = (BaseEntity) this.elParse.getContext().findContextObjectByName(destClassName);

            /*if (!dest.hasProperty(endDateName)) {
                Map pp = new HashMap<>();
                pp.put(endDateName, Date.class);
                dest = (BaseEntity) dest.initDynaMap(pp);
                this.elParse.getContext().addContext(destClassName, dest);
            }*/

            JspElementInputDate end = (JspElementInputDate) JspElementInputDate.newInstance(tableClass, endDateName, formatPattern);
            end.parseEl(this.elParse);
            this.addLaydateJs(endDateName);
            this.queryCondList.add(end);
        }

        return temp;
    }

    public JspElementFormSelect queryCondAddFormSelect(String fieldName) {
        return this.queryCondAddFormSelect(this.mainTableClass, fieldName);
    }

    public JspElementFormSelect queryCondAddFormSelect(Class tableClass, String fieldName) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementFormSelect temp = this.genJspElementFormSelect(tableClass, fieldName);
        JspElementSpan span = JspElementFormSelect.findMatchPureSpan(fieldChineseName);
        this.queryCondList.add(span);
        temp.setCorrespondJspElement(span);
        this.queryCondList.add(temp);
        return temp;
    }

    public JspElementFormSelect queryCondAddFormSelectJoin(String fieldName, Class joinTableClass, String... showFields) {
        try {
            return this.queryCondAddFormSelectJoin(this.mainTableClass, fieldName, (BaseEntity) joinTableClass.newInstance(), showFields);
        } catch (IllegalAccessException | InstantiationException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public JspElementFormSelect queryCondAddFormSelectJoin(Class tableClass, String fieldName, BaseEntity joinTable, String... showFields) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementFormSelect temp = this.genJspElementFormSelectJoin(tableClass, fieldName, joinTable, showFields);
        JspElementSpan span = JspElementFormSelect.findMatchPureSpan(fieldChineseName);
        this.queryCondList.add(span);
        temp.setCorrespondJspElement(span);
        this.queryCondList.add(temp);
        return temp;
    }

    public JspElementTagLibExcel toolButtonAddTagExcel(String tagType) {
        return this.toolButtonAddTagExcel(tagType, this.controllerSavePath, null);
    }

    public JspElementTagLibExcel toolButtonAddTagExcel(String tagType, Map<String, String> urlParams) {
        return this.toolButtonAddTagExcel(tagType, this.controllerSavePath, urlParams);
    }

    public JspElementTagLibExcel toolButtonAddTagExcel(String tagType, String url, Map<String, String> urlParams) {
        JspElementTagLibExcel je = (JspElementTagLibExcel) JspElementTagLibExcel.newInstance(tagType, url, urlParams);
        return je;
    }

    public JspElementTagLibDealRow toolButtonAddRowTag(String title) {
        return this.toolButtonAddRowTag(this.controllerFormPath, title, null);
    }

    public JspElementTagLibDealRow toolButtonAddRowTag(String url, String title, Map<String, String> urlParams, String... label) {
        String labelTemp = label.length > 0 ? label[0] : null;
        return this.toolButtonAddTagCrud("table:addRow", null, url, title, labelTemp, (String) null, false, (String) null, (String) null, urlParams);
    }

    public JspElementTagLibDealRow toolButtonDelRowTag(String confirmTip) {
        return this.toolButtonDelRowTag(this.controllerBatchDeletePath, confirmTip, null);
    }

    public JspElementTagLibDealRow toolButtonDelRowTag(String url, String confirmTip, Map<String, String> urlParams) {
        String id = "contentTable";
        this.ifShowCheckBox = true;
        return this.toolButtonAddTagCrud("table:delRow", id, url, null, null, confirmTip, false, null, null, urlParams);
    }

    public JspElementTagLibDealRow toolButtonCustomBatchTagCrud(String url, String label, String confirmTip, Map<String, String> urlParams) {
        String id = "contentTable";
        this.ifShowCheckBox = true;
        return this.toolButtonAddTagCrud("table:batchOperation", id, url, null, label, confirmTip, false, null, null, urlParams);
    }

    private JspElementTagLibDealRow toolButtonAddTagCrud(String tagType, String id, String url, String title, String label, String confirmTip, boolean needTarget, String width, String height, Map<String, String> urlParams) {
        JspElementTagLibDealRow je = JspElementTagLibDealRow.newInstance(tagType, id, url, title, label, confirmTip, needTarget, width, height, urlParams);
        this.toolButtonList.add(je);
        return je;
    }

    public JspElementTab toolButtonAddTab(String url, String tabOptionDesc, boolean ifmatchValue, Map<String, String> urlParams) {
        if (StringUtils.isEmpty(this.toolButtonTagHtml)) {
            MyStringBuffer sb = MyStringBuffer.newInstance();
            sb.appendK("<div class=\"layui-tab layui-tab-brief\" lay-filter=\"demo\">");
            sb.appendK("   <ul class=\"layui-tab-title\">");
            sb.appendK("   </ul>");
            sb.appendK("</div>");
            this.toolButtonTagHtml = sb.toString();
        }

        JspElementTab je = (JspElementTab) JspElementTab.newInstance(this.elParse, url, tabOptionDesc, ifmatchValue, urlParams);
        this.toolButtonTagHtml = this.toolButtonTagHtml.replace("   </ul>", je.getHtml() + "\n   </ul>");
        return je;
    }

    public void tableDataSmartJoin(Class tableClass, String fieldName) {
        this.tableDataSmartJoin(tableClass, fieldName, (String) null);
    }

    public void tableDataSmartJoin(Class tableClass, String fieldName, String fieldAlias, String... pointJoinFiled) {
        if (this.mainTableClass == tableClass) {
            this.tableDataSmart(fieldName);
        }

        try {
            if (StringUtils.isEmpty(fieldAlias)) {
                fieldAlias = fieldName;
            }

            JoinTableBean destJtb = this.autoJoinTable(tableClass, null);
            /*if (pointJoinFiled != null && pointJoinFiled.length > 0) {
                Map pointFieldMap = MapUtils.stringArrayToMap(pointJoinFiled);
                Iterator it = destJtb.getOnConditions().iterator();

                while (it.hasNext()) {
                    JoinOnBean job = (JoinOnBean) it.next();
                    if (!StringUtils.isEmpty(job.getMainFieldName()) && !pointFieldMap.containsKey(StringUtils.toCamelCase(job.getMainFieldName()))) {
                        it.remove();
                    }
                }
            }*/

            destJtb.setJoinKind(JoinTableBean.LEFT_JOIN);
            destJtb.putSelect(fieldName, fieldAlias);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        (new SmartJspGen(this.mainTableClass, this)).tableDataGen(new String[]{fieldAlias});
    }

    public void tableDataSmart(Class tableClass, String... fieldNames) {
        (new SmartJspGen(tableClass, this)).tableDataGen(fieldNames);
    }

    public <T extends BaseEntity> void tableDataSmart(SFunction<T, ?>... getName) {
        (new SmartJspGen(this.mainTableClass, this)).tableDataGen(getName);

    }

    public void tableDataSmart(String... fieldNames) {
        (new SmartJspGen(this.mainTableClass, this)).tableDataGen(fieldNames);
    }

    public JspElementImg tableDataAddImg(String fieldName, String alt, String width, String height) {
        return this.tableDataAddImg(this.mainTableClass, fieldName, alt, width, height);
    }

    public JspElementImg tableDataAddImg(String fieldName, String alt, String style) {
        return this.tableDataAddImg(this.mainTableClass, fieldName, alt, style);
    }

    public JspElementImg tableDataAddImg(Class tableClass, String fieldName, String alt, String style) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementImg jei = (JspElementImg) JspElementImg.newInstance(tableClass, fieldName, alt, style);
        this.tableThList.add(JspElementImg.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(jei);
        return jei;
    }

    public JspElementImg tableDataAddImg(Class tableClass, String fieldName, String alt, String width, String height) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementImg jei = (JspElementImg) JspElementImg.newInstance(tableClass, fieldName, alt, width, height);
        jei.genCode();
        this.tableThList.add(JspElementImg.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(jei);
        return jei;
    }

    public void tableDataAddTDMuti(String... fieldNames) {
        String[] var5 = fieldNames;
        int var4 = fieldNames.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            String fieldName = var5[var3];
            this.tableDataAddTD(fieldName);
        }

    }

    public <T extends BaseEntity> JspElementTD tableDataAddTD(Class tableClass, SFunction<T, ?> fieldName) {
        JspElementTD temp = JspElementTD.newInstance(tableClass, fieldName);
        temp.genCode();
        this.tableThList.add(JspElementTD.findMatchPureTh("测试"));
        this.tableDataList.add(temp);
        return temp;
    }

    public JspElementTD tableDataAddTD(Class tableClass, String fieldName) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementTD temp = JspElementTD.newInstance(tableClass, fieldName);
        temp.genCode();
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
    }

    public JspElementTD tableDataAddTDForVirField(String fieldName, String fieldChineseName) {
        JspElementTD temp = JspElementTD.newInstance(this.mainTableClass, fieldName);
        temp.genCode();
        fieldChineseName = StringUtils.isEmpty((CharSequence) this.currFieldChineseNameMap.get(fieldName)) ? fieldChineseName : (String) this.currFieldChineseNameMap.get(fieldName);
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
    }

    public JspElementTD tableDataAddTD(String fieldName) {
        return this.tableDataAddTD(this.mainTableClass, fieldName);
    }

    public JspElementTD tableDataAddTDByFinder(Class tableClass, String fieldName) {
        return this.tableDataAddTDByFunction(tableClass, fieldName, "FieldValueConvertFinder", "findDataValue");
    }

    public JspElementTD tableDataAddTDByFinder(String fieldName) {
        return this.tableDataAddTDByFinder(this.mainTableClass, fieldName);
    }


    public <T extends BaseEntity, W extends BaseEntity> JspElementTD tableDataAddTDByJoinTable(SFunction<T, ?> mainTableOnColumn, Class<W> joinTableClass, SFunction<W, ?> childTableOnColumn) {
        //String idValue = JspElement.getEntityNameByClass(this.mainTableClass, false) + "." + fieldName;
        /*String joinTableEntityName = "";
        int var8 = joinTableNames.length;

        for (int var7 = 0; var7 < var8; ++var7) {
            String joinTableName = joinTableNames[var7];
            if (StringUtils.isEmpty(joinTableEntityName)) {
                joinTableEntityName = joinTableName;
            } else {
                joinTableEntityName = joinTableEntityName + "," + joinTableName;
            }
        }*/

/*
        Map<String, Object> extendParams = new HashMap<>();
        extendParams.put("idValue", joinTableClass.getName());
        extendParams.put("joinTableEntityName", joinTableClass.getName());
        extendParams.put("showFields", childTableOnColumn);
        JspElementTD temp = JspElementTD.newInstance(this.mainTableClass, mainTableOnColumn, "JspTool", "findJoinTableDataValue", extendParams);
        String fieldChineseName = JspElement.findTableFieldChineseName(this.mainTableClass, fieldName, this.currFieldChineseNameMap);
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
*/

        return null;
    }

    public JspElementTD tableDataAddTDByJoinTable(String fieldName, Class joinTableClass, String... showFields) {
        return this.tableDataAddTDByJoinTable(fieldName, new Class[]{joinTableClass}, showFields);
    }


    public <T extends BaseEntity> void tableDataAddTD(SFunction<T, ?>[] getName) {
        for (SFunction<T, ?> tsFunction : getName) {
            this.tableDataAddTD(this.mainTableClass, tsFunction);
        }
    }

    public <T extends BaseEntity> JspElementTD tableDataAddTD(SFunction<T, ?> getName) {
        return this.tableDataAddTD(this.mainTableClass, getName);
    }

    public JspElementTD tableDataAddTDByJoinTable(String fieldName, Class[] joinTableClasss, String... showFields) {
        String[] joinTableNames = new String[joinTableClasss.length];

        for (int i = 0; i < joinTableClasss.length; ++i) {
            joinTableNames[i] = JspElement.getEntityNameByClass(joinTableClasss[i], true);
        }

        return this.tableDataAddTDByJoinTable(fieldName, joinTableNames, showFields);
    }

    public JspElementTD tableDataAddTDByJoinTable(String fieldName, String[] joinTableNames, String... showFields) {
        String idValue = JspElement.getEntityNameByClass(this.mainTableClass, false) + "." + fieldName;
        String joinTableEntityName = "";
        int var8 = joinTableNames.length;

        for (int var7 = 0; var7 < var8; ++var7) {
            String joinTableName = joinTableNames[var7];
            if (StringUtils.isEmpty(joinTableEntityName)) {
                joinTableEntityName = joinTableName;
            } else {
                joinTableEntityName = joinTableEntityName + "," + joinTableName;
            }
        }

        Map<String, Object> extendParams = new HashMap<>();
        extendParams.put("idValue", idValue);
        extendParams.put("joinTableEntityName", joinTableEntityName);
        extendParams.put("showFields", showFields);
        JspElementTD temp = JspElementTD.newInstance(this.mainTableClass, fieldName, "JspTool", "findJoinTableDataValue", extendParams);
        temp.genCode();
        String fieldChineseName = JspElement.findTableFieldChineseName(this.mainTableClass, fieldName, this.currFieldChineseNameMap);
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
    }

    public JspElementTD tableDataAddTDForDate(String fieldName) {
        return this.tableDataAddTDByFunction(this.mainTableClass, fieldName, "DateUtils", "formatDate");
    }

    public JspElementTD tableDataAddTDForDate(String fieldName, String formatPattern) {
        JspElementTD date = this.tableDataAddTDByFunction(this.mainTableClass, fieldName, "DateUtils", "formatDate");
        String entityName = JspElementTD.getEntityNameByClass(this.mainTableClass, new boolean[]{false});
        date.attribute.put("tjText", date.createAttrDateFormatElConvert(entityName, fieldName, "formatDate", formatPattern));
        return date;
    }

    public JspElementTD tableDataAddTDForDate(Class tableClass, String fieldName, String formatPattern) {
        JspElementTD date = this.tableDataAddTDByFunction(tableClass, fieldName, "DateUtils", "formatDate");
        String entityName = JspElementTD.getEntityNameByClass(tableClass, new boolean[]{false});
        date.attribute.put("tjText", date.createAttrDateFormatElConvert(entityName, fieldName, "formatDate", formatPattern));
        return date;
    }

    public JspElementTD tableDataAddTDByFunction(Class tableClass, String fieldName, String toolName, String functionName) {
        JspElementTD temp = (JspElementTD) JspElementTD.newInstance(tableClass, fieldName, toolName, functionName, new Map[0]);
        temp.genCode();
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
    }

    public JspElementTD tableDataAddTDStatic(String textContent, String fieldChineseName) {
        JspElementTD temp = (JspElementTD) JspElementTD.newInstance(textContent);
        temp.genCode();
        this.tableThList.add(JspElementTD.findMatchPureTh(fieldChineseName));
        this.tableDataList.add(temp);
        return temp;
    }

    private JspElement findElementByName(List<JspElement> jeList, String fieldName) {
        Iterator var4 = jeList.iterator();

        JspElement je;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            je = (JspElement) var4.next();
        } while (!StringUtils.isNotEmpty(je.getEntityName()) || !je.getFieldName().equals(fieldName));

        return je;
    }

    public Map<String, String> buildParams(Map<String, String> origin, String key, String value) {
        if (origin == null) {
            origin = new HashMap<>();
        }

        origin.put(key, value);
        return origin;
    }

    public Map<String, String> buildElParams(String key, String value) {
        Map origin = new HashMap();
        origin.put(key, "${" + value + "}");
        return origin;
    }

    public Map<String, String> buildElParams(Map<String, String> origin, String key, String value) {
        if (origin == null) {
            origin = new HashMap();
        }

        origin.put(key, "${" + value + "}");
        return origin;
    }

    public DynamicJspListConfigVO(Class mainTableClass) {
        super(mainTableClass);
        //this.tableDataAddTDStatic("(page.pageNo-1)*page.pageSize+status1.count", "序号");
    }

    public DynamicJspListConfigVO(BaseEntity be, String dynamicManageTitleName, String dynamicFormUrl) {
        super(be, dynamicManageTitleName, dynamicFormUrl);
        //this.tableDataAddTDStatic("(page.pageNo-1)*page.pageSize+status1.count", "序号");
    }

    public static DynamicJspListConfigVO newInstance(BaseEntity be, String dynamicManageTitleName, String dynamicFormUrl) {
        return new DynamicJspListConfigVO(be, dynamicManageTitleName, dynamicFormUrl);
    }

    public TjList<JspElement> getQueryCondList() {
        return this.queryCondList;
    }

    public void setQueryCondList(TjList<JspElement> queryCondList) {
        this.queryCondList = queryCondList;
    }

    public TjList<JspElement> getToolButtonList() {
        return this.toolButtonList;
    }

    public void setToolButtonList(TjList<JspElement> toolButtonList) {
        this.toolButtonList = toolButtonList;
    }

    public TjList<JspElement> getTableDataList() {
        return this.tableDataList;
    }

    public void setTableDataList(TjList<JspElement> tableDataList) {
        this.tableDataList = tableDataList;
    }

    public TjList<JspElement> getTableThList() {
        return this.tableThList;
    }

    public void setTableThList(TjList<JspElement> tableThList) {
        this.tableThList = tableThList;
    }

    public TjList<JspElement> getTableButtonList() {
        return this.tableButtonList;
    }

    public void setTableButtonList(TjList<JspElement> tableButtonList) {
        this.tableButtonList = tableButtonList;
    }

    public List<String> getQueryCondEles() {
        List<String> result = new ArrayList<>();
        Iterator var3 = this.queryCondList.iterator();

        while (var3.hasNext()) {
            JspElement je = (JspElement) var3.next();
            result.add(je.getHtml());
        }

        System.out.println("QueryCondEles:\n" + result);
        return result;
    }

    public List<String> getToolButtonEles() {
        List<String> result = new ArrayList<>();
        Iterator var3 = this.toolButtonList.iterator();

        while (var3.hasNext()) {
            JspElement je = (JspElement) var3.next();
            result.add(je.getHtml());
        }

        System.out.println("ToolButtonEles:\n" + result);
        return result;
    }

    public List<String> getTableDataListEles() {
        List<String> result = new ArrayList<>();
        Iterator var3 = this.tableDataList.iterator();

        while (var3.hasNext()) {
            JspElement je = (JspElement) var3.next();
            result.add(je.getHtml());
        }

        System.out.println("TableDataList:\n" + result);
        return result;
    }

    public List<String> getTableThListEles() {
        List<String> result = new ArrayList<>();
        Iterator var3 = this.tableThList.iterator();

        while (var3.hasNext()) {
            JspElement je = (JspElement) var3.next();
            result.add(je.getHtml());
        }

        System.out.println("TableThListEles:\n" + result);
        return result;
    }

    public List<String> getTableButtonListEles() {
        List<String> result = new ArrayList<>();
        Iterator var3 = this.tableButtonList.iterator();

        while (var3.hasNext()) {
            JspElement je = (JspElement) var3.next();
            result.add(je.getHtml());
        }

        System.out.println("TableButtonListEles:\n" + result);
        return result;
    }

    public boolean isIfShowCheckBox() {
        return this.ifShowCheckBox;
    }

    public void setIfShowCheckBox(boolean ifShowCheckBox) {
        this.ifShowCheckBox = ifShowCheckBox;
    }

    public String getToolButtonTagHtml() {
        return this.toolButtonTagHtml;
    }

    public void setToolButtonTagHtml(String toolButtonTagHtml) {
        this.toolButtonTagHtml = toolButtonTagHtml;
    }


}

