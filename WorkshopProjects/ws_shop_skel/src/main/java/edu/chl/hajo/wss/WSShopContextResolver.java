/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.wss;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * This is used to remove '@'s from attribute names when serializing to JSON
 * and also produce an array of objects (if list returned)
 * The default serialization is "mapped" (badgerfish) here changed to "natural", see code 
 * 
 * Need dependency on jersey-json see pom. 
 * 
 * @author hajo
 */
@Provider
public class WSShopContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {ProductProxy.class};

    public WSShopContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.natural().build(),
                types);
    } 

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return (types[0].equals(objectType)) ? context : null;
    }
}
