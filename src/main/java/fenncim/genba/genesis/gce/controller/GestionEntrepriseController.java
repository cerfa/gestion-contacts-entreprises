package fenncim.genba.genesis.gce.controller;


import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.dto.EntrepriseDto;
import fenncim.genba.genesis.gce.exception.EnterpriseDataException;
import fenncim.genba.genesis.gce.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.ENTERPRISE_NOT_FOUND;

@RestController
@RequestMapping("/api/v1/entreprises")
public class GestionEntrepriseController {

   private final EnterpriseService enterpriseService;

   @Autowired
    public GestionEntrepriseController( EnterpriseService enterpriseService) {
       this.enterpriseService = enterpriseService;
    }

    @GetMapping("")
    public ResponseEntity<List<EntrepriseDto>> retrieveEnterprises() {
       return ResponseEntity.ok(this.enterpriseService.retrieveAllEnterprises());
    }
    @PostMapping("/enterprise/create")
    public ResponseEntity<String> registerEnterprise(@RequestBody EntrepriseDto entrepriseDto) throws EnterpriseDataException {
       String result = this.enterpriseService.saveEnterprise(entrepriseDto);
       return ResponseEntity.accepted().body(result);
    }

    @PutMapping("/enterprise/update")
    public ResponseEntity<String> updateContact(@RequestBody EntrepriseDto entrepriseDto) throws EnterpriseDataException {
        String result = this.enterpriseService.updateEnterprise(entrepriseDto);
        return ResponseEntity.accepted().body(result);
    }

    @GetMapping("/enterprise/{tva}/delete")
    public ResponseEntity<String> deleteEnterpriseByTva(@PathVariable("tva") String tva) throws EnterpriseDataException {
       String result = this.enterpriseService.deleteEnterprise(tva);
       if(ENTERPRISE_NOT_FOUND.equals(result)) {
           return ResponseEntity.notFound().build();
       } else {
           return ResponseEntity.ok().body(result);
       }
    }

    @GetMapping("/enterprise/{tva}/find")
    public ResponseEntity<EntrepriseDto> findEnterpriseByTva(@PathVariable("tva") String tva) throws EnterpriseDataException {
        EntrepriseDto foundEnterpriseDto = this.enterpriseService.findEnterpriseGivenTva(tva);
        if(null == foundEnterpriseDto) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(foundEnterpriseDto);
        }
    }

    @PostMapping("/enterprise/{tva}/add/contact")
    public ResponseEntity<String> addContactEnterpriseGivenTva(@PathVariable("tva") String tva, @RequestBody ContactDto contactDto) throws EnterpriseDataException {
        String addedContactResult = this.enterpriseService.addContactToEnterpriseGivenTva(tva, contactDto);
        if(ENTERPRISE_NOT_FOUND.equals(addedContactResult)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ENTERPRISE_NOT_FOUND);
        } else {
            return ResponseEntity.accepted().body(addedContactResult);
        }
    }

 }
