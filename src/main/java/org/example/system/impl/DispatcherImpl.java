package org.example.system.impl;

import org.example.exceptions.BehaviorException;
import org.example.exceptions.EmptyStorageException;
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
    public Response processUserRequest(Request request) throws ResourceNotFoundException, BehaviorException {
        checkRequest(request);

        Person person = personSvc.getPerson(request.getFirstName(), request.getLastName());

        controlBehavior(person);

        PresentType presentType = presentSvc.getPresentType(request.getPresentType());

        controlPresentQuantity(presentType);

        postSvc.sendPresentTo(presentSvc.takePresent(presentType), person);

        return new SuccessResponse(person.getFirstName(), person.getLastName(), presentType.getTypeName());
    }

    private void checkRequest(Request request) {
        checkNotNull(request);
        checkNotEmpty(request.getFirstName());
        checkNotEmpty(request.getLastName());
        checkNotEmpty(request.getPresentType());
    }

    private void controlPresentQuantity(PresentType presentType) throws ResourceNotFoundException {
        int presentQuantity = presentSvc.getQuantity(presentType);

        if (notEnoughQuantityCritical(presentQuantity)) {
            postSvc.requestProduction(presentType, PresentService.REQUEST_BORDER * 2);
            throw new EmptyStorageException("All storages are empty now. Request already sent to factory");
        }

        if (notEnoughQuantityWarning(presentQuantity)) {
            postSvc.requestProduction(presentType, PresentService.REQUEST_BORDER + 1);
        }
    }

    private void controlBehavior(Person person) throws BehaviorException {
        boolean behavior = behaviorSvc.requestBehavior(person);

        if (!behavior) {
            throw new BehaviorException(person + " cannot receive the present because of bad behavior");
        }
    }

    private boolean notEnoughQuantityWarning(int quantity) {
        return quantity < PresentService.REQUEST_BORDER;
    }

    private boolean notEnoughQuantityCritical(int quantity) {
        return quantity < 1;
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
