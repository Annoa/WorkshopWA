/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.mb;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.ShopFactory;


/**
 * Wrapper for shop
 * @author hajo
 */


public enum Shop {
    INSTANCE;
    
    private final IShop s;

    private Shop() {
        s = ShopFactory.getShop(true);
    }

    public IProductCatalogue getProductCatalogue() {
        return s.getProductCatalogue();
    }
}
