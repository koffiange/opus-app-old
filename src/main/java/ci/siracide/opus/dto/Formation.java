package ci.siracide.opus.dto;

import ci.siracide.opus.enumeration.Diplome;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Formation {
    @Getter @Setter
    private String filiere;
    @Getter @Setter
    private Diplome diplome;
    @Getter @Setter
    private Integer annee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return Objects.equals(filiere, formation.filiere) &&
                diplome == formation.diplome &&
                Objects.equals(annee, formation.annee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filiere, diplome, annee);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "filiere='" + filiere + '\'' +
                ", diplome=" + diplome +
                ", annee=" + annee +
                '}';
    }
}
