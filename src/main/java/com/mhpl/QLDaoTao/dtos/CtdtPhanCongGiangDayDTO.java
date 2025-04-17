package com.mhpl.QLDaoTao.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class CtdtPhanCongGiangDayDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pcgdId;
	
	private int mahp;
	private String nhom;
	private int giangvienId;
	private String hotencbgd;
	private int sotietthuchien;
	private int sotietthucte;
	public CtdtPhanCongGiangDayDTO() {}
	public CtdtPhanCongGiangDayDTO(int pcgdId, int mahp, String nhom, int giangvienId, String hotencbgd,
			int sotietthuchien, int sotietthucte) {
		this.pcgdId = pcgdId;
		this.mahp = mahp;
		this.nhom = nhom;
		this.giangvienId = giangvienId;
		this.hotencbgd = hotencbgd;
		this.sotietthuchien = sotietthuchien;
		this.sotietthucte = sotietthucte;
	}
	public int getPcgdId() {
		return pcgdId;
	}
	public void setPcgdId(int pcgdId) {
		this.pcgdId = pcgdId;
	}
	public int getMahp() {
		return mahp;
	}
	public void setMahp(int mahp) {
		this.mahp = mahp;
	}
	public String getNhom() {
		return nhom;
	}
	public void setNhom(String nhom) {
		this.nhom = nhom;
	}
	public int getGiangvienId() {
		return giangvienId;
	}
	public void setGiangvienId(int giangvienId) {
		this.giangvienId = giangvienId;
	}
	public String getHotencbgd() {
		return hotencbgd;
	}
	public void setHotencbgd(String hotencbgd) {
		this.hotencbgd = hotencbgd;
	}
	public int getSotietthuchien() {
		return sotietthuchien;
	}
	public void setSotietthuchien(int sotietthuchien) {
		this.sotietthuchien = sotietthuchien;
	}
	public int getSotietthucte() {
		return sotietthucte;
	}
	public void setSotietthucte(int sotietthucte) {
		this.sotietthucte = sotietthucte;
	}
	
}
