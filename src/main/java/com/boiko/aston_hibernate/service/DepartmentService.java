package com.boiko.aston_hibernate.service;

import com.boiko.aston_hibernate.dto.department.DepartmentDTO;
import com.boiko.aston_hibernate.dto.department.InsertDepartmentRequest;
import com.boiko.aston_hibernate.dto.department.UpdateDepartmentRequest;
import com.boiko.aston_hibernate.model.Department;
import com.boiko.aston_hibernate.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public void add(InsertDepartmentRequest request) {
        departmentRepository.insert(request);
    }

    public void update(UpdateDepartmentRequest request) {
        departmentRepository.update(request);
    }

    public void delete(Long id) {
        departmentRepository.delete(id);
    }

    public DepartmentDTO getByID(Long id) {
        Department department = departmentRepository.getByID(id);
        return new DepartmentDTO(department.getId(), department.getName());
    }

    public List<DepartmentDTO> getAll() {
        return departmentRepository.getAll()
                .stream()
                .map(x -> new DepartmentDTO(x.getId(), x.getName()))
                .toList();
    }
}
