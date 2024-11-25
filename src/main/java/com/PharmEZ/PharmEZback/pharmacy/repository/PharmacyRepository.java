package com.PharmEZ.PharmEZback.pharmacy.repository;

import com.PharmEZ.PharmEZback.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    // 주소로 약국 찾기
    List<Pharmacy> findByAddressContaining(String address);

    // 특정 범위 내 위도 및 경도로 검색
    List<Pharmacy> findByLatitudeBetweenAndLongitudeBetween(
        Double latitudeStart, Double latitudeEnd,
        Double longitudeStart, Double longitudeEnd
    );
}