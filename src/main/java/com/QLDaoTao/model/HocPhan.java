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
	private Integer maHp;
	
    private Integer khungId;

    private String tenHocPhan;

    private Integer soTinChi;

    private Integer soTietLyThuyet;

    private Integer soTietThucHanh;

    private Integer soTietThucTap;

    private Integer heSoHocPhan;

}
