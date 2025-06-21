package com.kedostt_backend.Kedostt_Backend.repository;

import com.kedostt_backend.Kedostt_Backend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
