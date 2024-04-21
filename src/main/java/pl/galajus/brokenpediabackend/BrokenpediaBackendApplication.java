package pl.galajus.brokenpediabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class BrokenpediaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrokenpediaBackendApplication.class, args);
    }

}
