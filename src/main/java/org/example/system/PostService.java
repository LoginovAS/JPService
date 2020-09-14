package org.example.system;

import org.example.model.Person;
import org.example.model.PresentType;

/**
 * Sends presents to people or requests to factory to produce new presents.
 * In common case can send some async requests.
 */
public interface PostService {

    /**
     * Sends specified present to specified person.
     * Logs this action into DB.
     * @param present
     * @param person
     */
    void sendPresentTo(PresentType present, Person person);

    /**
     * Send request to external system to produce specified
     * quantity of specified present type.
     * @param presentType
     */
    default void requestProduction(PresentType presentType, int quantity) {

    }

}
