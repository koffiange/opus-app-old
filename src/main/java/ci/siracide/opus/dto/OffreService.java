package ci.siracide.opus.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OffreService extends BaseDto{
    @Getter @Setter
    private String titre;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private BigDecimal remuneration;
    @Getter @Setter
    private LocalDate dateExpiration;

    private OffreService() {
    }
}
