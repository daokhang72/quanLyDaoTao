package com.QLDaoTao.repository;

import com.QLDaoTao.model.KhungChuongTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhungChuongTrinhRepository extends JpaRepository<KhungChuongTrinh, Integer> {
    List<KhungChuongTrinh> findByCtdt_CtdtId(Integer ctdtId);
}
