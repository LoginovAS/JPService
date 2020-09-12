package org.example.controller;

import org.example.modelui.Request;
import org.example.modelui.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/jps")
public class PresentController {

    @PostMapping
    public Response requestPresent(@Valid @RequestBody Request request) {
        return null;
    }

}
