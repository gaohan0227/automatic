package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.CustomArrayList;
import org.apache.commons.lang3.StringUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class JspElementTagLibExcel extends JspElement {
    public JspElementTagLibExcel(String elementCode,String url, String index, String label, String target, Map<String, String> map) {
        super(null, null);
        this.elementCode = elementCode;
        url = this.appendParamsForUrl(url, map);
        this.attribute.put("url", this.createAttrSimple("url", url));
        if (StringUtils.isNotEmpty(index)) {
            this.attribute.put(INDEX, this.createAttrSimple(INDEX, index));
        }

        if (StringUtils.isNotEmpty(label)) {
            this.attribute.put("label", this.createAttrSimple("label", label));
        }

        if (StringUtils.isNotEmpty(target)) {
            this.attribute.put(TARGET, this.createAttrSimple(CustomArrayList.ALLATORIxDEMO("njhl\u007f\u007f"), target));
        }

    }

    public void initDefaultAttribute() {
        this.attribute.put("url", this.createAttrSimple("url", ""));
        this.attribute.put(INDEX, this.createAttrSimple(INDEX, ""));
        this.attribute.put("label", this.createAttrSimple("label", ""));
        this.attribute.put(TARGET, this.createAttrSimple(TARGET, ""));
    }

    public void genCode() {
        super.genCode();
    }

    public static JspElementTagLibExcel newInstance(String elementCode, String url, Map<String, String> a) {
        return new JspElementTagLibExcel(elementCode, url, null, null, null, a);
    }

    public static JspElementTagLibExcel newInstance(String elementCode,String url, String index, String label, String target, Map<String, String> map) {
        return new JspElementTagLibExcel(elementCode, url, index, label, target, map);
    }

    public static JspElementTagLibExcel newInstance(String elementCode, String url) {
        return new JspElementTagLibExcel(elementCode, url, null, null, null, null);
    }
}
