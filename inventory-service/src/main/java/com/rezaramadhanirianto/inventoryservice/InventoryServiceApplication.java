package com.rezaramadhanirianto.inventoryservice;

import com.rezaramadhanirianto.inventoryservice.model.Inventory;
import com.rezaramadhanirianto.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("ipad_10");
			inventory.setQuantiy(100);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("ipad_11");
			inventory1.setQuantiy(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
