package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.SpringElParse;
import priv.bigant.aotomatic.common.jsp.JspAttribute;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementFormSelectOption extends JspElement {
    public JspElementFormSelectOption(Class a, String b, String c, String d) {
        super(getEntityNameByClass(a, true), b);
        elementCode = "option";
        ifFormElement = true;
        String var10001 = this.getEntityName();
        attribute.put("items", createAttrElConvert(var10001, b, c, d));
    }

    public void initSelectDataCollection(SpringElParse a) {
        JspAttribute var2 = attribute.get("items");
        JspAttribute var10000;
        Object object;
        if ((object = a.parseElExpress(var2.getAttrCovertValue())) instanceof Map) {
            var10000 = var2;
            var2.setSelectDataType("0");
        } else {
            var10000 = var2;
            var2.setSelectDataType("1");
        }

        var10000.setSelectDataCollection(object);
    }

    public void genCode() {
        super.genCode();
    }

    public static JspElementFormSelectOption newInstance(Class a, String b, String c, String d) {
        return new JspElementFormSelectOption(a, b, c, d);
    }

    public JspElementFormSelectOption(String a, String b) {
        super("", "");
        this.elementCode = "option";
        this.ifFormElement = true;
        this.attribute.put("value", createAttrSimple("value", a));
        this.attribute.put("label", createAttrSimple("label", b));
        this.attribute.put("htmlEscape", null);
    }

    public void initDefaultAttribute() {
        this.attribute.put("items", null);
        this.attribute.put("htmlEscape", createAttrSimple("htmlEscape", "false"));
    }

    public static JspElementFormSelectOption newInstance(String a, String b) {
        return new JspElementFormSelectOption(a, b);
    }
}
