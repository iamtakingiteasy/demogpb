package org.dclou.example.demogpb.customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringRestDataConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Customer.class);
	}
}


//	@Bean
//	public RepositoryRestConfigurer repositoryRestConfigurer() {
//
//		return new RepositoryRestConfigurerAdapter() {
//			@Override
//			public void configureRepositoryRestConfiguration(
//					RepositoryRestConfiguration config) {
//				config.exposeIdsFor(Customer.class, Class2.class);
//			}
//		}
//
//	}