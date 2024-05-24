package com.boiko.aston_hibernate.dto.project;

import java.time.LocalDate;

public record InsertProjectRequest(
        String name,
        LocalDate startDate,
        LocalDate endDate
) {
}
