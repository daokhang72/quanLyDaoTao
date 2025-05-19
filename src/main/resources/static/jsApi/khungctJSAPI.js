const URL = 'http://localhost:8081';

// Lấy tất cả các khung của một CTĐT (theo id CTĐT)
export function getKhungByCTDTId(ctdtId) {
    return fetch(`${URL}/khungchuongtrinh/${ctdtId}`)
        .then(response => response.json())
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
function getAllKhungCT() {
  return fetch(`${URL}/khungchuongtrinh`)
    .then(res => {
      if (!res.ok) throw new Error('Lỗi server');
      return res.json();
    });
}
// Tạo khung mới
export function createKhung(formData) {
    return fetch(`${URL}/khungchuongtrinh`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(async response => {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        })
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}

// Xóa khung theo ID
export function deleteKhungById(id) {
    return fetch(`${URL}/khungchuongtrinh/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (![200, 204].includes(response.status)) {
                throw new Error('Xóa thất bại');
            }
            return { success: true };
        })
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}

// Cập nhật khung theo ID
export function updateKhungById(id, formData) {
    return fetch(`${URL}/khungchuongtrinh/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(async response => {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        })
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}

// Lấy danh sách học phần theo nhóm (truyền groupId hoặc khungId)
function getHocPhanTheoNhom(khungId) {
    return fetch(`${URL}/hocphan?khungId=${khungId}`)
        .then(response => response.json())
        .catch(error => {
            console.error("Lỗi:", error);
            throw error;
        });
}

// Lấy thống kê tín chỉ từng nhóm trong khung
function getThongKeTinChi(khungId) {
    return fetch(`${URL}/khungchuongtrinh/${khungId}/thongke`)
        .then(response => response.json())
        .catch(error => {
            console.error("Lỗi:", error);
            throw error;
        });
}

// Lấy danh sách tất cả CTĐT (nếu cần trong trang khung)
function getAllCTDT() {
    return fetch(`${URL}/thongtin`)
        .then(response => response.json())
        .catch(error => {
            console.error("Lỗi:", error);
            throw error;
        });
}
