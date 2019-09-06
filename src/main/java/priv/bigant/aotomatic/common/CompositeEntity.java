package priv.bigant.aotomatic.common;

import java.util.List;

public class CompositeEntity {
    private List<CompositeEntityChildRen> k;
    private String K;
    private String ALLATORIxDEMO;

    public String getValue() {
        return ALLATORIxDEMO;
    }

    public void setParentType(String a) {
        this.K = a;
    }

    public static String ALLATORIxDEMO(String a) {
        int var10000 = (2 ^ 5) << 4 ^ 3 << 1;
        int var10001 = 3 << 3 ^ 4;
        int var10002 = (3 ^ 5) << 3 ^ 2;
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

    public String getParentType() {
        return K;
    }

    public void setChildren(List<CompositeEntity.CompositeEntityChildRen> a) {
        k = a;
    }

    public void setValue(String a) {
        ALLATORIxDEMO = a;
    }

    public CompositeEntity() {
    }

    public List<CompositeEntity.CompositeEntityChildRen> getChildren() {
        return k;
    }

    public class CompositeEntityChildRen {
        private String K;
        private String ALLATORIxDEMO;

        public String getType() {
            return K;
        }

        public void setValue(String ax) {
            ALLATORIxDEMO = ax;
        }

        public String getValue() {
            return ALLATORIxDEMO;
        }

        public void setType(String ax) {
            K = ax;
        }

        public CompositeEntityChildRen() {
        }

        public CompositeEntityChildRen(String axx, String ax) {
            K = axx;
            ALLATORIxDEMO = ax;
        }
    }
}
