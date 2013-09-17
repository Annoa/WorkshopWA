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
@Named("delProduct")
@RequestScoped
public class DelProductBB {
    
    private Product product;
    private Shop shop = Shop.INSTANCE;
    
    public void setSelected(Long id){
        this.product = shop.getProductCatalogue().find(id);
    }
    
    public String delete() {
        shop.getProductCatalogue().remove(product.getId());
        return Navigation.PRODUCT_SUCCESS.toString();  // TODO
    }
    
    public String getName() {
        return product.getName();
    }
    public String getId() {
        return product.getId().toString();
    }
    public String getPrice() {
        return ""+product.getPrice();
    }
    
}
