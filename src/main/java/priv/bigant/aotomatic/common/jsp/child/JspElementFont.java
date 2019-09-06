package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementFont extends JspElement {
    public static JspElementFont newInstance(String color, String tjText) {
        return new JspElementFont(color, tjText);
    }

    public JspElementFont(String color, String tjText) {
        super("", "");
        this.elementCode = "font";
        this.attribute.put(COLOR, this.createAttrSimple(COLOR, color));
        this.attribute.put(TJ_TEXT, this.createAttrSimple(TJ_TEXT, tjText));
    }

    public void initDefaultAttribute() {
        this.attribute.put(COLOR, this.createAttrSimple(COLOR, ""));
        this.attribute.put(TJ_TEXT, this.createAttrSimple(TJ_TEXT, ""));
    }

    public void genCode() {
        super.genCode();
    }
}
