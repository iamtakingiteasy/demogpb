package org.dclou.example.demogpb.order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class OrderTestApp {

	@Bean
	RestTemplate restTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		converter.setObjectMapper(mapper);
		//OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, oauth2Context);
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>> singletonList(converter));
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OrderTestApp.class);
		app.setAdditionalProfiles("test");
		app.run(args);
	}

}
