package priv.bigant.aotomatic.common.jsp;


import java.util.List;

public class JspAttribute {
    private String attrName;
    private String tableName;
    protected String selectDataType;
    private boolean ifContainElExpress;
    private String L;
    private String e;
    protected Object selectDataCollection;
    private Integer k;
    private boolean K;
    protected String jspToolFunctionName;
    private List<String> ALLATORIxDEMO;

    public String getAttrName() {
        return this.attrName;
    }

    public void setSelectDataCollection(Object selectDataCollection) {
        this.selectDataCollection = selectDataCollection;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public JspAttribute() {
    }

    public void setIfContainElExpress(boolean a) {
        this.ifContainElExpress = a;
    }

    public void setTableName(String a) {
        this.tableName = a;
    }

    public String getAttrCovertValue() {
        return this.e;
    }

    public Object getSelectDataCollection() {
        return this.selectDataCollection;
    }

    public void setAttrCovertValue(String a) {
        this.e = a;
    }

    public boolean isIfStatic() {
        return this.K;
    }

    public Integer getLoadFieldNum() {
        return this.k;
    }

    public String getSelectDataType() {
        return this.selectDataType;
    }

    public void setIfStatic(boolean a) {
        this.K = a;
    }

    public static JspAttribute newInstance() {
        return new JspAttribute();
    }

    public String getAttrOriginValue() {
        return this.L;
    }

    public boolean isIfContainElExpress() {
        return this.ifContainElExpress;
    }

    public void setSelectDataType(String a) {
        this.selectDataType = a;
    }

    public void setJspToolFunctionName(String a) {
        this.jspToolFunctionName = a;
    }

    public String getJspToolFunctionName() {
        return this.jspToolFunctionName;
    }

    public JspAttribute(boolean a, String b, String c, String d, String e) {
        this.K = a;
        this.tableName = b;
        this.attrName = c;
        this.L = d;
        this.e = e;
    }

    public void setLoadFieldNum(Integer a) {
        this.k = a;
    }

    public void setAttrOriginValue(String a) {
        this.L = a;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setMultiLieldName(List<String> a) {
        this.ALLATORIxDEMO = a;
    }

    public List<String> getMultiLieldName() {
        return this.ALLATORIxDEMO;
    }
}
