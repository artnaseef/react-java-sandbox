package com.artnaseef.sandbox.react.jetty.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by art on 7/27/19.
 */
@Path("/")
public class RestHelloWorld {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World from REST service";
    }
}
