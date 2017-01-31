package br.edu.recebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class RecebeApplication extends SpringBootServletInitializer  {

	
	@RequestMapping("/recebe")
    public String home() {
        return "Bem vindo a aplicação B que consome as mensagens do ActiveMQ";
    }

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(RecebeApplication.class);
}
	
	
	
	public static void main(String[] args) {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		SpringApplication.run(RecebeApplication.class, args);
	}
}
