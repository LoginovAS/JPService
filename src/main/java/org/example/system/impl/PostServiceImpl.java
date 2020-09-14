package org.example.system.impl;

import org.example.model.Person;
import org.example.model.PresentToPerson;
import org.example.model.PresentType;
import org.example.repository.PresentToPersonRepository;
import org.example.system.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PresentToPersonRepository presentToPersonRepository;

    @Override
    public void sendPresentTo(PresentType present, Person person) {
        PresentToPerson presentToPerson = new PresentToPerson(present, person);
        presentToPersonRepository.save(presentToPerson);
    }

}
