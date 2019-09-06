package priv.bigant.aotomatic.common;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface HtmlField {

    String value() default "";
}
