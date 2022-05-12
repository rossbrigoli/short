package org.acme;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


@RequestScoped
@Path("/create")
public class ShortUrl {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody LongURL longURL) {

        try {
            var id = URLCache.addUrl(longURL.getLongURL(), longURL.getTTL());
            return Response.created(URI.create("/"+id)).build();
        } catch (Exception ex) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Status.OK).entity(URLCache.Urls).build();
    }
}
