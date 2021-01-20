package ci.siracide.opus.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum TypeCommunaute {
    RELIGIEUSE("Communauté Réligieuse"),
    ASSOCIATION("Association");

    @Getter
    private String libelle;

    TypeCommunaute(String libelle) {
        this.libelle = libelle;
    }
}
