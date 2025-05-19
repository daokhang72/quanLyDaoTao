package com.QLDaoTao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ctdt_thongtinchung")
public class ThongTinChung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ctdtId;
	
    private String tenCtdt;

    private String bac;

    private String loaiBang;

    private String loaiHinhDaoTao;

    private Float thoiGian;

    private Integer soTinChi;

    private String khoaQuanLy;

    private String ngonNgu;

    private String website;

    private String banHanh;

}
