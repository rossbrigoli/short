package org.acme;
import javax.ws.rs.Path;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import java.net.URI;
import javax.ws.rs.GET;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/")
public class Forwarder {
    
    Logger logger = Logger.getLogger(this.getClass());

    @GET
    @Path("/{id}")
    public Response post(@PathParam String id) {
        
        if (!URLCache.Urls.containsKey(id)) {
            return Response.status(Status.NOT_FOUND).build();
        }

        URI longURL = URI.create(URLCache.Urls.get(id).getLongURL());
        return Response.temporaryRedirect(longURL).build();
    }
}
