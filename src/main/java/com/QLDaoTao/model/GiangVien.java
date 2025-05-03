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
@Table(name = "ctdt_giangvien")
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
