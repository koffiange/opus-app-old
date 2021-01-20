package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;


public class Competence extends BaseDto{
    @Getter @Setter
    private String libelle;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Integer anneeExperience;

    @Getter @Setter
    private Utilisateur utilisateur;

    public Competence() {
    }
}
