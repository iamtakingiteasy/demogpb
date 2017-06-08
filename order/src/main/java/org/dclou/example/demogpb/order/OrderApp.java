package org.dclou.example.demogpb.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableHystrixDashboard
@EnableCircuitBreaker
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

