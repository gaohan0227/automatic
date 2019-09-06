package priv.bigant.aotomatic.common;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import priv.bigant.aotomatic.StringUtils;

import java.io.Serializable;
import java.util.*;

public class JoinTableBean implements Serializable {
    public static String INNER_JOIN = " inner join ";
    public static String LEFT_JOIN = " left join ";
    private Map<String, WhereValueBean> WhereMap;
    public static String ASC = "ASC";
    private boolean B;
    private List<JoinOnBean> onConditions;
    private Map<String, String> D;
    private Class<BaseEntity> mainTable;
    private Map<String, String> L;
    private String e;
    private static final long k = 1L;
    public static String DESC = "DESC";
    private String joinKind;
    private Class<BaseEntity> childTable;
    public static String RIGHT_JOIN = " right join ";

    public JoinTableBean putWhereBetween(String a, Object b, Object c) {
        this.WhereMap.put(a, new WhereValueBean(" between ", a, a));
        return this;
    }

    public JoinTableBean putWhereSign(String a, String b, Object c) {
        this.WhereMap.put(a, new WhereValueBean(a, a));
        return this;
    }

    public void setJoinKind(String a) {
        this.joinKind = a;
    }

    public JoinTableBean putWhereLike(String a, Object b) {
        this.WhereMap.put(a, new WhereValueBean(" like ", a));
        return this;
    }

    public JoinTableBean putOnCond(SFunction<? extends BaseEntity, ?> mainColumn, SFunction<? extends BaseEntity, ?> childColumn, String andOr) {
        JoinOnBean joinOnBean = new JoinOnBean(mainColumn, childColumn);
        if (StringUtils.isNotEmpty(andOr)) {
            joinOnBean.setAndOr(andOr);
        }

        this.onConditions.add(joinOnBean);
        return this;
    }

    public void setOnConditions(List<JoinOnBean> a) {
        this.onConditions = a;
    }

    public JoinTableBean putWhereIn(String a, Object b) {
        this.WhereMap.put(a, new WhereValueBean(" in ", a));
        return this;
    }

    public void setTableAlias(String a) {
        this.e = a;
    }

    public JoinTableBean putOnCond(SFunction<? extends BaseEntity, ?> mainColumn, SFunction<? extends BaseEntity, ?> childColumn) {
        this.onConditions.add(new JoinOnBean(mainColumn, childColumn));
        return this;
    }

    public void setOrderyByMap(Map<String, String> a) {
        this.D = a;
    }

    public JoinTableBean joinInto(Class<BaseEntity> a) {
        //this.putJoinTable(a);
        return this;
    }

    public static JoinTableBean fastGetJtb(Class<BaseEntity> table, SFunction<? extends BaseEntity, ?> mainColumn, SFunction<? extends BaseEntity, ?> childColumn) {
        JoinTableBean var3 = new JoinTableBean();
        var3.setChildTable(table);
        var3.putOnCond(mainColumn, childColumn);
        return var3;
    }

    public Map<String, WhereValueBean> getWhereMap() {
        return this.WhereMap;
    }

    public JoinTableBean putOnCondAppendSql(String a) {
        JoinOnBean var2 = new JoinOnBean();
        var2.setOnSql(a);
        var2.setKeepIt(true);
        this.onConditions.add(var2);
        return this;
    }

    public void setWhereMap(Map<String, WhereValueBean> a) {
        this.WhereMap = a;
    }

    public List<JoinOnBean> getOnConditions() {
        return this.onConditions;
    }

    public JoinTableBean(Class<BaseEntity> a, String b, String c, List<JoinOnBean> d, Map<String, WhereValueBean> e, Map<String, String> f) {
        this.joinKind = LEFT_JOIN;
        this.onConditions = new ArrayList();
        this.WhereMap = new LinkedHashMap();
        this.WhereMap = new HashMap();
        this.D = new LinkedHashMap();
        this.setChildTable(a);
        this.e = b;
        this.joinKind = c;
        this.onConditions = d;
        this.WhereMap = e;
        this.L = f;
    }

    public JoinTableBean putWhere(String a) {
        this.WhereMap.put(a, new WhereValueBean(" = ", (Object) null));
        return this;
    }

    public void setMainTableName(Class<BaseEntity> a) {
        this.mainTable = a;
    }

    public boolean isIfSubQuery() {
        return this.B;
    }

    public String getJoinKind() {
        return this.joinKind;
    }

    public JoinTableBean() {
        this.joinKind = LEFT_JOIN;
        this.onConditions = new ArrayList();
        this.WhereMap = new LinkedHashMap();
        this.WhereMap = new HashMap();
        this.D = new LinkedHashMap();
    }

    public JoinTableBean putOnCond(JoinOnBean a) {
        this.onConditions.add(a);
        return this;
    }

    public Class<BaseEntity> getMainTableName() {
        return this.mainTable;
    }

    public Map<String, String> getSelectMap() {
        return this.L;
    }

    public JoinTableBean putOnCondValue(String a, String b, String c) {
        JoinOnBean var4 = new JoinOnBean();
        var4.setChildFieldName(a);
        var4.setOnValue1(a);
        if (StringUtils.isNotEmpty(a)) {
            var4.setAndOr(a);
        }

        this.onConditions.add(var4);
        return this;
    }

    public void setSelectMap(Map<String, String> a) {
        this.L = a;
    }

    public String getTableAlias() {
        /*JoinTableBean var10000;
        if (StringUtils.isEmpty(this.e) && StringUtils.isNotEmpty(this.ALLATORIxDEMO)) {
            if (this.ALLATORIxDEMO(this.ALLATORIxDEMO)) {
                var10000 = a;
                this.e = CustomArrayList.ALLATORIxDEMO("i~x\u007f{ivn") + ((new Random()).nextInt(9) + 1);
                return var10000.e;
            }

            this.e = this.ALLATORIxDEMO.toLowerCase() + ((new Random()).nextInt(9) + 1) + ((new Random()).nextInt(9) + 1);
        }

        var10000 = a;
        return var10000.e;*/
        return null;
    }

    public JoinTableBean(Class<BaseEntity> a, List<JoinOnBean> b, Map<String, String> c) {
        this.joinKind = LEFT_JOIN;
        this.onConditions = new ArrayList();
        this.WhereMap = new LinkedHashMap();
        this.WhereMap = new HashMap();
        this.D = new LinkedHashMap();
        this.setChildTable(a);
        this.onConditions = b;
        this.L = c;
    }

    public JoinTableBean(Class<BaseEntity> a, List<JoinOnBean> b, Map<String, WhereValueBean> c, Map<String, String> d) {
        this.joinKind = LEFT_JOIN;
        this.onConditions = new ArrayList();
        this.WhereMap = new LinkedHashMap();
        this.WhereMap = new HashMap();
        this.D = new LinkedHashMap();
        this.setChildTable(a);
        this.onConditions = b;
        this.WhereMap = c;
        this.L = d;
    }

    public void setChildTable(Class<BaseEntity> childTable) {
        this.childTable = childTable;
    }

    public JoinTableBean putWhere(String a, WhereValueBean b) {
        this.WhereMap.put(a, b);
        return this;
    }

    public Class<BaseEntity> getChildTable() {
        return this.childTable;
    }

    public JoinTableBean putWhereIsNull(String a, boolean... b) {
        boolean var3 = false;
        if (a != null && b.length > 0 && b[0]) {
            var3 = true;
        }

        a = this.getTableAlias() + CustomArrayList.ALLATORIxDEMO("%") + a;//StringUtils.toUnderScoreCase(a);
        if (var3) {
            this.WhereMap.put(a, new WhereValueBean(" is null "));
            return this;
        } else {
            this.WhereMap.put(a, new WhereValueBean(" is not null "));
            return this;
        }
    }

    public JoinTableBean putOrderby(String a, String b) {
        this.D.put(a, b);
        return this;
    }

    public JoinTableBean putSelect(String a, String b) {
        this.L.put(a, b);
        return this;
    }

    public JoinTableBean putOnCondValue(String a, String b) {
        JoinOnBean var3 = new JoinOnBean();
        var3.setChildFieldName(a);
        var3.setOnValue1(b);
        this.onConditions.add(var3);
        return this;
    }

    public JoinTableBean putWhereSign(String a, String b) {
        this.WhereMap.put(a, new WhereValueBean(a, (Object) null));
        return this;
    }

    public JoinTableBean putWhere(String a, Object b) {
        this.WhereMap.put(a, new WhereValueBean(" = ", a));
        return this;
    }

    public JoinTableBean putSelect(String a) {
        this.L.put(a, a);
        return this;
    }

    public static JoinTableBean fastGetJtb(Class<BaseEntity> mainTable, Class<BaseEntity> childTable, String joinKind, SFunction<? extends BaseEntity, ?> mainColumn, SFunction<? extends BaseEntity, ?> childColumn, String andOr) {
        JoinTableBean joinTableBean = new JoinTableBean();
        if (mainTable != null) {
            joinTableBean.setMainTableName(mainTable);
        }

        joinTableBean.setChildTable(childTable);
        if (StringUtils.isNotEmpty(joinKind)) {
            joinTableBean.setJoinKind(joinKind);
        }

        joinTableBean.putOnCond(mainColumn, childColumn, andOr);
        return joinTableBean;
    }

    public JoinTableBean putOnCondValue(String a, String b, String c, String d) {
        JoinOnBean var5 = new JoinOnBean();
        var5.setChildFieldName(a);
        var5.setOnValue1(a);
        if (StringUtils.isNotEmpty(a)) {
            var5.setAndOr(a);
        }

        if (StringUtils.isNotEmpty(a)) {
            var5.setSign(a);
        }

        this.onConditions.add(var5);
        return this;
    }

    public JoinTableBean putMutiSelect(String... a) {
        int var2;
        for (int var10000 = var2 = 0; var10000 < a.length; var10000 = var2) {
            this.L.put(a[var2], a[var2++]);
        }

        return this;
    }

    public Map<String, String> getOrderyByMap() {
        return this.D;
    }

    public static JoinTableBean fastGetJtb(Class<BaseEntity> childTable, String joinKind, SFunction<? extends BaseEntity, ?> mainColumn, SFunction<? extends BaseEntity, ?> childColumn, String andOr) {
        JoinTableBean var5 = new JoinTableBean();
        var5.setChildTable(childTable);
        if (StringUtils.isNotEmpty(joinKind)) {
            var5.setJoinKind(joinKind);
        }
        var5.putOnCond(mainColumn, childColumn, andOr);
        return var5;
    }

    /*public static JoinTableBean fastGetJtb(String a, String b, String c, String d) {
        JoinTableBean var4 = new JoinTableBean();
        if (StringUtils.isNotEmpty(a)) {
            var4.setMainTableName(a);
        }

        var4.setTable(a);
        var4.putOnCond(a, a);
        return var4;
    }*/
}
