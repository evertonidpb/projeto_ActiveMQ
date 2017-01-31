package br.edu.recebe.model;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.edu.envia.model.*;
import br.edu.recebe.service.ReservaService;

public class Consumer implements Runnable {

	@Autowired
    ReservaService service;

   

	@Override
    public void run() {
     
    	try {
            ActiveMQConnectionFactory factory = 
            new ActiveMQConnectionFactory("tcp://localhost:61616");
            //Cria e inicializa a conexão com ActiveMQ
            Connection connection = factory.createConnection();
            connection.start();
            
            // Cria a sessão
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            //Cria a fila e informa qual o destinatário (Esse nome tem que ser o mesmo do destinatario na classe JavaProducer)
            Destination msg = session.createQueue("Mensagem");            
            Destination rsv = session.createQueue("Reserva");  
            
            //Consumindo mensagem presente no ActiveMq
            MessageConsumer consumerMsg = session.createConsumer(msg);
            Message message = consumerMsg.receive();
            
            //Consumindo objeto presente no ActiveMq
            MessageConsumer consumerRsv = session.createConsumer(rsv);
            Message objeto = consumerRsv.receive();
            
        	System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
            if(objeto instanceof ObjectMessage){
            	ObjectMessage obsMsg = (ObjectMessage) objeto;
            	br.edu.envia.model.Reserva reserva = (br.edu.envia.model.Reserva) obsMsg.getObject();
            	
            	//br.edu.envia.model.Usuario usuario = (br.edu.envia.model.Usuario) obsMsg.getObject();
               
            	
                int id = reserva.getId();
                int id_user = reserva.getUsuario().getId();
                String nome = reserva.getUsuario().getNome();
                String cpf = reserva.getUsuario().getCpf();
                Reserva r = new Reserva();
                r.setId(id_user);
                Usuario usuario = new Usuario();
                usuario.setId(id_user);
                usuario.setNome(nome);
                usuario.setCpf(cpf);
                r.setUsuario(usuario);
           
         
            	ConverteArquivo converte = new ConverteArquivo();
            	converte.ConverteParaArquivo(r, "arquivo.txt");
                
                
                System.out.println("Objeto Recebido: ok ");
             
            }
            
            
            
            if (message instanceof TextMessage) {
                
                TextMessage textMessage = (TextMessage) message;
                  String text = textMessage.getText();
                 System.out.println("Consumer Received: " + text);
              } 
          
        
            session.close();
            connection.close();
            
    	}
        catch (Exception ex) {
            System.out.println("Exception Occured");
            ex.printStackTrace();
        }
    }
}
