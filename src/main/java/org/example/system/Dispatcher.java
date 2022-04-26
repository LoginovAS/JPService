package org.example.system;

import org.example.exceptions.BehaviorException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.modelui.Response;
import org.example.modelui.Request;
import org.example.modelui.SupplyNotification;

/**
 * Dispatcher processes external requests and decides to satisfy or not them.
 */
public interface Dispatcher {

    Response processUserRequest(Request request) throws ResourceNotFoundException, BehaviorException;
    void processFactoryNotification(SupplyNotification notification) throws ResourceNotFoundException;

}
