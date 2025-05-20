import {LoadHocPhan,getDeCuongChiTiet} from '/js/outline.js';

export default function createOutline() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1>Đề cương chi tiết</h1>

        <div class="top-bar">
            <div class="button-container">
                <select id = "hocPhanSelect" class="filter-combobox">
                  <option value="" disabled selected>Chọn Học Phần ⌵</option>
                </select>
                <button class="add-button" onclick="addCTDT()">➕ Thêm Đề Cương</button>
            </div>
        </div>

        <table class="đcct-table">
            <thead>
            <tr>
                <th>Tên bộ phận phụ trách</th>
                <th>Điểm đánh giá bộ phận</th>
                <th>Trọng số</th>
                <th>Hình thức đánh giá</th>
                <th>Tác vụ</th>
            </tr>
            </thead>
            <tbody id="deCuongTableBody">
            </tbody>
        </table>
    `;

    window.onload = LoadHocPhan();

    const select = document.getElementById("hocPhanSelect");
    select.addEventListener("change", async function () {
        const maHp = this.value;
        if (!maHp) return;

        try {
            const deCuongList = await getDeCuongChiTiet(maHp);

            const tbody = document.getElementById("deCuongTableBody");
            tbody.innerHTML = "";

            deCuongList.forEach(item => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${item.tenBoPhan}</td>
                    <td>${item.diemDanhGia}</td>
                    <td>${item.trongSo}</td>
                    <td>${item.hinhThuc}</td>
                    <td><button onclick="editRow('${item.id}')">✏️</button></td>
                `;
                tbody.appendChild(row);
            });
        } catch (error) {
            console.error("Lỗi khi lấy đề cương chi tiết:", error);
        }
    });
}
