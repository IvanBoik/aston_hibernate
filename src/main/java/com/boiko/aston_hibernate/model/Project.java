package com.boiko.aston_hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "employees_projects",
            joinColumns = { @JoinColumn(name = "id_project") },
            inverseJoinColumns = { @JoinColumn(name = "id_employee") })
    private List<Employee> employees;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
