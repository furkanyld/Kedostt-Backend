package com.kedostt_backend.Kedostt_Backend.repository;

import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import com.kedostt_backend.Kedostt_Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findAllByUser(User user);
}
