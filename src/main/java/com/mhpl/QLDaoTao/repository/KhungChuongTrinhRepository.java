package com.mhpl.QLDaoTao.repository;

import com.mhpl.QLDaoTao.model.KhungChuongTrinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhungChuongTrinhRepository extends JpaRepository<KhungChuongTrinh, Integer> {

}
