package org.dclou.example.demogpb.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import javax.annotation.PostConstruct;

@SpringCloudApplication
public class CatalogApplication {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogApplication(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@PostConstruct
	public void generateTestData() {
		itemRepository.save(new Item("iPod", 42.0));
		itemRepository.save(new Item("iPod touch", 21.0));
		itemRepository.save(new Item("iPod nano", 1.0));
		itemRepository.save(new Item("Apple TV", 100.0));
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
}
