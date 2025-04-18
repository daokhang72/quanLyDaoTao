package com.mhpl.QLDaoTao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeHoachDayHoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keHoachId;
	
    private int maHp;

    private String tenHocPhan;

    private int soTinChi;

    private String hocKyThucHien;

    private int maHocPhanTruoc;
}
