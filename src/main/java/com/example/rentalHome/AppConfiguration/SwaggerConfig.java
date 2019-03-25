package com.example.rentalHome.AppConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	    @Bean
	    public Docket swaggerRentalApi10() 
	    {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		           .groupName("User-api-1.0")
	                           .select()
	                           .apis(RequestHandlerSelectors
							   .basePackage("com.example.rentalHome.Controllers"))
	                           .paths(PathSelectors.regex("/user/.*/.*/v1.0"))
	                           .build()
	                           .pathMapping("/")
	                           .apiInfo(new ApiInfoBuilder()
									   .version("1.0").title("UserRecord API")
							           .description("Demonstrates a simple RESTful web service using Spring Boot and Java." +
											   "This web service provides an in-memory UserRecord service," +
											   " with the capability to get a all User information in the System, " +
											   "create user info,update user info, delete user info,user can be singIn in system," +
											   "and logIn in system. After exploring this project, We will understand the three common layers of a RESTful web service (namely domain, data source, and presentation)," +
											   " the common design decisions used when creating a web service, and the functionality provided by the Spring framework for creating a web service (as well as supplemental functionality, such as creating HATEOAS links). " +
											   "In particular,We can aslo create OpenAPI Documantion with Swagger links).And expose the following API Endpoints.")
									   .version("v1.0")
									   .termsOfServiceUrl("http://terms-of-services.url")
									   .license("https://github.com/SanketJaiswal24/SpringBootRestAPI | Sanket.Jaiswal2409@gmail.com")
									   .licenseUrl("https://github.com/SanketJaiswal24/SpringBootRestAPI")
									   .build());
	    }
	    
	  @Bean
	  public Docket swaggerRentalApi11() 
	   { 
		  return new Docket(DocumentationType.SWAGGER_2) 
				    .groupName("User-api-1.1")
				    .select()
	                .apis(RequestHandlerSelectors
	                .basePackage("com.example.rentalHome.Controllers"))
	                .paths(PathSelectors.regex("/user/.*/.*/v1.1"))
	                .build()
	                .pathMapping("/") 
	                .apiInfo(new
	                 ApiInfoBuilder()
	                .version("1.1")
	                .title("Rental Home API").
	                 description("Documentation RentalHome API v1.1")
	                .build()); }
	  
	  
	  @Bean
      public Docket swaggerRentalApi12()
	  { 
		  return new Docket(DocumentationType.SWAGGER_2) 
				    .groupName("User-api-1.2")
				    .select()
	                .apis(RequestHandlerSelectors
	                .basePackage("com.example.rentalHome.Controllers"))
	                .paths(PathSelectors.regex("/user/.*/.*/v1.2"))
	                .build()
	                .pathMapping("/")
	                .apiInfo(new
	                 ApiInfoBuilder()
	                .version("1.0")
	                .title("Rental Home API").
	                 description("Documentation RentalHome API v1.2")
	                .build()); 
		  }

/*		private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("Person")
	            .description("Person API author Sanket")
	            .version("VERSION")
	            .termsOfServiceUrl("http://terms-of-services.url")
	            .license("LICENSE")
	            .licenseUrl("http://url-to-license.com")
	            .build();
	    }*/
}
