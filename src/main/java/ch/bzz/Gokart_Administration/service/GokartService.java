package ch.bzz.Gokart_Administration.service;


import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Gokart;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gokart")
public class GokartService {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listGokarts(
            @CookieParam("userRole") String userRole
    ) {

        List<Gokart> gokartList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            gokartList = DataHandler.getInstance().readAllGokarts();
        }
        return Response
                .status(httpStatus)
                .entity(gokartList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGokart(
            @NotEmpty
            @Pattern(regexp = "[A-Za-z]{5}[0-9]{1,}[A-Za-z]{2}")
            @QueryParam("uuid") String gokart_number,
            @CookieParam("userRole") String userRole
    ) {
        Gokart gokart = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            gokart = DataHandler.getInstance().readGokartByGokart_number(gokart_number);
        }
        return Response
                .status(httpStatus)
                .entity(gokart)
                .build();
    }



    /**
     * inserts a new Gokart
     *
     * @param gokart the gokart
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertGokart(
            @Valid @BeanParam Gokart gokart,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            gokart.setGokart_number("GKNMA" + (int) Math.floor(Math.random() * 101) + "FJ");
            DataHandler.insertGokart(gokart);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new Gokart
     *
     * @param gokart_number the key
     * @param gokart        the gokart
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGokart(
            @Valid @BeanParam Gokart gokart,
            @FormParam("gokart_number") String gokart_number,
            @CookieParam("userRole") String userRole
    ) {

        int httpStatus;
        Gokart oldGokart = DataHandler.getInstance().readGokartByGokart_number(gokart_number);

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (oldGokart != null) {
                oldGokart.setFuel_typ(gokart.getFuel_typ());
                oldGokart.setPs(gokart.getPs());
                oldGokart.setMax_speed(gokart.getMax_speed());
                oldGokart.setWeight(gokart.getWeight());
                oldGokart.setColor(gokart.getColor());
                oldGokart.setBrake_typ(gokart.getBrake_typ());

                DataHandler.updateGokart();
            } else {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }


    /**
     * deletes a book identified by its uuid
     *
     * @param gokart_number the key
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGokartk(
            @NotEmpty
            @Pattern(regexp = "[A-Za-z]{5}[1-9]{1,}[A-Za-z]{2}")
            @QueryParam("gokart_number") String gokart_number,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (!DataHandler.deleteGokart(gokart_number)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}