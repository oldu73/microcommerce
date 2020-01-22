package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.model.ProductMargin;
import com.ecommerce.microcommerce.web.exceptions.ProductNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Api(description = "API pour les opérations CRUD sur les produits.")
@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //Récupérer la liste des produits
//    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    @GetMapping(value = "/Produits")
    public List<Product> listeProduits() {
        return productDao.findAll();
    }

    //Ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Product> ajouterProduit(@Valid @RequestBody Product product) throws Exception {
        return new ResponseEntity<>(productDao.save(product), HttpStatus.OK);
    }

    @PutMapping (value = "/Produits")
    public void updateProduit(@RequestBody Product product) {
        productDao.save(product);
    }

    //Récupérer un produit par son Id
    @ApiOperation(value = "Récupère un produit grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {

        Product product = productDao.findById(id);

        if (product == null) throw new ProductNotFoundException("Product with id " + id + " not found!");

        return product;
    }

    @DeleteMapping (value = "/Produits/{id}")
    public void supprimerProduit(@PathVariable int id) {
        productDao.deleteById(id);
    }

    @GetMapping(value = "test/prix/{prixLimit}")
    public List<Product> testeDeRequetes(@PathVariable int prixLimit) {

        //        return productDao.findByPrixGreaterThan(prixLimit);

        //Java Persistence Query Language (JPQL)
        return productDao.chercherUnProduitCher(prixLimit);
    }

    @GetMapping(value = "test/nom/{recherche}")
    public List<Product> testeDeRequetes(@PathVariable String recherche) {
        return productDao.findByNomLike("%"+recherche+"%");
    }

    @GetMapping(value = "/AdminProduits")
    public Map<String, Integer> calculerMargeProduit() {
        List<Product> productList = productDao.findAll();
        Map<String, Integer> stringIntegerHashMap;

        stringIntegerHashMap = productList.stream().collect(Collectors.toMap(Product::toString, p -> p.getPrix() - p.getPrixAchat()));

        return stringIntegerHashMap;
    }

    @GetMapping(value = "/Produits/trier")
    public List<Product>  trierProduitsParOrdreAlphabetique() {
        return productDao.findAllByOrderByNomAsc();
    }

}
