package org.example.system.impl;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.system.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPerson(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName).orElse(null);
    }
}
