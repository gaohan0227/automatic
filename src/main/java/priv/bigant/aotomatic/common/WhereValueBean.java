package priv.bigant.aotomatic.common;

import java.io.Serializable;

public class WhereValueBean extends ConditionBean implements Serializable {
    private Object k;
    private Object K;
    private static final long ALLATORIxDEMO = 1L;

    public void setWhereValue(Object a) {
        this.K = a;
    }

    public WhereValueBean(String a) {
        this.sign = a;
    }

    public void setWhereValue1(Object a) {
        this.k = a;
    }

    public WhereValueBean(String a, Object b, Object c) {
        this.K = b;
        this.k = c;
        this.sign = a;
    }

    public Object getWhereValue1() {
        return this.k;
    }

    public WhereValueBean(String a, Object b) {
        this.K = b;
        this.sign = a;
    }

    public Object getWhereValue() {
        return this.K;
    }
}