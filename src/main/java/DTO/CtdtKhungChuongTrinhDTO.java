package DTO;

public class CtdtKhungChuongTrinhDTO {
	 private int khungId;
	 private int ctdtId;
	 private String khoiKienThuc;
	 private String tenNhom;
	 private int soTinChiBatBuoc;
	 private int soTinChiTuChon;
	public CtdtKhungChuongTrinhDTO() {}
	public CtdtKhungChuongTrinhDTO(int khungId, int ctdtId, String khoiKienThuc, String tenNhom, int soTinChiBatBuoc,int soTinChiTuChon) {
		this.khungId = khungId;
		this.ctdtId = ctdtId;
		this.khoiKienThuc = khoiKienThuc;
		this.tenNhom = tenNhom;
		this.soTinChiBatBuoc = soTinChiBatBuoc;
		this.soTinChiTuChon = soTinChiTuChon;
	}
	public int getKhungId() {
		return khungId;
	}
	public void setKhungId(int khungId) {
		this.khungId = khungId;
	}
	public int getCtdtId() {
		return ctdtId;
	}
	public void setCtdtId(int ctdtId) {
		this.ctdtId = ctdtId;
	}
	public String getKhoiKienThuc() {
		return khoiKienThuc;
	}
	public void setKhoiKienThuc(String khoiKienThuc) {
		this.khoiKienThuc = khoiKienThuc;
	}
	public String getTenNhom() {
		return tenNhom;
	}
	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}
	public int getSoTinChiBatBuoc() {
		return soTinChiBatBuoc;
	}
	public void setSoTinChiBatBuoc(int soTinChiBatBuoc) {
		this.soTinChiBatBuoc = soTinChiBatBuoc;
	}
	public int getSoTinChiTuChon() {
		return soTinChiTuChon;
	}
	public void setSoTinChiTuChon(int soTinChiTuChon) {
		this.soTinChiTuChon = soTinChiTuChon;
	}
	
	
	 
}
