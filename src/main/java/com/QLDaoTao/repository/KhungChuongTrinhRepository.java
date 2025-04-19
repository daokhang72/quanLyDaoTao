package com.QLDaoTao.repository;

import com.QLDaoTao.model.KhungChuongTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhungChuongTrinhRepository extends JpaRepository<KhungChuongTrinh, Integer> {

}
