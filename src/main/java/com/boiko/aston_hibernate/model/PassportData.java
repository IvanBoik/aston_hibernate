package com.boiko.aston_hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "passports_data")
@NoArgsConstructor
@AllArgsConstructor
public class PassportData {
    @Id
    @Column(name = "id_employee")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_employee")
    private Employee employee;

    private String series;
    private String number;
    private LocalDate dateOfIssue;
    private String departmentCode;
    private String issuingAuthority;
}
