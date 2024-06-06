package fenncim.genba.genesis.gce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages ="fenncim.genba")
@EntityScan("fenncim.genba.genesis.gce.entity")
@EnableJpaRepositories("fenncim.genba.genesis.gce.entity.repository")
public class GestionContactEntrepriseVimBeServer {

	public static void main(String[] args) {
		SpringApplication.run(GestionContactEntrepriseVimBeServer.class, args);
	}

}
