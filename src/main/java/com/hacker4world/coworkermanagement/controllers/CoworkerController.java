package com.hacker4world.coworkermanagement.controllers;

import com.hacker4world.coworkermanagement.dtos.coworker.CoworkerDto;
import com.hacker4world.coworkermanagement.dtos.ResponseDto;
import com.hacker4world.coworkermanagement.entities.Coworker;
import com.hacker4world.coworkermanagement.services.CoworkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("coworker")
public class CoworkerController {
    private final CoworkerService coworkerService;

    @Autowired
    public CoworkerController(CoworkerService coworkerService) {
        this.coworkerService = coworkerService;
    }

    @PostMapping("add")
    public ResponseDto<Void> addCoworker(@RequestBody CoworkerDto coworkerData) {
        return coworkerService.addNewCoworker(coworkerData);
    }

    @GetMapping("all")
    public ResponseDto<List<Coworker>> getAllCoworkers() {
        return coworkerService.getAllCoworkers();
    }

    @DeleteMapping("delete/{coworkerId}")
    public ResponseDto<Void> deleteCoworker(@PathVariable int coworkerId) {
        return coworkerService.deleteCoworker(coworkerId);
    }

    @PutMapping("edit")
    public ResponseDto<Void> editCoworker(@RequestBody CoworkerDto coworkerData) {
        return coworkerService.updateCoworkerDetails(coworkerData);
    }

}
