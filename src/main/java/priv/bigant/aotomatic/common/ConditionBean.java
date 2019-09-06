package priv.bigant.aotomatic.common;

import java.io.Serializable;

public class ConditionBean implements Serializable {
    public static final String SIGN_BETWEEN = " between ";
    public static final String SIGN_SMALL_EQUAL = " <= ";
    public static final String SIGN_IN = " in ";
    public static final String SIGN_BIG = " > ";
    public static final String SIGN_IS_NULL = " is null ";
    protected String sign = " = ";
    public static final String SIGN_NOT_EQUAL = " != ";
    public static final String SIGN_SMALL = " < ";
    public static final String SIGN_IS_NOT_NULL = " is not null ";
    public static final String SIGN_BIG_EQUAL = " >= ";
    private static final long ALLATORIxDEMO = 1L;
    public static final String SIGN_EQUAL = " = ";
    public static final String SIGN_LIKE = " like ";

    public ConditionBean() {
    }

    public void setSign(String a) {
        this.sign = a;
    }

    public static String ALLATORIxDEMO(String a) {
        int var10000 = (2 ^ 5) << 4 ^ 5;
        int var10001 = 2 << 3 ^ 2;
        int var10002 = 2 << 3 ^ 3 ^ 5;
        int var10003 = a.length();
        char[] var10004 = new char[var10003];
        boolean var10006 = true;
        int var5 = var10003 - 1;
        var10003 = var10002;
        int var3;
        var10002 = var3 = var5;
        char[] var1 = var10004;
        int var4 = var10003;
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

    public String getSign() {
        return this.sign;
    }
}
