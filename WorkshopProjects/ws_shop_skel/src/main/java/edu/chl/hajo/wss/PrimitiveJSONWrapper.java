/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.wss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used to wrap primitive types to be able to send
 * as XML or JSON
 * @author hajo
 */
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class PrimitiveJSONWrapper<T> {
    
    private T value; 
    
    protected PrimitiveJSONWrapper(){
    }
    
    PrimitiveJSONWrapper(T value){
        this.value = value;
    }
    
    @XmlElement(required = true)
    public T getValue(){
        return value;
    }
    
}
