package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;

import java.util.List;

public interface AdoptionService {

    AdoptionDto createAdoption(AdoptionDto dto);

    List<AdoptionDto> getAllAdoptions();
}
