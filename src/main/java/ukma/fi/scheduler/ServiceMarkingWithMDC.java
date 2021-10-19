package ukma.fi.scheduler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Order(1)
@Service
@Aspect
public class ServiceMarkingWithMDC {

    private static final String BACKEND_FUNCTION_NAME = "backendFunctionName";

    @Pointcut("@within(ServiceMarker)")
    private void classesAnnotatedWithServiceMarker() {
    }

    @Around("classesAnnotatedWithServiceMarker()")
    public Object aroundAnnotatedClass(ProceedingJoinPoint joinPoint) throws Throwable {
        setMdcContextForClass(joinPoint);
        return joinPoint.proceed();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void setMdcContextForClass(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clazz = signature.getDeclaringType();
        ServiceMarker annotation = (ServiceMarker) clazz.getAnnotation(ServiceMarker.class);

        String functionName = annotation.functionName();

        if (StringUtils.isBlank(functionName)) {
            functionName = getClassName(signature.getDeclaringTypeName()) + "_" + signature.getMethod().getName();
        }

        MDC.put(BACKEND_FUNCTION_NAME, "[" + functionName + "]");
    }

    private String getClassName(String classFullName) {
        int startIndexOfClassName = StringUtils.lastIndexOf(classFullName, ".") + 1;
        return StringUtils.substring(classFullName, startIndexOfClassName);
    }
}
