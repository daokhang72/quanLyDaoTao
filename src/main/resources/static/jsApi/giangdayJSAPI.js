const API_BASE = 'http://localhost:8081/phanconggiangday';

// 1. Tạo mới phân công giảng dạy
export async function taoPhanCong(data) {
    const response = await fetch(API_BASE, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

// 2. Lấy tất cả phân công giảng dạy
export async function layTatCaPhanCong() {
    const response = await fetch(API_BASE);
    return response.json();
}

// 3. Cập nhật phân công giảng dạy theo ID
export async function capNhatPhanCong(id, data) {
    const response = await fetch(`${API_BASE}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

// 4. Xoá phân công giảng dạy theo ID
export async function xoaPhanCong(id) {
    await fetch(`${API_BASE}/${id}`, {
        method: 'DELETE'
    });
}
