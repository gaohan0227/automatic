package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementRichTextEditor extends JspElement {
    public static JspElementRichTextEditor newInstance(Class entityClass, String fieldName, Integer a) {
        return new JspElementRichTextEditor(entityClass, fieldName, a);
    }

    public JspElementRichTextEditor(Class entityClass, String fieldName, Integer a) {
        super(getEntityNameByClass(entityClass, true), fieldName);
        this.attribute.put(NAME, this.createAttrSimple(NAME, fieldName));
        this.attribute.put(VALUE, this.createAttrBasicEl(getEntityNameByClass(entityClass, true), fieldName));
        if (a != null) {
            this.attribute.put(AT_LEAST_CHAR, this.createAttrSimple(AT_LEAST_CHAR, "" + a));
        }

        this.elementCode = "table:richTextEditor";
    }

    public void genCode() {
        super.genCode();
    }

    public void initDefaultAttribute() {
        this.attribute.put(NAME, this.createAttrSimple(NAME, ""));
        this.attribute.put(AT_LEAST_CHAR, this.createAttrSimple(AT_LEAST_CHAR, "5"));
    }
}
