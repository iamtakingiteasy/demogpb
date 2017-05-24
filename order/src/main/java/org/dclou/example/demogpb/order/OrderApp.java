package org.dclou.example.demogpb.order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;


@SpringCloudApplication
@RibbonClient("order")
@EnableOAuth2Sso
@EnableZuulProxy
public class OrderApp extends WebSecurityConfigurerAdapter {

//	@Configuration
//	@EnableResourceServer
//	public static class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {
//
//		/*
//		http.antMatcher("/api/**").authorizeRequests()
//			.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//			.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//			.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
//			.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
//		 */
////		@Override
////		public void configure(HttpSecurity http) throws Exception {
////			http.antMatcher("/**")
////					.authorizeRequests()
////					.antMatchers("/", "/login**","/auth-service/**", "/webjars/**")
////					.permitAll()
////					.anyRequest()
////					.authenticated()
////					.and()
////					.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
////		}
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//					.and()
//					.csrf().disable()
//					.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), AbstractPreAuthenticatedProcessingFilter.class)
//					.addFilterBefore(new BasicAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)
//					.authorizeRequests()
//					.antMatchers("/admin/**").hasRole("ADMIN")
//					.antMatchers("/owner/**").hasAnyRole("OWNER", "ADMIN")
//					.antMatchers("/health", "invitation/accept").permitAll()
//					.antMatchers("/**").hasRole("USER");
//		}
//	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.logout().and().antMatcher("/**").authorizeRequests()
				.antMatchers("/login**", "/auth-service/**", "/auth/**", "/webjars/**").permitAll()
				.anyRequest().authenticated().and().csrf().disable();
	}


	@Configuration
	public  class ClientConfiguration {

		@Bean
		@ConfigurationProperties("security.oauth2.client")
		ClientCredentialsResourceDetails clientDetails() {
			return new ClientCredentialsResourceDetails();
		}

		@Bean
		RestTemplate restTemplate(OAuth2ClientContext context,
		                          ClientCredentialsResourceDetails client) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			mapper.registerModule(new Jackson2HalModule());

			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
			converter.setObjectMapper(mapper);
			//OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, oauth2Context);
			final RestTemplate restTemplate = new OAuth2RestTemplate(client, context);
			restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>> singletonList(converter));
			return restTemplate;
		}

	}

//	@Configuration
//	public class WorkaroundConfig extends WebMvcConfigurerAdapter {
//
//		@Autowired
//		//@Qualifier("tokenRelayRequestInterceptor")
//		HandlerInterceptor handlerInterceptor;
//
//		@Override
//		public void addInterceptors (InterceptorRegistry registry) {
//			registry.addInterceptor(handlerInterceptor);
//		}
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}

}
