package sun.study.note.spring.anno;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;
import sun.study.note.model.User;

import java.lang.reflect.Method;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-28
 */
@Aspect
@Component
public class AdviceTest {

    @Pointcut(value = "@annotation(sun.study.note.spring.anno.TestAnno)")
    public void test() {
    }

    @Around("test()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        TestAnno testAnno = signature.getMethod().getAnnotation(TestAnno.class);

        String[] parameterNames = signature.getParameterNames();

        int type = testAnno.type();
        if(type == 1){
            Class<?> aClass = testAnno.paramClass();
            for (int i = 0 ; i < parameterNames.length;i++){
                if(parameterNames[i].equals("user")){
                    Method method =  aClass.getMethod("getName");
                    Object invoke = method.invoke(args[i]);
                    System.out.println(invoke);

                }
            }
        }else {
            for (int i = 0 ; i < parameterNames.length;i++){
                if(parameterNames[i].equals("name")){
                    System.out.println(args[i]);
                }
            }
        }


        return joinPoint.proceed();
    }
}
