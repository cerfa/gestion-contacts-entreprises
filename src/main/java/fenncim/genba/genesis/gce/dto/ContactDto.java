package fenncim.genba.genesis.gce.dto;

import fenncim.genba.genesis.gce.model.ContactType;

import java.util.ArrayList;
import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.FIELD_MISSING_MESSAGE;

public record ContactDto(String nom, String prenom, ContactType contactType, AdresseDto adresse,
                         List<EntrepriseDto> entreprises) {
    public ContactDto {
        if(null == nom || nom.isEmpty()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("nom"));
        }

        if(null == prenom || prenom.isEmpty()) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("prenom"));
        }

        if(null == adresse) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat("adresse"));
        }

        if( 0 == ContactType.FREELANCE.compareTo(contactType)
                && (null == entreprises || entreprises.isEmpty())) {
            throw new IllegalArgumentException(FIELD_MISSING_MESSAGE.concat(" freelance tva shall be provided"));
        } else if(0 == ContactType.EMPLOYEE.compareTo(contactType)){
            entreprises = new ArrayList<>();
        }

    }
}
