package com.boiko.aston_hibernate.dto.employee;

import java.time.LocalDate;

public record InsertEmployeeRequest(
        Long positionID,
        Long departmentID,
        String series,
        String number,
        LocalDate dateOfIssue,
        String departmentCode,
        String issuingAuthority,
        String name,
        String surname,
        String email,
        String phone
) {
}
