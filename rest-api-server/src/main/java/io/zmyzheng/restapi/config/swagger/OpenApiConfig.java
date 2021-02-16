package io.zmyzheng.restapi.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-18 17:24
 * @Version 3.0.0
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption) {
        return new OpenAPI()
                .info(new Info()
                        .title("rest-api-server")
                        .version("")
                        .description("")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}
