package org.example.system;

import org.example.exceptions.EmptyStorageException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.model.PresentQuantity;
import org.example.model.PresentType;

/**
 * Works with present store (PresentType and PresentQuantity repos).
 * Can find, take or add presents.
 */
public interface PresentService {

    static final int REQUEST_BORDER = 3;

    /**
     * Tries to find existing PresentType.
     * If PresentType has been found returns it and decreases quantity by one.
     * If PresentType does not exist throws {@link ResourceNotFoundException}
     */
    PresentType takePresent(PresentType presentType) throws ResourceNotFoundException;

    /**
     * Tries to find existing PresentType.
     * If PresentType has been found just return it.
     * If PresentType does not exist throws {@link ResourceNotFoundException}
     */
    PresentType getPresentType(String typeName) throws ResourceNotFoundException;

    /**
     * Increase PresentType quantity by external request.
     * @param presentType
     * @param quantity
     * @throws ResourceNotFoundException
     */
    void addPresents(PresentType presentType, int quantity) throws ResourceNotFoundException;

    int getQuantity(PresentType presentType) throws ResourceNotFoundException;

}
