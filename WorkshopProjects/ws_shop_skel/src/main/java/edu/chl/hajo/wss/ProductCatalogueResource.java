package edu.chl.hajo.wss;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.net.URI;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author anno
 */
@Path("/products")
public class ProductCatalogueResource {
    private final IProductCatalogue productCatalogue 
            = Shop.INSTANCE.getProductCatalogue();

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
        List<Product> l = productCatalogue.getRange(fst, max-fst);
        List<ProductProxy> c = new ArrayList<>();
        for (Product p : l) {
            c.add(new ProductProxy(p));
        }
        GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>(c) {};
        
        return Response.ok(ge).build();  
    }
    
    @GET @Path("/count")
    public Response count() {
        PrimitiveJSONWrapper p = new PrimitiveJSONWrapper(productCatalogue.getCount());
        return Response.ok(p).build();  
    }
    
    @POST @Consumes("application/x-www-form-urlencoded")
    public Response add(@FormParam("name") String name, @FormParam("price") Double price) {
        productCatalogue.add(new Product(name, price));
        return Response.created(URI.create("/products")).build();
    }
    
    @PUT @Path("{oldId}") @Consumes("application/json")
    public Response update(@PathParam("oldId") Long id, JSONObject jsonObject) {
        System.out.println(id);
        productCatalogue.remove(id);
        try {
            productCatalogue.add(new Product(jsonObject.getLong("id"), jsonObject.getString("name"), jsonObject.getDouble("price")));
        } catch (JSONException ex) {
            Logger.getLogger(ProductCatalogueResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.created(URI.create("/products")).build();
    }
    
    @DELETE @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        productCatalogue.remove(id);
        return Response.ok().build();
    }
}
