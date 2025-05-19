import { loadUser, addCTDT, editUser, deleteUser, handleSearch } from '/js/user.js';

export default function createUser() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1>Danh sách người dùng</h1>
        <div class="top-bar">
            <div class="search-container">
                <input type="text" id="searchInput" class="search-input" placeholder="Nhập tên người dùng ..."/>
                <button id="searchBtn" class="search-button">🔍</button>
            </div>
            <div class="button-container">
                <button id="addBtn" class="add-button">➕ Thêm Người Dùng</button>
                <button id="editBtn" class="add-button">📝 Sửa Người Dùng</button>
                <button id="deleteBtn" class="add-button">🗑️ Xóa Người Dùng</button>
            </div>
        </div>

        <table class="user-table">
            <thead>
                <tr>
                    <th>Tên người dùng</th>
                    <th>Email</th>
                    <th>Mật khẩu</th>
                    <th>Họ tên người dùng</th>
                    <th>Admin</th>
                </tr>
            </thead>
            <tbody id="userTableBody">

            </tbody>
        </table>

        <!-- Modal thêm người dùng -->
        <div id="addUserModal" class="modal-overlay" style="display: none;">
            <div class="modal-content">
                <h3>Thêm người dùng</h3>
                <form id="addUserForm">
                    <input type="hidden" id="userId" name="userId">

                    <label for="userName">Tên người dùng:</label>
                    <input type="text" id="userName" name="userName" required><br><br>

                    <label for="userEmail">Email:</label>
                    <input type="email" id="userEmail" name="userEmail" required><br><br>

                    <label for="userPassword">Mật khẩu:</label>
                    <input type="password" id="userPassword" name="userPassword" required><br><br>

                    <label for="userFullName">Họ tên người dùng:</label>
                    <input type="text" id="userFullName" name="userFullName" required><br><br>

                    <label for="isAdmin">Là admin:</label>
                    <input type="checkbox" id="isAdmin" name="isAdmin"><br><br>

                    <button type="submit">Lưu</button>
                    <button type="button" id="cancelAddUser">Hủy</button>
                </form>
            </div>
        </div>

        <!-- Modal chỉnh sửa người dùng -->
        <div id="EditUserModal" class="modal-overlay" style="display: none;">
            <div class="modal-content">
                <h3>Chỉnh sửa người dùng</h3>
                <form id="EditUserForm">
                    <input type="hidden" id="editUserId" name="userId">

                    <label for="editUserName">Tên người dùng:</label>
                    <input type="text" id="editUserName" name="userName" required><br><br>

                    <label for="editUserEmail">Email:</label>
                    <input type="email" id="editUserEmail" name="userEmail" required><br><br>

                    <label for="editUserPassword">Mật khẩu:</label>
                    <input type="text" id="editUserPassword" name="userPassword" required><br><br>

                    <label for="editUserFullName">Họ tên người dùng:</label>
                    <input type="text" id="editUserFullName" name="userFullName" required><br><br>

                    <label for="editIsAdmin">Là admin:</label>
                    <input type="checkbox" id="editIsAdmin" name="isAdmin"><br><br>

                    <button type="submit">Lưu</button>
                    <button type="button" id="cancelEditUser">Hủy</button>
                </form>
            </div>
        </div>
    `;

    document.getElementById("addBtn").addEventListener("click", addCTDT);
    document.getElementById("editBtn").addEventListener("click", editUser);
    document.getElementById("deleteBtn").addEventListener("click", deleteUser);
    document.getElementById("searchBtn").addEventListener("click", handleSearch);
    document.getElementById("searchInput").addEventListener("input", handleSearch);

    document.getElementById("cancelAddUser").addEventListener("click", () => {
        document.getElementById("addUserModal").style.display = "none";
    });

    document.getElementById("cancelEditUser").addEventListener("click", () => {
        document.getElementById("EditUserModal").style.display = "none";
    });

    loadUser();
}
