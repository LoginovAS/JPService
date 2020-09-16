package org.example.system.impl;

import org.example.exceptions.ResourceNotFoundException;
import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.example.modelui.SupplyNotification;
import org.example.repository.PresentQuantityRepository;
import org.example.repository.PresentTypeRepository;
import org.example.system.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PresentServiceImpl implements PresentService {

    private static final int REQUEST_BORDER = 3;

    @Autowired
    private PresentTypeRepository presentTypeRepository;

    @Autowired
    private PresentQuantityRepository presentQuantityRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PresentType takePresent(String typeName) throws ResourceNotFoundException {
        PresentType presentType = getPresentType(typeName);
        PresentQuantity presentQuantity = getPresentQuantity(presentType);

        // Attempt to create external call.
        // Should be corrected after theory learning.
        // checkQuantity(presentQuantity);

        decrementQuantity(presentQuantity);

        return presentType;
    }

    public PresentType getPresentType(String typeName) throws ResourceNotFoundException {
        return presentTypeRepository.findByTypeName(typeName).orElseThrow(() -> new ResourceNotFoundException("Present type not found"));
    }

    private void decrementQuantity(PresentQuantity presentQuantity) {
        updatePresentQuantity(presentQuantity, presentQuantity.getQuantity() - 1);
    }

    // Attempt to create external call.
    // Should be corrected after theory learning.
    private void checkQuantity(PresentQuantity presentQuantity) {
        int quantity = presentQuantity.getQuantity();
        if (quantity < REQUEST_BORDER) {
            SupplyNotification supplyNotification = new SupplyNotification();
            supplyNotification.setPresentType(presentQuantity.getPresentType().getTypeName());
            supplyNotification.setQuantity(3);
            restTemplate.postForEntity("http://localhost:8190/api/produce", supplyNotification, Integer.class);
        }
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
