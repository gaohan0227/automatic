package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.StringUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementSpan extends JspElement {
    public static JspElement newInstance(String entityName, String fieldName, String clazz) {
        JspElementSpan jspElementSpan = new JspElementSpan(entityName, fieldName);
        jspElementSpan.attribute.put(TJ_TEXT, jspElementSpan.createAttrBasicEl(entityName, fieldName));
        if (StringUtils.isEmpty(clazz)) {
            jspElementSpan.attribute.put(CLASS, jspElementSpan.createAttrSimple(CLASS, clazz));
        }
        return jspElementSpan;
    }

    public void initDefaultAttribute() {
    }

    public static JspElementSpan newInstance(String tjText, String clazz) {
        JspElementSpan var2 = new JspElementSpan("", "");
        var2.attribute.put(TJ_TEXT, var2.createAttrSimple(TJ_TEXT, tjText));
        if (StringUtils.isEmpty(clazz)) {
            var2.attribute.put(CLASS, var2.createAttrSimple(CLASS, clazz));
        }

        return var2;
    }

    public void genCode() {
        super.genCode();
    }

    public JspElementSpan(String entityName, String fieldName) {
        super(entityName, fieldName);
        this.elementCode = "span";
    }
}
