package fenncim.genba.genesis.gce.controller;


import fenncim.genba.genesis.gce.dto.ContactDto;
import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fenncim.genba.genesis.gce.util.Constants.CONTACT_NOT_FOUND;

@RestController
@RequestMapping("/api/v1/contacts")
public class GestionContactsController {

   private final ContactService contactService;

   @Autowired
    public GestionContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("")
    public ResponseEntity<List<ContactDto>> retrieveContacts() {
       return ResponseEntity.ok(this.contactService.retrieveALL());
    }
    @PostMapping("/contact")
    public ResponseEntity<String> registerContact(@RequestBody ContactDto contactDto) throws ContactDataException {
       String result = this.contactService.saveContact(contactDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/contact")
    public ResponseEntity<String> updateContact(@RequestBody ContactDto contactDto) throws ContactDataException {
        String result = this.contactService.updateContact(contactDto);
        return ResponseEntity.accepted().body(result);
    }

    @DeleteMapping("/contact")
    public ResponseEntity<String> deleteContact(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws ContactDataException {
       String result = this.contactService.deleteContact(nom, prenom);
       if(CONTACT_NOT_FOUND.equals(result)) {
           return ResponseEntity.notFound().build();
       } else {
           return ResponseEntity.ok().body(result);
       }
    }

 }
