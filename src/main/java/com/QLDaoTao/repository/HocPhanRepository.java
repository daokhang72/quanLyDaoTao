package com.QLDaoTao.repository;

import com.QLDaoTao.model.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HocPhanRepository extends JpaRepository<HocPhan, Integer> {

    @Query("SELECT h FROM HocPhan h " +
            "WHERE CAST(h.maHp AS string) LIKE %:keyword% " +
            "OR LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR CAST(h.soTinChi AS string) LIKE %:keyword% " +
            "OR CAST(h.heSoHocPhan AS string) LIKE %:keyword%")
    List<HocPhan> searchAdvanced(@Param("keyword") String keyword);

    @Query("SELECT hp FROM HocPhan hp JOIN KhungChuongTrinh k ON hp.khungId = k.khungId WHERE k.ctdt = :ctdtId")
    List<HocPhan> findHocPhansByCtdtId(@Param("ctdtId") Integer ctdtId);

}
