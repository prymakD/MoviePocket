package com.moviePocket.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moviePocket"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                /*.additionalModels(typeResolver.resolve(User.class, Role.class, Movie.class))*/;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("MoviePocket API")
                .description("MoviePocket API reference for developers")
                .contact("javainuse@gmail.com").license("MIT License")
                .licenseUrl("https://opensource.org/license/mit/").version("1.0").build();
    }

}
