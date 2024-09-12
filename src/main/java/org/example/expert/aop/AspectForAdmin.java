package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j // 로그로 확인하기 위한 어노테이션
@Aspect // 어드바이스와 포인트컷을 하나로 묶은 모듈
public class AspectForAdmin {

    //포인트 컷 = 어드바이스를 적용할 곳. 특정 어노테이션에 적용
    @Pointcut("@annotation(org.example.expert.annotation.AdminOnly)")
    private void useAdminOnly(){}

    //어드바이스(횡단 관심사)
    @Around("useAdminOnly()")
    public Object AdviceMethod(ProceedingJoinPoint joinpoint) throws Throwable {
        // URL 을 구하기 위함
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //어드바이스가 실행되는 지점
        Object result = joinpoint.proceed();

        log.info("요청한 사용자의 ID =" + request.getAttribute("userId"));
        log.info("API 요청 시각 =" + LocalDateTime.now());
        log.info("API 요청 URL =" + request.getRequestURL());

        return result;


    }
}
