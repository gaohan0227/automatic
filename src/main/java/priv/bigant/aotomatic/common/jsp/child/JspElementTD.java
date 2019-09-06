package priv.bigant.aotomatic.common.jsp.child;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.HashMap;
import java.util.Map;

public class JspElementTD extends JspElement {
    public JspElementTD(Class entityClass, String fieldName) {
        super(getEntityNameByClass(entityClass, false), fieldName);
        this.elementCode = "td";
        this.attribute.put(TJ_TEXT, this.createAttrBasicEl(getEntityNameByClass(entityClass, false), fieldName));
    }

    public JspElementTD(Class entityClass, SFunction fieldName) {
        super(getEntityNameByClass(entityClass, false), fieldName, "");
        this.elementCode = "td";
        this.attribute.put(TJ_TEXT, this.createAttrBasicEl(getEntityNameByClass(entityClass, false), fieldName, ""));
    }

    public void initDefaultAttribute() {
    }

    public JspElementTD(String a) {
        super((String) null, (String) null);
        this.elementCode = "td";
        this.attribute.put(TJ_TEXT, this.createAttrSimple(TJ_TEXT, a));
    }

    public static JspElementTD newInstance(Class entityClass, String fieldName, String attnCovertValue, String d, Map<String, Object>... e) {
        return new JspElementTD(entityClass, fieldName, attnCovertValue, d, e);
    }

    /*public static JspElementTD newInstance(Class<BaseEntity> entityClass, SFunction fieldName, String attnCovertValue, String d, Map<String, Object>... e) {

    }*/

    public void genCode() {
        super.genCode();
    }

    /*public JspElementTD(Class<BaseEntity> entityClass, SFunction fieldName, String attnCovertValue, String d, Map<String, Object>... e) {
        super(entityClass, fieldName);
        this.elementCode = "td";
        this.attrMap.put(TJ_TEXT, this.createAttrElConvert(getEntityNameByClass(entityClass, false), fieldName, attnCovertValue, d, e));
    }*/

    public JspElementTD(Class entityClass, String fieldName, String attnCovertValue, String d, Map<String, Object>... e) {
        super(getEntityNameByClass(entityClass, false), fieldName);
        this.elementCode = "td";
        this.attribute.put(TJ_TEXT, this.createAttrElConvert(getEntityNameByClass(entityClass, false), fieldName, attnCovertValue, d, e));
    }

    public static JspElementTD newInstance(Class entityClass, String fieldName) {
        return new JspElementTD(entityClass, fieldName);
    }

    public static JspElementTD newInstance(Class entityClass, SFunction fieldName) {
        return new JspElementTD(entityClass, fieldName);
    }

    public static JspElementTD newInstance(Class entityClass, String fieldName, String attnCovertValue) {
        return new JspElementTD(entityClass, fieldName, (String) null, attnCovertValue, new HashMap<>());
    }

    public static JspElementTD newInstance(String attr, String attnCovertValue) {
        JspElementTD var2;
        (var2 = new JspElementTD("")).attribute.put(attr, var2.createAttrSimple(attr, attnCovertValue));
        return var2;
    }

    public static JspElementTD newInstance(String value) {
        return new JspElementTD(value);
    }
}
