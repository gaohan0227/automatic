package priv.bigant.aotomatic.common.jsp;

import priv.bigant.aotomatic.StringUtils;
import priv.bigant.aotomatic.common.BaseEntity;
import priv.bigant.aotomatic.common.TjList;
import priv.bigant.aotomatic.common.jsp.child.*;

import java.util.*;

public class DynamicJspFormConfigVO extends DynamicJspBaseConfigVO {
    private int columnNum = 2;
    private TjList<JspElement> labelTdList = new TjList();
    private TjList<JspElement> dataTdList = new TjList();
    private String enctype = "application/x-www-form-urlencoded";
    private final String multipart_form_data = "multipart/form-data";
    Map<String, List<JspElement>> oneLabelMutiDataMap = new HashMap<>();

    public DynamicJspFormConfigVO(Class mainTableClass) {
        super(mainTableClass);
    }

    public void fromSmart(Class tableClass, String... fieldNames) {
        (new SmartJspGen(tableClass, this)).formGen(fieldNames);
    }

    public void formSmart(String... fieldNames) {
        (new SmartJspGen(this.mainTableClass, this)).formGen(fieldNames);
    }

    public void removeMustInputField(String... fieldName) {
        if (fieldName.length >= 1) {
            Map<String, String> mustInput = new HashMap<>();//ListUtils.ListToMap(ListUtils.arrayToList(fieldName));
            if (mustInput.containsKey("")) {
                mustInput.remove("");
            }

            if (mustInput.size() >= 1) {
                Iterator var4 = this.dataTdList.iterator();

                while(true) {
                    while(var4.hasNext()) {
                        JspElement je = (JspElement)var4.next();
                        String name;
                        JspElement destLabelTd;
                        if (je.isIfFormElement()) {
                            name = ((JspAttribute)je.attribute.get("path")).getAttrCovertValue();
                            if (mustInput.containsKey(name)) {
                                je.removeValidateByClass("required");
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealRemoveMustInputFont(destLabelTd);
                            }
                        } else if (je.getElementCode().equals("up:single")) {
                            name = je.attribute.get("name") == null ? "" : ((JspAttribute)je.attribute.get("name")).getAttrCovertValue();
                            if (name == null) {
                                name = "";
                            }

                            if (mustInput.containsKey(name)) {
                                je.removeValidateWithValue("required");
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealRemoveMustInputFont(destLabelTd);
                            }
                        } else {
                            name = je.attribute.get("name") == null ? "" : ((JspAttribute)je.attribute.get("name")).getAttrCovertValue();
                            String id = je.attribute.get("id") == null ? "" : ((JspAttribute)je.attribute.get("id")).getAttrCovertValue();
                            if (name == null) {
                                name = "";
                            }

                            if (id == null) {
                                id = "";
                            }

                            if (mustInput.containsKey(name) || mustInput.containsKey(id)) {
                                je.removeValidateByClass("required");
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealRemoveMustInputFont(destLabelTd);
                            }
                        }
                    }

                    return;
                }
            }
        }
    }

    public void setMustInputField(String... fieldName) {
        if (fieldName.length >= 1) {
            Map<String, String> mustInput = new HashMap<>();//ListUtils.ListToMap(ListUtils.arrayToList(fieldName));
            if (mustInput.containsKey("")) {
                mustInput.remove("");
            }

            if (mustInput.size() >= 1) {
                Iterator var4 = this.dataTdList.iterator();

                while(true) {
                    while(var4.hasNext()) {
                        JspElement je = (JspElement)var4.next();
                        String name;
                        JspElement destLabelTd;
                        if (je.isIfFormElement()) {
                            name = je.attribute.get("path").getAttrCovertValue();
                            if (mustInput.containsKey(name)) {
                                je.addValidateByClass("required");
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealAddMustInputFont(destLabelTd);
                            }
                        } else if (je.getElementCode().equals("up:single")) {
                            name = je.attribute.get("name") == null ? "" : ((JspAttribute)je.attribute.get("name")).getAttrCovertValue();
                            if (name == null) {
                                name = "";
                            }

                            if (mustInput.containsKey(name)) {
                                je.addValidateWithValue("required", "true", (String)null);
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealAddMustInputFont(destLabelTd);
                            }
                        } else {
                            name = je.attribute.get("name") == null ? "" : ((JspAttribute)je.attribute.get("name")).getAttrCovertValue();
                            String id = je.attribute.get("id") == null ? "" : ((JspAttribute)je.attribute.get("id")).getAttrCovertValue();
                            if (name == null) {
                                name = "";
                            }

                            if (id == null) {
                                id = "";
                            }

                            if (mustInput.containsKey(name) || mustInput.containsKey(id)) {
                                je.addValidateByClass("required");
                                destLabelTd = je.getCorrespondJspElement();
                                this.dealAddMustInputFont(destLabelTd);
                            }
                        }
                    }

                    return;
                }
            }
        }
    }

    private void dealRemoveMustInputFont(JspElement destLabelTd) {
        if (destLabelTd != null) {
            JspElementLabel jlabel = (JspElementLabel)destLabelTd.getChildList().get(0);
            jlabel.getChildList().clear();
        }

    }

    public void dealAddMustInputFont(JspElement destLabelTd) {
        if (destLabelTd != null) {
            JspElementLabel jlabel = (JspElementLabel)destLabelTd.getChildList().get(0);
            jlabel.getChildList().clear();
            jlabel.addChild(JspElementFont.newInstance("red", "*"));
        }

    }

    public JspElementInputDate addInputDate(String fieldName) {
        return this.addInputDate(this.mainTableClass, fieldName, (String)null);
    }

    public JspElementInputDate addInputDate(Class tableClass, String fieldName) {
        return this.addInputDate(tableClass, fieldName, (String)null);
    }

    public JspElementInputDate addInputDate(Class tableClass, String fieldName, String formatPattern) {
        if (tableClass == null) {
            tableClass = this.mainTableClass;
        }

        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementInputDate temp = (JspElementInputDate)JspElementInputDate.newInstance(tableClass, fieldName, formatPattern);
        temp.parseEl(this.elParse);
        this.addLaydateJs(fieldName);
        JspElementTD jtd = this.getLabelTdByChineseName(fieldChineseName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.setCorrespondJspElement(jtd);
        temp.addSimpleAttr("class", "laydate-icon form-control layer-date");
        return temp;
    }

    public JspElementFormSelect addFormSelect(String fieldName) {
        return this.addFormSelect(this.mainTableClass, fieldName);
    }

    public JspElementFormSelect addFormSelect(Class tableClass, String fieldName) {
        JspElementFormSelect temp = this.genJspElementFormSelect(tableClass, fieldName);
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementTD jtd = this.getLabelTdByChineseName(fieldChineseName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementFormSelect addFormSelectJoin(String fieldName, Class joinTableClass, String... showFields) {
        try {
            return this.addFormSelectJoin(this.mainTableClass, fieldName, new BaseEntity[]{(BaseEntity)joinTableClass.newInstance()}, showFields);
        } catch (IllegalAccessException | InstantiationException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public JspElementFormSelect addFormSelectJoin(String fieldName, String[] joinTableNames, String... showFields) {
        BaseEntity[] joinTables = new BaseEntity[joinTableNames.length];

        for(int i = 0; i < joinTableNames.length; ++i) {
            String className = joinTableNames[i];

            /*try {
                joinTables[i] = (BaseEntity)PackageUtil.findDestClass(className).newInstance();
            } catch (IllegalAccessException | InstantiationException var8) {
                var8.printStackTrace();
            }*/
        }

        return this.addFormSelectJoin(this.mainTableClass, fieldName, joinTables, showFields);
    }

    public JspElementFormSelect addFormSelectJoin(Class tableClass, String fieldName, BaseEntity[] joinTables, String... showFields) {
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        JspElementFormSelect dest = null;
        JspElementFormSelect backup = null;
        int var10 = joinTables.length;

        for(int var9 = 0; var9 < var10; ++var9) {
            BaseEntity joinTable = joinTables[var9];
            JspElementFormSelect temp = this.genJspElementFormSelectJoin(tableClass, fieldName, joinTable, showFields);
            List<JspElement> childList = temp.getChildList();
            JspElementFormSelectOption hasData = null;
            Iterator var16 = childList.iterator();

            while(var16.hasNext()) {
                JspElementFormSelectOption jj = (JspElementFormSelectOption)var16.next();
                if (jj.attribute.containsKey("items") && jj.attribute.get("items") != null) {
                    hasData = jj;
                    break;
                }
            }

            Object dataCollection = hasData.attribute.get("items").getSelectDataCollection();
            if (dataCollection != null) {
                if (dataCollection instanceof List) {
                    if (((List)dataCollection).size() > 0) {
                        dest = temp;
                        break;
                    }
                } else if (dataCollection instanceof Map && ((Map)dataCollection).size() > 0) {
                    dest = temp;
                    break;
                }
            }

            backup = temp;
        }

        if (dest == null) {
            dest = backup;
        }

        JspElementTD jtd = this.getLabelTdByChineseName(fieldChineseName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(dest);
        dest.setCorrespondJspElement(jtd);
        return dest;
    }

    public JspElementFormInput addFormInputForVirField(String fieldName, String fieldChineseName) {
        JspElementFormInput temp = (JspElementFormInput)JspElementFormInput.newInstance(this.mainTableClass, fieldName);
        temp.addSimpleAttr("class", "form-control");
        temp.genCode();
        if (this.currFieldChineseNameMap.containsKey(fieldName)) {
            fieldChineseName = (String)this.currFieldChineseNameMap.get(fieldName);
        }

        JspElementTD jtd = this.getLabelTdByChineseName(fieldChineseName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public void addFormInput(String... fieldNames) {
        String[] var5 = fieldNames;
        int var4 = fieldNames.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String fieldName = var5[var3];
            this.addFormInput(this.mainTableClass, fieldName);
        }

    }

    public JspElementFormInput addFormInput(Class tableClass, String fieldName) {
        JspElementFormInput temp = (JspElementFormInput)JspElementFormInput.newInstance(tableClass, fieldName);
        temp.addSimpleAttr("class", "form-control");
        temp.genCode();
        JspElementTD jtd = this.getLabelTd(fieldName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementFormTextarea addFormTextarea(String fieldName) {
        return this.addFormTextarea(this.mainTableClass, fieldName);
    }

    public JspElementFormTextarea addFormTextarea(Class tableClass, String fieldName) {
        JspElementFormTextarea temp = (JspElementFormTextarea)JspElementFormTextarea.newInstance(tableClass, fieldName);
        temp.addSimpleAttr("class", "form-control");
        temp.genCode();
        JspElementTD jtd = this.getLabelTd(fieldName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementPopWindowSelect addopWindowSelectMutil(String fieldName, String popWindowEntityName, String showFields, String callBackMethodName, String url, Map<String, String> urlParams) {
        return this.addopWindowSelect(this.mainTableClass, fieldName, popWindowEntityName, showFields, callBackMethodName, true, url, urlParams);
    }

    public JspElementPopWindowSelect addopWindowSelectSingle(String fieldName, String popWindowEntityName, String showFields, String callBackMethodName, String url, Map<String, String> urlParams) {
        return this.addopWindowSelect(this.mainTableClass, fieldName, popWindowEntityName, showFields, callBackMethodName, false, url, urlParams);
    }

    private JspElementPopWindowSelect addopWindowSelect(Class tableClass, String fieldName, String popWindowEntityName, String showFields, String callBackMethodName, boolean mutilSelect, String url, Map<String, String> urlParams) {
        JspElementPopWindowSelect temp = (JspElementPopWindowSelect)JspElementPopWindowSelect.newInstance(tableClass, fieldName, popWindowEntityName, showFields, callBackMethodName, mutilSelect, url, urlParams);
        String fieldChineseName = JspElement.findTableFieldChineseName(tableClass, fieldName, this.currFieldChineseNameMap);
        temp.setErrorMessage("请选择" + fieldChineseName);
        temp.genCode();
        JspElementTD jtd = this.getLabelTd(fieldName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.parseEl(this.elParse);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementRichTextEditor addRichTextEditor(String fieldName, Integer atLeastCharNum) {
        JspElementRichTextEditor temp = JspElementRichTextEditor.newInstance(this.mainTableClass, fieldName, atLeastCharNum);
        temp.genCode();
        JspElementTD jtd = this.getLabelTd(fieldName);
        this.labelTdList.add(jtd);
        this.dataTdList.add(temp);
        temp.parseEl(this.elParse);
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementUpSingle addSinglePic(String fieldName, String tipInfo) {
        JspElementSpan jes = (JspElementSpan)JspElementSpan.newInstance(tipInfo, "tip");
        JspElementUpSingle single = this.addSinglePic(this.mainTableClass, fieldName, (String)null);
        this.appendSameShowEle(single, jes);
        return single;
    }

    public JspElementUpSingle addSinglePic(String fieldName) {
        return this.addSinglePic(this.mainTableClass, fieldName, (String)null);
    }

    public JspElementUpSingle addSinglePic(Class tableClass, String fieldName, String accept) {
        JspElementUpSingle temp = (JspElementUpSingle)JspElementUpSingle.newInstance(tableClass, fieldName, accept, false);
        temp.parseEl(this.elParse);
        String fieldChineseName = JspElement.findTableFieldChineseName(this.mainTableClass, fieldName, this.currFieldChineseNameMap);
        JspElementTD jtd = this.getLabelTdByChineseName(fieldChineseName);
        this.labelTdList.add(jtd);
        temp.genCode();
        this.dataTdList.add(temp);
        this.enctype = "multipart/form-data";
        temp.setCorrespondJspElement(jtd);
        return temp;
    }

    public JspElementFormHidden addHiddenForm(String fieldName) {
        JspElementFormHidden temp = (JspElementFormHidden)JspElementFormHidden.newInstance(this.mainTableClass, fieldName);
        temp.genCode();
        this.hiddenList.add(temp);
        return temp;
    }

    private JspElementTD getLabelTd(String fieldName) {
        String fieldChineseName = JspElement.findTableFieldChineseName(this.mainTableClass, fieldName, this.currFieldChineseNameMap);
        return this.getLabelTdByChineseName(fieldChineseName);
    }

    private JspElementTD getLabelTdByChineseName(String fieldChineseName) {
        JspElementTD jet = (JspElementTD)JspElementTD.newInstance("class", "width-15 active");
        JspElementLabel jlabel = (JspElementLabel)JspElementLabel.newInstance("class", "pull-right", fieldChineseName);
        jet.getChildList().add(jlabel);
        jet.genCode();
        return jet;
    }

    public JspElement findElementByName(String fieldName) {
        Iterator var3 = this.dataTdList.iterator();

        JspElement je;
        do {
            if (!var3.hasNext()) {
                var3 = this.hiddenList.iterator();

                do {
                    if (!var3.hasNext()) {
                        return null;
                    }

                    je = (JspElement)var3.next();
                } while(!StringUtils.isNotEmpty(je.getEntityName()) || !fieldName.equals(je.getFieldName()));

                return je;
            }

            je = (JspElement)var3.next();
        } while(!StringUtils.isNotEmpty(je.getEntityName()) || !fieldName.equals(je.getFieldName()));

        return je;
    }

    public List<JspElement> findDataTdListByLabelTdIndex(String index) {
        List<JspElement> result = new ArrayList<>();
        if (this.oneLabelMutiDataMap.size() < 1) {
            result.add((JspElement)this.dataTdList.get(Integer.parseInt(index)));
            return result;
        } else {
            JspElement label = (JspElement)this.labelTdList.get(Integer.parseInt(index));
            Iterator var5 = this.dataTdList.iterator();

            while(var5.hasNext()) {
                JspElement je = (JspElement)var5.next();
                if (label.toString().equals(je.getCorrespondJspElement() == null ? "" : je.getCorrespondJspElement().toString())) {
                    result.add(je);
                }
            }

            return result;
        }
    }

    public void appendSameShowEle(JspElement main, JspElement child) {
        if (main.getCorrespondJspElement() == null) {
            System.out.println("元素" + main.getElementCode() + "找不到对应label，无法与" + child.getElementCode() + "并列，请联系管理员！");
        } else {
            child.setCorrespondJspElement(main.getCorrespondJspElement());
            this.dataTdList.add(child);
            if (this.oneLabelMutiDataMap.containsKey(main.getCorrespondJspElement().toString())) {
                List<JspElement> originList = (List)this.oneLabelMutiDataMap.get(main.getCorrespondJspElement().toString());
                if (!originList.contains(main)) {
                    originList.add(main);
                }

                originList.add(child);
            } else {
                List<JspElement> originList = new ArrayList<>();
                originList.add(main);
                originList.add(child);
                this.oneLabelMutiDataMap.put(main.getCorrespondJspElement().toString(), originList);
            }

        }
    }

    public DynamicJspFormConfigVO(BaseEntity be, String dynamicManageTitleName, String dynamicSavePath) {
        super(be, dynamicManageTitleName, (String)null);
        if (StringUtils.isNotEmpty(dynamicSavePath)) {
            this.controllerSavePath = dynamicSavePath;
        }

    }

    public static DynamicJspFormConfigVO newInstance(Class mainTableClass) {
        return new DynamicJspFormConfigVO(mainTableClass);
    }

    public static DynamicJspFormConfigVO newInstance(BaseEntity be, String dynamicManageTitleName, String dynamicSavePath) {
        return new DynamicJspFormConfigVO(be, dynamicManageTitleName, dynamicSavePath);
    }

    public int getColumnNum() {
        return this.columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public TjList<JspElement> getLabelTdList() {
        return this.labelTdList;
    }

    public void setLabelTdList(TjList<JspElement> labelTdList) {
        this.labelTdList = labelTdList;
    }

    public TjList<JspElement> getDataTdList() {
        return this.dataTdList;
    }

    public void setDataTdList(TjList<JspElement> dataTdList) {
        this.dataTdList = dataTdList;
    }

    public String getEnctype() {
        return this.enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }
}
