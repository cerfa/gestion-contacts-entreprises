package fenncim.genba.genesis.gce.service;


import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.dto.EntrepriseDto;
import fenncim.genba.genesis.gce.entity.Entreprise;
import fenncim.genba.genesis.gce.entity.repository.ContactRepository;
import fenncim.genba.genesis.gce.entity.repository.EntrepriseRepository;
import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.exception.EnterpriseDataException;
import fenncim.genba.genesis.gce.mapper.EnterprisesMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.CONTACT_ADDED_ENTERPRISE;
import static fenncim.genba.genesis.gce.util.Constants.CREATED;
import static fenncim.genba.genesis.gce.util.Constants.DELETED;
import static fenncim.genba.genesis.gce.util.Constants.ENTERPRISE_NOT_FOUND;
import static fenncim.genba.genesis.gce.util.Constants.UPDATED;
import static fenncim.genba.genesis.gce.util.Constants.UPDATE_ENTERPRISE_ERROR;

@Service
public class EnterpriseService {
    private final EntrepriseRepository entrepriseRepository;

    private final ContactRepository contactRepository;
    public EnterpriseService(EntrepriseRepository entrepriseRepository, ContactRepository contactRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.contactRepository = contactRepository;
    }
    public List<EntrepriseDto> retrieveAllEnterprises() {
     return EnterprisesMapper.convertListEntrepriseEntityToListEntrepriseDto(this.entrepriseRepository.findByDeleteDateIsNull());
    }

    public String saveEnterprise(EntrepriseDto  entrepriseDto) throws EnterpriseDataException {
        try{
            var enterprise = EnterprisesMapper.convertEntrepriseDtoToEntrepriseEntity(entrepriseDto);
            enterprise.setCreationDate(new Timestamp(System.currentTimeMillis()));
            this.entrepriseRepository.saveAndFlush(enterprise);
        } catch (Exception except) {
            throw new EnterpriseDataException(except.getMessage());
        }
        return CREATED;
    }

    public String updateEnterprise(EntrepriseDto  entrepriseDto) throws EnterpriseDataException {
        try{
            var currentEnterprise = retrieveEnterprise(entrepriseDto.tva());
            if(null ==  currentEnterprise) {
                return ENTERPRISE_NOT_FOUND;
            }
            var updatedEnterprise = EnterprisesMapper.convertEntrepriseDtoToEntrepriseEntity(entrepriseDto);
            Integer enterpriseId  = currentEnterprise.getEnterpriseId();
            Timestamp creationDate = currentEnterprise.getCreationDate();
            BeanUtils.copyProperties(updatedEnterprise, currentEnterprise);
            currentEnterprise.setEnterpriseId(enterpriseId);
            currentEnterprise.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            currentEnterprise.setCreationDate(creationDate);
            this.entrepriseRepository.saveAndFlush(currentEnterprise);

        } catch (Exception except) {
            throw new EnterpriseDataException(except.getMessage());
        }
        return UPDATED;
    }

    public EntrepriseDto findEnterpriseGivenTva(String tva) throws EnterpriseDataException {
        try{
            var enterprise = retrieveEnterprise(tva);
            if(null == enterprise) {
               return null;
            }

            return EnterprisesMapper.convertEntrepriseEntityToEntrepriseDto(enterprise);
        } catch (Exception except) {
            throw new EnterpriseDataException(except.getMessage());
        }
    }

    public String addContactToEnterpriseGivenTva(String tva, ContactDto contactDto) throws EnterpriseDataException {
        try{
            var enterprise = retrieveEnterprise(tva);
            if(null == enterprise) {
                return ENTERPRISE_NOT_FOUND;
            }

            var contact = contactRepository.findByNomAndPrenom(contactDto.nom(), contactDto.prenom());
            if(null == contact) {
                throw new ContactDataException("Contact inexistant");
            }

            enterprise.getContacts().add(contact);
            entrepriseRepository.saveAndFlush(enterprise);

            return CONTACT_ADDED_ENTERPRISE;
        } catch (Exception except) {
            throw new EnterpriseDataException(UPDATE_ENTERPRISE_ERROR.concat(except.getMessage()));
        }
    }


    public String deleteEnterprise(String tva) throws EnterpriseDataException {
        try{
            var enterprise = retrieveEnterprise(tva);
            enterprise.setDeleteDate(new Timestamp(System.currentTimeMillis()));
            this.entrepriseRepository.saveAndFlush(enterprise);
        } catch (Exception except) {
            throw new EnterpriseDataException(except.getMessage());
        }
        return DELETED;
    }

    private Entreprise retrieveEnterprise(String tva) {
        return this.entrepriseRepository.findByTva(tva);
    }
}
