package com.QLDaoTao.repository;

import com.QLDaoTao.model.ThongTinChung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThongTinChungRepository extends JpaRepository<ThongTinChung, Integer> {
    @Query("""
    SELECT t FROM ThongTinChung t 
    WHERE 
        LOWER(t.tenCtdt) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.bac) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.loaiBang) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.loaiHinhDaoTao) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.khoaQuanLy) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.ngonNgu) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.website) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(t.banHanh) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<ThongTinChung> searchByKeyword(@Param("keyword") String keyword);
}
