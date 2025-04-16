package DTO;

public class CtdtKeHoachMoNhomDTO {
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
    
    
}		
