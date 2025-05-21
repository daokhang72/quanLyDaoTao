import {
    layTatCaPhanCong,
    xoaPhanCong,
    capNhatPhanCong,
    taoPhanCong
} from '/jsApi/giangdayJSAPI.js';

export default function createAssignment() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1>Danh sách học phần đã phân công</h1>
        <div class="top-bar">
            <div class="search-container">
                <input type="text" id="searchInput" class="search-input" placeholder="Nhập tên giảng viên, mã học phần..." />
                <button class="search-button" id="searchButton">🔍</button>
            </div>
            <div class="button-container">
                <button class="add-button" onclick="addCTDT()">➕ Thêm Học Phần</button>
            </div>
        </div>

        <table class="pcgd-table">
            <thead>
                <tr>
                    <th>Mã học phần</th>
                    <th>Giảng viên</th>
                    <th>Nhóm</th>
                    <th>Số tiết thực hiện</th>
                    <th>Số tiết thực tế</th>
                    <th>Tác vụ</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>

        <div id="editModal" class="modal" style="display:none;">
            <div class="modal-content">
                <h3>Chỉnh sửa phân công</h3>
                <form id="editForm">
                    <input type="hidden" id="editId" />
                    <label>Mã học phần: <input type="number" id="editMahp" required /></label><br/>
                    <label>Giảng viên: <input type="text" id="editGV" required /></label><br/>
                    <label>Nhóm: <input type="text" id="editNhom" required /></label><br/>
                    <label>Số tiết thực hiện: <input type="number" id="editSTTH" required /></label><br/>
                    <label>Số tiết thực tế: <input type="number" id="editSTTT" required /></label><br/>
                    <button type="submit">Lưu</button>
                    <button type="button" id="closeModal">Hủy</button>
                </form>
            </div>
        </div>
        <div id="addModal" class="modal" style="display:none;">
            <div class="modal-content">
                <h3>Thêm học phần phân công</h3>
                <form id="addForm">
                    <label>Mã học phần: <input type="number" id="addMahp" required /></label><br/>
                    <label>Giảng viên: <input type="text" id="addGV" required /></label><br/>
                    <label>Nhóm: <input type="text" id="addNhom" required /></label><br/>
                    <label>Số tiết thực hiện: <input type="number" id="addSTTH" required /></label><br/>
                    <label>Số tiết thực tế: <input type="number" id="addSTTT" required /></label><br/>
                    <button type="submit">Lưu</button>
                    <button type="button" id="closeAddModal">Hủy</button>
                </form>
            </div>
        </div>
    `;

    const tbody = document.querySelector(".pcgd-table tbody");
    const searchInput = document.getElementById("searchInput");
    const searchButton = document.getElementById("searchButton");

    async function hienThiPhanCongVaoBang(filter = "") {
        try {
            const data = await layTatCaPhanCong();
            tbody.innerHTML = "";

            const filtered = data.filter(item =>
                item.hotencbgd.toLowerCase().includes(filter.toLowerCase()) ||
                item.mahp.toString().includes(filter)
            );

            filtered.forEach(item => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${item.mahp}</td>
                    <td>${item.hotencbgd}</td>
                    <td>${item.nhom}</td>
                    <td>${item.sotietthuchien}</td>
                    <td>${item.sotietthucte}</td>
                    <td>
                        <button class="edit-button" data-id="${item.pcgdId}">✏️</button>
                        <button class="delete-button" data-id="${item.pcgdId}">🗑️</button>
                    </td>
                `;
                tbody.appendChild(row);
            });

            ganSuKienChoNut();

        } catch (error) {
            console.error("Lỗi khi lấy danh sách phân công giảng dạy:", error);
            tbody.innerHTML = `<tr><td colspan="6">Không thể tải dữ liệu</td></tr>`;
        }
    }

    function ganSuKienChoNut() {
        const deleteButtons = document.querySelectorAll(".delete-button");
        const editButtons = document.querySelectorAll(".edit-button");

        deleteButtons.forEach(btn => {
            btn.addEventListener("click", async () => {
                const id = btn.getAttribute("data-id");
                if (confirm("Bạn có chắc muốn xoá?")) {
                    await xoaPhanCong(id);
                    await hienThiPhanCongVaoBang(searchInput.value);
                }
            });
        });

        editButtons.forEach(btn => {
            btn.addEventListener("click", () => {
                const row = btn.closest("tr");
                document.getElementById("editId").value = btn.getAttribute("data-id");
                document.getElementById("editMahp").value = row.children[0].innerText;
                document.getElementById("editGV").value = row.children[1].innerText;
                document.getElementById("editNhom").value = row.children[2].innerText;
                document.getElementById("editSTTH").value = row.children[3].innerText;
                document.getElementById("editSTTT").value = row.children[4].innerText;
                document.getElementById("editModal").style.display = "flex";
            });
        });
    }

    document.getElementById("closeModal").addEventListener("click", () => {
        document.getElementById("editModal").style.display = "none";
    });

    document.getElementById("editForm").addEventListener("submit", async (e) => {
        e.preventDefault();
        const id = document.getElementById("editId").value;
        const newData = {
            mahp: parseInt(document.getElementById("editMahp").value),
            hotencbgd: document.getElementById("editGV").value,
            nhom: document.getElementById("editNhom").value,
            sotietthuchien: parseInt(document.getElementById("editSTTH").value),
            sotietthucte: parseInt(document.getElementById("editSTTT").value),
        };

        await capNhatPhanCong(id, newData);
        document.getElementById("editModal").style.display = "none";
        await hienThiPhanCongVaoBang(searchInput.value);
    });

    searchButton.addEventListener("click", () => {
        hienThiPhanCongVaoBang(searchInput.value);
    });

    hienThiPhanCongVaoBang();

    window.addCTDT = function () {
        document.getElementById("addForm").reset();
        document.getElementById("addModal").style.display = "flex";
    }

    document.getElementById("closeAddModal").addEventListener("click", () => {
        document.getElementById("addModal").style.display = "none";
    });

    document.getElementById("addForm").addEventListener("submit", async (e) => {
        e.preventDefault();
        const newData = {
            mahp: parseInt(document.getElementById("addMahp").value),
            hotencbgd: document.getElementById("addGV").value,
            nhom: document.getElementById("addNhom").value,
            sotietthuchien: parseInt(document.getElementById("addSTTH").value),
            sotietthucte: parseInt(document.getElementById("addSTTT").value),
        };

        try {
            await taoPhanCong(newData); // Cần import API này bên trên
            document.getElementById("addModal").style.display = "none";
            await hienThiPhanCongVaoBang(searchInput.value);
        } catch (error) {
            console.error("Lỗi khi thêm phân công:", error);
            alert("Thêm thất bại.");
        }
    });

}
