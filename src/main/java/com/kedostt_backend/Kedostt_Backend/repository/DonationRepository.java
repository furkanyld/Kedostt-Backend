package com.kedostt_backend.Kedostt_Backend.repository;

import com.kedostt_backend.Kedostt_Backend.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
