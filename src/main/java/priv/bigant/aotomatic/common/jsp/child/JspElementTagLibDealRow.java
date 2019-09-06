package priv.bigant.aotomatic.common.jsp.child;

import org.apache.commons.lang3.StringUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementTagLibDealRow extends JspElement {
    public void setOpenDialogWidthHeight(String width, String height) {
        if (StringUtils.isNotEmpty(width)) {
            this.attribute.put(WIDTH, this.createAttrSimple(WIDTH, width));
        }

        if (StringUtils.isNotEmpty(height)) {
            this.attribute.put(HEIGHT, this.createAttrSimple(HEIGHT, height));
        }

    }

    public void setNeedTarget(boolean isNeedTarget) {
        if (isNeedTarget) {
            this.attribute.put(NEED_TARGET, this.createAttrSimple(NEED_TARGET, String.valueOf(isNeedTarget)));
        }

    }

    public JspElementTagLibDealRow(String elementCode, String url, String id, String title, String width, String height, boolean isNeedTarget, String confirmTip, String label, Map<String, String> map) {
        super(null, null);
        this.elementCode = elementCode;
        url = this.appendParamsForUrl(url, map);
        this.attribute.put(ID, this.createAttrSimple(ID, id));
        this.attribute.put("url", this.createAttrSimple("url", url));
        this.attribute.put(TITLE, this.createAttrSimple(TITLE, title));
        if (StringUtils.isNotEmpty(width)) {
            this.attribute.put(WIDTH, this.createAttrSimple(WIDTH, width));
        }

        if (StringUtils.isNotEmpty(height)) {
            this.attribute.put(HEIGHT, this.createAttrSimple(HEIGHT, height));
        }

        if (StringUtils.isNotEmpty(confirmTip)) {
            this.attribute.put(CONFIRM_TIP, this.createAttrSimple(CONFIRM_TIP, confirmTip));
        }

        if (StringUtils.isNotEmpty(label)) {
            this.attribute.put("label", this.createAttrSimple("label", label));
        }

        if (isNeedTarget) {
            this.attribute.put(NEED_TARGET, this.createAttrSimple(NEED_TARGET, String.valueOf(isNeedTarget)));
        }

    }

    public void genCode() {
        super.genCode();
    }

    public void initDefaultAttribute() {
        this.attribute.put(ID, this.createAttrSimple(ID, ""));
        this.attribute.put("url", this.createAttrSimple("url", ""));
        this.attribute.put(TITLE, this.createAttrSimple(TITLE, ""));
        this.attribute.put(WIDTH, this.createAttrSimple(WIDTH, ""));
        this.attribute.put(HEIGHT, this.createAttrSimple(HEIGHT, ""));
        this.attribute.put("label", this.createAttrSimple("label", (String)null));
        this.attribute.put(CONFIRM_TIP, this.createAttrSimple(CONFIRM_TIP, ""));
        this.attribute.put(TARGET, this.createAttrSimple(TARGET, ""));
        this.attribute.put(NEED_TARGET, this.createAttrSimple(NEED_TARGET, ""));
    }

    public static JspElementTagLibDealRow newInstance(String elementCode, String url, String id, String title, String width, String height, boolean isNeedTarget, String confirmTip, String label, Map<String, String> map) {
        return new JspElementTagLibDealRow(elementCode, url, id, title, width, height, isNeedTarget, confirmTip, label, map);
    }
}
