package com.PharmEZ.PharmEZback.pharmacy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pharmacy Entity
 *
 * @author sylee
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "pharmacy") // 테이블 이름 매핑
public class Pharmacy {

    @Id
    @Column(name = "pharmacy_id")
    private Long id;

    @Column(name = "pharmacy_address", nullable = false)
    private String address;

    @Column(name = "pharmacy_name", nullable = false)
    private String name;

    @Column(name = "pharmacy_tel")
    private String tel;

    @Column(name = "mon_start")
    private String monStart;

    @Column(name = "mon_close")
    private String monClose;

    @Column(name = "tue_start")
    private String tueStart;

    @Column(name = "tue_close")
    private String tueClose;

    @Column(name = "wed_start")
    private String wedStart;

    @Column(name = "wed_close")
    private String wedClose;

    @Column(name = "thu_start")
    private String thuStart;

    @Column(name = "thu_close")
    private String thuClose;

    @Column(name = "fri_start")
    private String friStart;

    @Column(name = "fri_close")
    private String friClose;

    @Column(name = "sat_start")
    private String satStart;

    @Column(name = "sat_close")
    private String satClose;

    @Column(name = "sun_start")
    private String sunStart;

    @Column(name = "sun_close")
    private String sunClose;

    @Column(name = "public_holiday_start")
    private String publicHolidayStart;

    @Column(name = "public_holiday_close")
    private String publicHolidayClose;

    @Column(name = "front_zip_code")
    private Integer frontZipCode;

    @Column(name = "back_zip_code")
    private Integer backZipCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
