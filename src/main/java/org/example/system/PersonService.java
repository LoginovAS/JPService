package org.example.system;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.Person;

public interface PersonService {

    Person getPerson(String firstName, String lastName) throws ResourceNotFoundException;

}
