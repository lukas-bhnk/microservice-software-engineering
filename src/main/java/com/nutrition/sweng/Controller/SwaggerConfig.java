/*
package com.nutrition.sweng.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nutrition.sweng")) //Package-name for selection
                .paths(regex("/rest/meal.*")) //Path-name for selection. Swagger describes just the RestController, because the paths are starting with /rest/
                .paths(regex("/rest/food.*"))
                .paths(regex("/rest/nutritionalValues.*"))
                .paths(regex("/rest/minerals.*"))
                .paths(regex("/rest/vitamins.*"))
                .build();
    }

}
*/
