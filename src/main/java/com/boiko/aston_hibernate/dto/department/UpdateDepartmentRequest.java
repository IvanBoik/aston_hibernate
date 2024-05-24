package com.boiko.aston_hibernate.dto.department;

public record UpdateDepartmentRequest(
        Long id,
        String name
) {
}
