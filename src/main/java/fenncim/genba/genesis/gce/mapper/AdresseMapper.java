package fenncim.genba.genesis.gce.mapper;

import fenncim.genba.genesis.gce.dto.AdresseDto;
import fenncim.genba.genesis.gce.entity.Adresse;

public final class AdresseMapper {
    private AdresseMapper() {
        //clean code
    }
    public static AdresseDto convertAdresseEntityToAdresseDto(Adresse adresse) {
        return new AdresseDto(adresse.getRue(), adresse.getNumeroDeRue(), adresse.getBoite(), adresse.getVille(), adresse.getCap());
    }

    public static Adresse convertAdresseDtoToAdresseEntity(AdresseDto adresseDto) {
        return new Adresse(adresseDto.rue(), adresseDto.numeroDeRue(), adresseDto.boite(),
                adresseDto.ville(), adresseDto.cap());
    }
}
