package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    
    public static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1, new String("Ordinateur Portable"), 350, 200));
        products.add(new Product(2, new String("Aspirateur robot"), 500, 150));
        products.add(new Product(3, new String("Table de Ping Pong"), 750, 350));
    }
    
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Product save(Product product) {
        if (product.getNom() != null) {
            products.add(product);
            return product;
        }

        return null;
    }

}
