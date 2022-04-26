package org.example.system.impl;

import org.example.exceptions.BadArgumentException;
import org.example.exceptions.BehaviorException;
import org.example.exceptions.EmptyStorageException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.model.Person;
import org.example.model.PresentType;
import org.example.modelui.BadResponse;
import org.example.modelui.Request;
import org.example.modelui.Response;
import org.example.modelui.SuccessResponse;
import org.example.system.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DispatcherTest {

    @MockBean
    private PersonService personSvc;

    @MockBean
    private BehaviorService behaviorSvc;

    @MockBean
    private PresentService presentSvc;

    @MockBean
    private PostService postSvc;

    @Autowired
    private Dispatcher dispatcher;

    private Request request;
    private Response successResponse;
    private Response badResponse;
    private Person person;
    private PresentType presentType;

    @TestConfiguration
    public static class DispatcherImplTestContextConfiguration {

        @Bean
        public Dispatcher dispatcher() {
            return new DispatcherImpl();
        }

    }

    @Before
    public void setUp() throws ResourceNotFoundException {
        String firstName = "firstName";
        String lastName = "lastName";
        String typeName = "typeName";

        person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        request = new Request();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPresentType(typeName);

        presentType = new PresentType();
        presentType.setTypeName(typeName);

        successResponse = new SuccessResponse(firstName, lastName, typeName);
        badResponse = new BadResponse(firstName, lastName, typeName);

        when(personSvc.getPerson(firstName, lastName)).thenReturn(person);
        when(presentSvc.takePresent(presentType)).thenReturn(presentType);
        when(presentSvc.getPresentType(request.getPresentType())).thenReturn(presentType);

        doNothing().when(postSvc).requestProduction(presentType, PresentService.REQUEST_BORDER * 2);
        doNothing().when(postSvc).sendPresentTo(presentType, person);
    }

    /**
     * Sends correct request and expects success response.
     * @throws ResourceNotFoundException
     */
    @Test
    public void testProcessUserRequestSuccessResponse() throws ResourceNotFoundException, BehaviorException {
        when(behaviorSvc.requestBehavior(person)).thenReturn(true);
        when(presentSvc.getQuantity(presentType)).thenReturn(5);
        Response actualResponse = dispatcher.processUserRequest(request);

        Assert.assertEquals(successResponse.getMessage(), actualResponse.getMessage());
    }

    /**
     * Sends correct request and expects bad response.
     * @throws ResourceNotFoundException
     */
    @Test(expected = BehaviorException.class)
    public void testProcessUserRequestBadResponse() throws ResourceNotFoundException, BehaviorException {
        when(behaviorSvc.requestBehavior(person)).thenReturn(false);
        dispatcher.processUserRequest(request);
    }

    /**
     * Expects EmptyStorageException when PresentType quantity < 1.
     * @throws ResourceNotFoundException
     * @throws BehaviorException
     */
    @Test(expected = EmptyStorageException.class)
    public void testProcessUserRequestEmptyStorageException() throws ResourceNotFoundException, BehaviorException {
        when(behaviorSvc.requestBehavior(person)).thenReturn(true);
        when(presentSvc.getQuantity(presentType)).thenReturn(0);

        dispatcher.processUserRequest(request);
    }

    /**
     * Expects BadArgumentException in case of null request.
     * @throws ResourceNotFoundException
     */
    @Test(expected = BadArgumentException.class)
    public void testProcessUserRequestExceptionWhenRequestEmpty() throws ResourceNotFoundException, BehaviorException {
        dispatcher.processUserRequest(null);
    }

    /**
     * Expects BadArgumentException in case of request with empty fields.
     * @throws ResourceNotFoundException
     */
    @Test(expected = BadArgumentException.class)
    public void testProcessUserRequestExceptionWhenParamsEmpty() throws ResourceNotFoundException, BehaviorException {
        Request request = new Request();
        dispatcher.processUserRequest(request);
    }

}
