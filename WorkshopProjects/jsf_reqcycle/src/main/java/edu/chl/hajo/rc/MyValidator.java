/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.hajo.rc;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * A custom validator (there are some default) Possible to
 * add to editableValueHolder's
 * @author hajo
 */
@FacesValidator(value = "myValidator")
public class MyValidator implements Validator{

    public MyValidator(){
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Some code to validate here
        LogUtil.info("*** Validating" , LogUtil.RED);
    }

}
