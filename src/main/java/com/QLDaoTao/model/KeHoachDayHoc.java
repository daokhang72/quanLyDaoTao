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
@Table(name = "ctdt_kehoachdayhoc")
public class KeHoachDayHoc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int keHoachId;
	
    private int maHp;

    private String tenHocPhan;

    private int soTinChi;

    private String hocKyThucHien;

    private int maHocPhanTruoc;
}
