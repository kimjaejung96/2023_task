package com.tredlinx.task.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("tradlinx-v1")
                .pathsToMatch("/v1/**")
                .build();
    }
    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "jwtAuthorization";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer"));

        return new OpenAPI()
                .info(new Info()
                        .title("tradlinx Task API")
                        .description("API Info")
                        .version("v1"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
