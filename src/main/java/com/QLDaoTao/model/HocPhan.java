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
@Table(name = "ctdt_hocphan")
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

    private int heSoHocPhan;

}
