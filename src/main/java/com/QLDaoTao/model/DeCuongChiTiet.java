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
    @Column(name = "decuong_id")
	private Long deCuongId;

    @Column(name = "ma_hp")
    private Integer maHp;

    private String tenBoPhan;

    private String diemDanhGia;

    private Float trongSo;

    private String hinhThucDanhGia;
}
	