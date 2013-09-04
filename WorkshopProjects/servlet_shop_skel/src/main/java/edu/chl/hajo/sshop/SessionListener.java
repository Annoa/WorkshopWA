/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.sshop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author anno
 */
@WebListener()
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, "Creating session with id: {0}", se.toString());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Logger.getAnonymousLogger().log(Level.INFO, "Destroying session with id: {0}", se.toString());
    }
}
