package com.PharmEZ.PharmEZback.pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//직접 엔티티를 클라이언트로 반환하기보다, 필요한 데이터만 포함한 DTO를 반환하도록 설계
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyDTO {
    private Long id;
    private String name;
    private String address;
    private String tel;
    private Double latitude;
    private Double longitude;
}
