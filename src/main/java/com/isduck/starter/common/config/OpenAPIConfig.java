package com.isduck.starter.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Contact contact = new Contact().name("daeun-ryu")
                .url("https://github.com/eunduck/spring-starter")
                .email("aisduck20@gmail.com");

        Info info = new Info()
                .version(appVersion)
                .title("Sample Rest API")
                .description("Restful API")
                .termsOfService("https://swagger.io/terms/")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .components(new Components()
                        .addSchemas("Response200", getSchema("처리 성공 관련 메시지", 200))
                        .addSchemas("Response400",
                                getSchema("BAD_REQUEST(잘못된 요청) 관련 오류 코드", "BAD_REQUEST(잘못된 요청) 관련 오류 메시지", 400))
                        .addSchemas("Response404",
                                getSchema("NOT_FOUND(존재하지 않는 리소스) 관련 오류 코드", "NOT_FOUND(존재하지 않는 리소스) 관련 오류 메시지", 404))
                );
    }

    private Schema getSchema(String message, int status) {
        return new Schema<Map<String, Object>>()
                .addProperty("message", new StringSchema().example(message))
                .addProperty("status", new IntegerSchema().example(status));
    }

    private Schema getSchema(String code, String message, int status) {
        return new Schema<Map<String, Object>>()
                .addProperty("code", new StringSchema().example(code))
                .addProperty("message", new StringSchema().example(message))
                .addProperty("status", new IntegerSchema().example(status));
    }

}
