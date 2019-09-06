package priv.bigant.aotomatic.common.jsp.child;

import org.apache.commons.lang3.StringUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

public class JspElementImg extends JspElement {
    public static JspElementImg newInstance(Class entityClass, String fieldName, String alt, String width, String height) {
        return new JspElementImg(entityClass, fieldName, alt, width, height);
    }

    public JspElementImg(Class entityClass, String fieldName, String alt, String width, String height) {
        this(entityClass, fieldName);
        this.attribute.put(ALT, this.createAttrSimple(ALT, alt));
        if (StringUtils.isNotEmpty(width)) {
            this.attribute.put(WIDTH, this.createAttrSimple(WIDTH, width + PX));
        }

        if (StringUtils.isNotEmpty(height)) {
            this.attribute.put(HEIGHT, this.createAttrSimple(HEIGHT, height + PX));
        }

    }

    public void initDefaultAttribute() {
        this.attribute.put(CLASS, this.createAttrSimple(CLASS, "lazy"));
        this.attribute.put(WIDTH, this.createAttrSimple(WIDTH, "80"));
        this.attribute.put(HEIGHT, this.createAttrSimple(HEIGHT, "60"));
    }

    public static JspElementImg newInstance(Class entityClass, String fieldName, String alt, String style) {
        return new JspElementImg(entityClass, fieldName, alt, style);
    }

    public JspElementImg(Class entityClass, String fieldName) {
        super(getEntityNameByClass(entityClass), fieldName);
        this.elementCode = "img";
        this.attribute.put("data-src", this.createAttrBasicEl(fieldName, getEntityNameByClass(entityClass, true)));
    }

    public JspElementImg(Class entityClass, String fieldName, String alt, String style) {
        this(entityClass, fieldName);
        this.attribute.put(ALT, this.createAttrSimple(ALT, alt));
        this.attribute.put(STYLE, this.createAttrSimple(STYLE, style));
    }

    public void genCode() {
        super.genCode();
    }
}
