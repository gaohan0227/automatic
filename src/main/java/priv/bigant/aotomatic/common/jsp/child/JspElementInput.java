package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;
import priv.bigant.aotomatic.common.table.TableColumn;

public class JspElementInput extends JspElement {
    public void genCode() {
        super.genCode();
    }

    public static JspElementInput newInstance(Class clazz, String a) {
        return new JspElementInput(clazz, a);
    }

    public void initDefaultAttribute() {
        this.attribute.put(ID, this.createAttrSimple(ID, ""));
        this.attribute.put(NAME, this.createAttrSimple(NAME, ""));
        this.attribute.put(TYPE, this.createAttrSimple(TYPE, "text"));
        this.attribute.put("maxlength", this.createAttrSimple("maxlength", ""));
        this.attribute.put(CLASS, this.createAttrSimple(CLASS, ""));
    }

    public JspElementInput(Class entityClass, String fieldName) {
        super(getEntityNameByClass(entityClass, true), fieldName);
        this.elementCode = "input";
        this.attribute.put(ID, this.createAttrSimple(ID, fieldName));
        this.attribute.put(NAME, this.createAttrSimple(NAME, fieldName));
        TableColumn a;
        if ((a = findTableColumn(entityClass, fieldName)) != null) {
            String length = a.getMaxLength();
            if ("date".equals(a.getJdbcType()) ||"datetime".equals(a.getJdbcType())) {
                length = "20";
            }

            this.attribute.put("maxlength", this.createAttrSimple("maxlength", length));
        }

    }
}
