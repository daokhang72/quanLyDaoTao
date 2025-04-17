package DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class CtdtDeCuongChiTietDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deCuongId;
	
    private int maHp;
    private String tenBoPhan;
    private String diemDanhGia;
    private Float trongSo;
    private String hinhThucDanhGia;
	public CtdtDeCuongChiTietDTO() {}
	public CtdtDeCuongChiTietDTO(int deCuongId, int maHp, String tenBoPhan, String diemDanhGia, Float trongSo,
			String hinhThucDanhGia) {
		this.deCuongId = deCuongId;
		this.maHp = maHp;
		this.tenBoPhan = tenBoPhan;
		this.diemDanhGia = diemDanhGia;
		this.trongSo = trongSo;
		this.hinhThucDanhGia = hinhThucDanhGia;
	}
	public int getDeCuongId() {
		return deCuongId;
	}
	public void setDeCuongId(int deCuongId) {
		this.deCuongId = deCuongId;
	}
	public int getMaHp() {
		return maHp;
	}
	public void setMaHp(int maHp) {
		this.maHp = maHp;
	}
	public String getTenBoPhan() {
		return tenBoPhan;
	}
	public void setTenBoPhan(String tenBoPhan) {
		this.tenBoPhan = tenBoPhan;
	}
	public String getDiemDanhGia() {
		return diemDanhGia;
	}
	public void setDiemDanhGia(String diemDanhGia) {
		this.diemDanhGia = diemDanhGia;
	}
	public Float getTrongSo() {
		return trongSo;
	}
	public void setTrongSo(Float trongSo) {
		this.trongSo = trongSo;
	}
	public String getHinhThucDanhGia() {
		return hinhThucDanhGia;
	}
	public void setHinhThucDanhGia(String hinhThucDanhGia) {
		this.hinhThucDanhGia = hinhThucDanhGia;
	}
    
    
}
	