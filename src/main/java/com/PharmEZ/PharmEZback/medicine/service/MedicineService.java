package com.PharmEZ.PharmEZback.medicine.service;

import com.PharmEZ.PharmEZback.common.exception.MedicineNotFoundException;
import com.PharmEZ.PharmEZback.medicine.dto.MedicineDTO;
import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import com.PharmEZ.PharmEZback.medicine.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<MedicineDTO> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MedicineDTO getMedicineById(Long id) {
    Medicine medicine = medicineRepository.findById(id)
            .orElseThrow(() -> new MedicineNotFoundException("Medicine not found with ID: " + id));
    return convertToDTO(medicine);
    }

    private MedicineDTO convertToDTO(Medicine medicine) {
        return MedicineDTO.builder()
                .id(medicine.getId())
                .pharmaceuticalCompany(medicine.getPharmaceuticalCompany())
                .medicineName(medicine.getMedicineName())
                .efficacy(medicine.getEfficacy())
                .medicineUse(medicine.getMedicineUse())
                .precautionWarn(medicine.getPrecautionWarn())
                .usingPrecaution(medicine.getUsingPrecaution())
                .medicineInteractions(medicine.getMedicineInteractions())
                .medicineSideEffect(medicine.getMedicineSideEffect())
                .storage(medicine.getStorage())
                .image(medicine.getImage())
                .build();
    }
}