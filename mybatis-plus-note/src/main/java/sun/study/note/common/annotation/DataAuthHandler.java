package sun.study.note.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author sunzhen <sunzhen03@kuaishou.com>
 * Created on 2021-06-28
 */
@Aspect
@Component
public class DataAuthHandler {

    @Pointcut(value = "@annotation(sun.study.note.common.annotation.DataAuth)")
    public void test() {
    }

    @Around("test()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        DataAuth dataAuth = signature.getMethod().getAnnotation(DataAuth.class);
       String methodName = dataAuth.targetParamClassMethodName();
        String targetParamName = dataAuth.targetParamName();

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(targetParamName)) {
                if (!"".equals(methodName)) {
                    Method method = parameterTypes[i].getMethod(methodName);
                    result = method.invoke(args[i]);
                } else {
                    result = args[i];
                }
            }
        }
        System.err.println(result);
        return joinPoint.proceed();
    }
}
