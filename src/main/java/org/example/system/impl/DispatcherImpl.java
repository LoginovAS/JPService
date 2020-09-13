package org.example.system.impl;

import org.example.model.Person;
import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.example.modelui.Request;
import org.example.modelui.Response;
import org.example.repository.PersonRepository;
import org.example.repository.PresentTypeRepository;
import org.example.system.Dispatcher;
import org.example.system.PersonService;
import org.example.system.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatcherImpl implements Dispatcher {

    @Autowired
    private PresentService presentSvc;

    @Autowired
    private PersonService personSvc;

    @Override
    public Response processRequest(Request request) {
        Person person = personSvc.getPerson(request.getFirstName(), request.getLastName());

        PresentType presentType = presentSvc.takePresent(request.getPresentType());

        Response response = new Response(person.getFirstName(), person.getLastName(), presentType.getTypeName());

        return response;
    }

    private Person findPerson(String firstName, String lastName) {
        return null;
    }
}
