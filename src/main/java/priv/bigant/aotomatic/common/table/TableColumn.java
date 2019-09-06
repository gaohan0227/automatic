package priv.bigant.aotomatic.common.table;

import priv.bigant.aotomatic.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.*;

public class TableColumn {
    private Map<String, String> widgetTypeMap = new HashMap<>();
    private String F;
    private List<String> c;
    private String i;
    private static final long G = 1L;
    public static final String area_list_tabledata = "area_list_tabledata";
    private String d;
    public static final String area_list_querycond = "area_list_querycond";
    public static final String area_form = "area_form";
    private int H;
    private String a;
    private String tableName;
    private String B;
    public static final String area_defalut = "area_defalut";
    private String m;
    private Integer D;
    private String f;
    private String L;
    private String e;
    private Object k;
    private Integer K;
    private String ALLATORIxDEMO;

    public void setPricisionTwo(Integer a) {
        this.K = a;
    }

    public void setComboBoxSuffixIndex(String a) {
        this.f = a;
    }

    public void putIntoWidgetTypeMap(String a, String... b) {
        if (b.length >= 1 && !StringUtils.isEmpty(b[0])) {
            this.widgetTypeMap.put(b[0], a);
        } else {
            this.widgetTypeMap.put("area_defalut", a);
        }
    }

    public void setComments(String a) {
        this.m = a;
    }

    public String getIfTimeStamp() {
        return this.i;
    }

    public void setJdbcType(String a) {
        this.F = a;
    }

    public Integer getPricisionTwo() {
        return this.K;
    }

    public void setPricisionOne(Integer a) {
        this.D = a;
    }

    public void setJavaType(String a) {
        this.a = a;
    }

    public List<String> getFormFieldValidateList() {
        return this.c;
    }

    public List<String> getAnnotationList() {
        ArrayList var1 = new ArrayList();
        if ("This".equals(this.getJavaType())) {
            var1.add("com.fasterxml.jackson.annotation.JsonBackReference");
        }

        /*if ("java.util.Date".equals(this.getJavaType())) {
            var1.add(TemplateSMS.ALLATORIxDEMO("V\tXHS\u0007F\u0012P\u0014M\u000bYH_\u0007V\rF\t[HT\b[\tA\u0007A\u000fZ\b\u001b,F\t[ Z\u0014X\u0007ANE\u0007A\u0012P\u0014[F\bF\u0017\u001fL\u001fLKx+\u0018\u0002QF}.\u000f\u000bX\\F\u0015\u0017O"));
        }

        if ("1".equals(this.getIsNull()) || ControllerDevelopDescVO.ALLATORIxDEMO("d;E&Y(").equals(this.getJavaType())) {
            if (!"1".equals(this.getIsNull()) && TemplateSMS.ALLATORIxDEMO("f\u0012G\u000f[\u0001").equals(this.getJavaType()) && !"0".equals(this.D)) {
                var1.add(ControllerDevelopDescVO.ALLATORIxDEMO("X=Pa_&U*E!V;RaA.[&S.C EaT Y<C=V&Y;Da{*Y(C'\u001f\"^!\n\u007f\u001boZ.Or") + this.D + TemplateSMS.ALLATORIxDEMO("J\u0015\u000bP\u0015F\u0007R\u0003\bD") + a.getComments() + ControllerDevelopDescVO.ALLATORIxDEMO("锰庑徊題亄亹o\u0007o咻o") + this.D + TemplateSMS.ALLATORIxDEMO("F乾閒\u0017O"));
                return var1;
            }

            if (ControllerDevelopDescVO.ALLATORIxDEMO("d;E&Y(").equals(this.getJavaType()) && !"0".equals(this.D)) {
                var1.add(TemplateSMS.ALLATORIxDEMO("Z\u0014RH]\u000fW\u0003G\bT\u0012PHC\u0007Y\u000fQ\u0007A\tGHV\t[\u0015A\u0014T\u000f[\u0012FHy\u0003[\u0001A\u000e\u001d\u000b\\\b\bV\u0019FX\u0007M[") + this.D + ControllerDevelopDescVO.ALLATORIxDEMO("c\u0017\"R<D.P*\nm") + a.getComments() + TemplateSMS.ALLATORIxDEMO("错库徣顎亭亻F\u0005F咹F") + this.D + ControllerDevelopDescVO.ALLATORIxDEMO("o乼閻\u0015f"));
            }
        }*/

        return var1;
    }

    @Length(
            min = 1,
            max = 200
    )
    public String getName() {
        return StringUtils.lowerCase(this.ALLATORIxDEMO);
    }

    public TableColumn(String a) {
        this.tableName = a;
    }

    public String getJdbcType() {
        return StringUtils.lowerCase(this.F);
    }

    public void addIntoFormFieldValidateList(String a) {
        if (this.c == null) {
            this.c = new ArrayList();
        }

        this.c.add(a);
    }

    public String getJavaType() {
        return this.a;
    }

    public TableColumn() {
    }

    /*public String getSimpleJavaType() {
        if (TemplateSMS.ALLATORIxDEMO("a\u000e\\\u0015").equals(this.getJavaType())) {
            return StringUtils.capitalize(this.l);
        } else {
            return StringUtils.indexOf(this.getJavaType(), ".") != -1 ? StringUtils.substringAfterLast(this.getJavaType(), TemplateSMS.ALLATORIxDEMO("H")) : this.getJavaType();
        }
    }*/

    public Integer getPricisionOne() {
        return this.D;
    }

    public void setTableName(String a) {
        this.tableName = a;
    }

    public List<String> getSimpleAnnotationList() {
        ArrayList var1 = new ArrayList();
        /*Iterator var3;
        Iterator var10000 = var3 = this.getAnnotationList().iterator();

        while(var10000.hasNext()) {
            String var2 = (String)var3.next();
            var10000 = var3;
            var1.add(StringUtils.substringAfterLast(var2, "."));
        }*/

        return var1;
    }

    public void setExtendInfo(Object a) {
        this.k = a;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getIsNull() {
        return this.e;
    }

    public void setFormFieldValidateList(List<String> a) {
        this.c = a;
    }

    public void setIsNull(String a) {
        this.e = a;
    }

    public String getComboBoxSuffixIndex() {
        return this.f;
    }

    public void setSort(int a) {
        this.H = a;
    }

    public String getValidateType() {
        /*String var1 = null;
        if (!StringUtils.startsWithIgnoreCase(this.getJdbcType(), ControllerDevelopDescVO.ALLATORIxDEMO("u\u0006p\u0006y\u001b")) && !StringUtils.startsWithIgnoreCase(this.getJdbcType(), TemplateSMS.ALLATORIxDEMO("{3x$p4"))) {
            if (!StringUtils.startsWithIgnoreCase(this.getJdbcType(), ControllerDevelopDescVO.ALLATORIxDEMO("\u000br\f~\u0002v\u0003")) && !StringUtils.startsWithIgnoreCase(this.getJdbcType(), TemplateSMS.ALLATORIxDEMO("(`+p4|%"))) {
                if (StringUtils.startsWithIgnoreCase(this.getJdbcType(), ControllerDevelopDescVO.ALLATORIxDEMO("\u001b~\u0001n\u0006y\u001b"))) {
                    var1 = "number";
                    return "number";
                } else {
                    if (StringUtils.startsWithIgnoreCase(this.getJdbcType(), TemplateSMS.ALLATORIxDEMO("/{2"))) {
                        var1 = "number";
                    }

                    return var1;
                }
            } else {
                var1 = "number";
                return "number";
            }
        } else {
            String[] var2;
            if (((var2 = StringUtils.split(StringUtils.substringBetween(this.getJdbcType(), ControllerDevelopDescVO.ALLATORIxDEMO("g"), TemplateSMS.ALLATORIxDEMO("O")), ",")) == null || var2.length != 2 || Integer.parseInt(var2[1]) <= 0) && var2 != null && var2.length == 1) {
                Integer.parseInt(var2[0]);
            }

            var1 = "number";
            return "number";
        }*/
        return null;
    }

    public void setIsPk(String a) {
        this.L = a;
    }

    public String getMaxLength() {
        String var1 = "0";
        if (this.D != null && this.D > 0) {
            var1 = String.valueOf(this.D);
        }

        return var1;
    }

    public String[][] getJavaFieldAttrs() {
        /*String[] var1;
        String[][] var2 = new String[(var1 = StringUtils.split(StringUtils.substringAfter(this.getJavaField(), "|"), TemplateSMS.ALLATORIxDEMO("\u001a"))).length][2];
        int var3;
        if (var1 != null) {
            for(int var10000 = var3 = 0; var10000 < var1.length; var10000 = var3) {
                var2[var3][0] = var1[var3];
                String[] var4 = var2[var3];
                String var10002 = StringUtils.toUnderScoreCase(var1[var3]);
                ++var3;
                var4[1] = var10002;
            }
        }

        return var2;*/
        return null;
    }

    public void setIfTimeStamp(String a) {
        this.i = a;
    }

    public String getIsPk() {
        return this.L;
    }

    public String getJavaField() {
        return this.d;
    }

    public String findWidgetType(String a) {
        String var2 = null;
        if (StringUtils.isNotEmpty(a)) {
            var2 = (String) this.widgetTypeMap.get(a);
        }

        if (StringUtils.isEmpty(var2)) {
            var2 = (String) this.widgetTypeMap.get("area_defalut");
        }

        return var2;
    }

    public String getSimpleJavaField() {
        return StringUtils.substringBefore(this.getJavaField(), ".");
    }

    public void setDateFormatType(String a) {
        this.B = a;
    }

    public Object getExtendInfo() {
        return this.K;
    }

    public String getNameAndComments() {
        return this.getName() + (this.m == null ? "" : "  :  " + this.m);
    }

    public String getJavaFieldName() {
        String[][] var1;
        return (var1 = this.getJavaFieldAttrs()).length > 0 ? this.getSimpleJavaField() + "." + var1[0][0] : "";
    }

    public int getSort() {
        return this.H;
    }

    public void setJavaField(String a) {
        this.d = a;
    }

    public String getComments() {
        return this.m;
    }

    public Boolean getIsNotBaseField() {
        //return !StringUtils.equals(this.getSimpleJavaField(), TemplateSMS.ALLATORIxDEMO("\\\u0002")) && !StringUtils.equals(this.getSimpleJavaField(), "remarks") && !StringUtils.equals(this.getSimpleJavaField(), ControllerDevelopDescVO.ALLATORIxDEMO("T=R.C*u6")) && !StringUtils.equals(this.getSimpleJavaField(), "createDate") && !StringUtils.equals(this.getSimpleJavaField(), TemplateSMS.ALLATORIxDEMO("@\u0016Q\u0007A\u0003w\u001f")) && !StringUtils.equals(this.getSimpleJavaField(), "updateDate") && !StringUtils.equals(this.getSimpleJavaField(), "delFlag") ? true : false;
        return false;
    }

    public String getDateFormatType() {
        return this.B;
    }

    public void setName(String a) {
        this.ALLATORIxDEMO = a;
    }

    public String getJavaFieldId() {
        return StringUtils.substringBefore(this.getJavaField(), "|");
    }
}
