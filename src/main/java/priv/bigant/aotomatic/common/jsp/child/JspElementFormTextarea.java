package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;
import priv.bigant.aotomatic.common.table.TableColumn;

public class JspElementFormTextarea extends JspElement {
    public void initDefaultAttribute() {
        this.attribute.put(PATH, this.createAttrSimple(PATH, ""));
        this.attribute.put("htmlEscape", this.createAttrSimple("htmlEscape", "false"));
        this.attribute.put("maxlength", this.createAttrSimple("maxlength", ""));
        this.attribute.put(CLASS, this.createAttrSimple(CLASS, "form-control"));
    }

    public static JspElementFormTextarea newInstance(String a) {
        return newInstance((Class)null, a);
    }

    public JspElementFormTextarea(Class entityClass, String fieldName) {
        super(getEntityNameByClass(entityClass, true), fieldName);
        this.elementCode = "textarea";
        this.ifFormElement = true;
        this.attribute.put(PATH, this.createAttrSimple(PATH, fieldName));
        TableColumn a;
        if ((a = findTableColumn(entityClass, fieldName)) != null) {
            this.attribute.put("maxlength", this.createAttrSimple("maxlength", a.getMaxLength()));
        }

    }

    public static JspElementFormTextarea newInstance(Class entityClass, String fieldName) {
        return new JspElementFormTextarea(entityClass, fieldName);
    }
}
