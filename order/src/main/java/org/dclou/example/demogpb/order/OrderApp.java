package org.dclou.example.demogpb.order;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.RetryLoadBalancerInterceptor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringCloudApplication
@EnableZuulProxy
@RibbonClient("order")
public class OrderApp  {

	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}


}
























//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.logout().and().antMatcher("/**").authorizeRequests()
//				.antMatchers("/login**", "/auth-service/**", "/auth/**", "/webjars/**").permitAll()
//				.anyRequest().authenticated().and().csrf().disable();
//	}

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

