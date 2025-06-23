package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationDto {

    private Long id;

    private String donorName;

    private BigDecimal amount;

    private String message;

    private Long animalId;

    private LocalDateTime createdAt;
}
