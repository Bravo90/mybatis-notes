package sun.study.note.spring.anno;

import java.lang.annotation.*;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnno {

    Class<?> paramClass() default Object.class;
    int type() default 0;

}
