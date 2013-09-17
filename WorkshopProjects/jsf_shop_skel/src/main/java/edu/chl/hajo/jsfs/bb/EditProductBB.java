/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.jsfs.mb.Shop;
import edu.chl.hajo.shop.core.Product;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Gabriel
 */
@Named("editProduct")
@RequestScoped
public class EditProductBB {
    
    private Product product;
    private String name;
    private String price;
    private String id;
    private Shop shop = Shop.INSTANCE;
    
    public void setSelected(Long id){
        this.product = shop.getProductCatalogue().find(id);
        this.id = product.getId().toString();
        this.name = product.getName();
        this.price = ""+product.getPrice();
    }
    
    public String update() {
        
        shop.getProductCatalogue().add(new Product(product.getId(), name, Double.parseDouble(price)));
        //TODO: update product with new name and price. 
        //shop.getProductCatalogue().update(product);
        return Navigation.PRODUCT_SUCCESS.toString(); 
    }
    
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getPrice() {
        return price;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setPrice(String price){
        this.price = price;
    }

}
