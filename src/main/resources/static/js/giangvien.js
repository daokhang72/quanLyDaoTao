import { createGV, fetchGVList,updateGV,deleteGVById,getThongKeGV} from '/jsApi/giangVienJSAPI.js';


export function handleSearch() {
    const input = document.getElementById("searchInput").value.toLowerCase();
    const rows = document.querySelectorAll(".gv-table tbody tr");

    rows.forEach(row => {
        const userName = row.cells[1].innerText.toLowerCase();
        if (userName.includes(input)) {
             row.style.display = "";
        } else {
             row.style.display = "none";
        }
    });
}

export async function loadGV() {
    try {
        const res = await fetchGVList();

        if (res.status === 200) {
            const tbody = document.getElementById('GVTableBody');
            tbody.innerHTML = "";

            res.data.forEach(gv => {
                const row = document.createElement("tr");
                row.setAttribute('data-userid', gv.userId);
                row.setAttribute('data-id', gv.giangVienId);

                row.innerHTML = `
                    <td>${gv.giangVienId}</td>
                    <td>${gv.hoTen}</td>
                    <td>${gv.loaiGiangVien}</td>
                    <td>${gv.donVi}</td>
                    <td>${gv.email}</td>
                    <td>${gv.soDienThoai}</td>
                    <td>
                       <button class="edit-btn">✏️</button>
                       <button class="delete-btn">🗑️</button>
                    </td>
                `;

                row.addEventListener("click", () => {
                    document.querySelectorAll(".gv-table tbody tr").forEach(r => r.classList.remove("selected"));
                    row.classList.add("selected");
                });

                const editBtn = row.querySelector(".edit-btn");
                editBtn.addEventListener("click", (event) => {
                    event.stopPropagation(); // Ngăn sự kiện click dòng
                    fillEditFormFromRow(row); // Điền form
                    document.getElementById("editGVModal").style.display = "flex";
                });

                const deleteBtn = row.querySelector(".delete-btn");
                deleteBtn.addEventListener("click", async (event) => {
                    event.stopPropagation();
                    await deleteGV(deleteBtn); // gọi hàm xóa
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


export function addGV() {
    // Hiển thị modal
    const modal = document.getElementById("addGVModal");
    if (modal) {
        modal.style.display = "flex";
    }

    // Gắn sự kiện khi chắc chắn phần tử đã có trong DOM
    const form = document.getElementById("addGVForm");
    if (form) {
        form.addEventListener("submit", handleFormSubmit);
    } else {
        console.error("Không tìm thấy form addGVForm trong DOM.");
    }
}

async function handleFormSubmit(e) {
    e.preventDefault();

    const formData = {
                giangVienId: document.getElementById("giangVienId").value,
                userId: document.getElementById("userId").value,
                hoTen: document.getElementById("hoTen").value,
                loaiGiangVien: document.getElementById("loaiGiangVien").value,
                donVi: document.getElementById("donVi").value,
                email: document.getElementById("email").value,
                soDienThoai: document.getElementById("soDienThoai").value
            };

    try {
        const res = await createGV(formData);

        if (res.status === 200 || res.status === 201) {
            alert("Thêm giảng viên thành công!");
            document.getElementById("addGVModal").style.display = "none";
            loadGV();
        } else {
            alert("Lỗi khi thêm giảng viên: " + (res.data?.message || "Không rõ lý do"));
        }
    } catch (err) {
        alert("Đã xảy ra lỗi kết nối.");
    }
}

function fillEditFormFromRow(row) {
    try {
            const cells = row.getElementsByTagName("td");

            if (cells.length < 6) {
                alert("Dữ liệu giảng viên không đầy đủ.");
                return;
            }

            const giangVienId = cells[0].innerText.trim();
            const hoTen = cells[1].innerText.trim();
            const loaiGiangVien = cells[2].innerText.trim();
            const donVi = cells[3].innerText.trim();
            const email = cells[4].innerText.trim();
            const soDienThoai = cells[5].innerText.trim();

            // Lấy userId từ attribute data-userid
            const userId = row.dataset.userid;

            document.getElementById("editGiangVienId").value = giangVienId;
            document.getElementById("editHoTen").value = hoTen;
            document.getElementById("editLoaiGiangVien").value = loaiGiangVien;
            document.getElementById("editDonVi").value = donVi;
            document.getElementById("editEmail").value = email;
            document.getElementById("editSoDienThoai").value = soDienThoai;

            // Set giá trị cho select userId
            document.getElementById("editUserId").value = userId;

            document.getElementById("editGVModal").style.display = "block";

        } catch (e) {
            console.error("Lỗi trong fillEditFormFromRow:", e);
            alert("Không thể điền dữ liệu vào form giảng viên.");
        }
}
let isEditHandlerAttached = false;

export function editGV(button) {
    const row = button.closest("tr");
    fillEditFormFromRow(row);

    const modal = document.getElementById("editGVModal");
    if (modal) {
        modal.style.display = "flex";
    }

    const form = document.getElementById("editGVForm");
    if (form && !isEditHandlerAttached) {
        form.addEventListener("submit", handleEditFormSubmit);
        isEditHandlerAttached = true;
    }
}

export async function handleEditFormSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const giangVien = {
        giangVienId: formData.get("giangVienId"),
        userId: formData.get("userId"),
        hoTen: formData.get("hoTen"),
        loaiGiangVien: formData.get("loaiGiangVien"),
        donVi: formData.get("donVi"),
        email: formData.get("email"),
        soDienThoai: formData.get("soDienThoai")
    };

    try {
        const res = await updateGV(giangVien);
        if (res.status === 200) {
            alert("Cập nhật thành công!");
            document.getElementById("editGVModal").style.display = "none";
            loadGV();
        } else {
            alert("Cập nhật thất bại! Mã lỗi: " + res.status);
        }
    } catch (err) {
        alert("Có lỗi xảy ra trong quá trình cập nhật!");
        console.error(err);
    }
}


export function deleteGV(button) {
    const row = button.closest("tr");
    const giangVienId = row.getAttribute("data-id") || row.cells[0].innerText.trim();

    if (confirm("Bạn có chắc chắn muốn xóa người dùng này?")) {
        deleteGVById(giangVienId)
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

export async function showThongKeGV() {
    try {
        const canvas = document.getElementById("giangVienChart");
        if (!canvas) {
            console.error("Không tìm thấy canvas giangVienChart");
            alert("Không thể hiển thị biểu đồ: thiếu canvas.");
            return;
        }
        const ctx = canvas.getContext("2d");

        const res = await getThongKeGV();

        if (res.status !== 200 || !res.data) {
            alert("Không thể lấy dữ liệu thống kê!");
            return;
        }

        const { labels, counts } = res.data;

        if (!labels || !counts || labels.length === 0 || counts.length === 0) {
            alert("Không có dữ liệu thống kê!");
            return;
        }

        if (window.myChart instanceof Chart) {
            window.myChart.destroy();
        }

        window.myChart = new Chart(ctx, {
            type: "bar",
            data: {
                labels,
                datasets: [{
                    label: "Số lượng giảng viên",
                    data: counts,
                    backgroundColor: "rgba(75, 192, 192, 0.5)",
                    borderColor: "rgba(75, 192, 192, 1)",
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1
                        }
                    }
                }
            }
        });

        document.getElementById("statisticModal").style.display = "flex";
    } catch (error) {
        console.error("Lỗi khi hiển thị biểu đồ:", error);
        alert("Không thể hiển thị biểu đồ thống kê!");
    }
}
export function loadScript(src, callback) {
  const script = document.createElement("script");
  script.src = src;
  script.onload = callback;
  document.head.appendChild(script);
}
export function handleImportExcel() {
    const input = document.getElementById("excelFileInput");
    if (input) {
        input.click();

        input.onchange = (event) => {
            const file = event.target.files[0];
            if (!file) return;

            const reader = new FileReader();
            reader.onload = (e) => {
                const data = new Uint8Array(e.target.result);
                const workbook = XLSX.read(data, { type: "array" });

                const firstSheetName = workbook.SheetNames[0];
                const worksheet = workbook.Sheets[firstSheetName];

                const jsonData = XLSX.utils.sheet_to_json(worksheet, { header: 1 });
                console.log("Dữ liệu Excel:", jsonData);

                alert("Đã đọc xong file Excel! Xem console log.");
            };
            reader.readAsArrayBuffer(file);
        };
    } else {
        alert("Không tìm thấy input file!");
    }
}
export function handleExportExcel() {
    const table = document.getElementById("giangVienTable");
    if (!table) {
        alert("Không tìm thấy bảng dữ liệu!");
        return;
    }

    const workbook = XLSX.utils.table_to_book(table, { sheet: "GiangVien" });

    // Ghi file Excel
    XLSX.writeFile(workbook, "giangvien.xlsx");
}



