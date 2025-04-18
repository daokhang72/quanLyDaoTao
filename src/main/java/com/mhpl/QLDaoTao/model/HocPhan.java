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
public class HocPhan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maHp;
	
    private int khungId;

    private String tenHocPhan;

    private int soTinChi;

    private int soTietLyThuyet;

    private int soTietThucHanh;

    private int soTietThucTap;

    private int tongSoTiet;

    private int heSoHocPhan;

}
