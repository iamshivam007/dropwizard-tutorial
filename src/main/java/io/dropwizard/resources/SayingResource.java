package io.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.api.Saying;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SayingResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public SayingResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("hello-world")
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("error")
    public String testException() {
        throw new WebApplicationException("Manually raised error!!");
    }

    @GET
    @Path("error404")
    public String testException404() {
        throw new WebApplicationException("Manually raised error!!", Response.Status.NOT_FOUND);
    }
}
