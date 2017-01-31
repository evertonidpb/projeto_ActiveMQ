package br.edu.envia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class EnviaApplication extends SpringBootServletInitializer {

	 @RequestMapping("/envia")
	    public String home() {
	        return "Bem Vindo à aplicação Envia (aplicação A) cuja função é cadastrar usuarios"
	        		+ " e fazer uma reserva enviando os dados de um usuario especifico para"
	        		+ " o ActiveMq";
	    }

	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EnviaApplication.class);
    }
	
	
	public static void main (String[] args) {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		SpringApplication.run(EnviaApplication.class, args);
	}
}
