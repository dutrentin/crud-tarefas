package br.com.poc.controller;

import br.com.poc.dto.PersonDTO;
import br.com.poc.dto.PersonTransferDTO;
import br.com.poc.entidade.Person;
import br.com.poc.entidade.Task;
import br.com.poc.exception.GenericPersistenciaException;
import br.com.poc.service.PersonService;
import io.swagger.annotations.ApiOperation;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PersonController {

    private static final Logger log = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonTransferDTO> getPersons() throws IOException, BiffException {
        PersonTransferDTO personTransferDTO = new PersonTransferDTO();

        List<PersonDTO> persons = new ArrayList<>();

        for(Person person : personService.list()){
            PersonDTO personDTO = new PersonDTO(person.getId(), person.getName(), person.getEmail());
            personTransferDTO.getPersons().add(personDTO);
        }

        personTransferDTO.setTotal(personTransferDTO.getPersons().size());


        return ResponseEntity.status(HttpStatus.OK).body(personTransferDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Integer idperson) throws IOException, BiffException {
        PersonDTO personDTO = new PersonDTO();

        Person person  = personService.findPersonById(idperson);
        if(person != null){
            personDTO.setEmail(person.getEmail());
            personDTO.setId(person.getId());
            personDTO.setName(person.getName());
        }

        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Produces("application/json")
    @Consumes("application/json")
    public ResponseEntity<Void> save(@RequestBody PersonDTO person) {
        Person newPerson = new Person();
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());

        this.personService.save(newPerson);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand("").toUri();

        return ResponseEntity.created(uri).build();
    }

    /*@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<person>> getpersons() throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(personService.list());
    }
     */

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Altera os dados da entidade Tarefa")
    public void updateTask(@RequestBody Person person){

        try {
            this.personService.update(person);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove os dados da entidade Tarefa")
    public void removeTask(@PathVariable Integer id){

        try {
            this.personService.remove(id);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            throw new GenericPersistenciaException(e.getLocalizedMessage());
        }
    }

}
