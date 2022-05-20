package ch.bzz.Gokart_Administration.service;

import ch.bzz.Gokart_Administration.data.DataHandler;
import ch.bzz.Gokart_Administration.model.Karting_company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response test() {
        List<Karting_company> karting_companyList = DataHandler.getInstance().readAllKarting_companys();

        return Response
                .status(200)
                .entity(karting_companyList)
                .build();
    }
}
