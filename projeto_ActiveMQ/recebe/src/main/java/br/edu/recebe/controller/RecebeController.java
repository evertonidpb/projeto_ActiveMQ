package br.edu.recebe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.recebe.model.Consumer;
import br.edu.recebe.model.ConverteArquivo;
import br.edu.recebe.model.EnviaEmail;
import br.edu.recebe.model.Reserva;
import br.edu.recebe.model.Usuario;
import br.edu.recebe.service.*;


@Controller
public class RecebeController {

@Autowired
ReservaService service;
	    		

/* 
Esse método abaixo realiza a confirmação da reserva, recebe os objetos do ActiveMQ, 
e envia um e-mail com os dados da reserva para login: avaliacao1197@gmail.com 
	                                           senha: unipe@2017
*/
	
	   @RequestMapping(value = "/recebe/agenda/confirma",method = RequestMethod.GET)
		public ResponseEntity<String> confirma_reserva() {
			System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
			Consumer consumer = new Consumer();
			Thread consumerThread = new Thread(consumer);
			consumerThread.start();
			ConverteArquivo converte = new ConverteArquivo();
			Reserva reservando = converte.ConverteParaObjeto("arquivo.txt");
			service.save(reservando);
			EnviaEmail email = new EnviaEmail();
			email.enviar(reservando);
			return new ResponseEntity<String>("Reserva confirmada, um email de confirmação foi enviado para avaliacao1197@gmail.com", HttpStatus.OK );

		
			     }
		
	   
	   // Retorna uma lista de reservas que já foram confirmadas
	   @RequestMapping(value="/recebe/reserva", method = RequestMethod.GET)
		public ResponseEntity< List<Reserva> > listAllReservas() {
			return new ResponseEntity< List<Reserva> >
			(service.listAllReservas(), HttpStatus.OK);
		}

	   	   
	   
 /*...........  Os métodos abaixo não foram pedidos, coloquei  apenas para deixar o Rest com um CRUD completo ........... */
	   
	    //Método para  gravar uma reserva de maneira direta (sem passar pelo formulario, usando um cliente Rest para enviar os daos)
		@RequestMapping(value="/recebe/reserva", method = RequestMethod.POST)
		public ResponseEntity<String> createCliente(@RequestBody Reserva reserva) {

			try {
				 service.save(reserva);
				return new ResponseEntity<String>(HttpStatus.CREATED);

			 } catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		

		//Método para atualizar os valores de uma reserva (precisa ser enviado via json usando um cliente Rest)
		@RequestMapping(value = "recebe/reserva/{id}", method = RequestMethod.PUT)
		public ResponseEntity<String> putCliente(@PathVariable String id, @RequestBody Reserva reserva){

			try {
				service.save(reserva);
				return new ResponseEntity<String>(HttpStatus.OK);

			 } catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}


		
	    //Método para obter uma reserva específica
		 @RequestMapping(value = "recebe/reserva/{id}",method = RequestMethod.GET)
			public ResponseEntity<Reserva> getReserva(@PathVariable int id) {
				
				Reserva reserva = service.getById(id);
				
				return reserva == null ? 
						new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND) : 
							new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
			}


   
		//Método para apagar reserva
		 @RequestMapping(value= "recebe/reserva/{id}", method=RequestMethod.DELETE)
		  public ResponseEntity<String> delUsuario(@PathVariable int id, @RequestBody Reserva reserva) {

			 try {
					service.delete(reserva);
					return new ResponseEntity<String>(HttpStatus.OK);

				 } catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			 
		  }

		 
		 
		 
		 
}

		
