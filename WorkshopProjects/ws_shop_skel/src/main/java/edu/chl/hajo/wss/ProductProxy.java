package edu.chl.hajo.wss;


import edu.chl.hajo.shop.core.Product;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Need this because translation from XML to JSON
 * @author hajo
 */
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductProxy {

    // The wrapped product
    private Product product;

    protected ProductProxy() { // Must have
    }
   
    public ProductProxy(Product product) { 
        this.product = product; 
    }
    
    @XmlElement(required = true)
    public String getName() {
        return product.getName();
    }

    @XmlElement(required = true)
    public Long getId() {
        return product.getId();
    }

    @XmlElement(required = true)
    public double getPrice() {
        return product.getPrice();
    }
}
