package com.ecommerce.microcommerce.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @RequestMapping(value = "/Produits", method = RequestMethod.GET)
    public String listeProduits() {
        return "Un exemple de produit";
    }
}
