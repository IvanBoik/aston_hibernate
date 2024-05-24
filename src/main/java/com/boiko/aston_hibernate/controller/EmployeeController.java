package com.boiko.aston_hibernate.controller;

import com.boiko.aston_hibernate.dto.employee.InsertEmployeeRequest;
import com.boiko.aston_hibernate.dto.employee.UpdateEmployeeRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody InsertEmployeeRequest request) {
        try {
            employeeService.add(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedInsertException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdateEmployeeRequest request) {
        try {
            employeeService.update(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedUpdateException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            employeeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedDeleteException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.getByID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(employeeService.getAll());
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by_project/{id}")
    public ResponseEntity<?> getAllByProjectID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.getAllByProjectID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
