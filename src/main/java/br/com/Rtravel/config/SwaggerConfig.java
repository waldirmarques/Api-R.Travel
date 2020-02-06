package br.com.Rtravel.config;

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
	public Docket detalheApi() {
 
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
 
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.Rtravel")) //Coloca o pacote onde tem todas class java
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
 
		return docket;
	}
 
	private ApiInfoBuilder informacoesApi() {
 
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
 
		apiInfoBuilder.title("Api-Rtravel");
		apiInfoBuilder.description("Api que irá disponibilizar um ambiente de consulta de horários"
				+ " de linhas de ônibus, disponibilizando  opções de compartilhamento e informações"
				+ " por meio dos usuários.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para busca de informações.");
		apiInfoBuilder.license("Licença - Open Source");
		apiInfoBuilder.licenseUrl("http://www.rtravel.com.br");
		apiInfoBuilder.contact(this.contato());
 
		return apiInfoBuilder;
 
	}
	private Contact contato() {
 
		return new Contact(
				"Suporte Rtravel",
				"http://www.rtravel.com.br", 
				"suporte.rtravel@gmail.com");
	}
}
