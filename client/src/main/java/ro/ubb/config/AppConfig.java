package ro.ubb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ui.Console;

@Configuration
public class AppConfig {

    @Bean
    Console console(){
        return new Console();
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
