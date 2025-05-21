import {
    getAllPlans,
    createPlanAPI,
    updatePlanAPI,
    deletePlanAPI
} from "/jsApi/planJSAPI.js";

export default function createPlan() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1>Danh sách học phần từng học kỳ</h1>
    
        <div class="top-bar">
            <div class="search-container">
                <input type="text" id="searchInput" class="search-input"
                       placeholder="Nhập tên hoặc mã học phần"/>
                <button class="search-button" id="searchBtn">🔍</button>
            </div>
            <div class="button-container">
                <button class="add-button" id="addBtn">➕ Thêm Học Phần</button>
            </div>
        </div>
    
        <table class="khdh-table">
            <thead>
                <tr>
                    <th>Mã</th><th>Tên</th><th>Số TC</th>
                    <th>HK thực hiện</th>
                    <th>Tiên quyết</th><th>Hành động</th>
                </tr>
            </thead>
            <tbody id="planTableBody"></tbody>
        </table>
    
        <!-- Modal -->
        <div id="planModal" class="modal" style="display:none;">
            <div class="modal-content">
                <h2 id="modalTitle"></h2>
                <input type="hidden" id="modalKeHoachId" />
                <label>Mã học phần:</label>
                <input type="number" id="modalMaHp" /><br/>
                <label>Tên học phần:</label>
                <input type="text" id="modalTen" /><br/>
                <label>Số tín chỉ:</label>
                <input type="number" id="modalSoTC" /><br/>
                <label>Học kỳ thực hiện:</label>
                <input type="text" id="modalHocKy" /><br/>
                <label>Mã tiên quyết:</label>
                <input type="number" id="modalTienQuyet" /><br/>
                <button id="saveBtn">💾 Lưu</button>
                <button onclick="document.getElementById('planModal').style.display='none'">❌ Hủy</button>
            </div>
        </div>
    `;


    async function renderTable(filterText = "") {
        const data = await getAllPlans();
        const tbody = document.getElementById("planTableBody");
        tbody.innerHTML = "";

        data
            .filter(p =>
                p.tenHocPhan.toLowerCase().includes(filterText.toLowerCase()) ||
                p.maHp.toString().includes(filterText)
            )
            .forEach(plan => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                <td>${plan.maHp}</td>
                <td>${plan.tenHocPhan}</td>
                <td>${plan.soTinChi}</td>
                <td>${plan.hocKyThucHien}</td>
                <td>${plan.maHocPhanTruoc || ""}</td>
                <td>
                    <button class="editBtn" data-id="${plan.keHoachId}">Sửa</button>
                    <button class="deleteBtn" data-id="${plan.keHoachId}">Xoá</button>
                </td>
            `;
                tbody.appendChild(tr);
            });

        document.querySelectorAll(".editBtn").forEach(btn => {
            btn.onclick = () => openModal(data.find(p => p.keHoachId == btn.dataset.id));
        });
        document.querySelectorAll(".deleteBtn").forEach(btn => {
            btn.onclick = async () => {
                if (confirm("Xác nhận xoá?")) {
                    await deletePlanAPI(btn.dataset.id);
                    await renderTable();
                }
            };
        });
    }

    function openModal(plan = null) {
        document.getElementById("planModal").style.display = "flex";
        document.getElementById("modalTitle").innerText = plan ? "Sửa học phần" : "Thêm học phần";
        document.getElementById("modalKeHoachId").value = plan?.keHoachId || "";
        document.getElementById("modalMaHp").value = plan?.maHp || "";
        document.getElementById("modalTen").value = plan?.tenHocPhan || "";
        document.getElementById("modalSoTC").value = plan?.soTinChi || "";
        document.getElementById("modalHocKy").value = plan?.hocKyThucHien || "";
        document.getElementById("modalTienQuyet").value = plan?.maHocPhanTruoc || "";
    }

    async function savePlan() {
        const id = document.getElementById("modalKeHoachId").value;
        const plan = {
            maHp: parseInt(document.getElementById("modalMaHp").value),
            tenHocPhan: document.getElementById("modalTen").value,
            soTinChi: parseInt(document.getElementById("modalSoTC").value),
            hocKyThucHien: document.getElementById("modalHocKy").value,
            maHocPhanTruoc: parseInt(document.getElementById("modalTienQuyet").value) || 0
        };

        if (id) {
            await updatePlanAPI(id, plan);
        } else {
            await createPlanAPI(plan);
        }

        document.getElementById("planModal").style.display = "none";
        await renderTable();
    }

    // Gắn sự kiện
    document.getElementById("addBtn").onclick = () => openModal();
    document.getElementById("saveBtn").onclick = savePlan;
    document.getElementById("searchBtn").onclick = () => {
        const value = document.getElementById("searchInput").value;
        renderTable(value);
    };

    renderTable();
}
