package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementSelect extends JspElement {
    public void initDefaultAttribute() {
    }

    public JspElementSelect(Class a, String b) {
        super(getEntityNameByClass(a), b);
        super.elementCode = "select";
        super.ifFormElement = false;
    }

    public static JspElement newInstance(Class a, String b) {
        return new JspElementSelect(a, b);
    }

    public void genCode() {
        super.genCode();
    }
}
