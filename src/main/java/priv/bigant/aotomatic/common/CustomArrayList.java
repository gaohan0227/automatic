package priv.bigant.aotomatic.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomArrayList<E> extends ArrayList<E> implements Serializable {
    private static final long e = 1L;
    private String k = "a";
    private boolean K = false;
    private List<String> ALLATORIxDEMO;

    public static String ALLATORIxDEMO(String a) {
        int var10000 = 1 << 3 ^ 3;
        int var10001 = 1 << 3;
        int var10002 = 3 << 3 ^ 2;
        int var10003 = a.length();
        char[] var10004 = new char[var10003];
        boolean var10006 = true;
        int var5 = var10003 - 1;
        var10003 = var10002;
        int var3;
        var10002 = var3 = var5;
        char[] var1 = var10004;
        int var4 = var10003;
        var10001 = var10000;
        var10000 = var10002;

        for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
            var10001 = var3;
            char var6 = a.charAt(var3);
            --var3;
            var1[var10001] = (char)(var6 ^ var2);
            if (var3 < 0) {
                break;
            }

            var10002 = var3--;
            var1[var10002] = (char)(a.charAt(var10002) ^ var4);
        }

        return new String(var1);
    }

    /*public void setConvertType(boolean a) {
        this.K = a;
    }

    public CustomArrayList() {
    }

    public boolean add(E a) {
        if (!(a instanceof String)) {
            return super.add(a);
        } else {
            String var2 = a.toString();
            CustomArrayList var10000;
            if (this.K) {
                String var3;
                if (ALLATORIxDEMO(var2)) {
                    var3 = StringUtils.toUnderScoreCase(var2);
                    String var4 = StringUtils.isEmpty(this.K) ? "" : this.K + ListUtils.ALLATORIxDEMO("<");
                    var10000 = a;
                    a.ALLATORIxDEMO.add(var4 + var3 + FreeMarkers.ALLATORIxDEMO("Up\u00061") + var2);
                    var2 = var3;
                    return var10000.add(var2);
                }

                var3 = StringUtils.toCamelCase(var2.replace(ListUtils.ALLATORIxDEMO("<"), "").replace(FreeMarkers.ALLATORIxDEMO("9"), "").replace(ListUtils.ALLATORIxDEMO(";"), ""));
                a.ALLATORIxDEMO.add(var2 + FreeMarkers.ALLATORIxDEMO("Up\u00061") + var3);
            }

            var10000 = a;
            return var10000.add(var2);
        }
    }

    public void setTableAlias(String a) {
        this.K = a;
    }

    public boolean isConvertType() {
        return this.K;
    }

    public void add(int a, E a) {
        if (a instanceof String) {
            CustomArrayList var10000;
            String var3;
            label32: {
                var3 = a.toString();
                if (this.K) {
                    if (StringUtils.deleteWhitespace(var3).toLowerCase().contains(ListUtils.ALLATORIxDEMO(" }6|7:"))) {
                        var3 = var3 + FreeMarkers.ALLATORIxDEMO("1\u0014bUv\u0007~\u0000a7h6~\u0000\u007f\u0001W\u001ct\u0019u");
                    }

                    if (StringUtils.deleteWhitespace(var3).toLowerCase().contains(ListUtils.ALLATORIxDEMO(".s;:"))) {
                        var3 = var3 + FreeMarkers.ALLATORIxDEMO("1\u0014bUv\u0007~\u0000a7h8p\rW\u001ct\u0019u");
                    }

                    if (StringUtils.deleteWhitespace(var3).toLowerCase().contains(ListUtils.ALLATORIxDEMO(".{-:"))) {
                        var3 = var3 + FreeMarkers.ALLATORIxDEMO("1\u0014bUv\u0007~\u0000a7h8x\u001bW\u001ct\u0019u");
                    }

                    if (StringUtils.deleteWhitespace(var3).toLowerCase().contains(ListUtils.ALLATORIxDEMO("0g.:"))) {
                        var3 = var3 + FreeMarkers.ALLATORIxDEMO("1\u0014bUv\u0007~\u0000a7h&d\u0018W\u001ct\u0019u");
                    }

                    if (StringUtils.deleteWhitespace(var3).toLowerCase().contains(ListUtils.ALLATORIxDEMO("\"d$:"))) {
                        var3 = var3 + FreeMarkers.ALLATORIxDEMO("1\u0014bUv\u0007~\u0000a7h4g\u0012W\u001ct\u0019u");
                        var10000 = a;
                        break label32;
                    }
                } else {
                    var3 = StringUtils.toUnderScoreCase(var3);
                }

                var10000 = a;
            }

            var10000.add(a, var3);
        }

        super.add(a, a);
    }

    public CustomArrayList(boolean a, List<String> a) {
        this.K = a;
        a.ALLATORIxDEMO = a;
    }

    public String getTableAlias() {
        return this.K;
    }*/
}
