package br.com.poc.controller;

import br.com.poc.dto.PersonDTO;
import br.com.poc.dto.PersonTransferDTO;
import br.com.poc.entidade.Task;
import br.com.poc.entidade.User;
import br.com.poc.service.UserService;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PersonTransferDTO> getPersons() throws IOException, BiffException {
        PersonTransferDTO personTransferDTO = new PersonTransferDTO();

        List<PersonDTO> persons = new ArrayList<>();

        for(User user : userService.list()){
            PersonDTO personDTO = new PersonDTO(user.getId(), user.getNameUser(), user.getEmail());
            personTransferDTO.getPersons().add(personDTO);
        }

        personTransferDTO.setTotal(userService.list().size());


        return ResponseEntity.status(HttpStatus.OK).body(personTransferDTO);
    }

    /*@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() throws IOException, BiffException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.list());
    }
     */

}
