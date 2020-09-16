package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.Person;
import org.example.model.PresentType;
import org.example.modelui.*;
import org.example.system.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.validation.ValidationUtil.*;

@Service
public class DispatcherImpl implements Dispatcher {

    @Autowired
    private PresentService presentSvc;

    @Autowired
    private PersonService personSvc;

    @Autowired
    private BehaviorService behaviorSvc;

    @Autowired
    private PostService postSvc;

    @Override
    public Response processUserRequest(Request request) throws ResourceNotFoundException {
        checkRequest(request);

        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String present = request.getPresentType();

        Person person = personSvc.getPerson(firstName, lastName);

        boolean behavior = behaviorSvc.requestBehavior(person);

        if (!behavior) {
            return new BadResponse(firstName, lastName, present);
        }

        PresentType presentType = presentSvc.takePresent(present);

        postSvc.sendPresentTo(presentType, person);

        return new SuccessResponse(person.getFirstName(), person.getLastName(), presentType.getTypeName());
    }

    private void checkRequest(Request request) {
        checkNotNull(request);
        checkNotEmpty(request.getFirstName());
        checkNotEmpty(request.getLastName());
        checkNotEmpty(request.getPresentType());
    }

    @Override
    public void processFactoryNotification(SupplyNotification notification) throws ResourceNotFoundException {
        checkNotification(notification);

        String presentTypeName = notification.getPresentType();
        int quantity = notification.getQuantity();

        PresentType presentType = presentSvc.getPresentType(presentTypeName);

        presentSvc.addPresents(presentType, quantity);
    }

    private void checkNotification(SupplyNotification notification) {
        checkNotNull(notification);
        checkNotEmpty(notification.getPresentType());
        checkPositive(notification.getQuantity());
    }

}
