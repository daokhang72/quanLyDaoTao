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
@Table(name = "ctdt_khungchuongtrinh")
public class KhungChuongTrinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer khungId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ctdt")
	private ThongTinChung ctdt;

	private String khoiKienThuc;

	private String tenNhom;

	private Integer soTinChiBatBuoc;

	private Integer soTinChiTuChon;
}
