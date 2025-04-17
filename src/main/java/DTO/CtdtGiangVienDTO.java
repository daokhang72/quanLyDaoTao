package DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class CtdtGiangVienDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int giangVienId;
	
    private int userId;
    private String hoTen;
    private String loaiGiangVien;
    private String donVi;
    private String email;
    private String soDienThoai;
	public CtdtGiangVienDTO() {}
	public CtdtGiangVienDTO(int giangVienId, int userId, String hoTen, String loaiGiangVien, String donVi, String email,
			String soDienThoai) {
		this.giangVienId = giangVienId;
		this.userId = userId;
		this.hoTen = hoTen;
		this.loaiGiangVien = loaiGiangVien;
		this.donVi = donVi;
		this.email = email;
		this.soDienThoai = soDienThoai;
	}
	public int getGiangVienId() {
		return giangVienId;
	}
	public void setGiangVienId(int giangVienId) {
		this.giangVienId = giangVienId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getLoaiGiangVien() {
		return loaiGiangVien;
	}
	public void setLoaiGiangVien(String loaiGiangVien) {
		this.loaiGiangVien = loaiGiangVien;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	
}
