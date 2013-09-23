/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.bb;

import edu.chl.hajo.shop.core.Product;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author anno
 */
@Named("editProduct")
@ConversationScoped
public class EditProductBB extends ConversationalBase {
    
    @Override
    protected void execute() {
        Product p = new Product(super.getId(), super.getName(), Double.parseDouble(super.getPrice()));
        super.getProductCatalogue().update(p);
    }
    
}
