package fenncim.genba.genesis.gce.dto;

import java.sql.Timestamp;

import static fenncim.genba.genesis.gce.util.Constants.FIELD_MISSING_MESSAGE;

public record AdresseDto(String rue, int numeroDeRue, int boite, String ville, int cap) {
    public AdresseDto {
        if(null == rue || rue.isEmpty()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("rue"));
        }

        if(0 == numeroDeRue) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("numéro rue"));
        }

        if(0 == boite) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("boite"));
        }

        if(null == ville || ville.isEmpty()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("ville"));
        }

        if(0 == cap) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("cap"));
        }
    }
}
