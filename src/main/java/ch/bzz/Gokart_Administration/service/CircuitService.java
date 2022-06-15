package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Circuit;
import ch.bzz.Gokart_Administration.model.Gokart;

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
    public Response readCircuits(@QueryParam("id") int circuitID) {
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
     * inserts a new book
     * @param track_typ the fuel_typ
     * @param distance the max. speed
     * @param name the color
     * @param number_of_curves the amount of ps
     * @param number_of_straights the weight
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertCircuit(
            @FormParam("track_typ") String track_typ,
            @FormParam("distance") double distance,
            @FormParam("name") String name,
            @FormParam("number_of_curves") int number_of_curves,
            @FormParam("number_of_straights") int number_of_straights
    ) {
        Circuit circuit = new Circuit();
        circuit.setCircuitID((int) Math.floor(Math.random() * 101));
        setAttributes(
                circuit,
                track_typ,
                distance,
                name,
                number_of_curves,
                number_of_straights
        );

        DataHandler.insertCircuit(circuit);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new book
     * @param track_typ the fuel_typ
     * @param distance the max. speed
     * @param name the color
     * @param number_of_curves the amount of ps
     * @param number_of_straights the weight
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCircuit(
            @FormParam("circuitID") int circuitID,
            @FormParam("track_typ") String track_typ,
            @FormParam("distance") double distance,
            @FormParam("name") String name,
            @FormParam("number_of_curves") int number_of_curves,
            @FormParam("number_of_straights") int number_of_straights

    ) {
        int httpStatus = 200;
        Circuit circuit = DataHandler.getInstance().readCircuitByID(circuitID);
        if (circuit != null) {
            setAttributes(
                    circuit,
                    track_typ,
                    distance,
                    name,
                    number_of_curves,
                    number_of_straights
            );

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
     * deletes a book identified by its uuid
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



    /**
     * sets the attributes for the gokart-object
     * @param track_typ the key
     * @param circuit the title
     * @param number_of_curves the author
     * @param distance the uuid of the publisher
     * @param number_of_straights the price
     * @param name the isbn
     * @return Response
     */
    private void setAttributes(
            Circuit circuit,
            String track_typ,
            double distance,
            String name,
            int number_of_curves,
            int number_of_straights
    ) {
        circuit.setTrack_typ(track_typ);
        circuit.setDistance(distance);
        circuit.setName(name);
        circuit.setNumber_of_curves(number_of_curves);
        circuit.setNumber_of_straights(number_of_straights);
    }
}
