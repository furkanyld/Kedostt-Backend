package com.kedostt_backend.Kedostt_Backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species; // Tür

    private String breed; // ırk

    private int age;

    private String gender;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    private boolean adopted = false;
}
