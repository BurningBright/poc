package cn.burningbright.poc;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true")
@EnableSwagger2
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfiguration {
}