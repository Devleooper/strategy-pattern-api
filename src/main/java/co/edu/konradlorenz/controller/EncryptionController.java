package co.edu.konradlorenz.controller;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.service.EncryptionService;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class EncryptionController {

    EncryptionService encryptionService = new EncryptionService();;



    @POST
    @Path("/security")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doEncryption(EncryptionRequest request) {
        try {
            EncryptionResponse response = encryptionService.processAction(request);
            if (response != null) {
                return Response.status(Response.Status.OK)
                        .entity(response)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("send valid values")
                        .build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("internal server error")
                    .build();
        }

    }


}
