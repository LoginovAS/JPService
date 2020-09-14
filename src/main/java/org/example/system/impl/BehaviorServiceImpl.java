package org.example.system.impl;

import org.example.model.Person;
import org.example.system.BehaviorService;
import org.springframework.stereotype.Service;

@Service
public class BehaviorServiceImpl implements BehaviorService {
    @Override
    public boolean requestBehavior(Person person) {
        int random = (int)(Math.random() * 100000);
        return random % 2 == 0;
    }
}
