package com.QLDaoTao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ctdt_kehoachmonhom")
public class KeHoachMoNhom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int monNhomId;
	
    private int maHp;

    private String tenHocPhan;

    private int soTinChi;

    private String khoa;

    private int soTietLyThuyet;

    private int soTietBaiTap;

    private int soTietThucHanh;

    private int soTietTc;

    private float heSoHp;

    private String tongSoNhom;

    private int slsvNhom;
}