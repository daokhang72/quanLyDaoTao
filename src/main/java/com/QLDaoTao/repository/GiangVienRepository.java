package com.QLDaoTao.repository;

import com.QLDaoTao.model.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiangVienRepository extends JpaRepository<GiangVien, Integer> {
    @Query("SELECT g.loaiGiangVien, COUNT(g) FROM GiangVien g GROUP BY g.loaiGiangVien")
    List<Object[]> thongKeTheoLoai();

}
