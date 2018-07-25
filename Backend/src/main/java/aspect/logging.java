package aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


@Aspect
@Component
public class logging {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Before("execution(* server.controller..*.*(*, *))")
	public void log4j(JoinPoint joinPoint) {
		if(joinPoint.getArgs()[0] instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) joinPoint.getArgs()[0];
			String ip = req.getRemoteAddr();
			System.out.println("ip add: "+ip);
		}
		else {
			System.out.println("no");
		}
	}
}
