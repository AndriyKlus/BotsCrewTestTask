package com.botscrew.task.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    private Lector headOfDepartment;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Lector> lectors;

}
