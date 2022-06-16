package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Karting_company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * test service
 */
@Path("kartingCompany")
public class Karting_companyService {

    /**
     * confirms the application runs
     * @return  message
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listKarting_companys() {
        List<Karting_company> karting_companyList = DataHandler.getInstance().readAllKarting_companys();

        return Response
                .status(200)
                .entity(karting_companyList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKarting_company(@QueryParam("id") int karting_companyID) {
        int httpStatus = 200;
        Karting_company karting_company = DataHandler.getInstance().readKarting_companyByUUID(karting_companyID);
        if (karting_company == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(karting_company)
                .build();
    }



    /**
     * inserts a new book
     * @param restaurant the fuel_typ
     * @param name the color
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertKarting_company(
            @FormParam("name") String name,
            @FormParam("restaurant") boolean restaurant
    ) {
        Karting_company karting_company = new Karting_company();
        karting_company.setKarting_companyID((int) Math.floor(Math.random() * 101));
        setAttributes(
                karting_company,
                name,
                restaurant
        );

        DataHandler.insertKarting_company(karting_company);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a new book
     * @param karting_companyID the amount of ps
     * @param name the color
     * @param restaurant the weight
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKarting_company(
            @FormParam("karting_companyID") int karting_companyID,
            @FormParam("name") String name,
            @FormParam("restaurant") boolean restaurant

    ) {
        int httpStatus = 200;
        Karting_company karting_company = DataHandler.getInstance().readKarting_companyByUUID(karting_companyID);
        if (karting_company != null) {
            setAttributes(
                    karting_company,
                    name,
                    restaurant
            );

            DataHandler.updateKarting_company();
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
     * @param karting_companyID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKarting_company(
            @QueryParam("karting_companyID") int karting_companyID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteKarting_company(karting_companyID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }



    /**
     * sets the attributes for the karting_company-object
     * @param restaurant the key
     * @param karting_company the title
     * @param name the isbn
     * @return Response
     */
    private void setAttributes(
            Karting_company karting_company,
            String name,
            boolean restaurant
    ) {
        karting_company.setName(name);
        karting_company.setRestaurant(restaurant);
    }
}
