package ci.siracide.opus.backing;

import ci.siracide.opus.dto.*;
import ci.siracide.opus.service.UtilisateurService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

@Named
@ViewScoped
public class UtilisateurCreateBacking extends BaseBacking{
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Inject
    UtilisateurService utilisateurService;

    @Getter @Setter
    private boolean skip = false;

    @Getter @Setter
    private UtilisateurDto utilisateurDto = new UtilisateurDto();
    @Getter @Setter
    private Utilisateur utilisateur = new Utilisateur();
    @Getter @Setter
    private List<Formation> formationList = new ArrayList<>();
    @Getter @Setter
    private List<Competence> competenceList = new ArrayList<>();
    @Getter @Setter
    private List<ExperiencePro> experienceProList = new ArrayList<>();
    @Getter @Setter
    private List<Activite> activiteList = new ArrayList<>();
    @Getter @Setter
    private List<OffreEmploi> offreEmploiList = new ArrayList<>();
    @Getter @Setter
    private List<OffreService> offreServiceList = new ArrayList<>();
    @Getter @Setter
    private List<Business> businessList = new ArrayList<>();
    @Getter @Setter
    private List<String> motCleList;
    @Getter @Setter
    private Formation formation;
    @Getter @Setter
    private Competence competence;
    @Getter @Setter
    private Business business;

    @Getter @Setter
    private String dateNaissance;

    @PostConstruct
    public void init(){
        formation = new Formation();
        competence = new Competence();
        business = new Business();
    }

    public String onFlowProcess(FlowEvent event){
        if(skip) {
            skip = false;   //reset in case user goes back
            return "recapitulatif";
        }
        else {
            return event.getNewStep();
        }
    }

    public void addFormation(){
        if(!formationList.contains(formation)) {
            formationList.add(formation);
        } else {
            addGlobalErrorMessage("Cette formation existe dejà dans la liste");
        }

        formation = new Formation();
        LOG.info("FORMATION : "+formation.toString());
    }

    public void addCompetence(){
        if(!competenceList.contains(competence)) {
            competenceList.add(competence);
        } else {
            addGlobalErrorMessage("Cette compétence existe dejà dans la liste");
        }

        competence = new Competence();
    }

    public void addBusiness(){
        if(!businessList.contains(business)) {
            businessList.add(business);
        } else {
            addGlobalErrorMessage("Cette activité entrepreneuriale existe dejà dans la liste");
        }

        business = new Business();
    }

    public void deleteCompetence(Competence c){
        competenceList.remove(c);
        competence = new Competence();
    }

    public void deleteFormation(Formation f){
        formationList.remove(f);
        LOG.info("FORMATION : "+f.toString());
        formation = new Formation();
    }

    public void deleteBusiness(Business b){
        businessList.remove(b);
        business = new Business();
    }

    public void onEstEnPosteSwitch(){
        LOG.info("Utilisateur est en poste : "+ utilisateur.getEstEnPoste());
    }

    private void buildUtilisateurDto(){
        utilisateur.setDateNaissance(convertStringIntoLocalDate(dateNaissance));
        motCleList.forEach(s -> utilisateur.setMotsCles(utilisateur.getMotsCles().concat(", "+s)));
        utilisateur.setMotDePasse("jubile2021");
        utilisateurDto.setUtilisateur(utilisateur);
        utilisateurDto.setFormationList(formationList);
        utilisateurDto.setCompetenceList(competenceList);
        utilisateurDto.setExperienceProList(experienceProList);
        utilisateurDto.setBusinessList(businessList);
    }

    public void persist(){
        this.buildUtilisateurDto();
        try{
            utilisateurService.persist(utilisateurDto);
            closeSuccess();
        } catch (Exception e){
            showError(e.getMessage());
        }
    }
}
