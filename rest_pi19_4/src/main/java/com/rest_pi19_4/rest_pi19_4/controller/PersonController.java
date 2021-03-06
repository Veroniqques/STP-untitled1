package com.rest_pi19_4.rest_pi19_4.controller;

import com.rest_pi19_4.rest_pi19_4.entity.Person;
import com.rest_pi19_4.rest_pi19_4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> readPerson(){
        final List<Person> personList = personService.findAll();

        if (personList != null) {
            return new ResponseEntity<>(personList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Optional<Person>> readPerson(@PathVariable(name = "id" ) Long id){
        final Optional<Person> person = personService.findById(id);

        if (person.isPresent()) {
            return new ResponseEntity<>(person , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/persons")
    public void createPerson(@RequestBody Person person){
        personService.create(person);
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person, @PathVariable(name = "id") Long id) {
        if (personService.update(person, id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(name = "id") Long id){
        if (personService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
