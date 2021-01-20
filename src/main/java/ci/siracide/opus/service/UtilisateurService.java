package ci.siracide.opus.service;

import ci.siracide.opus.client.UtilisateurClient;
import ci.siracide.opus.dto.Utilisateur;
import ci.siracide.opus.dto.UtilisateurDto;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UtilisateurService implements UtilisateurClient {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    @ConfigProperty(name = "application.pole.opus.api.uri", defaultValue = "http://localhost:8701/pole-opus/api")
    String baseUri;
    URI apiUri;
    UtilisateurClient client;

    @PostConstruct
    public void init() {
        try {
            apiUri = new URI(baseUri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client = RestClientBuilder.newBuilder()
                .baseUri(apiUri)
                .build(UtilisateurClient.class);
    }

    @Override
    public List<Utilisateur> listAll() {
        return client.listAll();
    }

    @Override
    public List<UtilisateurDto> listAllUtilisateurDto() {
        return client.listAllUtilisateurDto();
    }

    @Override
    public void persist(UtilisateurDto utilisateurDto) {
        client.persist(utilisateurDto);
    }

    @Override
    public void delete(String uuid) {
        client.delete(uuid);
    }
}
