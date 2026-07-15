package ec.edu.ucacue.smartgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; 
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients 
@EntityScan(basePackages = "ec.edu.ucacue.smartgym.entity") 
public class SmartgymReservasServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartgymReservasServiceApplication.class, args);
    }
   
}
