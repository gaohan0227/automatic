package priv.bigant.aotomatic.common;

import priv.bigant.aotomatic.StringUtils;

import java.io.Serializable;

public class DataEntityFieldCallBackBean implements Serializable {
    private static final long l = 1L;
    private String B;
    private String m;
    private boolean D = false;
    private String f;
    private boolean L = false;
    private Object[] e;
    private String k;
    private String K;
    private String ALLATORIxDEMO;

    public void setIfToolMethod(boolean a) {
        this.D = a;
    }

    public boolean isIfToolMethod() {
        return this.D;
    }

    public DataEntityFieldCallBackBean() {
    }

    public String getTableName() {
        return this.K;
    }

    public void setValuePropName(String a) {
        this.ALLATORIxDEMO = a;
    }

    public DataEntityFieldCallBackBean(boolean a, String b, String c, String d, String e) {
        this.L = a;
        this.K = b;
        this.f = c;
        this.B = d;
        this.ALLATORIxDEMO = e;
    }

    public Object[] getOtherParam() {
        return this.e;
    }

    public void setTableName(String a) {
        this.K = a;
    }

    public DataEntityFieldCallBackBean(String a, String b, Object[] c) {
        this.m = a;
        this.K = b;
        this.e = c;
    }

    public String getClassName() {
        return this.m;
    }

    public void setResultPropName(String a) {
        this.f = a;
    }

    public boolean isQuick() {
        return this.L;
    }

    public String getValuePropName() {
        return this.ALLATORIxDEMO;
    }

    public void setClassName(String a) {
        this.m = a;
    }

    public DataEntityFieldCallBackBean(String a, String b) {
        this.m = a;
        this.K = b;
    }

    public String getMethodName() {
        return this.K;
    }

    public DataEntityFieldCallBackBean(Object a, String b, Object[] c) {
        this.m = StringUtils.firstToLower(a.getClass().getSimpleName());
        this.K = b;
        this.e = c;
    }



    public void setQuick(boolean a) {
        this.L = a;
    }

    public void setMethodName(String a) {
        this.K = a;
    }

    public String getQueryPropName() {
        return this.B;
    }

    public void setQueryPropName(String a) {
        this.B = a;
    }

    public String getResultPropName() {
        return this.f;
    }

    public void setOtherParam(Object[] a) {
        this.e = a;
    }
}
