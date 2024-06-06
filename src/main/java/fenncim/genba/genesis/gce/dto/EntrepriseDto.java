package fenncim.genba.genesis.gce.dto;

import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.FIELD_MISSING_MESSAGE;

public record EntrepriseDto(String tva, String denomination, AdresseDto adresseDto, List<ContactDto> contacts) {

    public EntrepriseDto {
        if(null == tva || 9 != tva.length()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat(" tva of 9 digits"));
        }

        if(null == denomination || denomination.isEmpty()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat(" denomination"));
        }
    }
}
