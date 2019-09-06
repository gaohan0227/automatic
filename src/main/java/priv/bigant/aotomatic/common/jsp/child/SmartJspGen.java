package priv.bigant.aotomatic.common.jsp.child;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import priv.bigant.aotomatic.common.BaseEntity;
import priv.bigant.aotomatic.common.PageMap;
import priv.bigant.aotomatic.common.Reflections;
import priv.bigant.aotomatic.common.jsp.DynamicJspBaseConfigVO;
import priv.bigant.aotomatic.common.jsp.DynamicJspFormConfigVO;
import priv.bigant.aotomatic.common.jsp.DynamicJspListConfigVO;
import priv.bigant.aotomatic.common.jsp.JspElement;
import priv.bigant.aotomatic.common.table.JoinTableToolsInfoBean;
import priv.bigant.aotomatic.common.table.TableColumn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SmartJspGen {
    private DynamicJspBaseConfigVO dynamicJspBaseConfigVO;
    private Map<String, MethodDescVO> L;
    private Map<String, SmartJspGen.MethodDescVO> e;
    //private Map<String, TableColumn> k;
    private Class entityTable;
    private Map<String, SmartJspGen.MethodDescVO> ALLATORIxDEMO;

    public SmartJspGen(Class<BaseEntity> entityTable, DynamicJspBaseConfigVO dynamicJspBaseConfigVO) {
        this.entityTable = entityTable;
        this.dynamicJspBaseConfigVO = dynamicJspBaseConfigVO;
        //this.k = DBTool.findTableFieldMap(a);
    }

    /*public void queryCondGen(String... strings) {
        int length = strings.length;

        for (String a : strings) {
            TableColumn var5;
            if ((var5 = (TableColumn)this.k.get(a)) == null) {
                ((DynamicJspListConfigVO)this.dynamicJspBaseConfigVO).queryCondAddFormInputForVirField(a, a);
            } else {
                SmartJspGen.MethodDescVO var6 =null;//= ALLATORIxDEMO("1", var5.findWidgetType("area_list_querycond"));
                Object[] var9 = new Object[2];
                var9[0] = this.entityTable;
                var9[1] = a;
                Object[] var7 = var9;
                if (var5.findWidgetType("area_list_querycond").equals("betweenDateselect")) {
                    var9 = new Object[4];
                    var9[0] = this.entityTable;
                    var9[1] = a;
                    var9[2] = "yyyy-MM-dd";
                    var9[3] = true;
                    var7 = var9;
                }

                if (var5.findWidgetType("area_list_querycond").equals("dateselect")) {
                    var9 = new Object[4];
                    var9[0] = this.entityTable;
                    var9[1] = a;
                    var9[2] = "yyyy-MM-dd";
                    var9[3] = false;
                    var7 = var9;
                }

                Reflections.invokeMethod(this.dynamicJspBaseConfigVO, var6.getMethodName(), var6.getParameterTypes(), var7);
            }
        }

    }*/

    public void formGen(String... a) {

       /* for (int var10000 = 0; var10000 < a.length; var10000++) {
            String w = a[var10000];
            TableColumn var5;
            if ((var5 = this.k.get(w)) == null) {
                ((DynamicJspFormConfigVO) this.dynamicJspBaseConfigVO).addFormInputForVirField(w, w);
            } else {
                String var6 = var5.findWidgetType("area_form");
                SmartJspGen.MethodDescVO var7 = this.ALLATORIxDEMO.get(var6);
                Object[] var21 = new Object[2];
                var21[0] = this.entityTable;
                var21[1] = a;
                Object[] var8 = var21;
                if (var6.equals("betweenDateselect") || var6.equals("dateselect")) {
                    var21 = new Object[3];
                    var21[0] = this.entityTable;
                    var21[1] = a;
                    var21[2] = "yyyy-MM-dd";
                    var8 = var21;
                }

                if (var6.equals("fileImage")) {
                    var21 = new Object[3];
                    var21[0] = this.entityTable;
                    var21[1] = a;
                    var21[2] = "image/*";
                    var8 = var21;
                }

                if (var6.equals("fileAudio")) {
                    var21 = new Object[3];
                    var21[0] = this.entityTable;
                    var21[1] = a;
                    var21[2] = "audio/*";
                    var8 = var21;
                }

                if (var6.equals("fileVideo")) {
                    var21 = new Object[3];
                    var21[0] = this.entityTable;
                    var21[1] = a;
                    var21[2] = "video/*";
                    var8 = var21;
                }

                if (var6.equals("select")) {
                    var21 = new Object[1];
                    var21[0] = a;
                    var8 = var21;
                }

                if (var6.equals("hidden")) {
                    var21 = new Object[1];
                    var21[0] = a;
                    var8 = var21;
                }

                String var9;
                Iterator var23;
                if (var6.equals("selectJoinTable")) {
                    List var16 = (List) var5.getExtendInfo();
                    var9 = JspElement.findWithCamelTableName(this.entityTable, false);
                    ArrayList var10 = new ArrayList();
                    ArrayList var11 = new ArrayList();

                    Iterator var13;
                    for (var23 = var13 = var16.iterator(); var23.hasNext(); var23 = var13) {
                        JoinTableToolsInfoBean var12 = (JoinTableToolsInfoBean) var13.next();
                        String var14 = null;
                        ArrayList var24;
                        if (var9.equals(var12.getMainTable().getName())) {
                            var14 = var12.getChildTable().getName();
                            var24 = var10;
                        } else {
                            var14 = var12.getMainTable().getName();
                            var24 = var10;
                        }

                        var24.add(var14);
                        var11.add(this.ALLATORIxDEMO.get(var14));
                    }

                    String[] var10001 = new String[var10.size()];
                    String[] var19 = (String[]) var10.toArray(var10001);
                    var10001 = new String[var11.size()];
                    String[] var20 = (String[]) var11.toArray(var10001);
                    var21 = new Object[3];
                    var21[0] = a;
                    var21[1] = var19;
                    var21[2] = var20;
                    var8 = var21;
                }

                Object var17 = Reflections.invokeMethod(this.dynamicJspBaseConfigVO, var7.getMethodName(), var7.getParameterTypes(), var8);
                if (var5.getFormFieldValidateList() != null && var5.getFormFieldValidateList().size() > 0) {
                    Iterator var18;
                    var23 = var18 = var5.getFormFieldValidateList().iterator();

                    while (var23.hasNext()) {
                        var9 = (String) var18.next();
                        String var22;
                        Object[] var25;
                        if ("required".equals(var9)) {
                            DynamicJspBaseConfigVO var26 = this.dynamicJspBaseConfigVO;
                            var22 = "setMustInputField";
                            var25 = new Object[1];
                            String[] var10005 = new String[1];
                            var10005[0] = w;
                            var25[0] = var10005;
                            Reflections.invokeMethodByName(var26, var22, var25);
                            var23 = var18;
                        } else {
                            var22 = "addValidateByClass";
                            var25 = new Object[1];
                            var25[0] = var9;
                            Reflections.invokeMethodByName(var17, var22, var25);
                            var23 = var18;
                        }
                    }
                }
            }

        }*/

    }

    public void tableDataGen(String... a) {
        /*String[] var4 = a;
        int var3 = a.length;

        int var2;
        for (int var10000 = var2 = 0; var10000 < var3; var10000 = var2) {
            String w = var4[var2];
            if (!"id".equals(w.toLowerCase())) {
                TableColumn var5;
                if ((var5 = this.k.get(w)) == null) {
                    ((DynamicJspListConfigVO) this.dynamicJspBaseConfigVO).tableDataAddTDForVirField(w, w);
                } else {
                    String var6 = var5.findWidgetType("area_list_tabledata");
                    SmartJspGen.MethodDescVO var7 = this.ALLATORIxDEMO.get(var6);
                    Object[] var18 = new Object[2];
                    var18[0] = this.entityTable;
                    var18[1] = a;
                    Object[] var8 = var18;
                    if (var6.equals("betweenDateselect")) {
                        var18 = new Object[3];
                        var18[0] = this.entityTable;
                        var18[1] = a;
                        var18[2] = "yyyy-MM-dd HH:mm:ss";
                        var8 = var18;
                    }

                    if (var6.equals("dateselect")) {
                        var18 = new Object[3];
                        var18[0] = this.entityTable;
                        var18[1] = a;
                        var18[2] = "yyyy-MM-dd HH:mm:ss";
                        var8 = var18;
                    }

                    if (var6.equals("fileImage")) {
                        var18 = new Object[4];
                        var18[0] = this.entityTable;
                        var18[1] = a;
                        var8 = var18;
                    }

                    if (var6.equals("selectJoinTable")) {
                        List var14 = (List) var5.getExtendInfo();
                        var6 = JspElement.findWithCamelTableName(this.entityTable, false);
                        ArrayList var9 = new ArrayList();
                        ArrayList var10 = new ArrayList();

                        Iterator var11;
                        for (Iterator var19 = var11 = var14.iterator(); var19.hasNext(); var19 = var11) {
                            JoinTableToolsInfoBean var15 = (JoinTableToolsInfoBean) var11.next();
                            String var12 = null;
                            ArrayList var20;
                            if (var6.equals(var15.getMainTable().getName())) {
                                var12 = var15.getChildTable().getName();
                                var20 = var9;
                            } else {
                                var12 = var15.getMainTable().getName();
                                var20 = var9;
                            }

                            var20.add(var12);
                            var10.add(this.ALLATORIxDEMO.get(var12));
                        }

                        String[] var10001 = new String[var9.size()];
                        String[] var16 = (String[]) var9.toArray(var10001);
                        var10001 = new String[var10.size()];
                        String[] var17 = (String[]) var10.toArray(var10001);
                        var18 = new Object[3];
                        var18[0] = a;
                        var18[1] = var16;
                        var18[2] = var17;
                        var8 = var18;
                    }

                    Reflections.invokeMethod(this.dynamicJspBaseConfigVO, var7.getMethodName(), var7.getParameterTypes(), var8);
                }
            }

            ++var2;
        }*/

    }

    public <T extends BaseEntity> void tableDataGen(SFunction<T, ?>[] getName) {
        for (SFunction<T, ?> w : getName) {

            ((DynamicJspListConfigVO) this.dynamicJspBaseConfigVO).tableDataAddTD(w);
            /*TableColumn var5;
            if ((var5 = this.k.get(w)) == null) {
                ((DynamicJspListConfigVO) this.dynamicJspBaseConfigVO).tableDataAddTDForVirField(w, w);
            } else {
                String var6 = var5.findWidgetType("area_list_tabledata");
                SmartJspGen.MethodDescVO var7 = this.ALLATORIxDEMO.get(var6);
                Object[] var18 = new Object[2];
                var18[0] = this.entityTable;
                var18[1] = a;
                Object[] var8 = var18;
                if (var6.equals("betweenDateselect")) {
                    var18 = new Object[3];
                    var18[0] = this.entityTable;
                    var18[1] = a;
                    var18[2] = "yyyy-MM-dd HH:mm:ss";
                    var8 = var18;
                }

                if (var6.equals("dateselect")) {
                    var18 = new Object[3];
                    var18[0] = this.entityTable;
                    var18[1] = a;
                    var18[2] = "yyyy-MM-dd HH:mm:ss";
                    var8 = var18;
                }

                if (var6.equals("fileImage")) {
                    var18 = new Object[4];
                    var18[0] = this.entityTable;
                    var18[1] = a;
                    var8 = var18;
                }

                if (var6.equals("selectJoinTable")) {
                    List var14 = (List) var5.getExtendInfo();
                    var6 = JspElement.findWithCamelTableName(this.entityTable, false);
                    ArrayList var9 = new ArrayList();
                    ArrayList var10 = new ArrayList();

                    Iterator var11;
                    for (Iterator var19 = var11 = var14.iterator(); var19.hasNext(); var19 = var11) {
                        JoinTableToolsInfoBean var15 = (JoinTableToolsInfoBean) var11.next();
                        String var12 = null;
                        ArrayList var20;
                        if (var6.equals(var15.getMainTable().getName())) {
                            var12 = var15.getChildTable().getName();
                            var20 = var9;
                        } else {
                            var12 = var15.getMainTable().getName();
                            var20 = var9;
                        }

                        var20.add(var12);
                        var10.add(this.ALLATORIxDEMO.get(var12));
                    }

                    String[] var10001 = new String[var9.size()];
                    String[] var16 = (String[]) var9.toArray(var10001);
                    var10001 = new String[var10.size()];
                    String[] var17 = (String[]) var10.toArray(var10001);
                    var18 = new Object[3];
                    var18[0] = a;
                    var18[1] = var16;
                    var18[2] = var17;
                    var8 = var18;
                }

                Reflections.invokeMethod(this.dynamicJspBaseConfigVO, var7.getMethodName(), var7.getParameterTypes(), var8);
            }*/
        }
    }

    protected class MethodDescVO {
        private String K;
        private Class<?>[] ALLATORIxDEMO;

        public String getMethodName() {
            return this.K;
        }

        public Class<?>[] getParameterTypes() {
            return this.ALLATORIxDEMO;
        }

        public void setMethodName(String ax) {
            this.K = ax;
        }

        public void setParameterTypes(Class<?>[] ax) {
            this.ALLATORIxDEMO = ax;
        }

        public MethodDescVO(String axx, Class<?>[] ax) {
            this.K = axx;
            this.ALLATORIxDEMO = ax;
        }
    }
}
