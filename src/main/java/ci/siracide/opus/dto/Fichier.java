package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;


public class Fichier extends BaseDto{
    @Getter @Setter
    private String nom;
    @Getter @Setter
    private String repertoire;
    @Getter @Setter
    private String extesion;
    @Getter @Setter
    private String contenu;

    @Getter @Setter
    private Activite activite;

    @Getter @Setter
    private OffreEmploi offreEmploi;

    public Fichier() {
    }
}
