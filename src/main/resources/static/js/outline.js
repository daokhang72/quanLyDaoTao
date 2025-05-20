import {getAllHocPhan} from '/jsApi/hocPhanJSAPI.js';
import {layDeCuongTheoIdMon} from '/jsApi/deCuongJSAPI.js';

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
    return layDeCuongTheoIdMon(maHp);
}
