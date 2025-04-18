package com.mhpl.QLDaoTao.models;

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
