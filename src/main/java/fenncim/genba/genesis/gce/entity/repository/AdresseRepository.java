package fenncim.genba.genesis.gce.entity.repository;

import fenncim.genba.genesis.gce.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
}
