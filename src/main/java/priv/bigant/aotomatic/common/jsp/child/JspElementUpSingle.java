package priv.bigant.aotomatic.common.jsp.child;

import org.apache.commons.lang3.StringUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementUpSingle extends JspElement {
    public static JspElementUpSingle newInstance(Class entityClass, String fieldName) {
        return new JspElementUpSingle(entityClass, fieldName, null, true, null, null);
    }

    public void initDefaultAttribute() {
        this.attribute.put(NAME, this.createAttrSimple(NAME, "single"));
        this.attribute.put(ECHO, this.createAttrSimple(ECHO, ""));
            this.attribute.put(ACCEPT, this.createAttrSimple(ACCEPT, "image/*"));
        this.attribute.put("required", this.createAttrSimple("required", "true"));
        this.attribute.put(FILE_TYPE, this.createAttrSimple(FILE_TYPE, "1"));
        this.attribute.put(IF_NULL_TIP, this.createAttrSimple(IF_NULL_TIP, "无"));
        this.attribute.put(SAVE_URL_FIELD, this.createAttrSimple(SAVE_URL_FIELD, null));
        this.attribute.put(UP_FILE_TYPE, this.createAttrSimple(UP_FILE_TYPE, null));
    }

    public static JspElementUpSingle newInstance(Class entityClass, String fieldName, String name, boolean isRequired) {
        return new JspElementUpSingle(entityClass, fieldName, name, isRequired, (String)null, (String)null);
    }

    public JspElementUpSingle(Class entityClass, String fieldName, String name, boolean isRequired, String accept, String fileType) {
        super(getEntityNameByClass(entityClass, false), fieldName);
        this.elementCode = "up:single";
        this.attribute.put(NAME, this.createAttrSimple(NAME, name));
        String entityName = getEntityNameByClass(entityClass, false);
        this.attribute.put(ECHO, this.createAttrBasicEl(entityName, fieldName));
        if (StringUtils.isNotEmpty(accept)) {
            this.attribute.put(ACCEPT, this.createAttrSimple(ACCEPT, accept));
        }

        this.attribute.put("required", this.createAttrSimple("required", isRequired ? "true" : "false"));
        this.attribute.put(FILE_TYPE, this.createAttrSimple(FILE_TYPE, fileType));
        this.attribute.put(IF_NULL_TIP, this.createAttrSimple(IF_NULL_TIP, fileType));
        this.attribute.put(UP_FILE_TYPE, this.createAttrSimple(UP_FILE_TYPE, null));
    }

    public void genCode() {
        super.genCode();
    }
}
