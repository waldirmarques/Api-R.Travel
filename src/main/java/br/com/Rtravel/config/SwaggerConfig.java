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
		apiInfoBuilder.description(this.descricao());
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para busca de informações de ônibus.");
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
	
	private String descricao() {
		
		return "Bem vindo à Api-Rtravel!\n\n"
				+ "Esta versão pode entregar dados puros em formatos JSON, e tenta se aderir ao máximo à arquitetura REST.\n" 
				+ "Nesta página você pode conhecer e experimentar as URLs de acesso aos dados, os parâmetros de query string\n"
				+ "que podem ser aplicados para filtrar e selecionar resultados, e as estruturas de dados que são retornadas.\n"
				+ "Por padrão, todos os serviços de listagens retornam 15 itens, e o limite por requisição é de 100 itens.\n"
				+ "ATENÇÃO: Esta versão ainda está incompleta, sujeita a mudanças. Caso você encontre problemas ou queira dar\n"
				+ "sugestões, por favor entre em contato.";
	}
	
}
