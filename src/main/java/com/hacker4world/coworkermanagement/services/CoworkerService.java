package com.hacker4world.coworkermanagement.services;

import com.hacker4world.coworkermanagement.dtos.coworker.CoworkerDto;
import com.hacker4world.coworkermanagement.dtos.ResponseDto;
import com.hacker4world.coworkermanagement.entities.Coworker;
import com.hacker4world.coworkermanagement.repositories.CoworkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoworkerService {

    private final CoworkerRepository coworkerRepository;

    @Autowired
    public CoworkerService(CoworkerRepository coworkerRepository) {
        this.coworkerRepository = coworkerRepository;
    }

    public ResponseDto<Void> addNewCoworker(CoworkerDto coworkerData) {
        Optional<Coworker> existingCoworker = coworkerRepository.findByEmail(coworkerData.getEmail());

        if (existingCoworker.isPresent()) return new ResponseDto<>(400, "A coworker with this email already exists");

        Coworker newCoworker = new Coworker(
                coworkerData.getFirstName(), coworkerData.getLastName(), coworkerData.getEmail(), coworkerData.getPhone()
        );

        coworkerRepository.save(newCoworker);

        return new ResponseDto<>(200, "Coworker added successfully");

    }

    public ResponseDto<List<Coworker>> getAllCoworkers() {
        List<Coworker> coworkers = coworkerRepository.findAll();
        return new ResponseDto<>(200, "Coworkers found", coworkers);
    }

    public ResponseDto<Void> deleteCoworker(int id) {
        coworkerRepository.deleteById(id);
        return new ResponseDto<>(200, "Coworker deleted successfully");
    }

    public ResponseDto<Void> updateCoworkerDetails(CoworkerDto coworkerData) {
        Optional<Coworker> existingCoworker = coworkerRepository.findByEmail(coworkerData.getEmail());

        if (existingCoworker.isEmpty()) return new ResponseDto<>(404, "A coworker with this email does not exist");

        Coworker coworker = existingCoworker.get();

        coworker.setFirstName(coworkerData.getFirstName());
        coworker.setLastName(coworkerData.getLastName());
        coworker.setEmail(coworkerData.getEmail());
        coworker.setPhone(coworkerData.getPhone());

        coworkerRepository.save(coworker);

        return new ResponseDto<>(200, "Coworker updated successfully");
    }

}
