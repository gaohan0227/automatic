package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementAtag extends JspElement {
    public JspElementAtag(boolean a, String custUrl, String c, String d, String g, Map<String, String> idParamsMap) {
        super("", "");
        this.attribute.put("target", this.createAttrSimple("target", custUrl));
        this.elementCode = "a";
        custUrl = this.appendParamsForUrl(custUrl, idParamsMap);
        JspElementAtag var7;
        if (a) {
            attribute.put("href", this.createAttrSimple("href", custUrl));
        } else {
            this.attribute.put("onclick", this.createAttrSimple("onclick", c));
        }

        this.attribute.put("tjText", this.createAttrSimple("tjText", d));
        this.attribute.put("class", this.createAttrSimple("class", g));
        this.attribute.put("htmlEscape", this.createAttrSimple("htmlEscape", "false"));
    }

    public void initDefaultAttribute() {
        this.attribute.put("href", this.createAttrSimple("href","#" ));
        this.attribute.put("htmlEscape", this.createAttrSimple("htmlEscape", "false"));
        this.attribute.put("target", this.createAttrSimple("target", ""));
        this.attribute.put("onclick", this.createAttrSimple("onclick", ""));
        this.attribute.put("class", this.createAttrSimple("class", ""));
        this.attribute.put("tjText", this.createAttrSimple("tjText", ""));
    }

    public void genCode() {
        super.genCode();
    }
}
