import { createUser, fetchUserList,updateUser,deleteUserById} from '/jsApi/userJSAPI.js';

export async function loadUser() {
    try {
        const res = await fetchUserList();

        if (res.status === 200) {
            const tbody = document.getElementById('userTableBody');
            tbody.innerHTML = "";

            res.data.forEach(user => {
                const row = document.createElement("tr");
                row.setAttribute('data-id', user.userId);
                row.innerHTML = `
                    <td>${user.userName}</td>
                    <td>${user.userEmail}</td>
                    <td>${user.userPassword}</td>
                    <td>${user.userFullName}</td>
                    <td>${user.admin === true ? "✔️" : "❌"}</td>
                `;

                // Gán sự kiện click để chọn dòng
                row.addEventListener("click", () => {
                    document.querySelectorAll(".user-table tbody tr").forEach(r => r.classList.remove("selected"));
                    row.classList.add("selected");
                });

                tbody.appendChild(row);
            });
        } else {
            alert("Không thể tải danh sách người dùng. Mã lỗi: " + res.status);
        }
    } catch (err) {
        alert("Đã xảy ra lỗi khi tải danh sách người dùng.");
    }
}

export function addCTDT() {
    // Hiển thị modal
    const modal = document.getElementById("addUserModal");
    if (modal) {
        modal.style.display = "flex";
    }

    // Gắn sự kiện khi chắc chắn phần tử đã có trong DOM
    const form = document.getElementById("addUserForm");
    if (form) {
        form.addEventListener("submit", handleFormSubmit);
    } else {
        console.error("Không tìm thấy form addUserForm trong DOM.");
    }
}

async function handleFormSubmit(e) {
    e.preventDefault();

    const formData = {
        userName: document.getElementById("userName").value,
        userEmail: document.getElementById("userEmail").value,
        userPassword: document.getElementById("userPassword").value,
        userFullName: document.getElementById("userFullName").value,
        isAdmin: document.getElementById("isAdmin").checked
    };

    try {
        const res = await createUser(formData);

        if (res.status === 200 || res.status === 201) {
            alert("Thêm người dùng thành công!");
            document.getElementById("addUserModal").style.display = "none";
            loadUser();
        } else {
            alert("Lỗi khi thêm người dùng: " + (res.data?.message || "Không rõ lý do"));
        }
    } catch (err) {
        alert("Đã xảy ra lỗi kết nối.");
    }
}

function fillEditFormFromRow(row) {
    try {
        const cells = row.getElementsByTagName("td");

        if (cells.length < 5) {
            alert("Dữ liệu người dùng không đầy đủ.");
            return;
        }

        const userId = row.getAttribute("data-id");
        const userName = cells[0].innerText;
        const userEmail = cells[1].innerText;
        const userPassword = cells[2].innerText;
        const userFullName = cells[3].innerText;
        const isAdminText = cells[4].innerText.trim();
        const isAdmin = isAdminText === "✔️";

        document.getElementById("editUserId").value = userId;
        document.getElementById("editUserName").value = userName;
        document.getElementById("editUserEmail").value = userEmail;
        document.getElementById("editUserPassword").value = userPassword;
        document.getElementById("editUserFullName").value = userFullName;
        document.getElementById("editIsAdmin").checked = isAdmin;
    } catch (e) {
        console.error("Lỗi trong fillEditFormFromRow:", e);
        alert("Không thể điền dữ liệu vào form. Kiểm tra bảng và dữ liệu.");
    }
}
export function editUser() {
    const selectedRow = document.querySelector(".user-table tbody tr.selected");
    if (!selectedRow) {
        alert("Vui lòng chọn 1 người dùng để sửa");
        return;
    }

    fillEditFormFromRow(selectedRow);

    const modal = document.getElementById("EditUserModal");
    if (modal) {
        modal.style.display = "flex";
    }

    const form = document.getElementById("EditUserForm");
    if (form) {
        form.removeEventListener("submit", handleEditFormSubmit);
        form.addEventListener("submit", handleEditFormSubmit);
    } else {
        console.error("Không tìm thấy form EditUserForm trong DOM.");
    }
}

async function handleEditFormSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const user = {
        userId: formData.get('userId'),
        userName: formData.get('userName'),
        userEmail: formData.get('userEmail'),
        userPassword: formData.get('userPassword'),
        userFullName: formData.get('userFullName'),
        isAdmin: formData.get('isAdmin') ? true : false
    };

    try {
        const res = await updateUser(user);
        if (res.status === 200) {
            alert("Cập nhật thành công!");
            document.getElementById("EditUserModal").style.display = "none";
            loadUser();
        } else {
            alert("Cập nhật thất bại! Mã lỗi: " + res.status);
        }
    } catch (err) {
        alert("Có lỗi xảy ra trong quá trình cập nhật!");
    }
}

export function deleteUser() {
    const selectedRow = document.querySelector(".user-table tbody tr.selected");
    if (!selectedRow) {
        alert("Vui lòng chọn 1 người dùng để xóa");
        return;
    }

    const userId = selectedRow.getAttribute("data-id");

    if (confirm("Bạn có chắc chắn muốn xóa người dùng này?")) {
        deleteUserById(userId)
            .then(res => {
                if (res.status === 200) {
                    alert("Xóa người dùng thành công!");
                    location.reload();
                } else {
                    alert("Xóa người dùng thất bại! Mã lỗi: " + res.status);
                }
            })
            .catch(err => {
                alert("Có lỗi xảy ra trong quá trình xóa!");
            });
    }
}


export function handleSearch() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll(".user-table tbody tr");

    rows.forEach(row => {
        const userName = row.cells[0].innerText.toLowerCase(); // Cột đầu tiên là tên người dùng
        if (userName.includes(input)) {
            row.style.display = ""; // Hiển thị
        } else {
            row.style.display = "none"; // Ẩn
        }
    });
}


export function loadUserOptions(selectId) {
    fetch("/admin/user/list")
        .then(res => res.json())
        .then(users => {
            const select = document.getElementById(selectId);
            users.forEach(user => {
                const option = document.createElement("option");
                option.value = user.userId;
                option.textContent = `${user.userFullName} (${user.userEmail})`;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("Lỗi khi tải danh sách người dùng:", err));
}

let currentChart = null;
export function renderUserStats() {

if (currentChart) {
    currentChart.destroy();
    currentChart = null;
  }
  fetchUserList()
    .then(res => {
      if (res.status !== 200) {
        document.getElementById("stat-content").innerHTML = "<p>Lỗi khi tải dữ liệu người dùng.</p>";
        return;
      }

      const users = res.data;

      // Tính toán
      const total = users.length;
      const adminCount = users.filter(u => u.admin).length;
      const userCount = total - adminCount;
      const adminPercent = ((adminCount / total) * 100).toFixed(1);
      const userPercent = ((userCount / total) * 100).toFixed(1);

      // Tạo HTML thống kê
      let html = `
        <h3>Thống kê Người dùng</h3>
        <ul>
          <li>🔢 Tổng số người dùng: <b>${total}</b></li>
          <li>👤 Số người là Admin: <b>${adminCount}</b></li>
          <li>👥 Số người là User thường: <b>${userCount}</b></li>
          <li>📊 Tỷ lệ Admin/User: <b>${adminPercent}% / ${userPercent}%</b></li>
        </ul>
        <canvas id="PieChart" width="300" height="300"></canvas>
      `;

      document.getElementById("stat-content").innerHTML = html;

      drawUserPieChart(adminCount, userCount);
    })
    .catch(err => {
      document.getElementById("stat-content").innerHTML = "<p>Không thể tải dữ liệu người dùng.</p>";
      console.error(err);
    });
}
function drawUserPieChart(adminCount, userCount) {
  const ctx = document.getElementById("PieChart").getContext("2d");

  new Chart(ctx, {
    type: "pie",
    data: {
      labels: ["Admin", "User thường"],
      datasets: [{
        data: [adminCount, userCount],
        backgroundColor: ["#36A2EB", "#FF6384"]
      }]
    },
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: "bottom"
        }
      }
    }
  });
}

export function loadScript(src, callback) {
  const script = document.createElement("script");
  script.src = src;
  script.onload = callback;
  document.head.appendChild(script);
}

