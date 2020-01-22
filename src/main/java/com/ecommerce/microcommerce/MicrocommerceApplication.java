package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MicrocommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MicrocommerceApplication.class, args);

		Product s = new Product();
		s.setNom("test");
		s.setPrix(234);
		s.setPrixAchat(124);
		run.getBean(ProductDao.class).save(s);
	}

}
