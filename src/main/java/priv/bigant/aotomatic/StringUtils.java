package priv.bigant.aotomatic;

import priv.bigant.aotomatic.common.CompositeEntity;
import priv.bigant.aotomatic.common.Encodes;
import priv.bigant.aotomatic.common.MyStringBuffer;
import priv.bigant.aotomatic.common.PageMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static String toHtml(String a) {
        return a == null ? "" : replace(replace(Encodes.escapeHtml(a), PageMap.ALLATORIxDEMO("H"), CompositeEntity.ALLATORIxDEMO("JP\u0004\u001dH")), PageMap.ALLATORIxDEMO("K"), CompositeEntity.ALLATORIxDEMO("\u0014\u0018P\u0005BM\u0012P\\\u0014A\u0006\tV"));
    }

    public static String findChineseFromStr(String a) {
        return matchResult("[\u4e00-\u9fa5]", a);
    }

    public static String matchResult(String a, String b) {
        Pattern pattern = Pattern.compile(a);
        StringBuilder var3 = new StringBuilder();

        Matcher d;
        int var6;
        for (d = pattern.matcher(b); d.find(); ) {
            for (int var4 = var6 = 0; var4 <= d.groupCount(); var4 = var6) {
                ++var6;
                var3.append(d.group());
            }
        }

        return var3.toString();
    }

    public static String firstToLower(String a) {
        char[] var1;
        if ((var1 = a.toCharArray())[0] >= 'A' && var1[0] <= 'Z') {
            var1[0] = (char) (var1[0] + 32);
        }

        return new String(var1);
    }

    public static String appendUrlParam(String a, String b, String c, boolean... d) {
        if (isEmpty(a)) {
            return a;
        } else {
            boolean var4 = false;
            if (d.length > 0) {
                var4 = d[0];
            }

            MyStringBuffer w;
            MyStringBuffer var10000 = w = new MyStringBuffer();
            String[] var10001 = new String[1];
            var10001[0] = a;
            var10000.append(var10001);
            if (!a.toString().contains("?")) {
                var10001 = new String[1];
                var10001[0] = "?";
                w.append(var10001);
            }

            if (var4) {
                a = "'" + a + "'";
            }

            if (isEmpty(a.toString().substring(a.toString().indexOf("?") + 1))) {
                var10001 = new String[3];
                var10001[0] = b;
                var10001[1] = "=";
                var10001[2] = c;
                w.append(var10001);
                var10000 = w;
            } else {
                var10001 = new String[4];
                var10001[0] = "&";
                var10001[1] = b;
                var10001[2] = "=";
                var10001[3] = c;
                w.append(var10001);
                var10000 = w;
            }

            return var10000.toString();
        }
    }

    public static String firstToUpper(String a) {
        char[] var1;
        if ((var1 = a.toCharArray())[0] >= 'a' && var1[0] <= 'z') {
            var1[0] = (char) (var1[0] - 32);
        }

        return new String(var1);
    }

    public static String findClassSimpleName(Class a, boolean... b) {
        String var2;
        if ((var2 = a.getSimpleName()).contains("$$")) {
            var2 = var2.substring(0, var2.indexOf("$$"));
        }

        boolean var3 = b.length > 0 && b[0];
        if (!var3) {
            var2 = firstToLower(var2);
        }

        return var2;
    }
}
