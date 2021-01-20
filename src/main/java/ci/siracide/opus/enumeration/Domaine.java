package ci.siracide.opus.enumeration;

import lombok.Getter;

public enum Domaine {

    AGROALIMENTAIRE("Agroalimentaire"),
    BPCI("Bois / Papier / Carton / Imprimerie"),
    CHIMIE("Chimie / Parachimie"),
    EDITION_COMMUNICATON_MULTIMEDIA(" Édition / Communication / Multimédia"),
    ETUDE_CONSEIL("Études et conseils"),
    INFORMATIQUE_TELECOM("Informatique / Télécoms"),
    METALURGIE("Métallurgie / Travail du métal"),
    SERVICE_AUX_ENTREPRISES("Services aux entreprises"),
    TRANSPORT_LOGISTIQUE("Transports / Logistique"),
    BANQUE_ASSURANCE("Banque / Assurance"),
    BTP_MATERIAUX("BTP / Matériaux de construction"),
    COMMERCE_DISTRIBUTION("Commerce / Négoce / Distribution"),
    ELECTRONIQUE_ELECTRICITE("Électronique / Électricité"),
    INDUSTRIE_PHARMACEUTIQUE("Industrie pharmaceutique"),
    MACHINE_EQUIPEMENT_ET_AUTOMOBILE("Machines et équipements / Automobile"),
    PLASTIQUE_CAOUTCHOUC("Plastique / Caoutchouc"),
    TEXTILE("Textile / Habillement / Chaussure"),
    ART("Musique / Art oratoire / Dessin"),
    EVENEMENTIEL("Evenementiel / Showbiz");

    @Getter
    private String libelle;

    Domaine(String libelle) {
        this.libelle = libelle;
    }
}
