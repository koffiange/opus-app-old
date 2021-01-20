package ci.siracide.opus.client;

import ci.siracide.opus.dto.Utilisateur;
import ci.siracide.opus.dto.UtilisateurDto;

import javax.ws.rs.*;
import java.util.List;

@Path("/v1/utilisateurs")
public interface UtilisateurClient {
    @GET
    List<Utilisateur> listAll();

    @GET
    @Path("/dto")
    List<UtilisateurDto> listAllUtilisateurDto();

    @POST
    void persist(UtilisateurDto utilisateurDto);

    @DELETE
    @Path("/dto/{uuid}")
    void delete(@PathParam("uuid") String uuid);
}
