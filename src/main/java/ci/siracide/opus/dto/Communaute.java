package ci.siracide.opus.dto;

import ci.siracide.opus.enumeration.TypeCommunaute;
import lombok.Getter;
import lombok.Setter;

public class Communaute extends BaseDto{
    @Getter @Setter
    private String libelle;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String logo;
    @Getter @Setter
    private String contactPrincipal;
    @Getter @Setter
    private String contactSecondaire;
    @Getter @Setter
    private String adresse;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private TypeCommunaute typeCommunaute;
    @Getter @Setter
    private String siteweb;
    @Getter @Setter
    private String facebook;
    @Getter @Setter
    private String instagram;
    @Getter @Setter
    private String twitter;

    public Communaute() {
    }
}
