package br.edu.envia.controller;

import java.util.List;

import javax.validation.Valid;

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
import br.edu.envia.model.PreparaEnvio;
import br.edu.envia.model.Usuario;
import br.edu.envia.service.UsuarioService;

@Controller
public class EnviaController {


	
	@Autowired
	private UsuarioService UsuarioService;
   
  
    
	// Acessa página de cadastro de usuários e mostra a lista de todos os usuários já cadastrados
	@GetMapping("/envia/usuario/cadastro")
	public ModelAndView listar() {
	ModelAndView modelAndView = new ModelAndView("cadastro_usuario");
	modelAndView.addObject("usuarios", UsuarioService.listAllUsuarios());
	return modelAndView;
	}


  
    //Faz a gravação de um usuário
	@RequestMapping(value="/envia/usuario/cadastro", method = RequestMethod.POST)
	public ResponseEntity<String> createUsuario(@Valid Usuario usuario) {

		try {
			UsuarioService.save(usuario);
			return new ResponseEntity<String>("Usuário criado com sucesso"
					+ ""
					+ "<a href='/envia/usuario/cadastro'> Ver usuários criados  </a>"
					+ "",HttpStatus.CREATED);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Erro ao gravar usuário", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public UsuarioService getUsuarioUsuarioService() {
		return UsuarioService;
	}
	
	
	
	//Retorna um usuário específico pelo o seu id e realiza o envio dos dados desse usuário para o ActiveMQ
	@RequestMapping(value = "/envia/agenda/reserva/{id}",method = RequestMethod.GET)
	public ResponseEntity<String> getUsuario(@PathVariable int id) {
		
		Usuario usuario = UsuarioService.getById(id);
		
		if(usuario == null) {
			
			
			 
			return new ResponseEntity<String>("Não há usuários cadastrados!", HttpStatus.NOT_FOUND);
		}
		
		else {
		  
				PreparaEnvio envio = new PreparaEnvio(usuario);
				Thread producerThread = new Thread(envio);
				producerThread.start();
			return new ResponseEntity<String>("Solicitação de reserva realizada (mensagem enviada para o ActiveMq...)  <a href='/envia/usuario/cadastro'> Voltar para lista de usuários  </a>", HttpStatus.OK );

	
		     }
	}

	
		
	
	 /*........................ Métodos genéricos apenas para deixar o Rest com um CRUD completo ..........................*/
	  

	
	//Método para atualizar valores de Usuario
	@RequestMapping(value = "envia/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> putUsuario(@PathVariable int id, @RequestBody Usuario Usuario){

		try {
			UsuarioService.save(Usuario);
			return new ResponseEntity<String>(HttpStatus.OK);

		 } catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	//Método para apagar usuario

	 @RequestMapping(value= "envia/usuario/{id}", method=RequestMethod.DELETE)
	  public ResponseEntity<String> delUsuario(@PathVariable int id, @RequestBody Usuario Usuario) {

		 try {
				UsuarioService.delete(Usuario);
				return new ResponseEntity<String>(HttpStatus.OK);

			 } catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		 
	  }

	
	
	
}
