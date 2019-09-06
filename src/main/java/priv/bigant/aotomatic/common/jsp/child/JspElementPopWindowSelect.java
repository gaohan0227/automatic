package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementPopWindowSelect extends JspElement {
    private String parentName;
    private String childName;
    private String errorMessage;

    public String getParentName() {
        return this.parentName;
    }

    public String getChildName() {
        return this.childName;
    }

    public static JspElementPopWindowSelect newInstance(Class tableClass, String fieldName, String popWindowEntityName, String showFields, String callBackMethodName, boolean mutilSelect, String url, Map<String, String> urlParams) {
        return new JspElementPopWindowSelect(tableClass, fieldName, popWindowEntityName, showFields, callBackMethodName, mutilSelect, url, urlParams);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setParentName(String a) {
        this.attribute.put(PARENT_NAME, this.createAttrSimple(PARENT_NAME, a));
        this.parentName = a;
    }

    public void setChildName(String a) {
        this.attribute.put(CHILD_NAME, this.createAttrSimple(CHILD_NAME, a));
        this.childName = a;
    }

    public void setErrorMessage(String a) {
        this.attribute.put(ERROR_MESSAGE, this.createAttrSimple(ERROR_MESSAGE, a));
        this.errorMessage = a;
    }

    public JspElementPopWindowSelect(Class tableClass, String fieldName, String popWindowEntityName, String showFields, String callBackMethodName, boolean mutilSelect, String url, Map<String, String> urlParams) {
        super(getEntityNameByClass(tableClass, true), fieldName);
        this.attribute.put(NAME, this.createAttrSimple(NAME, fieldName));
        this.attribute.put(VALUE, this.createAttrBasicEl(getEntityNameByClass(tableClass, true), fieldName));
        this.attribute.put("url", this.createAttrSimple("url", this.appendParamsForUrl(url, urlParams)));
        this.attribute.put(ENTITY_NAME, this.createAttrSimple(ENTITY_NAME, popWindowEntityName));
        this.attribute.put(SHOW_FIELDS, this.createAttrSimple(SHOW_FIELDS, showFields));
        this.attribute.put(CALL_BACK_METHOD_NAME, this.createAttrSimple(CALL_BACK_METHOD_NAME, callBackMethodName));
        this.attribute.put(MUTIL_SELECT, this.createAttrSimple(MUTIL_SELECT, String.valueOf(mutilSelect)));
        this.elementCode = "table:popWindowSelect";
    }

    public void initDefaultAttribute() {
        this.attribute.put(NAME, this.createAttrBasicEl(NAME, null));
        this.attribute.put(VALUE, this.createAttrBasicEl(VALUE, null));
        this.attribute.put(ENTITY_NAME, null);
        this.attribute.put(SHOW_FIELDS, null);
        this.attribute.put(WINDOWS_TITLE, null);
        this.attribute.put("url", this.createAttrBasicEl("url", null));
        this.attribute.put(CALL_BACK_METHOD_NAME, null);
        this.attribute.put(MUTIL_SELECT, null);
        this.attribute.put(PARENT_NAME, null);
        this.attribute.put(CHILD_NAME, null);
        this.attribute.put("required", this.createAttrSimple("required", "true"));
        this.attribute.put(ERROR_MESSAGE, null);
    }
}
