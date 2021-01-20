package ci.siracide.opus.enumeration;

import lombok.Getter;
import lombok.Setter;

public enum Diplome {
    BAC("BAC", "Baccalaureat"),
    BTS("BTS", "Brevet de Technicien Supérieur"),
    LICENCE_PROFESSIONNELLE("LICENCE PROFESSIONNELLE", "Brevet de Technicien Supérieur"),
    MAITRISE("MAITRISE", "Master"),
    MASTETR("MASTER", "Master"),
    DOCTORAT("DOCTORAT", "Doctorat");
    @Getter
    private String sigle;
    @Getter
    private String libelle;

    Diplome(String sigle, String libelle) {
        this.sigle = sigle;
        this.libelle = libelle;
    }
}
