package priv.bigant.aotomatic.common;

import priv.bigant.aotomatic.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static Logger K = LoggerFactory.getLogger(SpringContextHolder.class);
    private static ApplicationContext applicationContext = null;

    public SpringContextHolder() {
    }

    /**
     * 获取该表的dao实体
     *
     * @param baseEntity
     * @param booleans
     * @return
     */
    public static String findDaoNameByEntity(BaseEntity baseEntity, boolean... booleans) {
        Map<String, BaseMapper> beansOfType = applicationContext.getBeansOfType(BaseMapper.class);
        Collection<BaseMapper> values = beansOfType.values();
        for (BaseMapper x : values) {
            for (Class<?> anInterface : x.getClass().getInterfaces()) {
                for (Type genericInterface : anInterface.getGenericInterfaces()) {
                    Type[] type = ((ParameterizedType) genericInterface).getActualTypeArguments();
                    for (Type type1 : type) {
                        String typeName = type1.getTypeName();
                        String name = baseEntity.getClass().getName();
                        if (typeName.equals(name)) {
                            return x.getClass().getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取该表的service实体
     *
     * @param baseEntity
     * @param booleans
     * @return
     */
    public static String findServiceNameByEntity(BaseEntity baseEntity, boolean... booleans) {
        Map<String, BaseService> beansOfType = applicationContext.getBeansOfType(BaseService.class);
        Collection<BaseService> values = beansOfType.values();
        for (BaseService x : values) {
            Type type = ((ParameterizedType) x.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[1];
            String typeName = type.getTypeName();
            String name = baseEntity.getClass().getName();
            if (typeName.equals(name)) {
                return x.getClass().getName();
            }
        }
        return null;
    }

    public static Object getBean(String a) {
        return applicationContext.getBean(a);
    }

    public void setApplicationContext(ApplicationContext a) {
        applicationContext = a;
    }

    public static String findEntityNameByEntity(BaseEntity baseEntity, boolean... booleans) {
        String var2;
        if ((var2 = baseEntity.getClass().getSimpleName()).contains("$$")) {
            var2 = var2.substring(0, var2.indexOf("$$"));
        }

        boolean var3 = booleans.length <= 0 || booleans[0];
        if (var3) {
            var2 = StringUtils.firstToLower(var2);
        }

        return var2;
    }

    public static Connection getConnection() {
        return DataSourceUtils.getConnection((DataSource) applicationContext.getBean(ConditionBean.ALLATORIxDEMO("rsbsE}c`uw")));
    }

    public static void clearHolder() {
        if (K.isDebugEnabled()) {
            K.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }

        applicationContext = null;
    }

    public static Connection releaseConnection(Connection a) {
        DataSource var1 = (DataSource) applicationContext.getBean(ConditionBean.ALLATORIxDEMO("rsbsE}c`uw"));
        DataSourceUtils.releaseConnection(a, var1);
        return a;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> a) {
        return applicationContext.getBean(a);
    }

    public void destroy() throws Exception {
        clearHolder();
    }
}
