package com.mhpl.QLDaoTao.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class CtdtThongTinChungDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ctdtId;
	
    private String tenCtdt;
    private String bac;
    private String loaiBang;
    private String loaiHinhDaoTao;
    private float thoiGian;
    private int soTinChi;
    private String khoaQuanLy;
    private String ngonNgu;
    private String website;
    private String banHanh;
	public CtdtThongTinChungDTO() {}
	public CtdtThongTinChungDTO(int ctdtId, String tenCtdt, String bac, String loaiBang, String loaiHinhDaoTao,float thoiGian, int soTinChi, String khoaQuanLy, String ngonNgu, String website, String banHanh) {
		this.ctdtId = ctdtId;
		this.tenCtdt = tenCtdt;
		this.bac = bac;
		this.loaiBang = loaiBang;
		this.loaiHinhDaoTao = loaiHinhDaoTao;
		this.thoiGian = thoiGian;
		this.soTinChi = soTinChi;
		this.khoaQuanLy = khoaQuanLy;
		this.ngonNgu = ngonNgu;
		this.website = website;
		this.banHanh = banHanh;
	}
	public int getCtdtId() {
		return ctdtId;
	}
	public void setCtdtId(int ctdtId) {
		this.ctdtId = ctdtId;
	}
	public String getTenCtdt() {
		return tenCtdt;
	}
	public void setTenCtdt(String tenCtdt) {
		this.tenCtdt = tenCtdt;
	}
	public String getBac() {
		return bac;
	}
	public void setBac(String bac) {
		this.bac = bac;
	}
	public String getLoaiBang() {
		return loaiBang;
	}
	public void setLoaiBang(String loaiBang) {
		this.loaiBang = loaiBang;
	}
	public String getLoaiHinhDaoTao() {
		return loaiHinhDaoTao;
	}
	public void setLoaiHinhDaoTao(String loaiHinhDaoTao) {
		this.loaiHinhDaoTao = loaiHinhDaoTao;
	}
	public float getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(float thoiGian) {
		this.thoiGian = thoiGian;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public String getKhoaQuanLy() {
		return khoaQuanLy;
	}
	public void setKhoaQuanLy(String khoaQuanLy) {
		this.khoaQuanLy = khoaQuanLy;
	}
	public String getNgonNgu() {
		return ngonNgu;
	}
	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBanHanh() {
		return banHanh;
	}
	public void setBanHanh(String banHanh) {
		this.banHanh = banHanh;
	}
	
	
    
}
