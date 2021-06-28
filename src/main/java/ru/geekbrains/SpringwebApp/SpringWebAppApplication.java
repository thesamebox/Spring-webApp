package ru.geekbrains.SpringwebApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.SpringwebApp.model.entity.Product;
import ru.geekbrains.SpringwebApp.model.repository.ProductDao;

@SpringBootApplication
public class SpringWebAppApplication implements CommandLineRunner {
	private final ProductDao pd;

	public SpringWebAppApplication(ProductDao pd) {
		this.pd = pd;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWebAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final Product pr = pd.findById(2);
		System.out.println("");

	}
}
