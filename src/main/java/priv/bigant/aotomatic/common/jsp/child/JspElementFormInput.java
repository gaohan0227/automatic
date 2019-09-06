package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.jsp.JspElement;
import priv.bigant.aotomatic.common.table.TableColumn;

public class JspElementFormInput extends JspElement {
    public JspElementFormInput(Class a, String b) {
        super(getEntityNameByClass(a, true), b);
        this.elementCode = "input";
        this.ifFormElement = true;
        this.attribute.put("path", this.createAttrSimple("path", b));
        TableColumn tableColumn= findTableColumn(a, b);
        if (tableColumn  != null) {
            this.attribute.put("maxlength", this.createAttrSimple("maxlength", tableColumn.getMaxLength()));
        }

    }

    public static JspElementFormInput newInstance(Class a, String b) {
        return new JspElementFormInput(a, b);
    }

    public void initDefaultAttribute() {
        this.attribute.put(PATH, this.createAttrSimple(PATH, ""));
        this.attribute.put(HTML_ESCAPE, this.createAttrSimple(HTML_ESCAPE, "false"));
        this.attribute.put("maxlength", this.createAttrSimple("maxlength", ""));
        this.attribute.put(CLASS, this.createAttrSimple(CLASS, "form-control input-sm"));
    }

    public static JspElementFormInput newInstance(String a) {
        return newInstance((Class)null, a);
    }
}
