package aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class checkLogin {
	@Pointcut("execution(public *.handle*(..))")  
    public void k(){}
	
	@Before("k()")
	public void checkLoginStatus(JoinPoint jp) {
		Object[] reqRes = jp.getArgs();
		HttpServletRequest req = (HttpServletRequest) reqRes[0];
		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("Session is null");
		}else {
			System.out.println("Session is NOT null");
		}
	}
}
