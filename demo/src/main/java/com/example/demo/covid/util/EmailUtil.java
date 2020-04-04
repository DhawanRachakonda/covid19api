/**
 * 
 */
package com.example.demo.covid.util;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.covid.dtos.SuspectionReport;



/**
 * @author H156833
 *
 */
@Component
public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
    private static VelocityUtil velocityUtil;
    private static ApiUtility apiUtil;
	/*
	 * @Autowired private VelocityUtil velocityUtil;
	 */
    @Autowired
    public EmailUtil(ApiUtility apiUtil, VelocityUtil velocityUtil) {
        this.apiUtil = apiUtil;
        this.velocityUtil = velocityUtil;
    }
	/*
	 * VelocityUtil velocityUtil = new VelocityUtil();
	 * 
	 * @Autowired private ApiUtility apiUtil;
	 */
 
	public static void sendEmail(SuspectionReport report,String covidLocation) throws Exception {
		logger.info("--------------- sendEmailToCustomer --------------");
		try {
			Properties mailProps = new Properties();
			final String username = "incubationtracker@gmail.com";
	        final String password = "bvhibkzgkicfdsqh";

	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable","true");
	        props.put("mail.smtp.host", "smtp.gmail.com");    
	          props.put("mail.smtp.auth", "true");    
	          props.put("mail.smtp.port", "587");  ; //TLS
	        
	          Session session = Session.getDefaultInstance(props,    
	                  new javax.mail.Authenticator() {    
	                  protected PasswordAuthentication getPasswordAuthentication() {    
	                  return new PasswordAuthentication(username,password);  
	                  }    
	                 });    
			//mailProps.put("mail.smtp.host", apiUtil.getProperty("spring.mail.host"));
			//Session mailSession = Session.getDefaultInstance(mailProps, null);
			InternetAddress toAddrs = new InternetAddress(apiUtil.getProperty("email.to.address"));
			
			InternetAddress fromAddr = new InternetAddress(apiUtil.getProperty("email.from.address").toString().trim());
			// Create and initialize message
			javax.mail.Message message = new MimeMessage(session);
			message.setFrom(fromAddr);
			message.setRecipient(javax.mail.Message.RecipientType.TO, toAddrs);
			// message.setRecipients(Message.RecipientType.CC, ccAddrs);
			message.setSubject("Covid Contact Suspection" );
			InternetAddress[] replyTo = new InternetAddress[1];
			replyTo[0]=new InternetAddress(apiUtil.getProperty("email.from.address").toString().trim());
			message.setReplyTo(replyTo);
			message.setContent(velocityUtil.getEmailTemplate(report,covidLocation),"text/html");
			// Send message 			
			Transport.send(message);
			logger.info("--------------- Email has been sent --------------");
		} catch (MessagingException e) {
			logger.error("--------------- Exception --------------" + e.getMessage());
		}
	}
}
