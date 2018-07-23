package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import data.model.User;

@Aspect
@Component
public class checkSession {
	
	@Around("execution(* server.controller..*.handle*(..))")
	public @ResponseBody Object checkLoginStatus(ProceedingJoinPoint joinPoint) {
		Object[] reqRes = joinPoint.getArgs();
		//Signature signature =  joinPoint.getSignature();
		//Class returnType = ((MethodSignature) signature).getReturnType();
		HttpServletRequest req = (HttpServletRequest) reqRes[0];
		HttpSession session = req.getSession(false);
		if (session != null) {
            try {
            	System.out.println("proceeded");
				return joinPoint.proceed();
			} catch (Throwable e) {
				System.out.println("something wrong");
				return new User("null");
			}
        }
		else { // session == null
			System.out.println("User not logged");
			return new User("null");
		}
	}

}
