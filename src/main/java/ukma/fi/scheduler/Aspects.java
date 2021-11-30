package ukma.fi.scheduler;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Order(1)
@Service
@Aspect
@Log4j2
public class Aspects {

    @Around("@within(Time) || @annotation(Time)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " EXECUTED IN " + executionTime + "MS");
        return  proceed;
    }

    @Around("@within(ArgsAsp) || @annotation(ArgsAsp)")
    public Object getArgs(ProceedingJoinPoint joinPoint) throws Throwable {

        StringBuilder before = new StringBuilder();
        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> before.append(o.toString()).append(", "));
        log.info("INPUT OF: " + joinPoint.getSignature() + ": {" + before + "}");

        Object proceed = joinPoint.proceed();

        StringBuilder args = new StringBuilder();
        Arrays.stream(joinPoint.getArgs())
                .forEach(o -> args.append(o.toString()).append(", "));
        log.info("RETURN OF: " + joinPoint.getSignature() + ": {" + proceed.toString() + "}");

        return  proceed;
    }
}
