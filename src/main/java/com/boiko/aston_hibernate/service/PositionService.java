package com.boiko.aston_hibernate.service;

import com.boiko.aston_hibernate.dto.passport.InsertPassportRequest;
import com.boiko.aston_hibernate.dto.passport.PassportDTO;
import com.boiko.aston_hibernate.dto.passport.UpdatePassportRequest;
import com.boiko.aston_hibernate.dto.position.InsertPositionRequest;
import com.boiko.aston_hibernate.dto.position.PositionDTO;
import com.boiko.aston_hibernate.dto.position.UpdatePositionRequest;
import com.boiko.aston_hibernate.model.PassportData;
import com.boiko.aston_hibernate.model.Position;
import com.boiko.aston_hibernate.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public void add(InsertPositionRequest request) {
        positionRepository.insert(request);
    }

    public void update(UpdatePositionRequest request) {
        positionRepository.update(request);
    }

    public void delete(Long id) {
        positionRepository.delete(id);
    }

    public PositionDTO getByID(Long id) {
        Position position = positionRepository.getByID(id);
        return new PositionDTO(position.getId(), position.getName());
    }

    public List<PositionDTO> getAll() {
        return positionRepository.getAll()
                .stream()
                .map(x -> new PositionDTO(x.getId(), x.getName()))
                .toList();
    }
}
