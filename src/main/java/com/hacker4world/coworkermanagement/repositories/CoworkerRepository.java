package com.hacker4world.coworkermanagement.repositories;

import com.hacker4world.coworkermanagement.entities.Coworker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoworkerRepository extends JpaRepository<Coworker, Integer> {
    Optional<Coworker> findByEmail(String email);
}
