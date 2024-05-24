package com.boiko.aston_hibernate.controller;

import com.boiko.aston_hibernate.dto.department.InsertDepartmentRequest;
import com.boiko.aston_hibernate.dto.department.UpdateDepartmentRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody InsertDepartmentRequest request) {
        try {
            departmentService.add(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedInsertException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdateDepartmentRequest request) {
        try {
            departmentService.update(request);
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
            departmentService.delete(id);
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
            return ResponseEntity.ok(departmentService.getByID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(departmentService.getAll());
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
