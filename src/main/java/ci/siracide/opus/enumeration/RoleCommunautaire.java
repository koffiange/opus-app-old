package ci.siracide.opus.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum RoleCommunautaire {
    CHEF("Chef de communauté"),
    PARTICIPANT("Membre participant de la communauté");

    @Getter
    private String libelle;

    RoleCommunautaire(String libelle) {
        this.libelle = libelle;
    }
}
