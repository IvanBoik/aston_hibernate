package com.boiko.aston_hibernate.controller;

import com.boiko.aston_hibernate.dto.position.InsertPositionRequest;
import com.boiko.aston_hibernate.dto.position.UpdatePositionRequest;
import com.boiko.aston_hibernate.exception.EntityNotFoundException;
import com.boiko.aston_hibernate.exception.FailedDeleteException;
import com.boiko.aston_hibernate.exception.FailedInsertException;
import com.boiko.aston_hibernate.exception.FailedUpdateException;
import com.boiko.aston_hibernate.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody InsertPositionRequest request) {
        try {
            positionService.add(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (FailedInsertException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody UpdatePositionRequest request) {
        try {
            positionService.update(request);
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
            positionService.delete(id);
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
            return ResponseEntity.ok(positionService.getByID(id));
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(positionService.getAll());
        }
        catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
