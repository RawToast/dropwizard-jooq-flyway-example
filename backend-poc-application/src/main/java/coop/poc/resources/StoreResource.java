package coop.poc.resources;

import coop.poc.api.forms.StoreForm;
import coop.poc.api.stores.Store;
import coop.poc.services.StoreService;
import io.dropwizard.jersey.params.IntParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@Produces(MediaType.APPLICATION_JSON)
public class StoreResource {

    private StoreService service;

    public StoreResource (StoreService storeService) {
        this.service = storeService;
    }

    @GET
    @Path("/all")
    public Response findAll(){

        List<Store> stores = service.listStores();

        return Response.ok(stores).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") @NotNull String name){

        List<Store> stores = service.findStore(name);

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


    @POST
    @Path("/{id}/delete")
    public Response deleteStore(@PathParam("id") IntParam storeId){
        service.deleteStore(storeId.get().intValue());
        return Response.ok().build();
    }
}
