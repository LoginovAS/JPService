package org.example.system;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.PresentType;

/**
 * Works with present store. Can find, take or add presents.
 */
public interface PresentService {

    /**
     * Tries to find existing PresentType.
     * If PresentType has been found return it and decrease quantity by one.
     * If PresentType does not exist throws {@link ResourceNotFoundException}
     */
    PresentType takePresent(String typeName) throws ResourceNotFoundException;

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

}
