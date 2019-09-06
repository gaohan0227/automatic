package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementLabel extends JspElement {
    public void initDefaultAttribute() {
    }

    public static JspElementLabel newInstance(String entityName, String b, String c) {
        JspElementLabel a;
        (a = new JspElementLabel(entityName)).attribute.put(b, a.createAttrSimple(b, c));
        return a;
    }

    public JspElementLabel(String a) {
        this();
        this.attribute.put(TJ_TEXT, this.createAttrSimple(TJ_TEXT, a));
    }

    public static JspElement newInstance(String a) {
        return new JspElementLabel(a);
    }

    public JspElementLabel() {
        super("", "");
        this.elementCode = "label";
    }

    public void genCode() {
        super.genCode();
    }
}
