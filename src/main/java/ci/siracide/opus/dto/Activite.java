package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class Activite extends BaseDto{
    @Getter @Setter
    private String libelle;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID ")
    public Utilisateur utilisateur;

    public Activite() {
    }
}
