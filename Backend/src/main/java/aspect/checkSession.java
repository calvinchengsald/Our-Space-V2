package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
				e.printStackTrace();
				System.out.println("something wrong");
				return null;
			}
        }
		else { // session == null
			System.out.println("User not logged");
			return null;
		}
	}

}
