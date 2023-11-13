package com.microcommerce.productservice;

import com.github.javafaker.Faker;
import com.microcommerce.productservice.data.entity.Product;
import com.microcommerce.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootApplication
@Slf4j
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			productRepository.deleteAll();

			Faker faker = new Faker();
			var testProducts = new ArrayList<Product>();

			for (int i = 0; i < 50; i++) {
				var name = faker.commerce().productName();

				testProducts.add(Product.builder()
						.skuCode(name.toLowerCase().replace(" ","_"))
						.description(faker.lorem().paragraph(2))
						.name(name)
						.price(new BigDecimal(faker.commerce().price()))
						.stock(faker.number().numberBetween(1, 500))
						.isDeleted(faker.bool().bool())
						.imageUrl(faker.internet().image())
						.build());
			}

			productRepository.saveAll(testProducts);
		};
	}
}
