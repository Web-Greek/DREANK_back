package gcu.backend.dreank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DreankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreankApplication.class, args);
    }

}
