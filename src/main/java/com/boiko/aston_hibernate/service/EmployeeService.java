package com.boiko.aston_hibernate.service;

import com.boiko.aston_hibernate.dto.employee.EmployeeDTO;
import com.boiko.aston_hibernate.dto.employee.InsertEmployeeRequest;
import com.boiko.aston_hibernate.dto.employee.UpdateEmployeeRequest;
import com.boiko.aston_hibernate.mapper.EmployeeDTOMapper;
import com.boiko.aston_hibernate.model.Employee;
import com.boiko.aston_hibernate.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper mapper;

    public void add(InsertEmployeeRequest request) {
        employeeRepository.insert(request);
    }

    public void update(UpdateEmployeeRequest request) {
        employeeRepository.update(request);
    }

    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    public EmployeeDTO getByID(Long id) {
        Employee employee = employeeRepository.getByID(id);
        return mapper.apply(employee);
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.getAll()
                .stream()
                .map(mapper)
                .toList();
    }

    public List<EmployeeDTO> getAllByProjectID(Long id) {
        return employeeRepository.getAllByProjectID(id)
                .stream()
                .map(mapper)
                .toList();
    }
}
