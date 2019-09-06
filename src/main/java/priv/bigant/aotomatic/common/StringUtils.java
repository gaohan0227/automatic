/*
package priv.bigant.aotomatic.common;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static final String K = "UTF-8";
    private static final char ALLATORIxDEMO = '_';

    public static String toString(byte[] a) {
        try {
            return new String(a, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return "";
        }
    }

    public StringUtils() {
    }

    public static String jsGetVal(String a) {
        StringBuilder var1 = new StringBuilder();
        StringBuilder var2 = new StringBuilder();
        String[] var3 = split(a, PageMap.ALLATORIxDEMO("l"));

        int var4;
        for(int var10000 = var4 = 0; var10000 < var3.length; var10000 = var4) {
            var2.append(CompositeEntity.ALLATORIxDEMO("X") + var3[var4]);
            StringBuilder var10002 = (new StringBuilder(PageMap.ALLATORIxDEMO("c"))).append(var2.substring(1));
            String var10003 = CompositeEntity.ALLATORIxDEMO("\rQ\u0015L");
            ++var4;
            var1.append(var10002.append(var10003).toString());
        }

        var1.append(var2.substring(1));
        return var1.toString();
    }

    public static Float toFloat(Object a) {
        return toDouble(a).floatValue();
    }

    public static boolean compareTwoStrEqual(String a, String a) {
        if (a == null && a == null) {
            return true;
        } else if (a == null && a != null) {
            return false;
        } else {
            return a != null && a == null ? false : a.equals(a);
        }
    }

    public static void main(String[] a) {
        System.out.println(PageMap.ALLATORIxDEMO("HmamamamamamamamamamamamamamamamamamamamamamamamaDanbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbmHmbnbnbnbnambmbnbmbnbnambmambmambmanbmambnbnbnbnaDanbnbnbnanananbnanbnananbmbnananananbmbnbnbnbnbmHmbnbnbnbmambmbnbmbnbmambnanbmbmbmanbnanbnbnbnbnaDanbnbnbnananamanamanananbmbnamanananamanbnbnbnbmHmbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnaDan\r,$;1-#:+!,n 7b\u000f.\"#:-<+n\r,$;1-#:-<b8t`vn\u0006\u000b\u000f\u0001bmHmbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnaDanbnbnbnbnbn*:6>xam959l/.\"#:-<+`!!/nbnbnbnbnbnbmHmbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnbnaDamamamamamamamamamamamamamamamamamamamamamamamamH"));
        Map var1 = split2Map(CompositeEntity.ALLATORIxDEMO("S\u0014QZY\u001cYZA\u0005A\u0005"), ',');
        System.out.println(var1.toString());
    }

    public static String toUnderScoreCase(String a) {
        if (a == null) {
            return null;
        } else {
            StringBuilder var1 = new StringBuilder();
            boolean var2 = false;

            int var3;
            for(int var10000 = var3 = 0; var10000 < a.length(); var10000 = var3) {
                char var4 = a.charAt(var3);
                boolean var5 = true;
                if (var3 < a.length() - 1) {
                    var5 = Character.isUpperCase(a.charAt(var3 + 1));
                }

                StringBuilder var6;
                if (var3 > 0 && Character.isUpperCase(var4)) {
                    if (!var2 || !var5) {
                        var1.append('_');
                    }

                    var2 = true;
                    var6 = var1;
                } else {
                    var2 = false;
                    var6 = var1;
                }

                ++var3;
                var6.append(Character.toLowerCase(var4));
            }

            return var1.toString();
        }
    }

    public static String judgeFieldIfNull(Object a, Map<String, String> a) {
        StringBuilder var2 = new StringBuilder();

        Iterator var4;
        for(Iterator var10000 = var4 = a.keySet().iterator(); var10000.hasNext(); var10000 = var4) {
            String var3 = (String)var4.next();
            Object var5 = Reflections.invokeGetter(a, var3);
            if (Reflections.getFieldValue(a, var3) == null || ALLATORIxDEMO(Reflections.getFieldValue(a, var3).getClass(), var5)) {
                var2.append(PageMap.ALLATORIxDEMO("\u0019") + (String)a.get(var3) + CompositeEntity.ALLATORIxDEMO("+冷寏丿肋丈稌Ｓ|"));
            }
        }

        return var2.toString();
    }

    public static String toHtml(String a) {
        return a == null ? "" : replace(replace(Encodes.escapeHtml(a), PageMap.ALLATORIxDEMO("H"), CompositeEntity.ALLATORIxDEMO("JP\u0004\u001dH")), PageMap.ALLATORIxDEMO("K"), CompositeEntity.ALLATORIxDEMO("\u0014\u0018P\u0005BM\u0012P\\\u0014A\u0006\tV"));
    }

    public static String[] splitAppendSelfAfter(String a, String a) {
        Pattern var10000 = Pattern.compile(a);
        String a = var10000.matcher(a);
        String[] var2 = var10000.split(a);
        String var3 = "";
        int var4;
        if (var2.length > 0) {
            for(int var5 = var4 = 0; var5 < var2.length; var5 = var4) {
                if (a.find()) {
                    var2[var4] = var3 + var2[var4];
                    var3 = a.group();
                } else {
                    var2[var4] = var3 + var2[var4];
                }

                ++var4;
            }
        }

        return var2;
    }

    public static String toCapitalizeCamelCase(String a) {
        if (a == null) {
            return null;
        } else {
            a = toCamelCase(a);
            return a.substring(0, 1).toUpperCase() + a.substring(1);
        }
    }

    public static String abbrToNewLine(String a, int a, String a) {
        if (a == null) {
            return "";
        } else {
            try {
                StringBuffer var3 = new StringBuffer();
                ALLATORIxDEMO(replaceHtml(StringEscapeUtils.unescapeHtml4(a)), a, var3, a);
                return var3.toString();
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
                return "";
            }
        }
    }

    public static String matchResult(String a, String a) {
        Pattern var2 = Pattern.compile(a);
        StringBuilder var3 = new StringBuilder();

        Matcher a;
        int var6;
        for(Matcher var10000 = a = var2.matcher(a); var10000.find(); var10000 = a) {
            for(int var4 = var6 = 0; var4 <= a.groupCount(); var4 = var6) {
                ++var6;
                var3.append(a.group());
            }
        }

        return var3.toString();
    }

    public static Integer findNumData(String a) {
        Matcher var1;
        if ((var1 = Pattern.compile(PageMap.ALLATORIxDEMO("\u0015\u001c~ow\u001f")).matcher(a)).find()) {
            return isEmpty(var1.replaceAll("")) ? new Integer(-999) : new Integer(var1.replaceAll("").trim());
        } else {
            return new Integer(-999);
        }
    }

    public static void setValueIfNotBlank(String a, String a) {
        if (isNotBlank(a)) {
            ;
        }

    }

    public static String replaceHtml(String a) {
        return isBlank(a) ? "" : Pattern.compile(CompositeEntity.ALLATORIxDEMO("J\u001c]\rH")).matcher(a).replaceAll("");
    }

    public static boolean inString(String a, String... a) {
        if (a != null) {
            String[] var4 = a;
            int var3 = a.length;

            int var2;
            for(int var10000 = var2 = 0; var10000 < var3; var10000 = var2) {
                String[] a = var4[var2];
                if (a.equals(trim(a))) {
                    return true;
                }

                ++var2;
            }
        }

        return false;
    }

    public static String getRemoteAddr(HttpServletRequest a) {
        String var10000;
        String var1;
        if (isNotBlank(var1 = a.getHeader(PageMap.ALLATORIxDEMO("\u001ac\u0010+#\"o\u0007\u0012")))) {
            var10000 = var1 = a.getHeader(CompositeEntity.ALLATORIxDEMO(".\u001f0]\u0004E\u0017@\u0012W\u0012\u001f0]\u0004"));
        } else if (isNotBlank(var1)) {
            var10000 = var1 = a.getHeader(PageMap.ALLATORIxDEMO("\u0012<-6;c\u0001\"++,:o\u0007\u0012"));
        } else {
            if (isNotBlank(var1)) {
                var1 = a.getHeader(CompositeEntity.ALLATORIxDEMO("e:\u001f&@\u0019J\u000f\u001f5^\u001fW\u0018F[{&"));
            }

            var10000 = var1;
        }

        return var10000 != null ? var1 : a.getRemoteAddr();
    }

    public static String convertDbTableNameToJavaName(String a, boolean a) {
        if (isEmpty(a)) {
            return a;
        } else {
            a = toCamelCase(a.toLowerCase().replace(PageMap.ALLATORIxDEMO(": "), ""));
            return a ? firstToUpper(a) : firstToLower(a);
        }
    }

    public static Integer toInteger(Object a) {
        return toLong(a).intValue();
    }

    public static boolean contains(String a, String... a) {
        boolean var2 = false;
        String[] var5 = a;
        int var4 = a.length;

        int var3;
        for(int var10000 = var3 = 0; var10000 < var4; var10000 = var3) {
            String[] a = var5[var3];
            if (contains(a.toLowerCase(), a.toLowerCase())) {
                var2 = true;
                return true;
            }

            ++var3;
        }

        return var2;
    }

    public static String getMessage(String a, Object[] a) {
        LocaleResolver var2 = (LocaleResolver)SpringContextHolder.getBean(LocaleResolver.class);
        HttpServletRequest var3 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Locale var4 = var2.resolveLocale(var3);
        return SpringContextHolder.getApplicationContext().getMessage(a, a, var4);
    }

    public static String findClassSimpleName(Class a, boolean... a) {
        String var2;
        if ((var2 = a.getSimpleName()).contains("$$")) {
            var2 = var2.substring(0, var2.indexOf("$$"));
        }

        boolean var3 = a.length > 0 ? a[0] : false;
        if (!var3) {
            var2 = firstToLower(var2);
        }

        return var2;
    }

    public static String findSetGetByProp(String a, boolean a) {
        return a ? "set" + firstToUpper(a) : "get" + firstToUpper(a);
    }

    public static Long toLong(Object a) {
        return toDouble(a).longValue();
    }

    public static Map<String, String> split2Map(String a) {
        String[] var1 = split(a, ',');
        LinkedHashMap var2 = new LinkedHashMap();
        String[] var5 = var1;
        int var4 = var1.length;

        int var3;
        for(int var10000 = var3 = 0; var10000 < var4; var10000 = var3) {
            String var6 = var5[var3];
            ++var3;
            var2.put(var6, var6);
        }

        return var2;
    }

    public static byte[] getBytes(String a) {
        if (a != null) {
            try {
                return a.getBytes("UTF-8");
            } catch (UnsupportedEncodingException var2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String appendUrlParam(String a, String a, String a, boolean... a) {
        if (isEmpty(a)) {
            return a;
        } else {
            boolean var4 = false;
            if (a.length > 0) {
                var4 = a[0];
            }

            MyStringBuffer a;
            MyStringBuffer var10000 = a = new MyStringBuffer();
            String[] var10001 = new String[1];
            boolean var10003 = true;
            var10001[0] = a;
            var10000.append(var10001);
            if (!a.toString().contains(PageMap.ALLATORIxDEMO("}"))) {
                var10001 = new String[1];
                var10003 = true;
                var10001[0] = CompositeEntity.ALLATORIxDEMO("I");
                a.append(var10001);
            }

            if (var4) {
                a = PageMap.ALLATORIxDEMO("e") + a + CompositeEntity.ALLATORIxDEMO("Q");
            }

            if (isEmpty(a.toString().substring(a.toString().indexOf(PageMap.ALLATORIxDEMO("}")) + 1))) {
                var10001 = new String[3];
                var10003 = true;
                var10001[0] = a;
                var10001[1] = CompositeEntity.ALLATORIxDEMO("K");
                var10001[2] = a;
                a.append(var10001);
                var10000 = a;
            } else {
                var10001 = new String[4];
                var10003 = true;
                var10001[0] = PageMap.ALLATORIxDEMO("d");
                var10001[1] = a;
                var10001[2] = CompositeEntity.ALLATORIxDEMO("K");
                var10001[3] = a;
                a.append(var10001);
                var10000 = a;
            }

            return var10000.toString();
        }
    }

    public static Map<String, String> split2Map(String a, Character a) {
        Character a = split(a, a);
        LinkedHashMap var2 = new LinkedHashMap();
        String[] var5 = a;
        int var4 = a.length;

        int var3;
        for(int var10000 = var3 = 0; var10000 < var4; var10000 = var3) {
            Character a = var5[var3];
            ++var3;
            var2.put(a, a);
        }

        return var2;
    }

    public static String firstToLower(String a) {
        char[] var1;
        if ((var1 = a.toCharArray())[0] >= 'A' && var1[0] <= 'Z') {
            var1[0] = (char)(var1[0] + 32);
        }

        return new String(var1);
    }

    public static String firstToUpper(String a) {
        char[] var1;
        if ((var1 = a.toCharArray())[0] >= 'a' && var1[0] <= 'z') {
            var1[0] = (char)(var1[0] - 32);
        }

        return new String(var1);
    }

    public static boolean isNumData(String a) {
        if (isEmpty(a)) {
            return false;
        } else {
            return Pattern.compile(PageMap.ALLATORIxDEMO("c\u0019~ow\u001fej`\u0019~ow\u001fekq>\u0015rc{\u0013ifl\u0015rc{\u0013ig}")).matcher(a).matches();
        }
    }

    public static int strToInt(Object a, int a) {
        String var2 = a == null ? "" : a.toString();
        return isBlank(var2) ? a : Integer.valueOf(var2);
    }

    public static String toCamelCase(String a) {
        if (a == null) {
            return null;
        } else {
            a = a.toLowerCase();
            StringBuilder var1 = new StringBuilder(a.length());
            boolean var2 = false;

            int var3;
            for(int var10000 = var3 = 0; var10000 < a.length(); var10000 = var3) {
                char var4;
                if ((var4 = a.charAt(var3)) == '_') {
                    var2 = true;
                } else if (var2) {
                    var1.append(Character.toUpperCase(var4));
                    var2 = false;
                } else {
                    var1.append(var4);
                }

                ++var3;
            }

            return var1.toString();
        }
    }

    public static List<String> judgeFieldIfNull(Object a, List<String> a) {
        ArrayList var2 = new ArrayList();

        Iterator var3;
        for(Iterator var10000 = var3 = a.iterator(); var10000.hasNext(); var10000 = var3) {
            List a = (String)var3.next();
            Object var4 = Reflections.invokeGetter(a, a);
            if (ALLATORIxDEMO(Reflections.getFieldValue(a, a).getClass(), var4)) {
                var2.add(a);
            }
        }

        return var2;
    }

    public static String findChineseFromStr(String a) {
        return matchResult(CompositeEntity.ALLATORIxDEMO("-n\u0003\u0006\u0013\u0002F\u001f*GOT\u0017\u0007+"), a);
    }

    public static String convertChineseToFirstSpell(String a) {
        String var1 = "";
        char[] var2 = a.toCharArray();
        HanyuPinyinOutputFormat var3 = new HanyuPinyinOutputFormat();
        int var10000 = 0;
        var3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        var3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int var4 = 0; var10000 < var2.length; var10000 = var4) {
            if (String.valueOf(var2[var4]).matches(PageMap.ALLATORIxDEMO("\u0019\u00127z'~rc\u001e;{(#{\u001f"))) {
                try {
                    String[] var5 = PinyinHelper.toHanyuPinyinStringArray(var2[var4], var3);
                    var1 = var1 + var5[0];
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            } else {
                var1 = var1 + var2[var4];
            }

            ++var4;
        }

        return var1;
    }

    public static boolean isContainChinese(String a) {
        return Pattern.compile(CompositeEntity.ALLATORIxDEMO("-串[龗+")).matcher(a).find();
    }

    public static String abbr2(String a, int a) {
        if (a == null) {
            return "";
        } else {
            StringBuffer var2 = new StringBuffer();
            int var3 = 0;
            boolean var5 = false;
            boolean var6 = false;
            int var7;
            int var10000 = var7 = 0;

            StringBuffer var16;
            while(true) {
                if (var10000 >= a.length()) {
                    var16 = var2;
                    break;
                }

                char var4;
                label84: {
                    label83: {
                        label82: {
                            UnsupportedEncodingException var14;
                            label96: {
                                boolean var15;
                                boolean var10001;
                                if ((var4 = a.charAt(var7)) == '<') {
                                    var15 = var5 = true;
                                } else if (var4 == '&') {
                                    var6 = true;
                                    var15 = var5;
                                } else if (var4 == '>' && var5) {
                                    var15 = var5 = false;
                                    --var3;
                                } else {
                                    if (var4 == ';' && var6) {
                                        var6 = false;
                                    }

                                    try {
                                        var15 = var5;
                                    } catch (UnsupportedEncodingException var10) {
                                        var14 = var10;
                                        var10001 = false;
                                        break label96;
                                    }
                                }

                                try {
                                    if (!var15 && !var6) {
                                        var3 += String.valueOf(var4).getBytes(PageMap.ALLATORIxDEMO("\u0005\f\t")).length;
                                        break label83;
                                    }
                                    break label82;
                                } catch (UnsupportedEncodingException var9) {
                                    var14 = var9;
                                    var10001 = false;
                                }
                            }

                            UnsupportedEncodingException var8 = var14;
                            var8.printStackTrace();
                        }

                        var10000 = var3;
                        break label84;
                    }

                    var10000 = var3;
                }

                if (var10000 > a - 3) {
                    var16 = var2;
                    var2.append(CompositeEntity.ALLATORIxDEMO("X\u001cX"));
                    break;
                }

                var2.append(var4);
                ++var7;
                var10000 = var7;
            }

            String var13 = var16.toString().replaceAll(PageMap.ALLATORIxDEMO("jpk\u0015\u001cr|\u0013hf~qk"), CompositeEntity.ALLATORIxDEMO("\u0016G\u0016D")).replaceAll(PageMap.ALLATORIxDEMO("~a}f\u0003\u001c\u0007\u000f>\f\u0003\u001d\u00072\u0000\u000f\u0011\u000b\u0004\u0001\f\u001a>\f\r\n\u001b2\u0000\u001c>\r\r\u0002>\r\r\u0002\u0005\u001c\r\u001b\u00122\u0006\n>\n\u00162\u0004\u001c\u0003\u0003\u00072\n\u000b\u0003\n>\u0006\u00102\n\u001a\u000f\u0002>\u0007\u000f\t>\u0007\f\u001e\u0017\u001a>\u0007\u0011\u0007\f\n\u0007\u0016>\u0002\u000b2\u000e\u0007\f\u0005>\u0003\u0007\u001a\u00032\r\u001e\u0016\u0007\r\u0000>\u001e>\u001e\u0003\u001c\u0003\u0003>\u001a\u0000\u0001\u0006\u0017>\u001a\u00062\u0016\b\r\u0001\u00162\u0016\u0006>\u001a\n\u000b\u0003\n>\u001a\u00102#<'/>,#='2 /1+$!,:>,-*;2 <>--\">--\"%<-;22&*>*62$<##'2*+#*>&02*:/\">'/)>',>7:>'1',*'6>\"+2.',%>#':#2->6'- >>>>#<##>: !&7>:&26(-!626&>:*+#*>:0g\u0019\u0010~p\u001fdmq|"), "").replaceAll(CompositeEntity.ALLATORIxDEMO("J\u001a-S[H7\u001f,o]\u001b-lJ\f+\u0018H\u001aX\u0018I\u001bJ\u001d*\u0003H"), PageMap.ALLATORIxDEMO("jp"));
            int a = Pattern.compile(CompositeEntity.ALLATORIxDEMO("J\u001a-S[H7\u001f,o]\u001b-lJ\f+\u0018H")).matcher(var13);
            ArrayList var12 = Lists.newArrayList();

            for(Matcher var17 = a; var17.find(); var17 = a) {
                var12.add(a.group(1));
            }

            for(var10000 = a = var12.size() - 1; var10000 >= 0; var10000 = a) {
                var2.append(PageMap.ALLATORIxDEMO("rm"));
                var2.append((String)var12.get(a));
                --a;
                var2.append(CompositeEntity.ALLATORIxDEMO("H"));
            }

            return var2.toString();
        }
    }

    public static Double toDouble(Object a) {
        if (a == null) {
            return 0.0D;
        } else {
            try {
                return Double.valueOf(trim(a.toString()));
            } catch (Exception var2) {
                return 0.0D;
            }
        }
    }

    public static String abbr(String a, int a) {
        if (a == null) {
            return "";
        } else {
            try {
                StringBuilder var2 = new StringBuilder();
                int var3 = 0;
                char[] var7;
                int var6 = (var7 = replaceHtml(StringEscapeUtils.unescapeHtml4(a)).toCharArray()).length;
                int var5;
                int var10000 = var5 = 0;

                StringBuilder var9;
                while(true) {
                    if (var10000 >= var6) {
                        var9 = var2;
                        break;
                    }

                    char var4 = var7[var5];
                    if ((var3 += String.valueOf(var4).getBytes(PageMap.ALLATORIxDEMO("\u0005\f\t")).length) > a - 3) {
                        var9 = var2;
                        var2.append(CompositeEntity.ALLATORIxDEMO("X\u001cX"));
                        break;
                    }

                    var2.append(var4);
                    ++var5;
                    var10000 = var5;
                }

                return var9.toString();
            } catch (UnsupportedEncodingException var8) {
                var8.printStackTrace();
                return "";
            }
        }
    }

    public static String addSingleQuoteForStr(String a, String a) {
        StringBuffer var2 = new StringBuffer();
        if (isEmpty(a)) {
            a = ",";
        }

        String[] var3 = a.split(a);

        int var4;
        for(int var10000 = var4 = 0; var10000 < var3.length; var10000 = var4) {
            if (isNotEmpty(var3[var4])) {
                var2.append(PageMap.ALLATORIxDEMO("e") + var3[var4] + CompositeEntity.ALLATORIxDEMO("Q") + a);
            }

            ++var4;
        }

        String var6;
        String var5 = var4.substring((var6 = var2.toString()).length() - 1, var6.length());
        if (a.equals(var5)) {
            return var6.substring(0, var6.length() - 1);
        } else {
            return var6;
        }
    }

    public static String replaceMobileHtml(String a) {
        return a == null ? "" : a.replaceAll(PageMap.ALLATORIxDEMO("rj\u0015#c8\u0013iqk\u00121e}`hq|"), CompositeEntity.ALLATORIxDEMO("\u000eR\u0003H"));
    }
}
*/
