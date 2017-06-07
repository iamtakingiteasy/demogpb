package org.dclou.example.demogpb.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.annotation.PostConstruct;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
public class CatalogApp {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogApp(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@PostConstruct
	public void generateTestData() {
		itemRepository.save(new Item("iPod", 42.0));
		itemRepository.save(new Item("iPod touch", 21.0));
		itemRepository.save(new Item("iPod nano", 1.0));
		itemRepository.save(new Item("Apple TV", 100.0));
	}

	@Configuration
	@EnableResourceServer
	public static class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/**").hasAuthority("ROLE_READER");
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogApp.class, args);
	}

}
