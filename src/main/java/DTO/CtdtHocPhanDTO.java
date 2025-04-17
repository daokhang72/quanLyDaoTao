package DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class CtdtHocPhanDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maHp;
	
    private int khungId;
    private String tenHocPhan;
    private int soTinChi;
    private int soTietLyThuyet;
    private int soTietThucHanh;
    private int soTietThucTap;
    private int tongSoTiet;
    private int heSoHocPhan;
    
	public CtdtHocPhanDTO() {}

	public CtdtHocPhanDTO(int maHp, int khungId, String tenHocPhan, int soTinChi, int soTietLyThuyet,
			int soTietThucHanh, int soTietThucTap, int tongSoTiet, int heSoHocPhan) {
		this.maHp = maHp;
		this.khungId = khungId;
		this.tenHocPhan = tenHocPhan;
		this.soTinChi = soTinChi;
		this.soTietLyThuyet = soTietLyThuyet;
		this.soTietThucHanh = soTietThucHanh;
		this.soTietThucTap = soTietThucTap;
		this.tongSoTiet = tongSoTiet;
		this.heSoHocPhan = heSoHocPhan;
	}

	public int getMaHp() {
		return maHp;
	}

	public void setMaHp(int maHp) {
		this.maHp = maHp;
	}

	public int getKhungId() {
		return khungId;
	}

	public void setKhungId(int khungId) {
		this.khungId = khungId;
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

	public int getSoTietLyThuyet() {
		return soTietLyThuyet;
	}

	public void setSoTietLyThuyet(int soTietLyThuyet) {
		this.soTietLyThuyet = soTietLyThuyet;
	}

	public int getSoTietThucHanh() {
		return soTietThucHanh;
	}

	public void setSoTietThucHanh(int soTietThucHanh) {
		this.soTietThucHanh = soTietThucHanh;
	}

	public int getSoTietThucTap() {
		return soTietThucTap;
	}

	public void setSoTietThucTap(int soTietThucTap) {
		this.soTietThucTap = soTietThucTap;
	}

	public int getTongSoTiet() {
		return tongSoTiet;
	}

	public void setTongSoTiet(int tongSoTiet) {
		this.tongSoTiet = tongSoTiet;
	}

	public int getHeSoHocPhan() {
		return heSoHocPhan;
	}

	public void setHeSoHocPhan(int heSoHocPhan) {
		this.heSoHocPhan = heSoHocPhan;
	}
    
    
}
