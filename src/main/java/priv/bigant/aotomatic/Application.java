package priv.bigant.aotomatic;

import org.apache.catalina.*;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = {"priv.bigant.aotomatic.autoCode.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(Application.class, args);
    }

    @Value("${spring.messages.basename}")
    private String basename;

    /*@Bean
    public ReloadableResourceBundleMessageSource messageSource() throws IOException {
        //Properties properties = new Properties();
        //properties.load(new FileInputStream(new File("message.properties")));
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.addBasenames(basename);
        Resource resource = annotationConfigServletWebServerApplicationContext.getResource("messages.properties");
        Resource resource1 = annotationConfigServletWebServerApplicationContext.getResource("/messages.properties");
        System.out.println(resource.exists() + "" + resource1.exists());
*//*        String message = reloadableResourceBundleMessageSource.getMessage("global.add", null, Locale.getDefault());
        System.out.println(message);*//*
        return reloadableResourceBundleMessageSource;
    }*/

/*    @Bean
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        return sessionLocaleResolver;
    }*/

    /**
     * 默认解析器 其中locale表示默认语言
     */
    /*@Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    *//**
     * 默认拦截器 其中lang表示切换语言的参数名
     *//*
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
                localeInterceptor.setParamName("lang");
                registry.addInterceptor(localeInterceptor);
            }
        };
    }*/

    /*@Bean
    public TomcatServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                //添加监听器
                context.addLifecycleListener(event -> {
                    if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
                        try {
                            //!!!资源所在url
                            URL url = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX);
                            //!!!资源搜索路径
                            String path = "/";
                            //手动创建一个ResourceSet
                            context.getResources().createWebResourceSet(WebResourceRoot.ResourceSetType.RESOURCE_JAR, "/", url, path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
    }*/

}

