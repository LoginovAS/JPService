package org.example.system;

import org.example.model.Person;
import org.example.model.PresentType;

public interface PostService {

    void sendPresentTo(PresentType present, Person person);
    default void requestProduction(PresentType presentType) {

    }

}
