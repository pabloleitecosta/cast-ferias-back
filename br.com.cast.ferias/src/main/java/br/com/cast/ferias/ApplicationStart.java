package br.com.cast.ferias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.cast.ferias.Config.Property.CastProperty;

@SpringBootApplication
@EnableConfigurationProperties(CastProperty.class)
public class ApplicationStart {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

}
