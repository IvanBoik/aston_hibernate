package com.boiko.aston_hibernate.mapper;

import com.boiko.aston_hibernate.dto.employee.EmployeeDTO;
import com.boiko.aston_hibernate.dto.passport.PassportDTO;
import com.boiko.aston_hibernate.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EmployeeDTOMapper implements Function<Employee, EmployeeDTO> {
    private final PassportDTOMapper passportDTOMapper;

    @Override
    public EmployeeDTO apply(Employee e) {
        PassportDTO dto = passportDTOMapper.apply(e.getPassport());
        return new EmployeeDTO(
                e.getId(),
                dto,
                e.getPosition().getName(),
                e.getDepartment().getName(),
                e.getName(),
                e.getSurname(),
                e.getEmail(),
                e.getPhone()
        );
    }
}
