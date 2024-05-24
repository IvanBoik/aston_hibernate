package com.boiko.aston_hibernate.dto.project;

import java.time.LocalDate;

public record UpdateProjectRequest(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate
) {
}
