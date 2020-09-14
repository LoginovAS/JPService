package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.example.repository.PresentQuantityRepository;
import org.example.repository.PresentTypeRepository;
import org.example.system.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentServiceImpl implements PresentService {

    @Autowired
    private PresentTypeRepository presentTypeRepository;

    @Autowired
    private PresentQuantityRepository presentQuantityRepository;

    @Override
    public PresentType takePresent(String typeName) throws ResourceNotFoundException {
        PresentType presentType = getPresentType(typeName);
        decrementQuantity(presentType);

        return presentType;
    }

    public PresentType getPresentType(String typeName) throws ResourceNotFoundException {
        return presentTypeRepository.findByTypeName(typeName).orElseThrow(() -> new ResourceNotFoundException("Present type not found"));
    }

    private void decrementQuantity(PresentType presentType) throws ResourceNotFoundException {
        PresentQuantity presentQuantity = getPresentQuantity(presentType);
        updatePresentQuantity(presentQuantity, presentQuantity.getQuantity() - 1);
    }

    @Override
    public void addPresents(PresentType presentType, int quantity) throws ResourceNotFoundException {
        PresentQuantity presentQuantity = getPresentQuantity(presentType);
        updatePresentQuantity(presentQuantity, presentQuantity.getQuantity() + quantity);
    }

    private void updatePresentQuantity(PresentQuantity quantity, int newQuantity) {
        quantity.setQuantity(newQuantity);
        presentQuantityRepository.save(quantity);
    }

    private PresentQuantity getPresentQuantity(PresentType presentType) throws ResourceNotFoundException {
        return presentQuantityRepository
                .findByPresentType(presentType)
                .orElseThrow(() -> new ResourceNotFoundException("Present type not found"));
    }
}
