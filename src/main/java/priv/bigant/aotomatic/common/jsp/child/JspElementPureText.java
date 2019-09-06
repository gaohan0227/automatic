package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementPureText extends JspElement {
    public JspElementPureText() {
        super("", "");
        this.elementCode = "text";
    }

    public void genCode() {
        super.genCode();
    }

    public static JspElementPureText newInstance(String html) {
        JspElementPureText jspElementPureText = new JspElementPureText();
        jspElementPureText.html = html;
        return jspElementPureText;
    }

    public void initDefaultAttribute() {
    }
}
