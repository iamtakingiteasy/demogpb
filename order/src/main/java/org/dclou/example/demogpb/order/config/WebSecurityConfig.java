//package org.dclou.example.demogpb.order.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.NestedConfigurationProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.filter.CompositeFilter;
//
//import javax.servlet.Filter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by msnikitin on 28.04.2017.
// */
////@Configuration
////@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	OAuth2ClientContext oauth2ClientContext;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**")
//				.authorizeRequests()
//				.antMatchers("/", "/login**", "/webjars/**")
//				.permitAll()
//				.anyRequest()
//				.authenticated()
//				.and().logout().logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))).permitAll()
//				.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//	}
//
//	private Filter ssoFilter() {
//		CompositeFilter filter = new CompositeFilter();
//		List<Filter> filters = new ArrayList<>();
//
//		filters.add(ssoFilter(megaclient(), "/login/megaclient", true));
//		filters.add(ssoFilter(megaclient2(), "/login/megaclient2", false));
//
//		filter.setFilters(filters);
//		return filter;
//	}
//
//	private Filter ssoFilter(WebSecurityConfig.ClientResources client, String path, boolean usePasswordGrant) {
//		OAuth2ClientAuthenticationProcessingFilter filter = usePasswordGrant ?
//		                                                    new OAuth2ClientAuthenticationProcessingFilter4PasswordGrant(path)
//		                                                                     : new OAuth2ClientAuthenticationProcessingFilter(path);
//
//		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
//		filter.setRestTemplate(template);
//
//		UserInfoTokenServices tokenServices = new UserInfoTokenServices(
//				client.getResource().getUserInfoUri(), client.getClient().getClientId());
//		tokenServices.setRestTemplate(template);
//
//		filter.setTokenServices(tokenServices);
//		return filter;
//	}
//
//	@Bean
//	@ConfigurationProperties("megaclient")
//	public WebSecurityConfig.ClientResources megaclient() {
//		return new WebSecurityConfig.ClientResourcesPasswordGrant();
//	}
//
//	@Bean
//	@ConfigurationProperties("megaclient")
//	public WebSecurityConfig.ClientResources megaclient2() {
//		return new WebSecurityConfig.ClientResourcesAuthCodeGrant();
//	}
//
//	@Bean
//	public OAuth2RestTemplate megaapiRestTemplate() {
//		OAuth2RestTemplate template = new OAuth2RestTemplate(megaclient().getClient(), oauth2ClientContext);
//		template.setRetryBadAccessTokens(false);
//		return template;
//	}
//
//	interface ClientResources {
//		BaseOAuth2ProtectedResourceDetails getClient();
//		ResourceServerProperties getResource();
//	}
//
//	class ClientResourcesPasswordGrant implements WebSecurityConfig.ClientResources {
//		@NestedConfigurationProperty
//		private ResourceServerProperties resource = new ResourceServerProperties();
//
//		@NestedConfigurationProperty
//		private ResourceOwnerPasswordResourceDetails client = new ResourceOwnerPasswordResourceDetails();
//
//		@Override
//		public ResourceOwnerPasswordResourceDetails getClient() {
//			return client;
//		}
//
//		@Override
//		public ResourceServerProperties getResource() {
//			return resource;
//		}
//	}
//
//	class ClientResourcesAuthCodeGrant implements WebSecurityConfig.ClientResources {
//		@NestedConfigurationProperty
//		private ResourceServerProperties resource = new ResourceServerProperties();
//
//		@NestedConfigurationProperty
//		private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
//
//		@Override
//		public AuthorizationCodeResourceDetails getClient() {
//			return client;
//		}
//
//		@Override
//		public ResourceServerProperties getResource() {
//			return resource;
//		}
//	}
//
//	public class OAuth2ClientAuthenticationProcessingFilter4PasswordGrant extends OAuth2ClientAuthenticationProcessingFilter {
//
//		public OAuth2ClientAuthenticationProcessingFilter4PasswordGrant(String defaultFilterProcessesUrl) {
//			super(defaultFilterProcessesUrl);
//
//			setAuthenticationSuccessHandler(new AuthenticationSuccessHandler4PasswordGrant());
//		}
//
//		@Override
//		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//				throws AuthenticationException, IOException, ServletException {
//			String username = request.getHeader("username");
//			String password = request.getHeader("password");
//			this.restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", username);
//			this.restTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", password);
//			return super.attemptAuthentication(request, response);
//		}
//
//		public class AuthenticationSuccessHandler4PasswordGrant extends SimpleUrlAuthenticationSuccessHandler {
//
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			                                    Authentication authentication) throws IOException, ServletException {
//				// This is actually not an error, but an OK message. It is sent to avoid redirects.
//				response.sendError(HttpServletResponse.SC_OK);
//			}
//		}
//	}
//
//
//}
