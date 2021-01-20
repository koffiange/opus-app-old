package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ExperiencePro extends BaseDto{
    @Getter @Setter
    private LocalDate dateDebut;
    @Getter @Setter
    private LocalDate dateFin;
    @Getter @Setter
    private String entreprise;
    @Getter @Setter
    private String domaine;
    @Getter @Setter
    private String fonction;

    @Getter @Setter
    private Utilisateur utilisateur;

    public ExperiencePro() {
    }
}
