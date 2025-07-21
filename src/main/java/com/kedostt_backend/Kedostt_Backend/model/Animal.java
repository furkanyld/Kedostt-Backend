package com.kedostt_backend.Kedostt_Backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species; // Tür

    private String breed; // ırk

    @Column(name = "age_years")
    private int ageYears;

    @Column(name = "age_months")
    private int ageMonths;

    private String gender;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "animal_images", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    private String videoUrl;

    private boolean adopted = false;

    @Column(nullable = false)
    private boolean isVisible = true;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations = new ArrayList<>();
}
