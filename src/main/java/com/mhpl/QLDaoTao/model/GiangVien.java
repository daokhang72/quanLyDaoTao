package com.mhpl.QLDaoTao.model;

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
public class GiangVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int giangVienId;
	
    private int userId;

    private String hoTen;

    private String loaiGiangVien;

    private String donVi;

    private String email;

    private String soDienThoai;
}
