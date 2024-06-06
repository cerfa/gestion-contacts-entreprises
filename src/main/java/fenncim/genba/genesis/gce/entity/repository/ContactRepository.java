package fenncim.genba.genesis.gce.entity.repository;

import fenncim.genba.genesis.gce.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public List<Contact> findByDeleteDateNotNull();
    public List<Contact> findByDeleteDateIsNull();
    public Contact findByNomAndPrenom(String nom, String prenom);
}
