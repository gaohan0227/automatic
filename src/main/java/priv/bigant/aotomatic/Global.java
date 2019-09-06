package priv.bigant.aotomatic;

import java.io.*;

public class Global implements Serializable {
    /*private static Object e;
    private static Global k = new Global();
    public static final String HIDE = "0";
    public static final String YES = "1";
    private static PropertiesLoader K;
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String SHOW = "1";
    private static Map<String, String> ALLATORIxDEMO = Maps.newHashMap();
    public static final String NO = "0";
    public static final String USERFILES_BASE_URL = "/userfiles/";

    public static String getProjectPath() {
        String var0;
        if (StringUtils.isNotBlank(var0 = getConfig(GlobalStaticDictBase.ALLATORIxDEMO("xbgzms|@id`")))) {
            return var0;
        } else {
            try {
                File var1;
                if ((var1 = (new DefaultResourceLoader()).getResource("").getFile()) != null) {
                    File var10000;
                    while(true) {
                        File var2;
                        if ((var2 = new File(var1.getPath() + File.separator + ServiceUtils.ALLATORIxDEMO("HkX") + File.separator + GlobalStaticDictBase.ALLATORIxDEMO("}iyf"))) != null) {
                            if (var2.exists()) {
                                var10000 = var1;
                                break;
                            }

                            if (var1.getParentFile() != null) {
                                var1 = var1.getParentFile();
                                continue;
                            }
                        }

                        var10000 = var1;
                        break;
                    }

                    var0 = var10000.toString();
                    return var0;
                }
            } catch (IOException var3) {
                var3.printStackTrace();
            }

            return var0;
        }
    }

    public static String getFrontPath() {
        return getConfig(ServiceUtils.ALLATORIxDEMO("]kTwOIZmS"));
    }

    static {
        String[] var10002 = new String[1];
        boolean var10004 = true;
        var10002[0] = GlobalStaticDictBase.ALLATORIxDEMO("k\u007ffvaw&`z\u007fxuzdau{");
        K = new PropertiesLoader(var10002);
    }



    public static Global getInstance() {
        return k;
    }

    public static String getConfig(String a) {
        String var1;
        if ((var1 = (String)ALLATORIxDEMO.get(a)) == null) {
            var1 = K.getProperty(a);
            ALLATORIxDEMO.put(a, var1 != null ? var1 : "");
        }

        return var1;
    }

    public static Boolean isDemoMode() {
        String var0 = getConfig(GlobalStaticDictBase.ALLATORIxDEMO("tm}g]gtm"));
        return !"true".equals(var0) && !"1".equals(var0) ? false : true;
    }

    public static void modifyConfig(String a, String a) {
        try {
            Properties var2;
            (var2 = getProperties()).setProperty(a, a);
            a = Global.class.getResource(ServiceUtils.ALLATORIxDEMO("6XvU\u007fR~\u0015iIvK|ImR|H")).getPath();
            String a = new FileOutputStream(a);
            var2.store(a, GlobalStaticDictBase.ALLATORIxDEMO("}gtavq"));
            a.close();
            a.flush();
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }*/

    /*public static Properties getProperties() {
        Properties var0 = new Properties();

        try {
            Reader var1 = Resources.getResourceAsReader(ServiceUtils.ALLATORIxDEMO("6XvU\u007fR~\u0015iIvK|ImR|H"));
            var0.load(var1);
            return var0;
        } catch (Exception var2) {
            return null;
        }
    }

    public static String getUserfilesBaseDir() {
        String var0;
        String var10000;
        if (StringUtils.isBlank(var0 = getConfig(GlobalStaticDictBase.ALLATORIxDEMO("}cmbnydu{>jq{ulyz")))) {
            try {
                var0 = ServletContextFactory.getServletContext().getRealPath(ServiceUtils.ALLATORIxDEMO("\u0014"));
            } catch (Exception var2) {
                return "";
            }

            var10000 = var0;
        } else {
            var10000 = var0;
        }

        if (!var10000.endsWith(GlobalStaticDictBase.ALLATORIxDEMO("'"))) {
            var0 = var0 + ServiceUtils.ALLATORIxDEMO("\u0014");
        }

        return var0;
    }

    public static Object findUserUtils() {
        if (e == null) {
            try {
                e = Class.forName(getConfig(GlobalStaticDictBase.ALLATORIxDEMO("}cmb]da|{")).trim()).newInstance();
            } catch (ClassNotFoundException var1) {
                var1.printStackTrace();
            } catch (InstantiationException var2) {
                var2.printStackTrace();
            } catch (IllegalAccessException var3) {
                var3.printStackTrace();
            }
        }

        return e;
    }*/

    public Global() {
    }
    public static String getAdminPath() {
        return null;
    }
    public static String getUrlSuffix() {

        return getConfig("urlSuffix");
    }

    public static String getConfig(String key){
        return System.getProperty(key);
    }

    /*public static String getSysPath() {
        return getConfig(GlobalStaticDictBase.ALLATORIxDEMO("{i{@id`"));
    }

    public static String getServerPath() {
        return getConfig(ServiceUtils.ALLATORIxDEMO("j^kM|IIZmS"));
    }

    public static Boolean isSynActivitiIndetity() {
        String var0 = getConfig(GlobalStaticDictBase.ALLATORIxDEMO("qkdafada>ac[ifQkdafadaYftmdadq"));
        return !"true".equals(var0) && !"1".equals(var0) ? false : true;
    }

    public static Object getConst(String a) {
        try {
            return Global.class.getField(a).get((Object)null);
        } catch (Exception var2) {
            return null;
        }
    }*/
}
