package priv.bigant.aotomatic.common;

import org.apache.commons.lang3.time.DateUtils;
import priv.bigant.aotomatic.common.jsp.JspElement;

import java.util.Map;

public class SpringElParse {
    private static NamedScopeEvaluationContext context;
    private static SpringElParse springElParse;

    public void setContext(NamedScopeEvaluationContext a) {
        context = a;
    }

    public SpringElParse transferPageUrlELExpress(String a) {
        /*if (StringUtils.isEmpty(a)) {
            return this;
        } else {
            String var2 = a;

            Map var3;
            Iterator var5;
            for(Iterator var10000 = var5 = (var3 = ALLATORIxDEMO.parseElExpress(a)).entrySet().iterator(); var10000.hasNext(); var10000 = var5) {
                String var6 = (String)((Map.Entry)var5.next()).getKey();
                Object var7 = null;
                String var8 = (var7 = (new SpelExpressionParser()).parseExpression(var6).getValue(K)) == null ? "" : var7.toString();
                var2 = var2.replace("${" + var6 + "}", var8);
            }

            if (var3.size() < 1) {
                return (SpringElParse) (new SpelExpressionParser()).parseExpression(a).getValue(K);
            } else {
                return ;
            }
        }*/
        return null;
    }

    public NamedScopeEvaluationContext getContext() {
        return context;
    }

    public SpringElParse parseElExpress(String a) {
        return transferPageUrlELExpress(a);
    }

    public SpringElParse() {
        context = new NamedScopeEvaluationContext();
        context.addContext("DateUtils", new DateUtils());
        /*ServletContext var1 = (ServletContext)SpringContextHolder.getBean(ServletContext.class);
        String var3 = var1.getContextPath() + Global.getAdminPath();*/
        context.addContext("ctx", "");

        try {
            context.addContext("JspTool", Class.forName("priv.bigant.aotomatic.common.JspToolBase").newInstance());
            context.addContext("FieldValueConvertFinder", Class.forName("priv.bigant.aotomatic.common.FieldValueConvertFinder").newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException var2) {
            var2.printStackTrace();
        }
    }

    public void setVariable(String a, Object b) {
        context.setVariable(a, b);
    }

    public void setVariable(Map<String, Object> a) {
        context.setVariables(a);
    }

    public static SpringElParse getInstance(BaseEntity a) {
        if (springElParse == null) {
            springElParse = new SpringElParse();
        }

        if (a != null) {
            Class clazz = a.getClass();
            String var1 = JspElement.getEntityNameByClass(clazz, false);
            springElParse.getContext().addContext(var1, a);
        }

        return springElParse;
    }
}
