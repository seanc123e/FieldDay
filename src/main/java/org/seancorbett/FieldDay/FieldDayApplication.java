package org.seancorbett.FieldDay;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(basePackages = "org.seancorbett.FieldDay")
public class FieldDayApplication {

	public static void main(String[] args) {

		SpringApplication.run(FieldDayApplication.class, args);
	}

}
