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
			if(!joinPoint.getSignature().getName().equals("handleCheckLogin")) {
				String ip = req.getRemoteAddr();
				logger.info("Request from: "+ip+"\n"+joinPoint.getSignature().getName());
			}
		}
		else {
			System.out.println("no");
		}
	}
}
