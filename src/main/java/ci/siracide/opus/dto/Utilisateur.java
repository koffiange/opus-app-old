package ci.siracide.opus.dto;


import ci.siracide.opus.enumeration.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Utilisateur extends BaseDto{
    @Getter @Setter
    private String nom;
    @Getter @Setter
    private String prenoms;
    @Getter @Setter
    private Genre genre;
    @Getter @Setter
    private LocalDate dateNaissance;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String motDePasse = "jubile2021";
    @Getter @Setter
    private String contactPrincipal;
    @Getter @Setter
    private String contactSecondaire;
    @Getter @Setter
    private String adresse;
    @Getter @Setter
    private String photoProfil;
    @Getter @Setter
    private String curriculumVitae;
    @Getter @Setter
    private Boolean estEnQueteEmploi;
    @Getter @Setter
    private Boolean estEnPoste;
    @Getter @Setter
    private Boolean estEntrepreneur;
    @Getter @Setter
    private String metier;
    @Getter @Setter
    private String descriptionMetier;
    @Getter @Setter
    private String employeur;
    @Getter @Setter
    private String motsCles = "";

    public Utilisateur() {
    }
}
