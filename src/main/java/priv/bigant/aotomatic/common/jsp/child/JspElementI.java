package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementI extends JspElement {
    public void initDefaultAttribute() {
    }

    public JspElementI() {
        super("", "");
        this.elementCode = "i";
    }

    public static JspElementI newInstance(String entityName) {
        JspElementI var1;
        (var1 = new JspElementI()).attribute.put(CLASS, var1.createAttrSimple(CLASS, entityName));
        return var1;
    }

    public void genCode() {
        super.genCode();
    }
}
