package com.example.asadmin.api;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.service.DiningSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dining-session")
public class DiningSessionController {

    @Autowired
    DiningSessionService service;

    @PostMapping("")
    public DiningSessionDTO createDiningSession(){
        return service.create();
    }
}
