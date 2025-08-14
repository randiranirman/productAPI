package org.moviecrudspring.productapi.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI().info(new Info().title("product service API ").description("This is the rest api for the product " +
                "service").license( new License().name("Apace 2.0")));
    }


}
