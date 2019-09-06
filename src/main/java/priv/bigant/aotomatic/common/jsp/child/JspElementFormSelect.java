package priv.bigant.aotomatic.common.jsp.child;

import priv.bigant.aotomatic.common.BaseEntity;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.List;

public class JspElementFormSelect extends JspElement {
    private BaseEntity baseEntity;
    private List<String> showFieldList;
    private boolean ALLATORIxDEMO;

    public void initDefaultAttribute() {
        attribute.put("class", createAttrSimple("class", "form-control m-b"));
        attribute.put("path", createAttrSimple("path", ""));
    }

    public void genCode() {
        super.genCode();
    }

    public JspElementFormSelect(Class baseEntity, String b) {
        super(getEntityNameByClass(baseEntity, false), b);
        elementCode = "select";
        ifFormElement = true;
        attribute.put("path", createAttrSimple("path", b));
    }

    public List<String> getShowFieldList() {
        return this.showFieldList;
    }

    public static JspElementFormSelect newInstance(Class a, String b, BaseEntity c, String... d) {
        return  new JspElementFormSelect(a, b);
    }

    public void setJoinTable(BaseEntity a) { this.baseEntity = a;
    }

    public BaseEntity getJoinTable() {
        return this.baseEntity;
    }

    public static JspElementFormSelect newInstance(Class a, String b) {
        return new JspElementFormSelect(a, b);
    }

    public void setShowFieldList(List<String> a) {
        this.showFieldList = a;
    }

    public boolean isIfJoinTable() {
        return this.ALLATORIxDEMO;
    }

    public void setIfJoinTable(boolean a) {
        this.ALLATORIxDEMO = a;
    }
}
