package gcu.backend.dreank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "gcu.backend.dreank.domain")
public class DreankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreankApplication.class, args);
    }

}
