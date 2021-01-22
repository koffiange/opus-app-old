package ci.siracide.opus.backing;

import ci.siracide.opus.dto.UtilisateurDto;
import ci.siracide.opus.service.UtilisateurService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

@Named
@ViewScoped
public class ProfilUtilisateurBacking extends BaseBacking{
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    UtilisateurService utilisateurService;
    @Getter @Setter
    private UtilisateurDto utilisateurDto = new UtilisateurDto();
    @Getter @Setter
    private Map<String, String> params;
    @Getter @Setter
    private List<String> tags;

    @PostConstruct
    public void init(){
        params = getRequestParameterMap();
        if(params.containsKey("uuid")){
            utilisateurDto = utilisateurService.findDtoById(params.get("uuid"));
            tags = Arrays.asList(utilisateurDto.getUtilisateur().getMotsCles().substring(2).split(", "));
        }
    }

    public String computeOld(){
        return String.valueOf(LocalDate.now().getYear() - utilisateurDto.getUtilisateur().getDateNaissance().getYear());
    }
}
