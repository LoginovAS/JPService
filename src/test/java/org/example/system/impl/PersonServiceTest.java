package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.repository.PersonRepository;
import org.example.system.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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

    @Test(expected = ResourceNotFoundException.class)
    public void getPersonExceptionTest() throws ResourceNotFoundException {
        String firstName = "firstName";
        String lastName = "lastName";
        Mockito.when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(Optional.empty());
        personSvc.getPerson(firstName, lastName);
    }

}
