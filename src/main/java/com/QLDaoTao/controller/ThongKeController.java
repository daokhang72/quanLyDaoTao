package com.QLDaoTao.controller;

import com.QLDaoTao.repository.HocPhanRepository;
import com.QLDaoTao.repository.GiangVienRepository;
import com.QLDaoTao.repository.ThongTinChungRepository;
import com.QLDaoTao.repository.KeHoachDayHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ThongKeController {

    @Autowired
    private HocPhanRepository hocPhanRepo;
    @Autowired
    private ThongTinChungRepository thongTinChungRepo;
    @Autowired
    private GiangVienRepository giangVienRepo;
    @Autowired
    private KeHoachDayHocRepository keHoachDayHocRepo;

    // API tổng hợp thống kê
    @GetMapping("/thongke")
    public Map<String, Long> thongKeTongQuat() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("soHocPhan", hocPhanRepo.count());
        stats.put("soCTDT", thongTinChungRepo.count());
        stats.put("soGiangVien", giangVienRepo.count());
        stats.put("soKeHoach", keHoachDayHocRepo.count());
        return stats;
    }
}
