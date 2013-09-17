package edu.chl.hajo.rc;

import java.io.Serializable;
import javax.annotation.PostConstruct;
/*
 * 
 * 
 *     NOTE: package for SessionScoped !!!!!!!
 * 
 * 
 */
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

/**
 * A CDI bean, not a listener. A model bean holding data.
 * Data set by JSF cycle
 * @author hajo 
 */
@Named("MyBean")
@SessionScoped
public class MyBean implements Serializable {

    private String data = "default value";

    public MyBean() {
    }

    @PostConstruct
    public void postContruct() {
        LogUtil.isAlive(this, LogUtil.MAGENTA);
    }

    public String getData() {
        LogUtil.info("*** Getting data from model. Data is: " + data, LogUtil.RED);
        return data;
    }

    public void setData(String data) {
        LogUtil.info("*** Setting data in update model to " + data, LogUtil.RED);
        this.data = data;
    }

    public void render(ComponentSystemEvent evt) {
        LogUtil.info("*** Call prerender " +  evt.toString());
    }
}
