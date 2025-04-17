package com.mhpl.QLDaoTao.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class CtdtKeHoachMoNhomDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int monNhomId;
	
    private int maHp;
    private String tenHocPhan;
    private int soTinChi;
    private String khoa;
    private int soTietLyThuyet;
    private int soTietBaiTap;
    private int soTietThucHanh;
    private int soTietTc;
    private float heSoHp;
    private String tongSoNhom;
    private int slsvNhom;
	public CtdtKeHoachMoNhomDTO() {}
	public CtdtKeHoachMoNhomDTO(int monNhomId, int maHp, String tenHocPhan, int soTinChi, String khoa,
			int soTietLyThuyet, int soTietBaiTap, int soTietThucHanh, int soTietTc, float heSoHp, String tongSoNhom,
			int slsvNhom) {
		this.monNhomId = monNhomId;
		this.maHp = maHp;
		this.tenHocPhan = tenHocPhan;
		this.soTinChi = soTinChi;
		this.khoa = khoa;
		this.soTietLyThuyet = soTietLyThuyet;
		this.soTietBaiTap = soTietBaiTap;
		this.soTietThucHanh = soTietThucHanh;
		this.soTietTc = soTietTc;
		this.heSoHp = heSoHp;
		this.tongSoNhom = tongSoNhom;
		this.slsvNhom = slsvNhom;
	}
	public int getMonNhomId() {
		return monNhomId;
	}
	public void setMonNhomId(int monNhomId) {
		this.monNhomId = monNhomId;
	}
	public int getMaHp() {
		return maHp;
	}
	public void setMaHp(int maHp) {
		this.maHp = maHp;
	}
	public String getTenHocPhan() {
		return tenHocPhan;
	}
	public void setTenHocPhan(String tenHocPhan) {
		this.tenHocPhan = tenHocPhan;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public String getKhoa() {
		return khoa;
	}
	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}
	public int getSoTietLyThuyet() {
		return soTietLyThuyet;
	}
	public void setSoTietLyThuyet(int soTietLyThuyet) {
		this.soTietLyThuyet = soTietLyThuyet;
	}
	public int getSoTietBaiTap() {
		return soTietBaiTap;
	}
	public void setSoTietBaiTap(int soTietBaiTap) {
		this.soTietBaiTap = soTietBaiTap;
	}
	public int getSoTietThucHanh() {
		return soTietThucHanh;
	}
	public void setSoTietThucHanh(int soTietThucHanh) {
		this.soTietThucHanh = soTietThucHanh;
	}
	public int getSoTietTc() {
		return soTietTc;
	}
	public void setSoTietTc(int soTietTc) {
		this.soTietTc = soTietTc;
	}
	public float getHeSoHp() {
		return heSoHp;
	}
	public void setHeSoHp(float heSoHp) {
		this.heSoHp = heSoHp;
	}
	public String getTongSoNhom() {
		return tongSoNhom;
	}
	public void setTongSoNhom(String tongSoNhom) {
		this.tongSoNhom = tongSoNhom;
	}
	public int getSlsvNhom() {
		return slsvNhom;
	}
	public void setSlsvNhom(int slsvNhom) {
		this.slsvNhom = slsvNhom;
	}
    
    
}		
