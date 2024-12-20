package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ContactDTO createContact(@RequestBody  ContactCreateDTO contactData) {
        var contact = new Contact();
        contact.setPhone(contactData.getPhone());
        contact.setFirstName(contactData.getFirstName());
        contact.setLastName(contactData.getLastName());
        var newContact = contactRepository.save(contact);
        var contactDTO = new ContactDTO();
        contactDTO.setCreatedAt(newContact.getCreatedAt());
        contactDTO.setPhone(newContact.getPhone());
        contactDTO.setFirstName(newContact.getFirstName());
        contactDTO.setLastName(newContact.getLastName());
        contactDTO.setId(newContact.getId());
        contactDTO.setUpdatedAt(newContact.getUpdatedAt());
        return contactDTO;
    }
    // END
}