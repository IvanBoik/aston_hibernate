package com.boiko.aston_hibernate.dto.project;

import com.boiko.aston_hibernate.dto.employee.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

public record ProjectDTO(
        Long id,
        String name,
        List<EmployeeDTO> employees,
        LocalDate startDate,
        LocalDate endDate
) {
}
