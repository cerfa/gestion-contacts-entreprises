package fenncim.genba.genesis.gce.mapper;

import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.dto.EntrepriseDto;
import fenncim.genba.genesis.gce.entity.Contact;
import fenncim.genba.genesis.gce.entity.Entreprise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class EnterprisesMapper {

    private EnterprisesMapper() {
        //Clean code
    }

    public static EntrepriseDto convertEntrepriseEntityToEntrepriseDto(Entreprise entreprise) {
        return new EntrepriseDto(entreprise.getTva(), entreprise.getDenomination(),
                AdresseMapper.convertAdresseEntityToAdresseDto(entreprise.getAdresse()),
                ContactsMapper.convertListContactEntityToListContactDto(entreprise.getContacts()));
    }

    public static List<EntrepriseDto> convertListEntrepriseEntityToListEntrepriseDto(List<Entreprise> entreprises) {
        List<EntrepriseDto> entreprisesDto = new ArrayList<>();
        for(Entreprise entreprise : entreprises) {
            entreprisesDto.add(convertEntrepriseEntityToEntrepriseDto(entreprise));
        }

        return entreprisesDto;
    }

    public static Entreprise convertEntrepriseDtoToEntrepriseEntity(EntrepriseDto entrepriseDto) {
        return new Entreprise(entrepriseDto.tva(), entrepriseDto.denomination(),
                AdresseMapper.convertAdresseDtoToAdresseEntity(entrepriseDto.adresseDto()));
    }

    public static List<Entreprise> convertEntrepriseDtoToEntrepriseEntity(List<EntrepriseDto> entreprisesDto) {
        List<Entreprise> entreprises = new ArrayList<>();
        for(EntrepriseDto entrepriseDto : entreprisesDto) {
            entreprises.add(convertEntrepriseDtoToEntrepriseEntity(entrepriseDto));
        }
        return entreprises;
    }
}
