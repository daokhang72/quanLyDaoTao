package com.QLDaoTao.repository;

import com.QLDaoTao.model.HocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HocPhanRepository extends JpaRepository<HocPhan, Integer> {

    @Query("SELECT h FROM HocPhan h " +
            "WHERE (:maHp IS NULL OR h.maHp =:maHp)" +
            "AND (:tenHocPhan IS NULL OR LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :tenHocPhan, '%'))) " +
            "AND (:soTinChi IS NULL OR h.soTinChi = :soTinChi) " +
            "AND (:heSoHocPhan IS NULL OR h.heSoHocPhan = :heSoHocPhan)")
    List<HocPhan> searchAdvanced(
            @Param("maHp") Integer maHp,
            @Param("tenHocPhan") String tenHocPhan,
            @Param("soTinChi") Integer soTinChi,
            @Param("heSoHocPhan") Integer heSoHocPhan
    );

    @Query("SELECT hp FROM HocPhan hp JOIN KhungChuongTrinh k ON hp.khungId = k.khungId WHERE k.ctdt = :ctdtId")
    List<HocPhan> findHocPhansByCtdtId(@Param("ctdtId") Integer ctdtId);

}
