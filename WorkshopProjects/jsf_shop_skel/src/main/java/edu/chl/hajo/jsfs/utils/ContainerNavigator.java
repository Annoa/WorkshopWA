/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.jsfs.utils;

import edu.chl.hajo.shop.utils.IEntity;
import edu.chl.hajo.shop.utils.IEntityContainer;
import java.util.List;

/**
 * Getting sublists of container, used for tabular display of data
 * Usage: See ProductServlet
 * 
 * @author hajo
 */
public class ContainerNavigator<T extends IEntity<Long>> {

    private final IEntityContainer<T, Long> container;
    private int first;  // Fist item in table
    private int nItems;  // Number of items in table

    public ContainerNavigator(int first, int nItems, IEntityContainer<T, Long> container) {
        this.first = first;
        this.nItems = nItems;
        this.container = container;
    }

    public List<T> next() {
        int count = container.getCount();
        first = (first + nItems < count) ? first + nItems : first;
        int n = adjustNItems(count);
        return container.getRange(first, n);
    }

    public List<T> previous() {
        first = (first - nItems > 0) ? first - nItems : 0;
        int count = container.getCount();
        int n = adjustNItems(count);
        return container.getRange(first, n);
    }

    private int adjustNItems(int count) {
        return (first + nItems > count) ? count - first : nItems;
    }

    public List<T> getRange() {
        int count = container.getCount();
        int n = adjustNItems(count);
        return container.getRange(first, n);
    }

    public int getFst() {
        return first;
    }

    public void setFst(int first) {
        this.first = first;
    }
    
    public int getnItems() {
        return nItems;
    }

    public void setnItems(int nItems) {
        this.nItems = nItems;
    }
}
