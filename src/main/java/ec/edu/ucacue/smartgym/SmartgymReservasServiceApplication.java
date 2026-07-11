package ec.edu.ucacue.smartgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; 

@SpringBootApplication
@EntityScan(basePackages = "ec.edu.ucacue.smartgym.entity") 
public class SmartgymReservasServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartgymReservasServiceApplication.class, args);
    }
}