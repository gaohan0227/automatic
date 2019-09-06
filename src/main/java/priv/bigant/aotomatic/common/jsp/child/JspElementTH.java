package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspAttribute;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.HashMap;
import java.util.Map;

public class JspElementTH extends JspElement {


    public static JspElementTH newInstance(String tableName, String attrName, String attnCovertValue, String d) {
        JspElementTH var4;
        Map<String, JspAttribute> var10000 = (var4 = new JspElementTH(tableName, attrName)).attribute;
        var10000.put(TJ_TEXT, var4.createAttrElConvert(tableName, attrName, attnCovertValue, d, new HashMap<>()));
        return var4;
    }

    public JspElementTH(String tableName, String attrName) {
        super(tableName, attrName);
        this.elementCode = "th";
    }

    public static JspElementTH newInstance(String tjText) {
        JspElementTH var1;
        (var1 = new JspElementTH((String) null, (String) null)).attribute.put(TJ_TEXT, var1.createAttrSimple(TJ_TEXT, tjText));
        return var1;
    }

    public void genCode() {
        super.genCode();
    }

    public void initDefaultAttribute() {
    }

    public static JspElementTH newInstance(String tableName, String attrName) {
        JspElementTH var2;
        (var2 = new JspElementTH(tableName, attrName)).attribute.put(TJ_TEXT, var2.createAttrBasicEl(tableName, attrName));
        return var2;
    }
}
