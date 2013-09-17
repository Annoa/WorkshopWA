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
    private String name;
    private String price;
    private String id;
    private Shop shop = Shop.INSTANCE;

    public void setSelected(Long id) {
        this.product = shop.getProductCatalogue().find(id);
        this.id = product.getId().toString();
        this.name = product.getName();
        this.price = "" + product.getPrice();
    }

    public String delete() {
        shop.getProductCatalogue().remove(product.getId());
        return Navigation.PRODUCT_SUCCESS.toString();  // TODO
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
