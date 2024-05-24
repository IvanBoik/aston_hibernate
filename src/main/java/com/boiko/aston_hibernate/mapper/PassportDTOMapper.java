package com.boiko.aston_hibernate.mapper;

import com.boiko.aston_hibernate.dto.passport.PassportDTO;
import com.boiko.aston_hibernate.model.PassportData;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PassportDTOMapper implements Function<PassportData, PassportDTO> {
    @Override
    public PassportDTO apply(PassportData passport) {
        return new PassportDTO(
                passport.getSeries(),
                passport.getNumber(),
                passport.getDateOfIssue(),
                passport.getDepartmentCode(),
                passport.getIssuingAuthority()
        );
    }
}
