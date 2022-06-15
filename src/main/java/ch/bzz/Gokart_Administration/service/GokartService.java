package ch.bzz.Gokart_Administration.service;


import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Gokart;

import javax.ws.rs.*;
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


    /**
     * inserts a new book
     * @param fuel_typ the fuel_typ
     * @param ps the amount of ps
     * @param max_speed the max. speed
     * @param weight the weight
     * @param color the color
     * @param brake_typ the brake typ
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @FormParam("fuel_typ") String fuel_typ,
            @FormParam("ps") int ps,
            @FormParam("max_speed") int max_speed,
            @FormParam("weight") double weight,
            @FormParam("color") String color,
            @FormParam("brake_typ") String brake_typ
    ) {
        Gokart gokart = new Gokart();
        gokart.setGokart_number("GKNMA" + Math.floor(Math.random() * 101) + "FJ");
        setAttributes(
                gokart,
                fuel_typ,
                ps,
                max_speed,
                weight,
                color,
                brake_typ
        );

        DataHandler.insertGokart(gokart);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new book
     * @param gokart_number the key
     * @param fuel_typ the title
     * @param ps the author
     * @param max_speed the uuid of the publisher
     * @param weight the price
     * @param color the isbn
     * @param brake_typ the isbn
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("gokart_number") String gokart_number,
            @FormParam("fuel_typ") String fuel_typ,
            @FormParam("ps") int ps,
            @FormParam("max_speed") int max_speed,
            @FormParam("weight") double weight,
            @FormParam("color") String color,
            @FormParam("brake_typ") String brake_typ
    ) {
        int httpStatus = 200;
        Gokart gokart = DataHandler.getInstance().readGokartByGokart_number(gokart_number);
        if (gokart != null) {
            setAttributes(
                    gokart,
                    fuel_typ,
                    ps,
                    max_speed,
                    weight,
                    color,
                    brake_typ
            );

            DataHandler.updateGokart();
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
     * @param gokart_number  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(
            @QueryParam("gokart_number") String gokart_number
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteGokart(gokart_number)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }



    /**
     * sets the attributes for the gokart-object
     * @param gokart  the gokart-object
     * @param fuel_typ  the fuel typ
     * @param ps  the author
     * @param max_speed  the uuid of the publisher
     * @param weight  the price
     * @param color the color
     * @param brake_typ the brake typ
     */
    private void setAttributes(
            Gokart gokart,
            String fuel_typ,
            int ps,
            int max_speed,
            double weight,
            String color,
            String brake_typ
    ) {
        gokart.setFuel_typ(fuel_typ);
        gokart.setPs(ps);
        gokart.setMax_speed(max_speed);
        gokart.setWeight(weight);
        gokart.setColor(color);
        gokart.setBrake_typ(brake_typ);
    }

}
