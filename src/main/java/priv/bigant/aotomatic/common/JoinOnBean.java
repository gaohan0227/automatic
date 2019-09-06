package priv.bigant.aotomatic.common;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.io.Serializable;

public class JoinOnBean extends ConditionBean implements Serializable {
    private SFunction<? extends BaseEntity,?> childColumn;
    private boolean D;
    private String f;
    private Object L;
    private Object e;
    private SFunction<? extends BaseEntity,?> mainColumn;
    private static final long K = 1L;
    private String andOr = "and";

    public void setMainFieldName(String a) {
        //this.k = StringUtils.toUnderScoreCase(a);
    }

    public void setOnValue1(Object a) {
        this.L = a;
    }

    public void setOnSql(String a) {
        this.f = a;
    }

    public JoinOnBean() {
    }

    public JoinOnBean(String a, String b, String c) {
        //this.k = StringUtils.toUnderScoreCase(a);
        //this.m = StringUtils.toUnderScoreCase(a);
        this.sign = a;
    }

    public JoinOnBean(String a, String b, Object c, Object d, String e) {
        //this.k = StringUtils.toUnderScoreCase(a);
        //this.m = StringUtils.toUnderScoreCase(a);
        this.L = a;
        this.e = a;
        this.sign = a;
    }

    public SFunction<? extends BaseEntity,?> getChildFieldName() {
        return this.childColumn;
    }

    public String getAndOr() {
        return this.andOr;
    }

    public void setChildFieldName(String a) {
        //this.m = StringUtils.toUnderScoreCase(a);
    }

    public Object getOnValue1() {
        return this.L;
    }

    public String getOnSql() {
        return this.f;
    }

    public Object getOnValue2() {
        return this.e;
    }

    public void setOnValue2(Object a) {
        this.e = a;
    }

    public JoinOnBean(String a, String b, Object c, String d) {
        //this.k = StringUtils.toUnderScoreCase(a);
        //this.m = StringUtils.toUnderScoreCase(a);
        this.L = a;
        this.sign = a;
    }

    public boolean isKeepIt() {
        return this.D;
    }

    public JoinOnBean(SFunction<? extends BaseEntity,?> mainColumn, SFunction<? extends BaseEntity,?> childColumn) {
        this.mainColumn = mainColumn;
        this.childColumn = childColumn;
    }

    public void setAndOr(String a) {
        this.andOr = a;
    }

    public SFunction<? extends BaseEntity,?> getMainFieldName() {
        return this.mainColumn;
    }

    public String findExpress(String a, String b, JoinTableBean c, Object d) throws Exception {
        /*StringBuilder var5 = new StringBuilder();
        if (StringUtils.isEmpty(a)) {
            //throw new Exception(ServiceException.ALLATORIxDEMO("孒蠷利呒丏肢丸稥＃"));
        } else if (this.D) {
            return this.getOnSql();
        } else {
            StringBuilder var10000;
            if (StringUtils.isEmpty(this.k)) {
                JoinTableBean a = (String)a.getSelectMap().get(StringUtils.toCamelCase(this.m));
                Object var6 = null;
                if (StringUtils.isNotEmpty(a)) {
                    Reflections.invokeGetter(a, a);
                }

                if (this.L == null || !StringUtils.isNotEmpty("" + this.L)) {
                    return "";
                }

                var6 = this.L;
                if (this.sign.equals(" like ")) {
                    var10000 = var5;
                    var5.append(a + CustomArrayList.ALLATORIxDEMO("%") + this.m + " " + this.sign + ServiceException.ALLATORIxDEMO("\"x'") + var6 + CustomArrayList.ALLATORIxDEMO("?,"));
                } else if (this.sign.equals(" between ")) {
                    var10000 = var5;
                    var5.append(a + ServiceException.ALLATORIxDEMO(",") + this.m + " " + this.sign + var6 + CustomArrayList.ALLATORIxDEMO("+{e~+") + this.e);
                } else if (this.L instanceof String) {
                    var5.append(a + ServiceException.ALLATORIxDEMO(",") + this.m + " " + this.sign + CustomArrayList.ALLATORIxDEMO(":,") + var6 + ServiceException.ALLATORIxDEMO("%"));
                    var10000 = var5;
                } else {
                    var5.append(a + CustomArrayList.ALLATORIxDEMO("%") + this.m + " " + this.sign + var6);
                    var10000 = var5;
                }
            } else if (StringUtils.isNotEmpty(a)) {
                if (this.k.contains(ServiceException.ALLATORIxDEMO(","))) {
                    var5.append(this.k + " " + this.sign + " " + a + CustomArrayList.ALLATORIxDEMO("%") + this.m);
                    var10000 = var5;
                } else {
                    var5.append(a + ServiceException.ALLATORIxDEMO(",") + this.k + " " + this.sign + " " + a + CustomArrayList.ALLATORIxDEMO("%") + this.m);
                    var10000 = var5;
                }
            } else {
                var10000 = var5;
                var5.append(this.k + " " + this.sign + " " + a + ServiceException.ALLATORIxDEMO(",") + this.m);
            }

            return var10000.toString();
        }*/return null;
    }

    public void setKeepIt(boolean a) {
        this.D = a;
    }
}
