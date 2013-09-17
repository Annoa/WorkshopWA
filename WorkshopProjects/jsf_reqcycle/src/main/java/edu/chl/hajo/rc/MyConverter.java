/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.rc;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converting in and outgoing values between pages (strings)
 * and java objects (there are a few default for primitive types)
 * @author hajo
 */
@FacesConverter(value = "myConverter")
public class MyConverter implements Converter {

    public MyConverter(){
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LogUtil.info("*** Converting to Object", LogUtil.RED);
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LogUtil.info("*** Converting to String", LogUtil.RED);
        return "";
    }
}
