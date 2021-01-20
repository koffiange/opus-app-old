package ci.siracide.opus.dto;

import ci.siracide.opus.enumeration.RoleCommunautaire;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class Membre extends BaseDto{
    @Getter @Setter
    private LocalDate dateAdhesion;
    @Getter @Setter
    private RoleCommunautaire role;

    @Getter @Setter
    private Utilisateur utilisateur;
    @Getter @Setter
    private Communaute communaute;

    public Membre() {
    }

    public Membre(Utilisateur utilisateur, Communaute communaute) {
        this.dateAdhesion = LocalDate.now();
        this.role = RoleCommunautaire.PARTICIPANT;
        this.utilisateur = utilisateur;
        this.communaute = communaute;
    }
}
