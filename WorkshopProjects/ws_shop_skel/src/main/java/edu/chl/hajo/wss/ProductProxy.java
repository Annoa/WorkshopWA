package edu.chl.hajo.wss;


import edu.chl.hajo.shop.core.Product;

/**
 * Need this because translation from XML to JSON
 * @author hajo
 */

public class ProductProxy {

    // The wrapped product
    private Product product;

    protected ProductProxy() { // Must have
    }
   
    public ProductProxy(Product product) { 
        this.product = product; 
    }
    
   
    public String getName() {
        return product.getName();
    }

   
    public Long getId() {
        return product.getId();
    }

   
    public double getPrice() {
        return product.getPrice();
    }
}
