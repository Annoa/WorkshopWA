package edu.chl.hajo.wss;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



/**
 *
 * @author anno
 */
//@XmlRootElement(name = "products")
@Path("/products")
public class ProductCatalogueResource {
    private final IProductCatalogue productCatalogue 
            = Shop.INSTANCE.getProductCatalogue();
    
    @Context
    private UriInfo uriInfo;

//    public ProductCatalogueResource(){}
    
    @GET
    public Response getAll() {
        List<Product> l 
                = productCatalogue.getRange(0, productCatalogue.getCount());
        List<ProductProxy> c = new ArrayList<>();
        for (Product p : l) {
            c.add(new ProductProxy(p));
        }
        
        GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>(c) {};
        
        return Response.ok(ge).build();
    }
    
    @GET @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        ProductProxy p = new ProductProxy(productCatalogue.find(id));
        return Response.ok(p).build();
    }
    
    @GET @Path("/range")
    public Response getRange(@QueryParam("fst") int fst, @QueryParam("max") int max) {
        List<Product> l = productCatalogue.getRange(fst, max);
        List<ProductProxy> c = new ArrayList<>();
        for (Product p : l) {
            c.add(new ProductProxy(p));
        }
        GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>(c) {};
        
        return Response.ok(ge).build();  
    }
    
    @GET @Path("/count")
    public Response count() {
        PrimitiveJSONWrapper<Integer> p = new PrimitiveJSONWrapper<>(productCatalogue.getCount());
        return Response.ok(p).build();  
    }
    
    @POST @Consumes("application/x-www-form-urlencoded")
    public Response add(@FormParam("name") String name, @FormParam("price") Double price) {
        Product product = new Product(name, price);
        productCatalogue.add(product);
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(product.getId())).build()).build();
    }
    
    @PUT @Path("{oldId}") @Consumes("application/json")
    public Response update(@PathParam("oldId") Long id, JSONObject jsonObject) {
        productCatalogue.remove(id);
        
        try {
            Product product = new Product(jsonObject.getLong("id")
                    , jsonObject.getString("name"), jsonObject.getDouble("price"));
            productCatalogue.add(product);
            return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(product.getId())).build()).build();
        } catch (JSONException ex) {
            Logger.getLogger(ProductCatalogueResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    @PUT @Path("{oldId}") @Consumes("application/x-www-form-urlencoded")
    public Response update(@PathParam("oldId") Long id, 
            @FormParam("id") Long newId, 
            @FormParam("name") String name, 
            @FormParam("price") Double price) {
        productCatalogue.remove(id);
        Product product = new Product(id, name, price);
        productCatalogue.add(product);
        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(product.getId())).build()).build();
    }
    
    @DELETE @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        productCatalogue.remove(id);
        return Response.ok().build();
    }
}
