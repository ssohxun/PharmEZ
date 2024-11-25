package com.PharmEZ.PharmEZback.medicine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Long id;
    private String pharmaceuticalCompany;
    private String medicineName;
    private String efficacy; // 약효
    private String medicineUse; // 사용법
    private String precautionWarn; // 경고 사항
    private String usingPrecaution; // 사용 시 주의사항
    private String medicineInteractions; // 약물 상호작용
    private String medicineSideEffect; // 부작용
    private String storage; // 저장 방법
    private String image; // 이미지 URL
}