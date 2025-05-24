import {handleSearch,loadGV,addGV,handleEditFormSubmit,deleteGV,showThongKeGV,loadScript,handleImportExcel,handleExportExcel} from '/js/giangvien.js';
import {loadUserOptions} from '/js/user.js';

export default function createGV() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1>Danh sách giảng viên</h1>
                <div class="top-bar">
                    <div class="search-container">
                        <input type="text" id="searchInput" class="search-input"placeholder="Nhập tên giảng viên ..."/>
                        <button id="searchBtn" class="search-button">🔍</button>
                    </div>
                    <div class="button-container">
                        <button id="importBtn" class="import-button">Import Excel 📥</button>
                        <button id="exporttBtn" class="export-button">Export Excel 📤</button>
                        <input type="file" id="excelFileInput" accept=".xlsx, .xls" style="display: none;">
                        <button id="filtertBtn" class="filter-button">Thống kê 📊</button>
                        <button id="addBtn" class="add-button">➕ Thêm Giảng Viên</button>
                    </div>
                </div>

                <table class="gv-table" id="giangVienTable">
                    <thead>
                    <tr>
                        <th>Giảng viên ID</th>
                        <th>Tên giảng viên</th>
                        <th>Loại giảng viên</th>
                        <th>Đơn vị</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Tác vụ</th>
                    </tr>
                    </thead>
                    <tbody id="GVTableBody">

                    </tbody>
                </table>
                <!-- Modal thêm giảng viên -->
                        <div id="addGVModal" class="modal-overlay" style="display: none;">
                            <div class="modal-content">
                                <h3>Thêm Giảng Viên</h3>
                                <form id="addGVForm">
                                    <label for="giangVienId">Mã Giảng Viên (ID):</label>
                                    <input type="text" id="giangVienId" name="giangVienId" required><br><br>

                                    <label for="userId">Tài khoản người dùng:</label>
                                    <select id="userId" name="userId" required>
                                        <option value="">-- Chọn tài khoản --</option>
                                    </select><br><br>

                                    <label for="hoTen">Tên giảng viên:</label>
                                    <input type="text" id="hoTen" name="hoTen" required><br><br>

                                    <label for="loaiGiangVien">Loại giảng viên:</label>
                                    <input type="text" id="loaiGiangVien" name="loaiGiangVien" required><br><br>

                                    <label for="donVi">Đơn vị:</label>
                                    <input type="text" id="donVi" name="donVi" required><br><br>

                                    <label for="email">Email:</label>
                                    <input type="email" id="email" name="email" required><br><br>

                                    <label for="soDienThoai">Số điện thoại:</label>
                                    <input type="tel" id="soDienThoai" name="soDienThoai" required><br><br>

                                    <button type="submit">Lưu</button>
                                    <button type="button" id="cancelAddGV">Hủy
                                    </button>
                                </form>
                            </div>
                        </div>
                <!-- Modal chỉnh sửa giảng viên -->
                        <div id="editGVModal" class="modal-overlay" style="display: none;">
                            <div class="modal-content">
                                <h3>Chỉnh sửa Giảng Viên</h3>
                                <form id="editGVForm">
                                    <label for="editGiangVienId">Mã Giảng Viên (ID):</label>
                                    <input type="text" id="editGiangVienId" name="giangVienId" readonly><br><br>

                                    <label for="editUserId">Tài khoản người dùng:</label>
                                    <select id="editUserId" name="userId" required>
                                        <option value="">-- Chọn tài khoản --</option>
                                    </select><br><br>

                                    <label for="editHoTen">Tên giảng viên:</label>
                                    <input type="text" id="editHoTen" name="hoTen" required><br><br>

                                    <label for="editLoaiGiangVien">Loại giảng viên:</label>
                                    <input type="text" id="editLoaiGiangVien" name="loaiGiangVien" required><br><br>

                                    <label for="editDonVi">Đơn vị:</label>
                                    <input type="text" id="editDonVi" name="donVi" required><br><br>

                                    <label for="editEmail">Email:</label>
                                    <input type="email" id="editEmail" name="email" required><br><br>

                                    <label for="editSoDienThoai">Số điện thoại:</label>
                                    <input type="tel" id="editSoDienThoai" name="soDienThoai" required><br><br>

                                    <button type="submit">Cập nhật</button>
                                    <button type="button" id="cancelEditGV">Hủy
                                    </button>
                                </form>
                            </div>
                        </div>
                <!-- Modal thống kê -->
                        <div id="statisticModal" class="modal-overlay" style="display: none;">
                            <div class="modal-content">
                                <h3>Thống kê giảng viên theo loại</h3>
                                <canvas id="giangVienChart" width="400" height="300"></canvas>
                                <br>
                                <button id="closeStatisticModal">Đóng</button>
                            </div>
                        </div>
                `;
               document.getElementById("addBtn").addEventListener("click", addGV);
               document.getElementById("filtertBtn").addEventListener("click", () => {
                   const statisticModal = document.getElementById("statisticModal");

                   if (statisticModal) {
                       statisticModal.style.display = "flex";

                       loadScript("https://cdn.jsdelivr.net/npm/chart.js", () => {
                           showThongKeGV();
                       });
                   }
               });
               document.getElementById("importBtn").addEventListener("click", () => {
                    loadScript("https://cdn.jsdelivr.net/npm/xlsx/dist/xlsx.full.min.js", () => {
                            console.log("Đã load xong thư viện XLSX.");
                            handleImportExcel();
                    });
               });
               document.getElementById("exporttBtn").addEventListener("click", () => {
                   loadScript("https://cdn.jsdelivr.net/npm/xlsx/dist/xlsx.full.min.js", () => {
                       console.log("Đã load xong thư viện XLSX.");
                       handleExportExcel();
                   });
               });
                document.getElementById("cancelAddGV").addEventListener("click", () => {
                        document.getElementById("addGVModal").style.display = "none";
                });

                document.getElementById("searchBtn").addEventListener("click", handleSearch);

                document.getElementById("cancelEditGV").addEventListener("click", () => {
                        document.getElementById("editGVModal").style.display = "none";
                });
                document.getElementById("closeStatisticModal").addEventListener("click", () => {
                         document.getElementById("statisticModal").style.display = "none";
                });
                const form = document.getElementById("editGVForm");
                    if (form) {
                        form.addEventListener("submit", handleEditFormSubmit);
                    }

                loadUserOptions("userId");
                loadUserOptions("editUserId");

                loadGV();
}
