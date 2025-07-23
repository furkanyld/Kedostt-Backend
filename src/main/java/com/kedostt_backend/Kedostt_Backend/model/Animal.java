package com.kedostt_backend.Kedostt_Backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "animal_image_file_ids", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "image_file_id")
    private List<String> imageFileIds = new ArrayList<>();

    @Column(name = "video_file_id")
    private String videoFileId;
}
