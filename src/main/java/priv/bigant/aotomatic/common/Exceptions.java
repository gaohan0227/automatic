package priv.bigant.aotomatic.common;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {
    public Exceptions() {
    }

    public static boolean isCausedBy(Exception a, Class<? extends Exception>... s) {
        Throwable var2;
        for(Throwable var10000 = var2 = a.getCause(); var10000 != null; var10000 = var2 = var2.getCause()) {
            Class[] var5 = s;
            int var4 = s.length;

            int var3;
            for(int var6 = var3 = 0; var6 < var4; var6 = var3) {
                if (var5[var3].isInstance(var2)) {
                    return true;
                }

                ++var3;
            }
        }

        return false;
    }

    public static RuntimeException unchecked(Exception a) {
        return a instanceof RuntimeException ? (RuntimeException)a : new RuntimeException(a);
    }

    public static Throwable getThrowable(HttpServletRequest a) {
        Throwable var1 = null;
        if (a.getAttribute("exception") != null) {
            return (Throwable)a.getAttribute("exception");
        } else {
            if (a.getAttribute("javax.servlet.error.exception") != null) {
                var1 = (Throwable)a.getAttribute("javax.servlet.error.exception");
            }

            return var1;
        }
    }

    public static String getStackTraceAsString(Throwable a) {
        if (a == null) {
            return "";
        } else {
            StringWriter var1;
            StringWriter var10000 = var1 = new StringWriter();
            a.printStackTrace(new PrintWriter(var1));
            return var10000.toString();
        }
    }
}
