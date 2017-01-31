package br.edu.recebe.model;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {

	public void enviar(Reserva reservando){
	

		Properties props = new Properties();
	    
	    //Definindo as propriedades da conexão
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");

	  
	    Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                     protected PasswordAuthentication getPasswordAuthentication() 
	                     {
	                           
	                    	 return new PasswordAuthentication("avaliacao1197@gmail.com", "unipe@2017");
	                     }
	                });
	  
	   session.setDebug(true);
	    try {
	             
	          Message message = new MimeMessage(session);
	         
	          //E-mail do Remetente
	          message.setFrom(new InternetAddress("avaliacao1197@gmail.com")); 
	 
	          //Destinatário         
	          Address[] toUser = InternetAddress .parse("avaliacao1197@gmail.com");  
	          message.setRecipients(Message.RecipientType.TO, toUser);
	          
	          // Título da mensagem
	          message.setSubject("Confirmação de Reserva (Trabalho de Fujioka)");
	          
	          //Conteúdo da mensagem
	          message.setText("E-mail de confirmação da reserva solicitada com os seguintes dados: "+
	           " Identificador da reserva: " + reservando.getId() +
	           " Identificador do usuario: " + reservando.getUsuario().getId() +
	           " Nome do usuario: " + reservando.getUsuario().getNome() +
	           " Cpf  do usuario: " + reservando.getUsuario().getCpf()
	        		  );
	   
	          Transport.send(message);
	         
	          System.out.println("E-mail enviado com sucesso!");
	     
	    } catch (MessagingException e) {
	          throw new RuntimeException(e);
	    }
	}
		
		
		
		
}
	


