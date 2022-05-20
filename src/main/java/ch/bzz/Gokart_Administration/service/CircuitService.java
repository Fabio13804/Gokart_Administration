package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Circuit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("circuit")
public class CircuitService {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listCircuits() {
        List<Circuit> circuitList = DataHandler.getInstance().readAllCircuits();
        return Response
                .status(200)
                .entity(circuitList)
                .build();
    }
}
