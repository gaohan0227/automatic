package priv.bigant.aotomatic.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import priv.bigant.aotomatic.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class JspToolBase {
    public static ListShowConstant lsc = new ListShowConstant();

    /*public String findJoinTableDataValue(String a, String a2, String a4) {
        String fieldName = "fieldName can't be blank";
        String[] var6;
        int var5 = (var6 = a.split(",")).length;

        for(int i = 0; i < var5; i++) {
            String var7 = PackageUtil.findDestClassPath(var6[i]);
            String[] var8 = a.split(",");
            Map var9 = null;

            try {
                BaseEntity var12 = (BaseEntity)Class.forName(var7).newInstance();
                var12.setId(a);
                if ((var9 = this.findJoinTableData(var12, var8)).containsKey(a)) {
                    fieldName = (String)var9.get(a);
                    return fieldName;
                }
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var10) {
                var10.printStackTrace();
            }
        }

        return fieldName;
    }
    public BaseEntity getEntityByEntityId(String a, String a) {
        if (StringUtils.isEmpty(a)) {
            return null;
        } else {
            a = PackageUtil.findDestClassPath(StringUtils.firstToUpper(a));
            BaseEntity var3 = null;

            BaseEntity var10000;
            label17: {
                try {
                    var3 = (BaseEntity)Class.forName(a).newInstance();
                } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var4) {
                    var10000 = var3;
                    var4.printStackTrace();
                    break label17;
                }

                var10000 = var3;
            }

            boolean[] var10001 = new boolean[0];
            boolean var10003 = true;
            return ((CrudService)SpringContextHolder.getBean(SpringContextHolder.findServiceNameByEntity(var10000, var10001))).get(a);
        }
    }

    public static String parseElForEle(String a, BaseEntity a, HttpServletRequest a2) {
        HttpServletRequest a = Jsoup.parseBodyFragment(a).body().children();
        BaseEntity a = SpringElParse.getInstance(a);

        Iterator var4;
        Iterator var5;
        for(Iterator var10000 = var4 = a.iterator(); var10000.hasNext(); var10000 = var4) {
            Element var3;
            for(var10000 = var5 = (var3 = (Element)var4.next()).attributes().iterator(); var10000.hasNext(); var10000 = var5) {
                Attribute var10 = (Attribute)var5.next();
                String var6 = var10.getKey();
                String var7;
                if (JspElement.ifElExpress(var7 = var10.getValue())) {
                    Object var11 = a.parseElExpress(var7);
                    var3.attr(var6, var11 == null ? null : var11.toString());
                }
            }
        }

        return a.outerHtml();
    }

    public String appendParamForUrl(String a, String... a) {
        if ((a = a).length % 2 == 0) {
            String var3 = "";
            String var4 = "";

            int var5;
            for(int var10000 = var5 = 0; var10000 < a.length; var10000 = var5) {
                if (var5 % 2 == 0) {
                    var3 = a[var5];
                } else {
                    var4 = a[var5];
                    String[] var10002 = new String[2];
                    boolean var10004 = true;
                    var10002[0] = var3;
                    var10002[1] = var4;
                    a = a.appendParamForUrl(a, var10002);
                }

                ++var5;
            }
        }

        return a;
    }

    public BaseEntity getEntityByEntityId(String a, String a, String a) throws Exception {
        if (StringUtils.isEmpty(a)) {
            return null;
        } else {
            a = PackageUtil.findDestClassPath(StringUtils.firstToUpper(a));
            BaseEntity var4 = null;

            JspToolBase var10000;
            label33: {
                try {
                    var4 = (BaseEntity)Class.forName(a).newInstance();
                } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var8) {
                    var10000 = a;
                    var8.printStackTrace();
                    break label33;
                }

                var10000 = a;
            }

            Iterator a;
            Iterator var11 = a = var10000.j(a).entrySet().iterator();

            while(var11.hasNext()) {
                String a = (Map.Entry)a.next();
                boolean[] var12 = new boolean[1];
                boolean var10002 = true;
                var12[0] = false;
                JoinTableBean var5 = JoinTableFinderFactory.finderIns(var12).findJTB(var4, (String)a.getKey(), JoinTableBean.LEFT_JOIN, (String)null, false);
                Iterator var7;
                var11 = var7 = ((List)a.getValue()).iterator();

                while(var11.hasNext()) {
                    String var6 = (String)var7.next();
                    var11 = var7;
                    var5.putSelect(var6);
                }

                var11 = a;
                var5.joinInto(var4);
            }

            boolean[] var10001 = new boolean[0];
            boolean var10003 = true;
            return ((CrudService)SpringContextHolder.getBean(SpringContextHolder.findServiceNameByEntity(var4, var10001))).get(a);
        }
    }

    protected static <T> T dealRetunByKey(String a, Map a) {
        return UploadUtils.ALLATORIxDEMO("X\rU").equals(a) ? a : a.get(a);
    }*/

    /*public static String transferPageUrlELExpress(String a, BaseEntity b, HttpServletRequest c) {
        if (StringUtils.isEmpty(a)) {
            return a;
        } else {
            String var3 = a;

            Iterator var4;
            for (Iterator var10000 = var4 = lsc(a).entrySet().iterator(); var10000.hasNext(); var10000 = var4) {
                String var5 = (String) ((Map.Entry) var4.next()).getKey();
                Object var6 = null;
                String var7;
                Object var8;
                if (var5.contains(UploadUtils.ALLATORIxDEMO("\u0017"))) {
                    var7 = var5.substring(var5.lastIndexOf(UploadUtils.ALLATORIxDEMO("\u0017")) + 1);
                    var8 = var6 = Reflections.getFieldValue(a, var7);
                } else {
                    var8 = UploadUtils.ALLATORIxDEMO("Z\u0015A").equals(var5) ? (var6 = a.getContextPath()) : (var6 = a.getParameter(var5));
                }

                var7 = var8 == null ? "" : var6.toString();
                var3 = var3.replace(UploadUtils.ALLATORIxDEMO("EB") + var5 + UploadUtils.ALLATORIxDEMO("D"), var7);
            }

            return var3;
        }
    }*/

/*
    public <T> T findJoinTableData(BaseEntity baseEntity, SFunction... select) {

        ArrayList var3 = new ArrayList();
        boolean[] var10001 = new boolean[0];
        boolean var10003 = true;
        LambdaQueryWrapper<BaseEntity> objectQueryWrapper = new QueryWrapper<>(baseEntity).lambda();
        objectQueryWrapper.select(select);

        List var13 = ((BaseMapper)SpringContextHolder.getBean(SpringContextHolder.findDaoNameByEntity(baseEntity, var10001))).selectList(objectQueryWrapper);
        HashMap hashMap = new HashMap();
        Iterator var4;
        Iterator var10000 = var4 = var13.iterator();

        while(var10000.hasNext()) {
            Object var14;
            String var5 = Reflections.getFieldValue(var14 = var4.next(), UploadUtils.ALLATORIxDEMO("\b]")).toString();
            MyStringBuffer var6 = MyStringBuffer.newInstance();
            String[] var10 = a;
            int var9 = a.length;

            int var8;
            for(int var16 = var8 = 0; var16 < var9; var16 = var8) {
                String var7 = var10[var8];
                if (Reflections.isContainFieldDyna(var14, var7)) {
                    Object var15;
                    String var11 = (var15 = Reflections.getFieldValue(var14, var7)) == null ? "" : var15.toString();
                    String[] var17;
                    if (StringUtils.isEmpty(var6.toString())) {
                        var17 = new String[1];
                        var17[0] = var11;
                        var6.append(var17);
                    } else {
                        var17 = new String[2];
                        var17[0] = "-";
                        var17[1] = var11;
                        var6.append(var17);
                    }
                }

                ++var8;
            }

            var10000 = var4;
            hashMap.put(var5, var6.toString());
        }

        return hashMap;
    }

    public Map<String, String> findJoinTableData(BaseEntity baseEntity, String[] a) {
        ArrayList var3 = new ArrayList();
        boolean[] var10001 = new boolean[0];
        boolean var10003 = true;
        QueryWrapper objectQueryWrapper = new QueryWrapper<>(baseEntity);
        SpringContextHolder.findServiceNameByEntity()
        ((BaseMapper)SpringContextHolder.getBean(SpringContextHolder.findDaoNameByEntity(baseEntity, var10001))).selectList();
        List var13 = ((BaseMapper)SpringContextHolder.getBean(SpringContextHolder.findDaoNameByEntity(baseEntity, var10001))).selectList(objectQueryWrapper);
        HashMap hashMap = new HashMap();
        Iterator var4;
        Iterator var10000 = var4 = var13.iterator();

        while(var10000.hasNext()) {
            Object var14;
            String var5 = Reflections.getFieldValue(var14 = var4.next(), UploadUtils.ALLATORIxDEMO("\b]")).toString();
            MyStringBuffer var6 = MyStringBuffer.newInstance();
            String[] var10 = a;
            int var9 = a.length;

            int var8;
            for(int var16 = var8 = 0; var16 < var9; var16 = var8) {
                String var7 = var10[var8];
                if (Reflections.isContainFieldDyna(var14, var7)) {
                    Object var15;
                    String var11 = (var15 = Reflections.getFieldValue(var14, var7)) == null ? "" : var15.toString();
                    String[] var17;
                    if (StringUtils.isEmpty(var6.toString())) {
                        var17 = new String[1];
                        var10003 = true;
                        var17[0] = var11;
                        var6.append(var17);
                    } else {
                        var17 = new String[2];
                        var10003 = true;
                        var17[0] = "-";
                        var17[1] = var11;
                        var6.append(var17);
                    }
                }

                ++var8;
            }

            var10000 = var4;
            hashMap.put(var5, var6.toString());
        }

        return hashMap;
    }

    public void setLsc(ListShowConstant a) {
        lsc = a;
    }

    public JspToolBase() {
    }
    */

    public static String parseATagUrl(String a, BaseEntity b, HttpServletRequest c) {
        /*Element var3;
        Element var10000 = var3 = Jsoup.parse(a).getElementsByTag("a").get(0);
        String var4 = var10000.attr("href");
        var10000.attr("href", transferPageUrlELExpress(var4, a, a));
        Element var10002 = var3;
        var10000 = var3;
        String var5 = var3.attr(UploadUtils.ALLATORIxDEMO("V\u000fZ\rP\u0002R"));
        var10002.attr(UploadUtils.ALLATORIxDEMO("V\u000fZ\rP\u0002R"), transferPageUrlELExpress(var5, a, a));
        return var10000.outerHtml();*/
        return "<a href=\"#\" htmlEscape=\"false\" onclick=\"openDialog('修改','${ctx}/fuelsaver/fuelSaver/form?id=${fuelSaver.id}','800px', '500px')\" class=\"btn btn-info btn-xs\"><i class=\"fa fa-edit\"></i>修改</a>";
    }

    public static ListShowConstant getLsc() {
        return lsc;
    }
}
