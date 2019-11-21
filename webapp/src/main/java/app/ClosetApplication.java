package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import infra.ClosetConfiguration;

@SpringBootApplication
@Import(ClosetConfiguration.class)
public class ClosetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClosetApplication.class, args);
	}
}
