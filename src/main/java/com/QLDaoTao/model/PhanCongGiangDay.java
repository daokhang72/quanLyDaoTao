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
public class PhanCongGiangDay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pcgdId;

	private int mahp;

	private String nhom;

	private int giangvienId;

	private String hotencbgd;

	private int sotietthuchien;

	private int sotietthucte;
}
