package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementFormHidden extends JspElement {
    public static JspElementFormHidden newInstance(Class a, String b) {
        String var2 = getEntityNameByClass(a, true);
        return new JspElementFormHidden(var2, b);
    }

    public void genCode() {
        super.genCode();
    }

    public void initDefaultAttribute() {
        this.attribute.put("path", this.createAttrSimple("path", ""));
    }

    public JspElementFormHidden(String a, String b) {
        super(a, b);
        this.elementCode = "hidden";
        this.ifFormElement = true;
        this.attribute.put("path", this.createAttrSimple("path", ""));
    }
}
