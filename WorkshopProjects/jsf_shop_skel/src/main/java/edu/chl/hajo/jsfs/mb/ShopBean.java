/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.mb;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.ShopFactory;i
import javax.inject.Singleton;

/**
 * Wrapper for shop
 *
 * @author hajo
 */
@Singleton
public class ShopBean {

    private Shop shop;
    private static ShopBean instance;

    private ShopBean() {
        this.shop = Shop.INSTANCE;
    }

    public static ShopBean getInstance() {
        if (instance == null) {
            instance = new ShopBean();
        }
        return instance;
    }

    public IProductCatalogue getProductCatalogue() {
        return this.shop.getProductCatalogue();
    }
}

enum Shop {

    INSTANCE;
    private final IShop s;

    private Shop() {
        s = ShopFactory.getShop(true);
    }

    public IProductCatalogue getProductCatalogue() {
        return s.getProductCatalogue();
    }
}
