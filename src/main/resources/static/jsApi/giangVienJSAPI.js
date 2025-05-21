const URL = 'http://localhost:8081'
export function createGV(formData) {
    return fetch("/admin/giangvien/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(async response => {
        const contentType = response.headers.get("content-type");

        if (response.ok && contentType && contentType.includes("application/json")) {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        } else {
            return {
                status: response.status,
                data: null
            };
        }
    })
    .catch(error => {
        console.error('Lỗi gửi dữ liệu:', error);
        throw error;
    });
}

export function fetchGVList() {
    return fetch("/admin/giangvien/list")
        .then(async response => {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        })
        .catch(error => {
            console.error("Lỗi khi tải danh sách giảng viên:", error);
            throw error;
        });
}
export function updateGV(giangvien) {
    return fetch('/admin/giangvien/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(giangvien)
    })
    .then(async response => {
        const data = await response.json().catch(() => ({}));
        return {
            status: response.status,
            data: data
        };
    })
    .catch(error => {
        console.error("Lỗi khi cập nhật giảng viên:", error);
        throw error;
    });
}
export function deleteGVById(giangVienId) {
    return fetch(`/admin/giangvien/delete/${giangVienId}`, {
        method: 'DELETE'
    })
    .then(async response => {
        const data = await response.json().catch(() => ({}));
        return {
            status: response.status,
            data: data
        };
    })
    .catch(error => {
        console.error('Lỗi khi xóa giảng viên:', error);
        throw error;
    });
}
export function getThongKeGV() {
    return fetch("/admin/giangvien/thongke")
        .then(async response => {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        })
        .catch(error => {
            console.error("Lỗi khi lấy dữ liệu thống kê:", error);
            throw error;
        });
}
