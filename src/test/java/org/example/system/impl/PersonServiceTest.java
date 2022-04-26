package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.example.system.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public PersonService personService() {
            return new PersonServiceImpl();
        }

    }

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personSvc;

    /**
     * Tries to get Person by incorrect name.
     * Expects ResourceNotFoundException when Person not found.
     * @throws ResourceNotFoundException
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testGetPersonResourceNotFoundException() throws ResourceNotFoundException {
        String firstName = "firstName";
        String lastName = "lastName";
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.empty());
        personSvc.getPerson(firstName, lastName);
    }

    @Test
    public void testGetPersonSuccess() throws ResourceNotFoundException {
        String firstName = "firstName";
        String lastName = "lastName";

        Person person = new Person();
        person.setFirstName(firstName);
        person.setFirstName(lastName);
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.of(person));

        Assert.assertEquals(person, personSvc.getPerson(firstName, lastName));

    }

}
