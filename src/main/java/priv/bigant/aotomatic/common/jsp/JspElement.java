package priv.bigant.aotomatic.common.jsp;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.util.CollectionUtils;
import priv.bigant.aotomatic.StringUtils;
import priv.bigant.aotomatic.common.*;
import priv.bigant.aotomatic.common.jsp.child.JspElementI;
import priv.bigant.aotomatic.common.jsp.child.JspElementSpan;
import priv.bigant.aotomatic.common.jsp.child.JspElementTH;
import priv.bigant.aotomatic.common.table.TableColumn;

import java.util.*;

public abstract class JspElement {
    public static final String PATH = "PATH";
    public static final String HTML_ESCAPE = "htmlEscape";
    public static final String CLASS = "class";
    public static final String TJ_TEXT = "tjText";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String VALUE = "value";
    public static final String INDEX = "index";
    public static final String TARGET = "target";
    public static final String TITLE = "title";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String CONFIRM_TIP = "confirmTip";
    public static final String NEED_TARGET = "needTarget";
    public static final String ALT = "alt";
    public static final String PX = "px";
    public static final String STYLE = "style";
    public static final String ECHO = "echo";
    public static final String ACCEPT = "accept";
    public static final String FILE_TYPE = "fileType";
    public static final String IF_NULL_TIP = "ifNullTip";
    public static final String SAVE_URL_FIELD = "saveUrlField";
    public static final String UP_FILE_TYPE = "upFileType";
    public static final String AT_LEAST_CHAR = "atLeastChar";
    public static final String ENTITY_NAME = "entityName";
    public static final String SHOW_FIELDS = "showFields";
    public static final String CALL_BACK_METHOD_NAME = "callBackMethodName";
    public static final String MUTIL_SELECT = "mutilSelect";
    public static final String WINDOWS_TITLE = "windowsTitle";
    public static final String PARENT_NAME = "parentName";
    public static final String CHILD_NAME = "childName";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String COLOR = "color";


    protected boolean ifFormElement;
    private String k;
    protected JspElement correspondJspElement;
    protected String html = "test";
    protected String elementCode;
    protected String childElementCode;
    private String fieldName;
    protected List<JspElement> childList = new ArrayList();
    public Map<String, JspAttribute> attribute = new HashMap();
    public String shiroHasPermission;
    private String entityName;
    protected String queryCondPrintPrefix;
    public String showHideElExpress;
    private Class<BaseEntity> entityClass;
    private SFunction<BaseEntity, ?> fieldNameFun;


    public String getFieldName() {
        return this.fieldName;
    }

    public void genCode() {
        String var1 = "";

        //String var1 = this.ALLATORIxDEMO(this.ifFormElement, this.elementCode);
        if (this.elementCode.equals("atagButton")) {
            var1 = "a";
        }

        if (!this.elementCode.equals("text")) {
            Element var2;
            this.bulidElementChild_digui(var2 = this.bulidElement(StringUtils.isEmpty(var1) ? this.elementCode : var1, this.attribute), this.childList);
            this.html = var2.outerHtml();
            System.out.println(this.html);
        }
    }

    public void setShiroHasPermission(String shiroHasPermission) {
        this.shiroHasPermission = shiroHasPermission;
    }

    public void addSimpleAttr(String attr, String attrValue) {
        this.attribute.put(attr, this.createAttrSimple(attr, attrValue));
    }

    public JspAttribute removeValidateByClass(String a) {
        JspAttribute var2;
        if ((var2 = (JspAttribute) this.attribute.get(CLASS)) != null) {
            String var3;
            if ((var3 = var2.getAttrCovertValue()) == null) {
                var3 = "";
            }

            if (var3.contains(a)) {
                var3 = var3.replace(" " + a, "");
                var2.setAttrCovertValue(var3);
            }
        }

        return var2;
    }

    public String getShiroHasPermission() {
        return this.shiroHasPermission;
    }

    public JspElement getCorrespondJspElement() {
        return this.correspondJspElement;
    }

    /*      public static String buildJsFunctionContent(String a, Map<String, String> a, String a) {
              StringBuffer var3;
              StringBuffer var10000 = var3 = new StringBuffer();
              String[] var10001 = new String[1];
              boolean var10003 = true;
              var10001[0] = buildJsFunctionDefine(a, a, false);
              var10000.append(var10001);
              var10001 = new String[1];
              var10003 = true;
              var10001[0] = Collections3.ALLATORIxDEMO("@");
              var3.appendK(var10001);
              var10001 = new String[1];
              var10003 = true;
              var10001[0] = a;
              var3.appendK(var10001);
              var10001 = new String[1];
              var10003 = true;
              var10001[0] = BaseController.ALLATORIxDEMO("Z");
              var3.appendK(var10001);
              return var3.toString();
          }

          protected void addChildList(JspElement[] a) {
              JspElement[] a = this.ALLATORIxDEMO(a);
              a.getChildList().addAll(a);
          }

          public void setFieldChineseName(String a) {
              this.K = a;
          }
          }
      */
    public boolean isIfFormElement() {
        return this.ifFormElement;
    }

    public static String filterChineseNameEnglish(String a) {
        String var1;
        if (StringUtils.isEmpty(var1 = StringUtils.findChineseFromStr(a))) {
            var1 = a;
        }
        return var1;
    }

    public String getShowHideElExpress() {
        return this.showHideElExpress;
    }

    public void setAttribute(Map<String, JspAttribute> attribute) {
        this.attribute = attribute;
    }

    public void bulidElementChild_digui(Element a, List<JspElement> list) {
        Iterator var3;
        for (Iterator var10000 = var3 = list.iterator(); var10000.hasNext(); var10000 = var3) {
            Object list1 = var3.next();
            Element var4 = this.bulidElement(elementCode, this.attribute);
            //Element var4 = this.bulidElement(this.(this.ifFormElement, this.getElementCode()), this.attrMap);
            Node[] var10002 = new Node[1];
            var10002[0] = var4;
            a.insertChildren(0, var10002);
            //this.bulidElementChild_digui(var4, this.getChildList());
        }

    }

    public JspElement(String entityName, String fieldName) {
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.initDefaultAttribute();
    }

    public JspElement(String entityName, SFunction fieldName, String str) {
        this.entityName = entityName;
        this.fieldNameFun = fieldName;
        this.initDefaultAttribute();
    }
/*    public JspElement(Class<BaseEntity> entityClass, SFunction fieldNameFun) {
        this.entityClass = entityClass;
        this.fieldNameFun = fieldNameFun;
        this.initDefaultAttribute();
    }*/
    /*public void printElement(String a, Map<String, JspAttribute> a) {
        String a = new Element(a);
        Iterator var3;
        Iterator var10000 = var3 = a.entrySet().iterator();

        while(true) {
            while(var10000.hasNext()) {
                Map.Entry a;
                String var4 = ((JspAttribute)(a = (Map.Entry)var3.next()).getValue()).getAttrCovertValue();
                if (Collections3.ALLATORIxDEMO(",Q\f^ O").equals(a.getKey())) {
                    if (StringUtils.isNotEmpty(var4)) {
                        var10000 = var3;
                        a.appendText(var4);
                        continue;
                    }
                } else if (StringUtils.isNotEmpty(var4)) {
                    StringBuffer var7;
                    StringBuffer var8 = var7 = new StringBuffer();
                    String[] var10001 = new String[5];
                    boolean var10003 = true;
                    var10001[0] = BaseController.ALLATORIxDEMO("f\\");
                    var10001[1] = this.queryCondPrintPrefix;
                    var10001[2] = Collections3.ALLATORIxDEMO("vZ,O*v9K\u0003\u001c");
                    var10001[3] = (String)a.getKey();
                    var10001[4] = BaseController.ALLATORIxDEMO("\u0000\u001f\t#S6U\u0001H4B0S\u0014F.R'Z");
                    var8.append(var10001);
                    a.attr((String)a.getKey(), var7.toString());
                }

                var10000 = var3;
            }

            System.out.println(Collections3.ALLATORIxDEMO("\u0011r\u0011r\u0011r\u0011") + a.outerHtml());
            return;
        }
    }*/

    public static boolean ifElExpress(String a) {
        boolean var1 = false;
        if (StringUtils.isEmpty(a)) {
            return var1;
        } else {
            if (a.contains("StringUtils") || a.contains("ListUtils") || a.contains("JspTool") || a.contains("DateUtils") || a.contains("FieldValueConvertFinder")) {
                var1 = true;
            }

            if (a.contains("${")) {
                var1 = true;
            }

            return var1;
        }
    }

    public static boolean ifElExpress(JspAttribute a) {
        return ifElExpress(a.getAttrCovertValue());
    }

    public static TableColumn findTableColumn(Class<BaseEntity> entityClass, String fieldName) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        TableColumn tableColumn = new TableColumn(tableInfo.getTableName());
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        TableFieldInfo tableFieldInfo = fieldList.get(0);
        FieldFill fieldFill = tableFieldInfo.getFieldFill();

        return tableColumn;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Map<String, JspAttribute> getAttribute() {
        return this.attribute;
    }

    public static String findWithCamelTableName(Class entityClass, boolean b) {
        String var2;
        if ((var2 = StringUtils.findClassSimpleName(entityClass, b)).toLowerCase().equals("user")) {
            return "sysUser";
        } else if (var2.toLowerCase().equals(TJ_TEXT)) {
            return "tbUser";
        } else {
            String a = (b ? "Tb" : "tb");
            return a + StringUtils.firstToUpper(var2);
        }
    }

    public static String findTableFieldChineseName(Class tableClass, String filedName, Map<String, String> currFieldChineseNameMap) {
        if (currFieldChineseNameMap.containsKey(filedName)) {
            return currFieldChineseNameMap.get(filedName);
        } else {
            TableColumn c;
            if ((c = findTableColumn(tableClass, filedName)) == null) {
                return filedName;
            } else {
                String a;
                if (StringUtils.isEmpty(a = c.getComments())) {
                    a = a + "未配置";
                }

                return a;
            }
        }
    }

    public void parseEl(SpringElParse a) {
        Iterator var2;
        for (Iterator var10000 = var2 = this.attribute.entrySet().iterator(); var10000.hasNext(); var10000 = var2) {
            JspAttribute var3;
            if (ifElExpress(var3 = (JspAttribute) ((Map.Entry) var2.next()).getValue())) {
                String var4;
                var4 = a.parseElExpress(var3.getAttrCovertValue()).toString();
                var3.setAttrCovertValue(var4);
            }
        }

    }

    public String getHtml() {
        this.genCode();
        return this.html;
    }

    public void addChild(JspElement... jspElements) {
        int var3 = jspElements.length;

        int var2;
        for (int var10000 = var2 = 0; var10000 < var3; var10000 = var2) {
            JspElement a;
            if ((a = jspElements[var2]) != null) {
                this.childList.add(a);
            }

            ++var2;
        }

    }

    public static JspElement findMatchPureI(String a) {
        return JspElementI.newInstance(a);
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public JspAttribute addValidateByClass(String a) {
        JspAttribute var2;
        if ((var2 = this.attribute.get(CLASS)) != null) {
            String var3;
            if ((var3 = var2.getAttrCovertValue()) == null) {
                var3 = "";
            }

            if (!var3.contains(a)) {
                var3 = var3 + " " + a;
                var2.setAttrCovertValue(var3);
                return var2;
            }
        } else {
            this.addSimpleAttr(CLASS, a);
        }
        return var2;
    }

    public static String filterBrackets(String a) {
        if (StringUtils.isEmpty(a)) {
            return a;
        } else {
            if (a.contains("(")) {
                a = a.substring(0, a.indexOf("("));
            }

            if (a.contains("（")) {
                a = a.substring(0, a.indexOf("（"));
            }

            return a;
        }
    }

    public void setEntityName(String a) {
        this.entityName = a;
    }

    public void setIfFormElement(boolean a) {
        this.ifFormElement = a;
    }

    public List<JspElement> getChildList() {
        return this.childList;
    }

    protected String appendCtxELForUrl(String a) {
        return a.startsWith("//") ? "${ctx}" + a : "${ctx}/" + a;
    }

    public JspElement removeValidateWithValue(String a) {
        if (this.attribute.containsKey(a)) {
            this.attribute.remove(a);
        }

        return this;
    }

    public String getElementCode() {
        return this.elementCode;
    }

    public JspAttribute createAttrDateFormatElConvert(String entityName, String fieldName, String type, String dateFormat) {
        JspAttribute var5 = new JspAttribute();
        var5.setTableName(entityName);
        var5.setAttrName(fieldName);
        var5.setIfContainElExpress(true);
        HashMap var6 = new HashMap();
        if (StringUtils.isNotEmpty(dateFormat)) {
            var6.put("datePattern", dateFormat);
        }

        var5.setAttrCovertValue(type);
        return var5;
    }

    public void setQueryCondPrintPrefix(String queryCondPrintPrefix) {
        this.queryCondPrintPrefix = queryCondPrintPrefix;
    }

    public void setChildList(List<JspElement> childList) {
        this.childList = childList;
    }

    public void setShowHideElExpress(String showHideElExpress) {
        this.showHideElExpress = showHideElExpress;
    }

    public abstract void initDefaultAttribute();

    public JspAttribute createAttrBasicEl(String tableName, String attrName) {
        JspAttribute var3 = new JspAttribute();
        var3.setTableName(tableName);
        var3.setAttrName(attrName);
        var3.setAttrOriginValue(null);
        var3.setAttrCovertValue(null);
        return var3;
    }

    public JspAttribute createAttrBasicEl(String tableName, SFunction attrName, String string) {
        JspAttribute var3 = new JspAttribute();
        var3.setTableName(tableName);
        var3.setAttrName("attrName");
        var3.setAttrOriginValue(null);
        var3.setAttrCovertValue(null);
        return var3;
    }

    public JspAttribute createAttrSimple(String a1, String a) {
        JspAttribute c = new JspAttribute();
        c.setIfStatic(true);
        c.setAttrCovertValue(a);
        return c;
    }

    public JspElement addValidateWithValue(String a, String b, String c) {
        label47:
        {
            switch (a.hashCode()) {
                case -1505266481:
                    if (!a.equals("equalTo")) {
                        return this;
                    }
                    break label47;
                case -612557761:
                    if (!a.equals("extension")) {
                        return this;
                    }
                    break label47;
                case -393139297:
                    if (!a.equals("required")) {
                        return this;
                    }
                    break label47;
                case -74460573:
                    if (!a.equals("rangelength")) {
                        return this;
                    }
                    break;
                case 107876:
                    if (!a.equals("max")) {
                        return this;
                    }
                    break label47;
                case 108114:
                    if (!a.equals("min")) {
                        return this;
                    }
                    break label47;
                case 108280125:
                    if (!a.equals("range")) {
                        return this;
                    }
                    break;
                case 124732746:
                    if (!a.equals("maxlength")) {
                        return this;
                    }
                    break label47;
                case 897211320:
                    if (!a.equals("minlength")) {
                        return this;
                    }
                    break label47;
                default:
                    return this;
            }

            this.addSimpleAttr(a, "[" + b + "," + c + "]");
            return this;
        }

        this.addSimpleAttr(b, c);
        return this;
    }

    public static JspElementSpan findMatchPureSpan(String a) {
        return (JspElementSpan) JspElementSpan.newInstance(a + ":", (String) null);
    }

    public String getFieldChineseName() {
        return this.fieldName;
    }

    public String getQueryCondPrintPrefix() {
        return this.queryCondPrintPrefix;
    }

    public static String buildJsFunctionDefine(String a, Map<String, String> b, boolean c) {
        StringBuffer var3 = new StringBuffer();
        StringBuffer var4 = new StringBuffer();

        String[] var10001;
        boolean var10003;
        if (a != null) {
            Iterator var5;
            Iterator var10000 = var5 = b.entrySet().iterator();

            while (var10000.hasNext()) {
                Map.Entry w;
                String var6 = (String) (w = (Map.Entry) var5.next()).getKey();
                if (c) {
                    var6 = "'" + (String) w.getValue() + "'";
                }

                if (StringUtils.isEmpty(var4.toString())) {
                    var10001 = new String[1];
                    var10003 = true;
                    var10001[0] = var6;
                    var4.append(var10001);
                    var10000 = var5;
                } else {
                    var10001 = new String[2];
                    var10003 = true;
                    var10001[0] = ",";
                    var10001[1] = var6;
                    var4.append(var10001);
                    var10000 = var5;
                }
            }
        }

        var10001 = new String[2];
        var10003 = true;
        var10001[0] = a;
        var10001[1] = "(";
        var3.append(var10001);
        var10001 = new String[1];
        var10003 = true;
        var10001[0] = var4.toString();
        var3.append(var10001);
        var10001 = new String[1];
        var10003 = true;
        var10001[0] = ")";
        var3.append(var10001);
        return var3.toString();
    }

    /*public JspAttribute createAttrElConvert(Class<BaseEntity> entityClass, SFunction fieldName, String attnCovertValue, String d, Map<String, Object>... e) {
        JspAttribute jspAttribute = new JspAttribute();
        jspAttribute.setTableName(tableName);
        jspAttribute.setAttrName(attrName);
        jspAttribute.setIfContainElExpress(true);
        Map var7 = null;
        if (e.length > 0) {
            var7 = e[0];
        }

        //jspAttribute.setAttrCovertValue(ALLATORIxDEMO(a, a, a, a, var7));
        jspAttribute.setAttrCovertValue(attnCovertValue);
        return jspAttribute;
    }*/

    public JspAttribute createAttrElConvert(String tableName, String attrName, String attnCovertValue, String d, Map<String, Object>... e) {
        JspAttribute jspAttribute = new JspAttribute();
        jspAttribute.setTableName(tableName);
        jspAttribute.setAttrName(attrName);
        jspAttribute.setIfContainElExpress(true);
        Map var7 = null;
        if (e.length > 0) {
            var7 = e[0];
        }

        //jspAttribute.setAttrCovertValue(ALLATORIxDEMO(a, a, a, a, var7));
        jspAttribute.setAttrCovertValue(attnCovertValue);
        return jspAttribute;
    }

    public Element bulidElement(String str, Map<String, JspAttribute> map) {
        Element a = new Element(str);

        Iterator var3;
        Iterator var10000 = var3 = map.entrySet().iterator();

        while (true) {
            while (var10000.hasNext()) {
                JspAttribute var4;
                Map.Entry entry;
                if ((var4 = (JspAttribute) (entry = (Map.Entry) var3.next()).getValue()) == null) {
                    var10000 = var3;
                } else {
                    String var7 = var4.getAttrCovertValue();
                    if (TJ_TEXT.equals(entry.getKey())) {
                        if (StringUtils.isNotEmpty(var7)) {
                            var10000 = var3;
                            a.appendText(var7);
                            continue;
                        }
                    } else if (StringUtils.isNotEmpty(var7)) {
                        a.attr((String) entry.getKey(), var7);
                    }

                    var10000 = var3;
                }
            }

            return a;
        }
    }

    public void setCorrespondJspElement(JspElement a) {
        this.correspondJspElement = a;
    }

    public static JspElementTH findMatchPureTh(String a) {
        return JspElementTH.newInstance(filterChineseName(a));
    }

    public void setFieldName(String a) {
        this.fieldName = a;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String appendParamsForUrl(String url, Map<String, String> map) {
        if (StringUtils.isEmpty(url)) {
            return null;
        } else {
            url = this.appendCtxELForUrl(url);
            Iterator var3;
            if (!CollectionUtils.isEmpty(map)) {
                for (var3 = map.entrySet().iterator(); var3.hasNext(); ) {
                    Map.Entry d = (Map.Entry) var3.next();
                    String var10001 = (String) d.getKey();
                    String var10002 = (String) d.getValue();
                    url = StringUtils.appendUrlParam(url, var10001, var10002, false);
                }
            }

            return url;
        }
    }

    public static String filterChineseName(String a) {
        String var1 = "";
        return filterChineseNameEnglish(filterBrackets(a));
    }

    public static String getEntityNameByClass(Class a, boolean... b) {
        String var2;
        if ((var2 = a.getSimpleName()).contains("$$")) {
            var2 = var2.substring(0, var2.indexOf("$$"));
        }

        boolean var3 = b.length > 0 && b[0];
        if (!var3) {
            var2 = StringUtils.firstToLower(var2);
        }

        return var2;
    }

    public Object getVal(BaseEntity baseEntity) {
        return fieldNameFun.apply(baseEntity);
    }

    public Object getFieldVal() throws NoSuchFieldException {
        SerializedLambda lambda = LambdaUtils.resolve(fieldNameFun);

        String fieldName = com.baomidou.mybatisplus.core.toolkit.StringUtils.resolveFieldName(lambda.getImplMethodName());
        Class implClass = lambda.getImplClass();
        HtmlField annotation = implClass.getDeclaredField(fieldName).getDeclaredAnnotation(HtmlField.class);
        return annotation == null ? implClass : annotation.value();
    }

}
