package com.boiko.aston_hibernate.dto.passport;

import java.time.LocalDate;

public record InsertPassportRequest(
        Long employeeID,
        String series,
        String number,
        LocalDate dateOfIssue,
        String departmentCode,
        String issuingAuthority
) {
}
