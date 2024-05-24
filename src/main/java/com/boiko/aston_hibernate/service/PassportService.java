package com.boiko.aston_hibernate.service;

import com.boiko.aston_hibernate.dto.passport.InsertPassportRequest;
import com.boiko.aston_hibernate.dto.passport.PassportDTO;
import com.boiko.aston_hibernate.dto.passport.UpdatePassportRequest;
import com.boiko.aston_hibernate.mapper.PassportDTOMapper;
import com.boiko.aston_hibernate.model.PassportData;
import com.boiko.aston_hibernate.repository.PassportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassportService {
    private final PassportRepository passportRepository;
    private final PassportDTOMapper mapper;

    public void add(InsertPassportRequest request) {
        passportRepository.insert(request);
    }

    public void update(UpdatePassportRequest request) {
        passportRepository.update(request);
    }

    public void delete(Long id) {
        passportRepository.delete(id);
    }

    public PassportDTO getByID(Long id) {
        PassportData passport = passportRepository.getByID(id);
        return mapper.apply(passport);
    }

    public List<PassportDTO> getAll() {
        return passportRepository.getAll()
                .stream()
                .map(mapper)
                .toList();
    }
}
