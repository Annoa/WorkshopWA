/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.rc;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
/*
 * 
 * 
 *     NOTE: package for RequestScoped !!!!!!!
 * 
 * 
 */

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 * Also a CDI managed bean.
 * This bean have the listener methods, like actionPerformed in Swing
 * Could combine data and listener bean
 * @author hajo
 */
@Named("MyListener")
@RequestScoped
public class MyListener {

    @PostConstruct
    public void postContruct() {
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }

    // Any method with this param works!
    public void valueChanged(ValueChangeEvent evt) {
        LogUtil.info("*** valueChanged", LogUtil.RED);
        LogUtil.info("(new) " + evt.getNewValue());
        LogUtil.info("(old) " + evt.getOldValue());
    }

    // Any method with this param works!
    public void buttonClicked(ActionEvent evt) {
        LogUtil.info("*** buttonClicked", LogUtil.RED);
    }
    
    // Can't overload
     public void buttonClickedUsingParam(String str) {
        LogUtil.info("*** otherButtonClicked " + str, LogUtil.RED);
    }
    
    

    // This (returned string) will be used by the JSF navigationssystem
    // Probably better using faces-config.xml for navigation
    // Any method with returntype ok
    public String navigate() {
        LogUtil.info("*** navigate to other.xhtml", LogUtil.RED);
        return "other";
    }
}
