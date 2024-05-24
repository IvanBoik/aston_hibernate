package com.boiko.aston_hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private PassportData passport;

    @ManyToOne
    @JoinColumn(name="id_position")
    private Position position;

    @ManyToOne
    @JoinColumn(name="id_department")
    private Department department;

    private String name;
    private String surname;
    private String email;
    private String phone;
}
