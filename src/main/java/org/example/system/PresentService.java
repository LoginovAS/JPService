package org.example.system;

import org.example.model.PresentType;

public interface PresentService {

    PresentType takePresent(String typeName);
    void addPresents(PresentType presentType, int quantity);

}
