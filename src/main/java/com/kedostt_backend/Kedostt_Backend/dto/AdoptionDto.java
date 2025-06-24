package com.kedostt_backend.Kedostt_Backend.dto;

import jakarta.validation.constraints.*;import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionDto {

    private Long id;

    @NotBlank(message = "Ad soyad boş bırakılamaz")
    private String fullName;

    @NotNull(message = "Yaş belirtilmelidir")
    @Min(value = 18, message = "En az 18 yaşında olmalısınız")
    @Max(value = 100, message = "Geçerli bir yaş giriniz")
    private Integer age;

    private String occupation;

    @NotBlank(message = "Telefon numarası zorunludur")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10,12}$", message = "Geçerli bir telefon numarası giriniz")
    private String phoneNumber;

    @NotBlank(message = "Email zorunludur")
    @Email(message = "Geçerli bir email giriniz")
    private String email;

    private String note;

    private Long animalId;
}
