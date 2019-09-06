package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementSelectOption extends JspElement {
    public void initDefaultAttribute() {
    }

    public void genCode() {
        super.genCode();
    }

    public static JspElement newInstance(String a) {
        return new JspElementSelectOption();
    }

    public JspElementSelectOption() {
        super("", "");
        elementCode = "option";
        ifFormElement = false;
    }
}
