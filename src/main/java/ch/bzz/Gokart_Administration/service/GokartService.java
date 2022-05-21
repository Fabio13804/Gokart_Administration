package ch.bzz.Gokart_Administration.service;


import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Gokart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gokart")
public class GokartService {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listGokarts() {
        List<Gokart> gokartList = DataHandler.getInstance().readAllGokarts();
        return Response
                .status(200)
                .entity(gokartList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBooks(@QueryParam("uuid") String gokart_number) {
        int httpStatus = 200;
        Gokart gokart = DataHandler.getInstance().readGokartByGokart_number(gokart_number);
        if (gokart == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(gokart)
                .build();
    }
}
