/**
 * 
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import data.model.EmailContainer;

/**
 * @author Jerry Affricot
 *
 */
public class EmailUtil {
	private static EmailContainer emailContainer;

	private final static String filename = "/usr/apache/filing/secure.txt";	

	private static void getEmailInfo() {
		try {
			
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			emailContainer = (EmailContainer) in.readObject();
			in.close();
			fileIn.close();			

		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found!!!!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}

	}
	
	

	/**
	 * @param email
	 *            recipient of the message
	 * @param subject
	 *            subject of the email
	 * @param body
	 *            message to be sent
	 * @return 1 if the message has been sent successfully
	 */
	public static int sendEmail(String email, String subject, String body) {
		
		getEmailInfo();
		
		if (emailContainer != null) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", emailContainer.getHost());
			props.put("mail.smtp.port", "587");

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailContainer.getUsername(), emailContainer.getPassword());
				}
			});

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(emailContainer.getUsername()));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

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
		return 0;
		


	}

}
