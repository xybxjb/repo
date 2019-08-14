package cn.deepcoding.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2 //Loads the spring beans required by the framework
@EnableSwaggerBootstrapUI
public class MySwaggerConfig {
	
	
	 @Bean
	 public Docket createRestApi() {

		 return new Docket(DocumentationType.SWAGGER_2)
	
		 .apiInfo(apiInfo())
	
		 .select()
	
		 .apis(RequestHandlerSelectors.basePackage("cn.deepcoding.controller.app"))
	
		 .paths(PathSelectors.any())
	
		 .build();
	
		 }
	 private ApiInfo apiInfo() {

		 return new ApiInfoBuilder()
	
		 .title("APP接口api")
	
		 .description("app接口")
	
		 .termsOfServiceUrl("http://localhost:8080/")
	
		 .contact("")
	
		 .version("1.0")
	
		 .build();

	 }
}
