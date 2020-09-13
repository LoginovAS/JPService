package org.example.controller;

import org.example.modelui.Request;
import org.example.modelui.Response;
import org.example.system.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/jps")
public class PresentController {

    @Autowired
    private Dispatcher dispatcher;

    @GetMapping("/request")
    public Response requestPresent(@Valid @RequestBody Request request) {
        return dispatcher.processRequest(request);
    }

    @PostMapping("/supply")
    public ResponseEntity<Response> supplyPresents(@Valid @RequestBody Request request) {
        return null;
    }

}
