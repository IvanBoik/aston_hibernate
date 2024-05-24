package com.boiko.aston_hibernate.service;

import com.boiko.aston_hibernate.dto.passport.PassportDTO;
import com.boiko.aston_hibernate.dto.project.InsertProjectRequest;
import com.boiko.aston_hibernate.dto.project.ProjectDTO;
import com.boiko.aston_hibernate.dto.project.UpdateProjectRequest;
import com.boiko.aston_hibernate.mapper.EmployeeDTOMapper;
import com.boiko.aston_hibernate.mapper.ProjectDTOMapper;
import com.boiko.aston_hibernate.model.Project;
import com.boiko.aston_hibernate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectDTOMapper mapper;

    public void add(InsertProjectRequest request) {
        projectRepository.insert(request);
    }

    public void update(UpdateProjectRequest request) {
        projectRepository.update(request);
    }

    public void delete(Long id) {
        projectRepository.delete(id);
    }

    public ProjectDTO getByID(Long id) {
        Project project = projectRepository.getByID(id);
        return mapper.apply(project);
    }

    public List<ProjectDTO> getAll() {
        return projectRepository.getAll()
                .stream()
                .map(mapper)
                .toList();
    }

    public void addEmployeeToProject(Long projectID, Long employeeID) {
        projectRepository.addEmployeeToProject(projectID, employeeID);
    }

    public void removeEmployeeFromProject(Long projectID, Long employeeID) {
        projectRepository.removeEmployeeFromProject(projectID, employeeID);
    }
}
