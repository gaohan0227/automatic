package priv.bigant.aotomatic.common.jsp;

import priv.bigant.aotomatic.StringUtils;
import priv.bigant.aotomatic.common.*;
import priv.bigant.aotomatic.common.jsp.child.JspElementFormSelect;
import priv.bigant.aotomatic.common.jsp.child.JspElementFormSelectOption;

import java.util.*;

public class DynamicJspBaseConfigVO {
    protected String dynamicManageTitleName;
    protected String dynamicModelName;
    protected String mainTableName;
    protected Class<BaseEntity> mainTableClass;
    protected String controllerBasePath;
    protected String controllerDeletePath;
    protected String controllerBatchDeletePath;
    protected String controllerSavePath;
    protected String controllerFormPath;
    protected String controllerQueryPath;
    protected String shiroHasPermissionPrefix;
    protected BaseEntity currEntityObj;
    protected Map<String, String> idParamsMap;
    protected SpringElParse elParse;
    protected List<JspElement> hiddenList;
    protected List<String> laydateJsList;
    protected Map<String, String> currFieldChineseNameMap;
    private TjList<String> cssClassList;
    private TjList<String> jsFunctionList;
    private TjList<String> extendDynamicJsFileName;

    public TjList<String> getCssClassList() {
        return this.cssClassList;
    }

    public void setCssClassList(TjList<String> cssClassList) {
        this.cssClassList = cssClassList;
    }

    public TjList<String> getJsFunctionList() {
        return this.jsFunctionList;
    }

    public void setJsFunctionList(TjList<String> jsFunctionList) {
        this.jsFunctionList = jsFunctionList;
    }

    public DynamicJspBaseConfigVO(Class mainTableClass) {
        this.idParamsMap = new HashMap<>();
        this.hiddenList = new TjList();
        this.laydateJsList = new ArrayList<>();
        this.currFieldChineseNameMap = new HashMap<>();
        this.cssClassList = new TjList();
        this.jsFunctionList = new TjList();
        this.mainTableClass = mainTableClass;
        this.mainTableName = JspElement.getEntityNameByClass(mainTableClass, new boolean[]{false});
        this.controllerBasePath = this.mainTableName.toLowerCase() + "/" + this.mainTableName + "/";
        this.idParamsMap.put("id", "${" + this.mainTableName + ".id}");
        this.controllerDeletePath = this.controllerBasePath + "delete";
        this.controllerBatchDeletePath = this.controllerBasePath + "deleteAll";
        this.controllerQueryPath = this.controllerBasePath + "list";
        this.controllerFormPath = this.controllerBasePath + "form";
        this.controllerSavePath = this.controllerBasePath + "save";
        this.dynamicModelName = this.mainTableName;
        this.shiroHasPermissionPrefix = this.mainTableName + ":" + this.mainTableName + ":";
    }

    public DynamicJspBaseConfigVO(BaseEntity be, String dynamicManageTitleName, String dynamicFormUrl) {
        this(be.getClass());
        this.currEntityObj = be;
        this.elParse = SpringElParse.getInstance(be);
        this.dynamicManageTitleName = dynamicManageTitleName;
        if (StringUtils.isNotEmpty(dynamicFormUrl)) {
            this.controllerQueryPath = dynamicFormUrl;
        }

    }

    public void addLaydateJs(String fieldName) {
        MyStringBuffer sb = MyStringBuffer.newInstance();
        sb.appendK("laydate({");
        sb.appendK("    elem : '#", fieldName, "',");
        sb.appendK("    event : 'focus'");
        sb.append("});");
        this.laydateJsList.add(sb.toString());
    }

    public void modifyFieldTitle(String fieldName, String fieldChineseName) {
        this.currFieldChineseNameMap.put(fieldName, fieldChineseName);
    }

    protected JspElementFormSelect genJspElementFormSelectJoin(Class tableClass, String fieldName, BaseEntity joinTable, String... showFields) {
        JspElementFormSelect temp = (JspElementFormSelect)JspElementFormSelect.newInstance(tableClass, fieldName);
        JspElementFormSelectOption fix = (JspElementFormSelectOption)JspElementFormSelectOption.newInstance("", "请选择");
        JspElementFormSelectOption op = (JspElementFormSelectOption)JspElementFormSelectOption.newInstance(tableClass, fieldName, "JspTool", "findJoinTableData");
        this.elParse.getContext().addContext("joinTable", joinTable);
        this.elParse.getContext().addContext("showFields", showFields);
        op.initSelectDataCollection(this.elParse);
        temp.addChild(new JspElement[]{fix});
        temp.addChild(new JspElement[]{op});
        return temp;
    }

    protected JspElementFormSelect genJspElementFormSelect(Class tableClass, String fieldName) {
        JspElementFormSelect temp = (JspElementFormSelect)JspElementFormSelect.newInstance(tableClass, fieldName);
        JspElementFormSelectOption fix = (JspElementFormSelectOption)JspElementFormSelectOption.newInstance("", "请选择");
        JspElementFormSelectOption op = (JspElementFormSelectOption)JspElementFormSelectOption.newInstance(tableClass, fieldName, "FieldValueConvertFinder", "findData");
        op.initSelectDataCollection(this.elParse);
        temp.addChild(new JspElement[]{fix});
        temp.addChild(new JspElement[]{op});
        return temp;
    }

    protected JspElement[] addNewEleForArray(JspElement[] childArray, JspElement newele) {
        List<JspElement> dest = Arrays.asList(childArray);
        dest.add(newele);
        childArray = (JspElement[])dest.toArray(childArray);
        return childArray;
    }

    public String getDynamicManageTitleName() {
        return this.dynamicManageTitleName;
    }

    public void setDynamicManageTitleName(String dynamicManageTitleName) {
        this.dynamicManageTitleName = dynamicManageTitleName;
    }

    public String getDynamicModelName() {
        return this.dynamicModelName;
    }

    public void setDynamicModelName(String dynamicModelName) {
        this.dynamicModelName = dynamicModelName;
    }

    public Class getMainTableClass() {
        return this.mainTableClass;
    }

    public void setMainTableClass(Class mainTableClass) {
        this.mainTableClass = mainTableClass;
    }

    public String getControllerBasePath() {
        return this.controllerBasePath;
    }

    public void setControllerBasePath(String controllerBasePath) {
        this.controllerBasePath = controllerBasePath;
    }

    public String getControllerDeletePath() {
        return this.controllerDeletePath;
    }

    public void setControllerDeletePath(String controllerDeletePath) {
        this.controllerDeletePath = controllerDeletePath;
    }

    public String getControllerSavePath() {
        return this.controllerSavePath;
    }

    public void setControllerSavePath(String controllerSavePath) {
        this.controllerSavePath = controllerSavePath;
    }

    public String getControllerQueryPath() {
        return this.controllerQueryPath;
    }

    public void setControllerQueryPath(String controllerQueryPath) {
        this.controllerQueryPath = controllerQueryPath;
    }

    public String getMainTableName() {
        return this.mainTableName;
    }

    public void setMainTableName(String mainTableName) {
        this.mainTableName = mainTableName;
    }

    public String getControllerFormPath() {
        return this.controllerFormPath;
    }

    public void setControllerFormPath(String controllerFormPath) {
        this.controllerFormPath = controllerFormPath;
    }

    public List<String> getLaydateJsList() {
        return this.laydateJsList;
    }

    public void setLaydateJsList(List<String> laydateJsList) {
        this.laydateJsList = laydateJsList;
    }

    public TjList<String> getExtendDynamicJsFileName() {
        return this.extendDynamicJsFileName;
    }

    public void setExtendDynamicJsFileName(TjList<String> extendDynamicJsFileName) {
        this.extendDynamicJsFileName = extendDynamicJsFileName;
    }

    public List<JspElement> getHiddenList() {
        return this.hiddenList;
    }

    public void setHiddenList(List<JspElement> hiddenList) {
        this.hiddenList = hiddenList;
    }

    public BaseEntity getCurrEntityObj() {
        return this.currEntityObj;
    }

    public void setCurrEntityObj(BaseEntity<?> currEntityObj) {
        this.currEntityObj = currEntityObj;
    }
}