package com.qc.information.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Author: czt
 * @Date: 18-12-13 上午10:49
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private ServletContext servletContext;

    @Bean
    public Docket docketBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("测试demo"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)
                .build();
    }


    @Bean
    public Docket securityApi() {
        return docket(
                "index",
                "测试项目API",
                "com.*"
        );
    }

    @Bean
    SecurityContext securityContext() {
        SecurityReference securityReference = SecurityReference.builder()
                .reference("oauth")
                .scopes(scopes().toArray(new AuthorizationScope[0]))
                .build();

        return SecurityContext.builder()
                .securityReferences(newArrayList(securityReference))
                .forPaths(regex("/(?!(?:swagger|v1.0/api-docs)).*"))
                .build();
    }

    private List<AuthorizationScope> scopes() {
        return newArrayList(
                new AuthorizationScope("Authorized User", "仅授权后才能调用本服务的API"));
    }


    private Docket docket(String groupName, String title, String pathRegex) {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("登录验证token").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .securitySchemes(Collections.singletonList(oauth()))
                .securityContexts(Collections.singletonList(securityContext()))
                .ignoredParameterTypes(ApiIgnore.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(pathRegex))
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("oauth")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    private List<GrantType> grantTypes() {
        GrantType grantType = new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpoint(servletContext.getContextPath() + "/swagger-login.html"))
                .build();
        return newArrayList(grantType);
    }
}
