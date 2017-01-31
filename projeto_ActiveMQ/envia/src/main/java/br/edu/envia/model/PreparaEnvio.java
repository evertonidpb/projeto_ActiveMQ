package br.edu.envia.model;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PreparaEnvio implements Runnable {

	Usuario usuario;
	public PreparaEnvio(Usuario usuario){
		this.usuario = usuario;
		
	}
	
public void run() {
        try { // Create a connection factory.
            ActiveMQConnectionFactory factory = 
            new ActiveMQConnectionFactory("tcp://localhost:61616");

            //Cria e inicializa a  conexão
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

           // Nome do destinatario para o envio da mensagem
            Destination msg = session.createQueue("Mensagem");
            
            // Nome de outro destinatário para o envio do objeto Reserva
             Destination objeto = session.createQueue("Reserva");
            
            

            // Create a producer
            MessageProducer producerMsg = session.createProducer(msg);
            MessageProducer produtorReserva = session.createProducer(objeto);
            
            // Indica se a mensagem será ou não persistente
            producerMsg.setDeliveryMode(DeliveryMode.PERSISTENT);

            
            //Enviando em foma de mensagem
            String mensagem = "Solicitação enviada com sucesso";
            TextMessage message = session.createTextMessage(mensagem);
            System.out.println("Mensagem enviada: " + mensagem);
            producerMsg.send(message);

            // Enviando em forma de objeto
            Reserva reserva = new Reserva();
            reserva.setId(this.usuario.getId());
            reserva.setUsuario(usuario);
        	ObjectMessage objMessage = session.createObjectMessage(reserva);
            produtorReserva.send(objMessage);
            
            session.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Exception Occured");
            ex.printStackTrace();
        }
    }
}


