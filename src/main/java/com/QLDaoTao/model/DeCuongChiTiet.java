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
@Table(name = "ctdt_decuongchitiet")
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
	