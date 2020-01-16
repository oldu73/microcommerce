package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);

    List<Product> findByPrixGreaterThan(int prixLimit);

    List<Product> findByNomLike(String recherche);

    //Java Persistence Query Language (JPQL)
//    @Query("SELECT NEW com.ecommerce.microcommerce.model.Product(p.id, p.nom, p.prix, p.prixAchat) FROM Product p WHERE p.prix > :prixLimit")
    @Query("SELECT p.id, p.nom, p.prix FROM Product p WHERE p.prix > :prixLimit")
    List<Product> chercherUnProduitCher(@Param("prixLimit") int prix);
}
