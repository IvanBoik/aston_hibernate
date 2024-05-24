package com.boiko.aston_hibernate.mapper;

import com.boiko.aston_hibernate.dto.employee.EmployeeDTO;
import com.boiko.aston_hibernate.dto.project.ProjectDTO;
import com.boiko.aston_hibernate.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProjectDTOMapper implements Function<Project, ProjectDTO> {
    private final EmployeeDTOMapper employeeDTOMapper;

    @Override
    public ProjectDTO apply(Project project) {
        List<EmployeeDTO> employees = project.getEmployees()
                .stream()
                .map(employeeDTOMapper)
                .toList();

        return new ProjectDTO(
                project.getId(),
                project.getName(),
                employees,
                project.getStartDate(),
                project.getEndDate()
        );
    }
}
