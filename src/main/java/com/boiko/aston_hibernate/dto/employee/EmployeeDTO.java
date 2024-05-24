package com.boiko.aston_hibernate.dto.employee;

import com.boiko.aston_hibernate.dto.passport.PassportDTO;

public record EmployeeDTO(
        Long id,
        PassportDTO passport,
        String position,
        String department,
        String name,
        String surname,
        String email,
        String phone
) {
}
