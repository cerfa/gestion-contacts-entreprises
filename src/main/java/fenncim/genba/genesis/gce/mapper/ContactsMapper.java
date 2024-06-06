package fenncim.genba.genesis.gce.mapper;

import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.entity.Contact;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public final class ContactsMapper {

    private ContactsMapper() {
        //Clean code
    }

    public static ContactDto convertContactEntityToContactDto(Contact contact) {
        return new ContactDto(contact.getNom(), contact.getPrenom(), contact.getContactType(),
                AdresseMapper.convertAdresseEntityToAdresseDto(contact.getAdresse()),
                EnterprisesMapper.convertListEntrepriseEntityToListEntrepriseDto(contact.getEntreprises()));
    }

    public static List<ContactDto> convertListContactEntityToListContactDto(List<Contact> contacts) {
        List<ContactDto> contactsDto = new ArrayList<>();
        for(Contact contact : contacts) {
            contactsDto.add(convertContactEntityToContactDto(contact));
        }

        return contactsDto;
    }

    public static Contact convertContactDtoToContactEntity(ContactDto contactDto) {
        return new Contact(contactDto.nom(), contactDto.prenom(), contactDto.contactType(), AdresseMapper.convertAdresseDtoToAdresseEntity(contactDto.adresse()),
                EnterprisesMapper.convertEntrepriseDtoToEntrepriseEntity(contactDto.entreprises()));
    }
}
