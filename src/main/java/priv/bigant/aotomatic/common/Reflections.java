package priv.bigant.aotomatic.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflections {
    private static final String e = "set";
    private static final String k = "$$";
    private static Logger K = LoggerFactory.getLogger(Reflections.class);
    private static final String ALLATORIxDEMO = "get";

    /*public static void invokeSetter(Object a, String a, Object a) {
        Object var3 = a;
        String a = StringUtils.split(a, FreeMarkers.ALLATORIxDEMO("?"));

        int var4;
        for(int var10000 = var4 = 0; var10000 < a.length; var10000 = var4) {
            String var5;
            boolean var10004;
            if (var4 < a.length - 1) {
                var5 = "get" + StringUtils.capitalize(a[var4]);
                Class[] var10002 = new Class[0];
                var10004 = true;
                Object[] var10003 = new Object[0];
                boolean var10005 = true;
                var3 = invokeMethod(var3, var5, var10002, var10003);
            } else {
                var5 = "set" + StringUtils.capitalize(a[var4]);
                Object[] var7 = new Object[1];
                var10004 = true;
                var7[0] = a;
                invokeMethodByName(var3, var5, var7);
            }

            ++var4;
        }

    }

    public static boolean isContainField(Object a, String b) {
        return getAccessibleField(a, b) != null;
    }

    public static Object invokeMethodByName(Object a, String a, Class[] a, Object[] a) {
        Method var4;
        if ((var4 = getAccessibleMethodByName(a, a)) == null) {
            Method var10000;
            label22: {
                try {
                    var4 = getJavassistMethod(a.getClass().getName(), a, a, a);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | ClassNotFoundException var7) {
                    var10000 = var4;
                    var7.printStackTrace();
                    break label22;
                }

                var10000 = var4;
            }

            if (var10000 == null) {
                throw new IllegalArgumentException("Could not find method [" + a + "] on target [" + a + "]");
            }
        }

        try {
            return var4.invoke(a, a);
        } catch (Exception var6) {
            throw convertReflectionExceptionToUnchecked(var6);
        }
    }*/

    public static Field getAccessibleField(Object a, String b) {
        String var10001 = "object can't be null";
        Object[] var10002 = new Object[0];
        boolean var10004 = true;
        Validate.notNull(a, var10001, var10002);
        var10001 = "fieldName can't be blank";
        var10002 = new Object[0];
        var10004 = true;
        Validate.notBlank(b, var10001, var10002);
        Class var2 = a.getClass();
        Class var10000 = var2;

        while(var10000 != Object.class) {
            try {
                Field var3;
                makeAccessible(var3 = var2.getDeclaredField(b));
                return var3;
            } catch (NoSuchFieldException var4) {
                var10000 = var2 = var2.getSuperclass();
            }
        }

        return null;
    }
/*
    public static List<String> findAllField(Object a, boolean a) {
        ArrayList var2 = Lists.newArrayList();
        int var10000;
        int var4;
        int var5;
        if (a) {
            PropertyDescriptor[] var8;
            var5 = (var8 = PropertyUtils.getPropertyDescriptors(a)).length;

            for(var10000 = var4 = 0; var10000 < var5; var10000 = var4) {
                PropertyDescriptor var7 = var8[var4];
                ++var4;
                var2.add(var7.getName());
            }

            return var2;
        } else {
            Field[] var6;
            var5 = (var6 = a.getClass().getDeclaredFields()).length;

            for(var10000 = var4 = 0; var10000 < var5; var10000 = var4) {
                Field var3 = var6[var4];
                ++var4;
                var2.add(var3.getName());
            }

            return var2;
        }
    }

    public static Class<?> getObjPropClass(Object a, String a) {
        if (getAccessibleField(a, a) == null) {
            a = UpdateCodeFor.ALLATORIxDEMO("v25=;3\r! >\"\u000e") + a;
        }

        return getAccessibleField(a, a).getType();
    }

    public static Object getFieldValue(Object a, String filedName) {
        Field var2;
        if ((var2 = getAccessibleField(a, a)) == null) {
            throw new IllegalArgumentException(FreeMarkers.ALLATORIxDEMO("6~\u0000}\u00111\u001b~\u00011\u0013x\u001buUw\u001ct\u0019uUJ") + a + "] on target [" + a + "]");
        } else {
            a = null;

            try {
                String a = var2.get(a);
                return a;
            } catch (IllegalAccessException var3) {
                K.error(UpdateCodeFor.ALLATORIxDEMO("也厾肯拊冨盕彐幩),"), var3.getMessage());
                return a;
            }
        }
    }*/

    public static Object getInstanceByClassName(String a, String... b) throws Exception {
        /*Object var2 = null;

        Object var10000;
        label40: {
            try {
                var2 = SpringContextHolder.getBean(Class.forName(a).newInstance().getClass());
            } catch (Exception var5) {
                try {
                    var2 = Class.forName(a).newInstance();
                } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var4) {
                    String var3 = b.length > 0 ? b[0] : FreeMarkers.ALLATORIxDEMO("r\u001a|[e\u0010\u007f\u0012{\u001ct");
                    List list;
                    if ((a = ClassScaner.scan(var3, a)) != null && a.size() > 0) {
                        var2 = Class.forName((String)a.get(0)).newInstance();
                    }

                    var10000 = var2;
                    break label40;
                }

                var10000 = var2;
                break label40;
            }

            var10000 = var2;
        }

        if (var10000 == null) {
            throw new Exception(UpdateCodeFor.ALLATORIxDEMO("戬乜剢簪") + a);
        } else {
            return var2;
        }*/
        return null;
    }

    public static void makeAccessible(Field a) {
        if ((!Modifier.isPublic(a.getModifiers()) || !Modifier.isPublic(a.getDeclaringClass().getModifiers()) || Modifier.isFinal(a.getModifiers())) && !a.isAccessible()) {
            a.setAccessible(true);
        }

    }

    public static Class getClassGenricType(Class a, int b) {
        /*Type var2;
        if (!((var2 = a.getGenericSuperclass()) instanceof ParameterizedType)) {
            K.warn(a.getSimpleName() + FreeMarkers.ALLATORIxDEMO("6\u00061\u0006d\u0005t\u0007r\u0019p\u0006bU\u007f\u001aeUA\u0014c\u0014|\u0010e\u0010c\u001ck\u0010u!h\u0005t"));
            return Object.class;
        } else {
            Type[] var3 = ((ParameterizedType)var2).getActualTypeArguments();
            if (a < var3.length && a >= 0) {
                if (!(var3[a] instanceof Class)) {
                    K.warn(a.getSimpleName() + FreeMarkers.ALLATORIxDEMO("1\u001b~\u00011\u0006t\u00011\u0001y\u00101\u0014r\u0001d\u0014}Ur\u0019p\u0006bU~\u001b1\u0006d\u0005t\u0007r\u0019p\u0006bUv\u0010\u007f\u0010c\u001crUa\u0014c\u0014|\u0010e\u0010c"));
                    return Object.class;
                } else {
                    return (Class)var3[a];
                }
            } else {
                K.warn(UpdateCodeFor.ALLATORIxDEMO("\u0018<57)hq") + a + FreeMarkers.ALLATORIxDEMO("Y1&x\u000ftU~\u00131") + a.getSimpleName() + UpdateCodeFor.ALLATORIxDEMO("v!q\u00020 0?4&4 8(46q\u0006(\"4hq") + var3.length);
                return Object.class;
            }
        }*/
        return null;
    }

    public static void setFieldValue(Object a, String b, Object c) {
        Field var3;
        if ((var3 = getAccessibleField(a, b)) == null) {
            throw new IllegalArgumentException("Could not find field [" + a + "] on target [" + a + "]");
        } else {
            try {
                var3.set(a, a);
            } catch (IllegalAccessException var4) {
                K.error("不可能抛出的异常:{}", var4.getMessage());
            }
        }
    }

    public static Object invokeMethod(Object a, String b, Class<?>[] c, Object[] d) {
        Method method = null;
        if ((a = getAccessibleMethod(a, b, c)) == null) {
            throw new IllegalArgumentException("Could not find method [" + a + "] on target [" + a + "]");
        } else {
            try {
                return method.invoke(a, a);
            } catch (Exception var4) {
                throw convertReflectionExceptionToUnchecked(var4);
            }
        }
    }

    public static Method getJavassistMethod(String a, String a1, Class[] a2, Object[] a3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
        ClassPool classPool = ClassPool.getDefault();
        Class clazz;
        Object var4 = (clazz = (new Loader(Thread.currentThread().getContextClassLoader(), classPool)).loadClass(a)).newInstance();
        var4 = null;
        if (a == null) {
            Class[] var10002 = new Class[1];
            boolean var10004 = true;
            var10002[0] = BaseEntity.class;
            return clazz.getMethod(a1, var10002);
        } else {
            return clazz.getMethod(a1, clazz);
        }
    }

    public static RuntimeException convertReflectionExceptionToUnchecked(Exception a) {
        if (!(a instanceof IllegalAccessException) && !(a instanceof IllegalArgumentException) && !(a instanceof NoSuchMethodException)) {
            if (a instanceof InvocationTargetException) {
                return new RuntimeException(((InvocationTargetException)a).getTargetException());
            } else {
                return a instanceof RuntimeException ? (RuntimeException)a : new RuntimeException("Unexpected Checked Exception.", a);
            }
        } else {
            return new IllegalArgumentException(a);
        }
    }

    /*public static <T> Class<T> getClassGenricType(Class a) {
        return getClassGenricType(a, 0);
    }

    public static boolean isContainFieldDyna(Object a, String a) {
        if (getAccessibleField(a, a) == null) {
            a = UpdateCodeFor.ALLATORIxDEMO("v25=;3\r! >\"\u000e") + a;
            if (getAccessibleField(a, a) == null) {
                return false;
            }
        }

        return true;
    }*/

    /*public static Map<String, Object> findAllDynaFieldForCglib(Object a, boolean a) throws IllegalArgumentException, IllegalAccessException {
        HashMap var2 = Maps.newHashMap();
        String var3;
        if (!(var3 = a.getClass().getSimpleName()).contains(FreeMarkers.ALLATORIxDEMO("Q57t\u0014\u007f2t\u001bt\u0007p\u0001~\u0007S\fR2]<S"))) {
            return var2;
        } else {
            var3 = var3.substring(0, var3.indexOf("$$"));

            Class var4;
            for(Class var10000 = var4 = a.getClass(); var10000 != null && !var4.getSimpleName().equals(var3); var10000 = var4 = var4.getSuperclass()) {
                Field[] var8;
                int var7 = (var8 = var4.getDeclaredFields()).length;

                int var6;
                for(int var9 = var6 = 0; var9 < var7; var9 = var6) {
                    Field var5 = var8[var6];
                    var2.put(var5.getName(), a ? FieldUtils.readField(var5, a, true) : var5.getName());
                    ++var6;
                }
            }

            return var2;
        }
    }*/

    public static Object invokeGetter(Object a, String b) {
        Object var2 = a;
        String[] var5;
        int var4 = (var5 = StringUtils.split(b, ".")).length;

        int var3;
        for(int var10000 = var3 = 0; var10000 < var4; var10000 = var3) {
            a = var5[var3];
            a = "get" + StringUtils.capitalize(b);
            Class[] var10002 = new Class[0];
            boolean var10004 = true;
            Object[] var10003 = new Object[0];
            boolean var10005 = true;
            ++var3;
            var2 = invokeMethod(var2, b, var10002, var10003);
        }

        return var2;
    }

    public Reflections() {
    }

    public static Class<?> getUseClass(Object a) {
        Assert.notNull(a, "Instance must not be null");
        Class var1;
        Class var2;
        return (var1 = a.getClass()) != null && var1.getName().contains("$$") && (var2 = var1.getSuperclass()) != null && !Object.class.equals(var2) ? var2 : var1;
    }

    public static Method getAccessibleMethodByName(Object a, String b) {
        String var10001 = "object can't be null";
        Object[] var10002 = new Object[0];
        boolean var10004 = true;
        Validate.notNull(a, var10001, var10002);
        var10001 = "methodName can't be blank";
        var10002 = new Object[0];
        var10004 = true;
        Validate.notBlank(b, var10001, var10002);
        Class var2 = a.getClass();

        for(Class var10000 = var2; var10000 != Object.class; var10000 = var2 = var2.getSuperclass()) {
            Method[] var6;
            int var5 = (var6 = var2.getDeclaredMethods()).length;

            int var4;
            for(int var7 = var4 = 0; var7 < var5; var7 = var4) {
                Method var3;
                if ((var3 = var6[var4]).getName().equals(a)) {
                    makeAccessible(var3);
                    return var3;
                }

                ++var4;
            }
        }

        return null;
    }

    /*public static Map<String, Class> findAllDynaFieldForCglib(Object a) throws IllegalArgumentException, IllegalAccessException {
        HashMap var1 = Maps.newHashMap();
        String var2;
        if (!(var2 = a.getClass().getSimpleName()).contains(UpdateCodeFor.ALLATORIxDEMO("vu\u001043?\u00154<4 0&> \u0013+\u0012\u0015\u001d\u001b\u0013"))) {
            return var1;
        } else {
            var2 = var2.substring(0, var2.indexOf("$$"));

            Class var3;
            for(Class var10000 = var3 = a.getClass(); var10000 != null && !var3.getSimpleName().equals(var2); var10000 = var3 = var3.getSuperclass()) {
                Field[] var7;
                int var6 = (var7 = var3.getDeclaredFields()).length;

                int var5;
                for(int var8 = var5 = 0; var8 < var6; var8 = var5) {
                    Field var4 = var7[var5];
                    String var10001 = var4.getName();
                    ++var5;
                    var1.put(var10001, var4.getClass());
                }
            }

            return var1;
        }
    }*/

    public static Method getAccessibleMethod(Object a, String b, Class<?>... c) {
        String var10001 = "object can't be null";
        Object[] var10002 = new Object[0];
        boolean var10004 = true;
        Validate.notNull(a, var10001, var10002);
        var10001 = "methodName can't be blank";
        var10002 = new Object[0];
        var10004 = true;
        Validate.notBlank(b, var10001, var10002);
        Class var3 = a.getClass();
        Class var10000 = var3;

        while(var10000 != Object.class) {
            try {
                Method var4;
                makeAccessible(var4 = var3.getDeclaredMethod(b, c));
                return var4;
            } catch (NoSuchMethodException var5) {
                var10000 = var3 = var3.getSuperclass();
            }
        }

        return null;
    }

    public static Object invokeMethodByName(Object a, String b, Object[] c) {
        Method var3;
        if ((var3 = getAccessibleMethodByName(a, b)) == null) {
            throw new IllegalArgumentException("Could not find method [" + a + "] on target [" + a + "]");
        } else {
            try {
                return var3.invoke(a, a);
            } catch (Exception var4) {
                throw convertReflectionExceptionToUnchecked(var4);
            }
        }
    }

    /*public static List<Method> getAccessibleMethodsByName(Object a, String a) {
        String var10001 = "object can't be null";
        Object[] var10002 = new Object[0];
        boolean var10004 = true;
        Validate.notNull(a, var10001, var10002);
        var10001 = "methodName can't be blank";
        var10002 = new Object[0];
        var10004 = true;
        Validate.notBlank(a, var10001, var10002);
        ArrayList var2 = new ArrayList();
        Class var3 = a.getClass();

        for(Class var10000 = var3; var10000 != Object.class; var10000 = var3 = var3.getSuperclass()) {
            Method[] var7;
            int var6 = (var7 = var3.getDeclaredMethods()).length;

            int var5;
            for(int var8 = var5 = 0; var8 < var6; var8 = var5) {
                Method var4;
                if ((var4 = var7[var5]).getName().equals(a)) {
                    makeAccessible(var4);
                    var2.add(var4);
                }

                ++var5;
            }
        }

        return var2;
    }*/

    public static void makeAccessible(Method a) {
        if ((!Modifier.isPublic(a.getModifiers()) || !Modifier.isPublic(a.getDeclaringClass().getModifiers())) && !a.isAccessible()) {
            a.setAccessible(true);
        }

    }

    /*public static Object invokeJavassistMethod(String a, String a, Class[] a, Object[] a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
        ClassPool var4 = ClassPool.getDefault();
        Class var10000 = (new Loader(Thread.currentThread().getContextClassLoader(), var4)).loadClass(a);
        Object var8 = var10000.newInstance();
        String a = var10000.getMethod(a, a);
        a = null;

        try {
            Class[] a = a.invoke(var8, a);
            return a;
        } catch (InvocationTargetException | IllegalArgumentException var5) {
            var5.printStackTrace();
            return a;
        }
    }*/
}