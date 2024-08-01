package br.com.roalmeida.tutorial_cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableCaching
public class TutorialCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialCacheApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(ProductService productService) {
		return args -> {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Id: 1: " + productService.getById(1L));
			System.out.println("Id: 2: " + productService.getById(2L));
			System.out.println("Id: 3: " + productService.getById(3L));
			System.out.println("Id: 4: " + productService.getById(4L));
			System.out.println("Id: 5: " + productService.getById(5L));
		};
	}
}


record Product (Long id, String name, String description) implements Serializable{
}

@Service
class ProductService {

	Map<Long, Product> products = new HashMap<>() {
		{
			put(1L, new Product(1L, "Product 1", "Description 1"));
			put(2L, new Product(2L, "Product 2", "Description 2"));
			put(3L, new Product(3L, "Product 3", "Description 3"));
			put(4L, new Product(4L, "Product 4", "Description 4"));
			put(5L, new Product(5L, "Product 5", "Description 5"));
			put(1L, new Product(1L, "Product 1", "Description 1"));
			put(2L, new Product(2L, "Product 2", "Description 2"));
			put(3L, new Product(3L, "Product 3", "Description 3"));
			put(4L, new Product(4L, "Product 4", "Description 4"));
			put(5L, new Product(5L, "Product 5", "Description 5"));
		}
	};

	@Cacheable("products")
	Product getById(Long id) {
		System.out.println("Buscando produtos...");
		simulateLatency();
		return products.get(id);
	}
	private void simulateLatency() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}