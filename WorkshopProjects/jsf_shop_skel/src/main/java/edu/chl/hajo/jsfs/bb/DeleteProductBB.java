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
@Named("deleteProduct")
@ConversationScoped
public class DeleteProductBB extends ConversationalBase {
    
    @Override
    protected void execute() {
        super.getProductCatalogue().remove(super.getId());
    }
    
}
