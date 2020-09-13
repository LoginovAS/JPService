package org.example.system.impl;

import org.example.system.BehaviorService;
import org.springframework.stereotype.Service;

public class BehaviorServiceImpl implements BehaviorService {
    @Override
    public boolean requestBehavior() {
        return true;
    }
}
