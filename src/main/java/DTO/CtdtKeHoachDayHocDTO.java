package DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
public class CtdtKeHoachDayHocDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keHoachId;
	
    private int maHp;
    private String tenHocPhan;
    private int soTinChi;
    private String hocKyThucHien;
    private int maHocPhanTruoc;
	public CtdtKeHoachDayHocDTO() {}
	public CtdtKeHoachDayHocDTO(int keHoachId, int maHp, String tenHocPhan, int soTinChi, String hocKyThucHien,int maHocPhanTruoc) {
		this.keHoachId = keHoachId;
		this.maHp = maHp;
		this.tenHocPhan = tenHocPhan;
		this.soTinChi = soTinChi;
		this.hocKyThucHien = hocKyThucHien;
		this.maHocPhanTruoc = maHocPhanTruoc;
	}
	public int getKeHoachId() {
		return keHoachId;
	}
	public void setKeHoachId(int keHoachId) {
		this.keHoachId = keHoachId;
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
	public String getHocKyThucHien() {
		return hocKyThucHien;
	}
	public void setHocKyThucHien(String hocKyThucHien) {
		this.hocKyThucHien = hocKyThucHien;
	}
	public int getMaHocPhanTruoc() {
		return maHocPhanTruoc;
	}
	public void setMaHocPhanTruoc(int maHocPhanTruoc) {
		this.maHocPhanTruoc = maHocPhanTruoc;
	}
    
    
}
