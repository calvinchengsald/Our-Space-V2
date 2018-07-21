/**
 * 
 */
package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Jerry Affricot
 *
 */
public class EmailUtil {
	// Server Mail Transfer Protocol
	private final static String host = "smtp.gmail.com";
	
	// username
	private final static String username = "billythewarden@gmail.com";
	
	// password
	private final static String password = "EfBDCXS8TfidGA7";
	
	
	
	/**
	 * @param email			recipient of the message
	 * @param subject		subject of the email
	 * @param body			message to be sent
	 * @return				1 if the message has been sent successfully
	 */
	public static int sendEmail(String email, String subject, String body) {
		Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(username));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(email));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         message.setText(body);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	         return 1;

	      } catch (MessagingException e) {
	    	  System.out.println("Error sending email: " + e.getMessage());
	    	  return 0;
	      }
		
	}

}
