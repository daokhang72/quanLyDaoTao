package com.QLDaoTao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongTinChung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ctdtId;
	
    private String tenCtdt;

    private String bac;

    private String loaiBang;

    private String loaiHinhDaoTao;

    private float thoiGian;

    private int soTinChi;

    private String khoaQuanLy;

    private String ngonNgu;

    private String website;

    private String banHanh;
    
}
