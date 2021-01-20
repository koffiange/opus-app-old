package ci.siracide.opus.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UtilisateurDto {
    @Getter @Setter
    private Utilisateur utilisateur;
    @Getter @Setter
    private Communaute communaute;
    @Getter @Setter
    private List<Formation> formationList;
    @Getter @Setter
    private List<Competence> competenceList;
    @Getter @Setter
    private List<ExperiencePro> experienceProList;
    @Getter @Setter
    private List<Activite> activiteList;
    @Getter @Setter
    private List<OffreEmploi> offreEmploiList;
    @Getter @Setter
    private List<OffreService> offreServiceList;
    @Getter @Setter
    private List<Business> businessList;

    public UtilisateurDto(Utilisateur utilisateur, List<Competence> competenceList,
                          List<ExperiencePro> experienceProList, List<Activite> activiteList,
                          List<OffreEmploi> offreEmploiList, List<OffreService> offreServiceList,
                          List<Business> businessList) {
        this.utilisateur = utilisateur;
        this.competenceList = competenceList;
        this.experienceProList = experienceProList;
        this.activiteList = activiteList;
        this.offreEmploiList = offreEmploiList;
        this.offreServiceList = offreServiceList;
        this.businessList = businessList;
    }

    public UtilisateurDto() {
    }
}
