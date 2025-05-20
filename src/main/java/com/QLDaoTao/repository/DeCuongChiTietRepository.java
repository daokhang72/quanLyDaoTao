package com.QLDaoTao.repository;

import com.QLDaoTao.model.DeCuongChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeCuongChiTietRepository extends JpaRepository<DeCuongChiTiet, Integer> {
    List<DeCuongChiTiet> findByMaHp(Long maHp);
}
