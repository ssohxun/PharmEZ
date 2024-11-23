package com.PharmEZ.PharmEZback.medicine.repository;

import com.PharmEZ.PharmEZback.medicine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
