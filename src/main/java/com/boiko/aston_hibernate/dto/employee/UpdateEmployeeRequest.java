package com.boiko.aston_hibernate.dto.employee;

public record UpdateEmployeeRequest(
        Long id,
        Long positionID,
        Long departmentID,
        String name,
        String surname,
        String email,
        String phone
) {
}
