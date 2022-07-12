package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Gokart;
import ch.bzz.Gokart_Administration.model.Karting_company;

import javax.validation.Valid;
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
    public Response listKarting_companys(
            @CookieParam("userRole") String userRole
    ) {

        List<Karting_company> karting_companyList = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            karting_companyList = DataHandler.getInstance().readAllKarting_companys();
        }
        return Response
                .status(httpStatus)
                .entity(karting_companyList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readKarting_company(
            @QueryParam("id") int karting_companyID,
            @CookieParam("userRole") String userRole
    ) {
        Karting_company karting_company = null;
        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            karting_company = DataHandler.getInstance().readKarting_companyByUUID(karting_companyID);
        }
        return Response
                .status(httpStatus)
                .entity(karting_company)
                .build();
    }



    /**
     * inserts a new karting_company
     * @param karting_company the karting_company
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertKarting_company(
            @Valid @BeanParam Karting_company karting_company,
            @CookieParam("userRole") String userRole
    ) {

        int httpStatus;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }else {
            httpStatus = 200;
            karting_company.setKarting_companyID((int) Math.floor(Math.random() * 101));
            DataHandler.insertKarting_company(karting_company);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a new karting_company
     * @param karting_companyID the karting_companyID
     * @param karting_company the karting_company
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateKarting_company(
            @Valid @BeanParam Karting_company karting_company,
            @FormParam("karting_companyID") int karting_companyID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        Karting_company oldKarting_company = DataHandler.getInstance().readKarting_companyByUUID(karting_companyID);

        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (oldKarting_company != null) {
                oldKarting_company.setName(karting_company.getName());
                oldKarting_company.setRestaurant(karting_company.getRestaurant());

                DataHandler.updateKarting_company();
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
     * deletes a karting_company identified by its uuid
     * @param karting_companyID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteKarting_company(
            @QueryParam("karting_companyID") int karting_companyID,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            if (!DataHandler.deleteKarting_company(karting_companyID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
