package org.example.system.impl;

import org.example.model.PresentQuantity;
import org.example.model.PresentType;
import org.example.repository.PresentQuantityRepository;
import org.example.repository.PresentTypeRepository;
import org.example.system.PostService;
import org.example.system.PresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PresentServiceImpl implements PresentService {

    @Autowired
    private PresentTypeRepository presentTypeRepository;

    @Autowired
    private PresentQuantityRepository presentQuantityRepository;

    @Override
    public PresentType takePresent(String typeName) {
        PresentType type = findTypeByName(typeName);

        PresentQuantity quantity = presentQuantityRepository.findByPresentType(type).orElse(null);

        if (quantity == null) {
            // TODO: обработка исключения.
        }

        quantity.setQuantity(quantity.getQuantity() - 1);
        presentQuantityRepository.save(quantity);

        return type;
    }

    @Override
    public void addPresents(PresentType presentType, int quantity) {

    }

    private PresentType findTypeByName(String typeName) {
        return presentTypeRepository.findByTypeName(typeName);
    }
}
