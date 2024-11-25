package com.PharmEZ.PharmEZback.pharmacy.service;

import com.PharmEZ.PharmEZback.pharmacy.dto.PharmacyDTO;
import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import com.PharmEZ.PharmEZback.pharmacy.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<PharmacyDTO> getAllPharmacies() {
        return pharmacyRepository.findAll()
            .stream()
            .map(pharmacy -> PharmacyDTO.builder()
                .id(pharmacy.getId())
                .name(pharmacy.getName())
                .address(pharmacy.getAddress())
                .tel(pharmacy.getTel())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build()
            ).collect(Collectors.toList());
    }
}