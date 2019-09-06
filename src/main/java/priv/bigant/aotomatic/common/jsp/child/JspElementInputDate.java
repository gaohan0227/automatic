package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.StringUtils;

public class JspElementInputDate extends JspElementInput {
    public static JspElementInputDate newInstance(Class baseEntity, String fieldName, String a) {
        return new JspElementInputDate(baseEntity, fieldName, a);
    }

    public void initDefaultAttribute() {
        this.attribute.put(ID,this.createAttrSimple(ID, ""));
        this.attribute.put(VALUE,this.createAttrSimple(VALUE, ""));
        this.attribute.put("maxlength",this.createAttrSimple("maxlength", ""));
        this.attribute.put(NAME,this.createAttrSimple(NAME, ""));
        this.attribute.put(TYPE,this.createAttrSimple(TYPE, "text"));
        this.attribute.put(CLASS,this.createAttrSimple(CLASS, "laydate-icon form-control layer-date input-sm"));
    }

    public JspElementInputDate(Class entityClass, String fieldName, String dateFormat) {
        super(entityClass, fieldName);
        this.elementCode = "input";
        this.childElementCode = "inputDate";
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = "yyyy-MM-dd";
        }
        this.attribute.put(VALUE, this.createAttrDateFormatElConvert(getEntityNameByClass(entityClass, true), fieldName, "formatDate", dateFormat));
    }
}
