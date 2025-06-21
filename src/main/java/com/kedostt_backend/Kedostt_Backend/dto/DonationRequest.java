package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequest {

    private String donorName;

    private BigDecimal amount;

    private String message;

    private Long animalId;
}
