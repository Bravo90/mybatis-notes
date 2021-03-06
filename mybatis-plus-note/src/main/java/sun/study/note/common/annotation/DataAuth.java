package sun.study.note.common.annotation;

import java.lang.annotation.*;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataAuth {

    String targetParamName() default "";

    String targetParamClassMethodName() default "";
}
