package org.example.system.impl;

import org.example.model.Person;
import org.example.model.PresentToPerson;
import org.example.model.PresentType;
import org.example.modelui.SupplyNotification;
import org.example.repository.PresentToPersonRepository;
import org.example.system.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PresentToPersonRepository presentToPersonRepository;

    @Override
    public void sendPresentTo(PresentType present, Person person) {
        PresentToPerson presentToPerson = new PresentToPerson(present, person);
        presentToPersonRepository.save(presentToPerson);
    }

    public void requestProduction(PresentType presentType, int quantity) {
        Thread thread = new Thread(() -> {
            RestTemplate restTemplate = new RestTemplate();
            SupplyNotification supplyNotification = new SupplyNotification();
            supplyNotification.setPresentType(presentType.getTypeName());
            supplyNotification.setQuantity(quantity);
            restTemplate.postForEntity("http://localhost:8190/api/produce", supplyNotification, Integer.class);
        });

        thread.start();
    }

}
