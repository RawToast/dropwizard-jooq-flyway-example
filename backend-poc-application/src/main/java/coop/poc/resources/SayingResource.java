package coop.poc.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import coop.poc.api.Saying;
import coop.poc.services.SayingService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/saying")
@Produces(MediaType.APPLICATION_JSON)
public class SayingResource {

    private final SayingService sayingService;

    public SayingResource(SayingService sayingService) {
        this.sayingService = sayingService;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return sayingService.generateNewSaying(name);
    }
}