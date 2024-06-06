package fenncim.genba.genesis.gce.entity.repository;

import fenncim.genba.genesis.gce.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    public List<Entreprise> findByDeleteDateIsNull();
    public Entreprise findByTva(String tva);
}
