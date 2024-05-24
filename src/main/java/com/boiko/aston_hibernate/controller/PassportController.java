package com.boiko.aston_hibernate.controller;

import com.boiko.aston_hibernate.dto.passport.InsertPassportRequest;
import com.boiko.aston_hibernate.dto.passport.UpdatePassportRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.service.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody InsertPassportRequest request) {
        try {
            passportService.add(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedInsertException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdatePassportRequest request) {
        try {
            passportService.update(request);
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
            passportService.delete(id);
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
            return ResponseEntity.ok(passportService.getByID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(passportService.getAll());
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}