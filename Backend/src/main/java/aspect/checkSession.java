package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import data.model.User;

@Aspect
@Component
public class checkSession {
	
	@Around("execution(* server.controller..*.handle*(..))")
	public User checkLoginStatus(ProceedingJoinPoint joinPoint) {
		Object[] reqRes = joinPoint.getArgs();
		HttpServletRequest req = (HttpServletRequest) reqRes[0];
		HttpSession session = req.getSession(false);
		if (session != null) {
            try {
				joinPoint.proceed();
			} catch (Throwable e) {
				System.out.println("something wrong");
				return new User("null");
			}
        }
		else { // session == null
			System.out.println("User not logged");
			return new User("null");
		}
		return new User("null");
	}
}
