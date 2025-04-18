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
public class DeCuongChiTiet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deCuongId;
	
    private int maHp;
    private String tenBoPhan;
    private String diemDanhGia;
    private Float trongSo;
    private String hinhThucDanhGia;
}
	