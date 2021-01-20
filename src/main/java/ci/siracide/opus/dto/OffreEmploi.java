package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OffreEmploi extends BaseDto{
    @Getter @Setter
    private String titre;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String entreprise;
    @Getter @Setter
    private BigDecimal remuneration;
    @Getter @Setter
    private LocalDate dateExpiration;

    @Getter @Setter
    private Utilisateur utilisateur;

    public OffreEmploi() {
    }
}
