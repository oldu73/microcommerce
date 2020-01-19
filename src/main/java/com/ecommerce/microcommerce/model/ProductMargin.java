package com.ecommerce.microcommerce.model;


public class ProductMargin {

    private Product product;
    private int margin;

    public ProductMargin() {
    }

    public ProductMargin(Product product, int margin) {
        this.product = product;
        this.margin = margin;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "ProductMargin{" +
                "product=" + product +
                ", margin=" + margin +
                '}';
    }

}
