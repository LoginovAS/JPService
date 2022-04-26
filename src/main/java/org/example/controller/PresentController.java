package org.example.controller;

import org.example.exceptions.BehaviorException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.modelui.Response;
import org.example.modelui.Request;
import org.example.modelui.SupplyNotification;
import org.example.system.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PresentController {

    @Autowired
    private Dispatcher dispatcher;

    @GetMapping("/request")
    public ResponseEntity<Response> requestPresent(@Valid @RequestBody Request request) throws ResourceNotFoundException, BehaviorException {
        return ResponseEntity.ok(dispatcher.processUserRequest(request));
    }

    @PostMapping("/supply")
    public ResponseEntity<String> supplyPresents(@Valid @RequestBody SupplyNotification notification) throws ResourceNotFoundException {
        dispatcher.processFactoryNotification(notification);
        return ResponseEntity.ok("Presents have been added");
    }

}
