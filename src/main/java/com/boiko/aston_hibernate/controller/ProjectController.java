package com.boiko.aston_hibernate.controller;

import com.boiko.aston_hibernate.dto.project.InsertProjectRequest;
import com.boiko.aston_hibernate.dto.project.UpdateProjectRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody InsertProjectRequest request) {
        try {
            projectService.add(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedInsertException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdateProjectRequest request) {
        try {
            projectService.update(request);
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
            projectService.delete(id);
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
            return ResponseEntity.ok(projectService.getByID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(projectService.getAll());
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<?> addToProject(@RequestParam Long projectID, @RequestParam Long employeeID) {
        try {
            projectService.addEmployeeToProject(projectID, employeeID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedUpdateException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee")
    public ResponseEntity<?> removeFromProject(@RequestParam Long projectID, @RequestParam Long employeeID) {
        try {
            projectService.removeEmployeeFromProject(projectID, employeeID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedUpdateException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
