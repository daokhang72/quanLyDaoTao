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
@Table(name = "ctdt_phanconggiangday")
public class PhanCongGiangDay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pcgd_id")
	private int pcgdId;

	@Column(name = "ma_hp")
	private int mahp;

	@Column(name = "nhom")
	private String nhom;

	@Column(name = "giangvien_id")
	private int giangvienId;

	@Column(name = "ho_ten_cbgd")
	private String hotencbgd;

	@Column(name = "so_tiet_thuc_hien")
	private int sotietthuchien;

	@Column(name = "so_tiet_thuc_te")
	private int sotietthucte;
}
