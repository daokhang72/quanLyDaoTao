import {LoadHocPhan,getDeCuongChiTiet} from '/js/outline.js';
import {taoDeCuong,suaDeCuong,xoaDeCuong} from '/jsApi/deCuongJSAPI.js';

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
                <button class="add-button" onclick="showDeCuongForm()">➕ Thêm Đề Cương</button>
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
                    <td>${item.hinhThuc ?? ""}</td>
                    <td>
                        <button class="button-frame edit-btn">Sửa</button>
                        <button class="button-frame delete-btn">Xóa</button>
                    </td>
                `;

                const editBtn = row.querySelector(".edit-btn");
                const deleteBtn = row.querySelector(".delete-btn");

                editBtn.addEventListener("click", () => {
                    showDeCuongForm("edit", item);
                });

                deleteBtn.addEventListener("click", async () => {
                    if (confirm("Bạn có chắc muốn xóa đề cương này?")) {
                        await xoaDeCuong(item.deCuongId);
                        select.dispatchEvent(new Event("change"));
                    }
                });

                tbody.appendChild(row);
            });
        } catch (error) {
            console.error("Lỗi khi lấy đề cương chi tiết:", error);
        }
    });

    const formPortal = document.createElement("div");
    formPortal.innerHTML = `
      <div id="decuongModal" class="modal hidden">
        <div class="modal-content">
          <h2 id="modalTitle">Thêm đề cương</h2>
          <label>Tên bộ phận: <input type="text" id="tenBoPhanInput" /></label>
          <label>Điểm đánh giá: <input type="number" id="diemDanhGiaInput" /></label>
          <label>Trọng số: <input type="number" id="trongSoInput" /></label>
          <label>Hình thức đánh giá: <input type="text" id="hinhThucInput" /></label>
          <div class="modal-actions">
            <button id="modalSaveBtn">💾 Lưu</button>
            <button id="modalCancelBtn">❌ Hủy</button>
          </div>
        </div>
      </div>
    `;
    document.body.appendChild(formPortal);


    let currentEditingItem = null;

    window.showDeCuongForm = function (mode, item = null) {
        currentEditingItem = item;

        document.getElementById("modalTitle").innerText = mode === "edit" ? "Sửa đề cương" : "Thêm đề cương";

        // Nếu là sửa, điền sẵn dữ liệu
        document.getElementById("tenBoPhanInput").value = item?.tenBoPhan ?? "";
        document.getElementById("diemDanhGiaInput").value = item?.diemDanhGia ?? "";
        document.getElementById("trongSoInput").value = item?.trongSo ?? "";
        document.getElementById("hinhThucInput").value = item?.hinhThuc ?? "";

        document.getElementById("decuongModal").classList.remove("hidden");
    };

    document.getElementById("modalCancelBtn").addEventListener("click", () => {
        document.getElementById("decuongModal").classList.add("hidden");
    });

    document.getElementById("modalSaveBtn").addEventListener("click", async () => {
        const tenBoPhan = document.getElementById("tenBoPhanInput").value;
        const diemDanhGia = parseFloat(document.getElementById("diemDanhGiaInput").value);
        const trongSo = parseFloat(document.getElementById("trongSoInput").value);
        const hinhThucDanhGia = document.getElementById("hinhThucInput").value;
        const maHp = parseInt(document.getElementById("hocPhanSelect").value, 10);

        if (!tenBoPhan || isNaN(diemDanhGia) || isNaN(trongSo)) {
            alert("Vui lòng điền đầy đủ thông tin hợp lệ.");
            return;
        }

        const data = { tenBoPhan, diemDanhGia, trongSo, hinhThucDanhGia, maHp };

        try {
            if (currentEditingItem) {
                await suaDeCuong(currentEditingItem.deCuongId, data);
            } else {
                await taoDeCuong(data);
            }
            document.getElementById("decuongModal").classList.add("hidden");
            document.getElementById("hocPhanSelect").dispatchEvent(new Event("change"));
        } catch (err) {
            alert("Có lỗi xảy ra khi lưu.");
            console.error(err);
        }
    });



}
