package fenncim.genba.genesis.gce.service;

import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.entity.Contact;
import fenncim.genba.genesis.gce.entity.repository.ContactRepository;
import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.mapper.ContactsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.CONTACT_NOT_FOUND;
import static fenncim.genba.genesis.gce.util.Constants.CREATED;
import static fenncim.genba.genesis.gce.util.Constants.DELETED;
import static fenncim.genba.genesis.gce.util.Constants.DELETED_CONTACT_ERROR;
import static fenncim.genba.genesis.gce.util.Constants.SAVE_CONTACT_ERROR;
import static fenncim.genba.genesis.gce.util.Constants.UPDATED;
import static fenncim.genba.genesis.gce.util.Constants.UPDATE_CONTACT_ERROR;

@Service
public class ContactService {
    private final ContactRepository contactsRepository;

    @Autowired
    public ContactService(ContactRepository contactsRepository) {
      this.contactsRepository = contactsRepository;
    }

    public List<ContactDto> retrieveALL() {
     return ContactsMapper.convertListContactEntityToListContactDto(contactsRepository.findByDeleteDateIsNull());
    }

    public String saveContact(ContactDto contactDto) throws ContactDataException {
        try {
            Contact contact = ContactsMapper.convertContactDtoToContactEntity(contactDto);
                            contact.setCreationDate(new Timestamp(System.currentTimeMillis()));
            this.contactsRepository.saveAndFlush(contact);

        } catch (Exception except) {
            throw new ContactDataException(SAVE_CONTACT_ERROR.concat(except.getMessage()));
        }

        return CREATED;
    }

    @Transactional
    public String updateContact(ContactDto contactDto) throws ContactDataException {
        try {
            Contact contact = retrieveContact(contactDto.nom(), contactDto.prenom());
            if (null == contact) {
                return CONTACT_NOT_FOUND;
            }
             Integer contactId = contact.getContactId();
             Integer adresseId = contact.getAdresse().getAdresseId();
            Timestamp  contactCreationDate = contact.getCreationDate();
            Contact updatedContact = ContactsMapper.convertContactDtoToContactEntity(contactDto);
            BeanUtils.copyProperties(updatedContact, contact);

            contact.getAdresse().setAdresseId(adresseId);
            contact.setContactId(contactId);
            contact.setCreationDate(contactCreationDate);
            contact.setUpdateDate(new Timestamp(System.currentTimeMillis()));

            this.contactsRepository.saveAndFlush(contact);
        } catch(Exception except) {
            throw new ContactDataException(UPDATE_CONTACT_ERROR.concat(except.getMessage()));
        }
        return UPDATED;
    }

    @Transactional
    public String deleteContact(String nom, String prenom) throws ContactDataException {
        try {
             Contact contact = retrieveContact(nom, prenom);
            if(null  == contact) {
                return CONTACT_NOT_FOUND;
            }

            contact.setDeleteDate(new Timestamp(System.currentTimeMillis()));
            this.contactsRepository.saveAndFlush(contact);
        } catch(Exception except) {
            throw new ContactDataException(DELETED_CONTACT_ERROR.concat(except.getMessage()));
        }

        return DELETED;
    }

    private Contact retrieveContact(String nom, String prenom) {
        return this.contactsRepository.findByNomAndPrenom(nom, prenom);
    }
}
