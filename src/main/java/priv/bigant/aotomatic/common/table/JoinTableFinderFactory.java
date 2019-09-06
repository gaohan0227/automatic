package priv.bigant.aotomatic.common.table;

import priv.bigant.aotomatic.StringUtils;
import org.springframework.util.CollectionUtils;
import priv.bigant.aotomatic.common.BaseEntity;
import priv.bigant.aotomatic.common.JoinTableBean;
import priv.bigant.aotomatic.common.Reflections;

import java.util.*;

public class JoinTableFinderFactory {
    protected Map<Class<BaseEntity>, List<JoinTableToolsInfoBean>> relationMap = new HashMap<>();
    protected Map<Class<BaseEntity>, List<JoinTableToolsInfoBean>> relationMapReverse = new HashMap<>();
    public static String tableSpitSign = "-";
    private Object initObject = null;//加载关联表配置类
    protected Map<String, List<String>> netRelationMap = new HashMap<>();
    private String initObjectParentClass = "com.tengjie.base.modules.utils.JoinTableFinder";
    private static List<JoinTableToolsInfoBean> allJtbSourceList = new ArrayList<>();
    protected static JoinTableFinderFactory finder;

    /**
     * 初始化关联表信息
     *
     * @throws Exception
     */
    protected void initListAndMap() throws Exception {
        String methodName = "initRelationList";
        if (this.initObject == null) {
            String[] var10002 = new String[0];
            this.initObject = Reflections.getInstanceByClassName(initObjectParentClass, var10002);
        }

        if (allJtbSourceList.size() < 1) {
            Reflections.invokeMethodByName(initObject, methodName, new Object[]{allJtbSourceList});
        }
        if (allJtbSourceList.size() > 0) {
            allJtbSourceList.forEach(x -> {
                List<JoinTableToolsInfoBean> joinTableToolsInfoBeans = relationMap.get(x);
                if (CollectionUtils.isEmpty(joinTableToolsInfoBeans))
                    joinTableToolsInfoBeans = new ArrayList<>();
                joinTableToolsInfoBeans.add(x);
                relationMap.put(x.getMainTable(), joinTableToolsInfoBeans);
            });
        }

    }

    /*public String digui_findRelationPathByNetData(String a, String a, Map<String, String> a) {
        Iterator var10000;
        List var4;
        Iterator var5;
        String var6;
        for (var10000 = var5 = (var4 = (List) a.netRelationMap.get(a)).iterator(); var10000.hasNext(); var10000 = var5) {
            var6 = (String) var5.next();
            if (a.containsKey(var6)) {
                var5.remove();
            }
        }

        a.put(a, a);
        Iterator a;
        if (var4 != null && var4.size() > 0) {
            for (var10000 = a = var4.iterator(); var10000.hasNext(); var10000 = a) {
                if ((var6 = (String) a.next()).equals(a)) {
                    return a;
                }

                String var8;
                if ((var8 = a.digui_findRelationPathByNetData(var6, a, a)) != null) {
                    return var6 + FileDescEntity.ALLATORIxDEMO("q\u007f") + var8;
                }
            }
        }

        return null;
    }

    public LinkedHashMap<String, JoinTableBean> findJTBByNetData(BaseEntity a, String a, boolean a) throws Exception {
        LinkedHashMap var4 = Maps.newLinkedHashMap();
        String var5 = Reflections.getFieldValue(a, ServiceUtils.ALLATORIxDEMO("dMyFoXyU~FuXv\\d")).toString();
        String var6;
        if ((var6 = a.digui_findRelationPathByNetData(var5, a, TjMap.newInstance())) == null) {
            System.out.println(ServiceUtils.ALLATORIxDEMO("扅且刋衱`") + var5 + FileDescEntity.ALLATORIxDEMO("\u0001响蠴\u001a") + a + ServiceUtils.ALLATORIxDEMO("f皝先聍先糢Ｚ"));
            return var4;
        } else {
            String[] var12 = var6.split(FileDescEntity.ALLATORIxDEMO("q\u007f"));
            JoinTableBean var7 = null;
            String[] var10 = var12;
            int var9 = var12.length;

            int var8;
            for (int var10000 = var8 = 0; var10000 < var9; var10000 = var8) {
                JoinTableBean var11;
                LinkedHashMap var13;
                label25:
                {
                    var6 = var10[var8];
                    var11 = null;
                    boolean[] var10003;
                    boolean var10005;
                    if (var7 == null) {
                        var10003 = new boolean[0];
                        var10005 = true;
                        var11 = a.findJTB(a, var6, var10003);
                        if (a) {
                            var13 = var4;
                            var11.joinInto(a);
                            break label25;
                        }
                    } else {
                        var10003 = new boolean[0];
                        var10005 = true;
                        var11 = a.findJTB(var7, var6, var10003);
                        if (a) {
                            var11.joinInto(a);
                        }
                    }

                    var13 = var4;
                }

                ++var8;
                var13.put(var6, var11);
                var7 = var11;
            }

            return var4;
        }
    }*/

    public Map<Class<? extends BaseEntity>, LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean>> findJTBByDirectRelation(Class<BaseEntity> mainTable, boolean b, Class<BaseEntity>... childTables) throws Exception {
        Map<Class<? extends BaseEntity>, LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean>> linkedHashMap = new LinkedHashMap<>();

        List<JoinTableToolsInfoBean> joinTableToolsInfoBeans = relationMap.get(mainTable);
        joinTableToolsInfoBeans.forEach(x -> {
            for (Class<BaseEntity> childTable : childTables) {
                if (x.getChildTable().equals(childTable)) {
                    LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean> objectObjectLinkedHashMap = new LinkedHashMap<>();
                    JoinTableBean joinTableBean = JoinTableBean.fastGetJtb(x.getMainTable(), x.getChildTable(), null, x.getMainRefFieldName(), x.getChildRefFieldName(), null);
                    objectObjectLinkedHashMap.put(x.getChildTable(), joinTableBean);
                    linkedHashMap.put(mainTable, objectObjectLinkedHashMap);
                }
            }

        });
        return linkedHashMap;
    }

    public JoinTableBean findJTBByDirectRelation(Class<BaseEntity> mainTable, Class<BaseEntity> childTable) throws Exception {

        List<JoinTableToolsInfoBean> joinTableToolsInfoBeans = relationMap.get(mainTable);
        for (JoinTableToolsInfoBean x : joinTableToolsInfoBeans) {

            if (x.getChildTable().equals(childTable)) {
                LinkedHashMap<Class<? extends BaseEntity>, JoinTableBean> objectObjectLinkedHashMap = new LinkedHashMap<>();
                JoinTableBean joinTableBean = JoinTableBean.fastGetJtb(x.getMainTable(), x.getChildTable(), null, x.getMainRefFieldName(), x.getChildRefFieldName(), null);
                return joinTableBean;
            }

        }
        return null;
    }

    public static JoinTableFinderFactory finderIns(boolean... a) {
        if (finder == null) {
            finder = new JoinTableFinderFactory();

            try {
                finder.initListAndMap();
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        } else if (a.length > 0 && a[0]) {
            allJtbSourceList.clear();
            finder = new JoinTableFinderFactory();

            try {
                finder.initListAndMap();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return finder;
    }

    public JoinTableFinderFactory() {
    }

    public JoinTableBean findJTB(JoinTableBean joinTableBean, Class<BaseEntity> baseEntityClass, boolean... booleans) throws Exception {
        boolean var4 = true;
        if (booleans.length > 0) {
            var4 = booleans[0];
        }

        return this.findJTB(joinTableBean, baseEntityClass, null, null, var4);
    }

    public JoinTableBean findJTB(JoinTableBean joinTableBean, Class<BaseEntity> baseEntity, String s, String s1, boolean... booleans) throws Exception {
        boolean var4 = true;
        if (booleans.length > 0) {
            var4 = booleans[0];
        }

        return this.findJTB(baseEntity, baseEntity, null, null, var4);
    }

    /*public JoinTableBean findJTB(JoinTableBean joinTableBean, String join, String c, String d, boolean e) throws Exception {
        Class table = joinTableBean.getChildTable();
        if (StringUtils.isEmpty(join)) {
            join = JoinTableBean.INNER_JOIN;
        }

        String var7 = table + tableSpitSign + a;
        List var8 = null;
        JoinTableBean var9 = null;
        if ((a.relationMap.containsKey(var7) ? (var8 = (List) a.relationMap.get(var7)) : (var8 = (List) a.relationMapReverse.get(var7))) == null) {
            throw new Exception(ServiceUtils.ALLATORIxDEMO("执丶利術") + var6 + FileDescEntity.ALLATORIxDEMO("响") + a + ServiceUtils.ALLATORIxDEMO("先聍皿酔罕俸恔５诌利qvRwoxYu^_Rw_|I籢世迂衷淢力"));
        } else {
            int var11;
            for (int var10000 = var11 = 0; var10000 < var8.size(); var10000 = var11) {
                JoinTableToolsInfoBean var10 = (JoinTableToolsInfoBean) var8.get(var11);
                if (var11 == 0) {
                    var9 = JoinTableBean.fastGetJtb(a.getTableAlias(), a, (String) var10.findTableOneToOneFieldMap().get(var6), (String) var10.findTableOneToOneFieldMap().get(a), a, a);
                } else {
                    var9.putOnCond((String) var10.findTableOneToOneFieldMap().get(var6), (String) var10.findTableOneToOneFieldMap().get(a), a);
                }

                ++var11;
            }

            if (a) {
                var9.putOnCondValue("delFlag", "1", " != ", a);
            }

            return var9;
        }
    }

    public static List<JoinTableToolsInfoBean> findJtbInfoBeanByTableAndField(String a, String a) {
        ArrayList var2 = Lists.newArrayList();
        boolean[] var10000 = new boolean[1];
        boolean var10002 = true;
        var10000[0] = false;

        Iterator var4;
        for (Iterator var8 = var4 = finderIns(var10000).relationMap.entrySet().iterator(); var8.hasNext(); var8 = var4) {
            Map.Entry var3;
            String[] var5 = ((String) (var3 = (Map.Entry) var4.next()).getKey()).split(tableSpitSign);
            JoinTableToolsInfoBean var6;
            Iterator var7;
            if (a.equals(var5[0])) {
                for (var8 = var7 = ((List) var3.getValue()).iterator(); var8.hasNext(); var8 = var7) {
                    if ((var6 = (JoinTableToolsInfoBean) var7.next()).getMainRefFieldName().equals(a)) {
                        var2.add(var6);
                    }
                }
            }

            if (a.equals(var5[1])) {
                for (var8 = var7 = ((List) var3.getValue()).iterator(); var8.hasNext(); var8 = var7) {
                    if ((var6 = (JoinTableToolsInfoBean) var7.next()).getChildRefFieldName().equals(a)) {
                        var2.add(var6);
                    }
                }
            }
        }

        return var2;
    }*/


    public JoinTableBean findJTB(Class<BaseEntity> baseEntity, Class<BaseEntity> childTableName, String joinKind, String andOr, boolean boo) throws Exception {
        if (StringUtils.isEmpty(joinKind)) {
            joinKind = JoinTableBean.INNER_JOIN;
        }
        String var6 = baseEntity.getName() + tableSpitSign + childTableName.getName();
        List<JoinTableToolsInfoBean> var7;
        JoinTableBean jtb = null;
        if ((this.relationMap.containsKey(var6) ? (var7 = this.relationMap.get(var6)) : (var7 = this.relationMapReverse.get(var6))) == null) {
            throw new Exception("找不到表" + baseEntity.getName() + "和" + childTableName + "关联的配置信息，请到JoinTableFinder类中进行添加");
        } else {
            for (int i = 0; i < var7.size(); ++i) {
                JoinTableToolsInfoBean joinTableToolsInfoBean = var7.get(i);
                if (i == 0) {
                    jtb = JoinTableBean.fastGetJtb(baseEntity, childTableName, joinKind, joinTableToolsInfoBean.getMainRefFieldName(), joinTableToolsInfoBean.getChildRefFieldName(), andOr);
                } else {
                    jtb.putOnCond(joinTableToolsInfoBean.findTableOneToOneFieldMap().get(childTableName), joinTableToolsInfoBean.findTableOneToOneFieldMap().get(childTableName), joinKind);
                }
            }

            if (boo) {
                jtb.putOnCondValue("delFlag", "1", " != ", joinKind);
            }

            return jtb;
        }
    }
}