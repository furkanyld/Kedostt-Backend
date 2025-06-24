package com.kedostt_backend.Kedostt_Backend.repository;

import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
