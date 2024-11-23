package com.PharmEZ.PharmEZback.medicine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Medicine Entity
 *
 * @author sylee
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @Column(name = "medicine_id", nullable = false)
    private Long id;

    @Column(name = "pharmaceutical_company", nullable = false)
    private String pharmaceuticalCompany;

    @Column(name = "medicine_name", nullable = false)
    private String medicineName;

    @Lob
    @Column(name = "medicine_efficacy")
    private String efficacy;

    @Lob
    @Column(name = "medicine_use")
    private String medicineUse;

    @Lob
    @Column(name = "precaution_warn")
    private String precautionWarn;

    @Lob
    @Column(name = "using_precaution")
    private String usingPrecaution;

    @Lob
    @Column(name = "medicine_interactions")
    private String medicineInteractions;

    @Lob
    @Column(name = "medicine_side_effect")
    private String medicineSideEffect;

    @Lob
    @Column(name = "medicine_storage")
    private String storage;

    @Column(name = "medicine_image")
    private String image;


}
