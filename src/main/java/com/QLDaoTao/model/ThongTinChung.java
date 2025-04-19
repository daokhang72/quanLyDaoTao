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
@Table(name = "ctdt_thongtinchung")
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
