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
@Table(name = "ctdt_khungchuongtrinh")
public class KhungChuongTrinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private int khungId;
	
	 private int ctdtId;

	 private String khoiKienThuc;

	 private String tenNhom;

	 private int soTinChiBatBuoc;

	 private int soTinChiTuChon;
}
