package io.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.api.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    public UserResource() {
    }

    @GET
    @Path("/{id}")
    @Timed
    public User get(@PathParam("id") Long id) {
        return new User("Stranger");
    }
}
