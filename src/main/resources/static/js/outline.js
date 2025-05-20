import {getAllHocPhan} from '/jsApi/hocPhanJSAPI.js';

export async function LoadHocPhan() {
    try {
        const dsHocPhan = await getAllHocPhan();
        const select = document.getElementById("hocPhanSelect");

        select.innerHTML = '<option value="" disabled selected>Chọn Học Phần ⌵</option>';

        dsHocPhan.forEach(hocPhan => {
            const option = document.createElement("option");
            option.value = hocPhan.maHp;
            option.textContent = hocPhan.tenHocPhan;
            select.appendChild(option);
        });

    } catch (error) {
        console.error("Lỗi khi tải học phần:", error);
    }
}

export async function getDeCuongChiTiet(maHp) {
    return [
        {
            id: 1,
            tenBoPhan: "Khoa Công nghệ thông tin",
            diemDanhGia: "8.5",
            trongSo: "40%",
            hinhThuc: "Bài tập lớn"
        },
        {
            id: 2,
            tenBoPhan: "Phòng khảo thí",
            diemDanhGia: "9.0",
            trongSo: "60%",
            hinhThuc: "Thi cuối kỳ"
        }
    ];
}
