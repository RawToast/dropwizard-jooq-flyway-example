package coop.poc.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import coop.poc.api.forms.StoreForm;
import coop.poc.api.stores.Store;
import coop.poc.services.StoreService;
import io.dropwizard.jersey.params.IntParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * GET 	/photos 	photos#index 	display a list of all photos
 GET 	/photos/new 	photos#new 	return an HTML form for creating a new photo
 POST 	/photos 	photos#create 	create a new photo
 GET 	/photos/:id 	photos#show 	display a specific photo
 GET 	/photos/:id/edit 	photos#edit 	return an HTML form for editing a photo
 PATCH/PUT 	/photos/:id 	photos#update 	update a specific photo
 DELETE 	/photos/:id 	photos#destroy 	delete a specific photo
 */
@Path("/stores")
@Timed
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoreResource {

    private StoreService service;

    public StoreResource (StoreService storeService) {
        this.service = storeService;
    }


    @GET
    public Response searchByName(@QueryParam("name") Optional<String> name){

        List<Store> stores;
        if (name.isPresent()){
            stores = service.findStore(name.get());
        } else {
            stores = service.listStores();
        }
        return Response.ok(stores).build();
    }


    @POST
    public Store createStore(@Valid StoreForm storeForm){

        service.persistStore(storeForm);
        return new Store.StoreBuilder().withName(storeForm.getName())
                                       .withPostcode(storeForm.getPostcode())
                                       .withStoreId(10).createStore();
    }

    @GET
    @Path("/{id}")
    public Store getStore(@PathParam("id") IntParam storeId) {
        return service.fetchStore(storeId.get().intValue());
    }


    @DELETE
    @Path("/{id}")
    public Response deleteStore(@PathParam("id") IntParam storeId){
        service.deleteStore(storeId.get().intValue());
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStore(@PathParam("id") IntParam storeId, @Valid StoreForm storeForm){
        Store store = service.update(storeId.get().intValue(), storeForm);
        return Response.ok(store).build();
    }
}
