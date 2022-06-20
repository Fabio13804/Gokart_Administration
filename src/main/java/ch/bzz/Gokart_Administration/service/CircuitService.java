package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Circuit;

import javax.validation.Valid;
import javax.ws.rs.*;
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
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCircuit(@QueryParam("id") int circuitID) {
        int httpStatus = 200;
        Circuit circuit = DataHandler.getInstance().readCircuitByID(circuitID);
        if (circuit == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(circuit)
                .build();
    }


    /**
     * inserts a new circuit
     * @param circuit the circuit
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertCircuit(
            @Valid @BeanParam Circuit circuit
    ) {
        circuit.setCircuitID((int) Math.floor(Math.random() * 101));

        DataHandler.insertCircuit(circuit);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new circuit
     * @param circuit the circuit
     * @param circuitID the Key
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCircuit(
            @Valid @BeanParam Circuit circuit,
            @FormParam("circuitID") int circuitID

    ) {
        int httpStatus = 200;
        Circuit oldCircuit = DataHandler.getInstance().readCircuitByID(circuitID);

        if (oldCircuit != null) {
            oldCircuit.setTrack_typ(circuit.getTrack_typ());
            oldCircuit.setDistance(circuit.getDistance());
            oldCircuit.setName(circuit.getName());
            oldCircuit.setNumber_of_curves(circuit.getNumber_of_curves());
            oldCircuit.setNumber_of_straights(circuit.getNumber_of_straights());

            DataHandler.updateCircuit();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a circuit identified by its uuid
     * @param circuitID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCircuit(
            @QueryParam("circuitID") int circuitID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteCircuit(circuitID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
