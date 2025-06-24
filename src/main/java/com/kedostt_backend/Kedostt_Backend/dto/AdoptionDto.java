package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionDto {

    private Long id;

    private String fullName;

    private Integer age;

    private String occupation;

    private String phoneNumber;

    private String email;

    private String note;

    private Long animalId;
}
