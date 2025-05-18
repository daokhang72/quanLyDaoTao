const URL = 'http://localhost:8081'
export function getAllCTDT() {
    return fetch(`${URL}/thongtin`)
        .then(response => response.json())
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
export function deleteCTDTByid(id) {
    return fetch(`${URL}/thongtin/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.status !== 204) {
                throw new Error('Xóa thất bại');
            }
            return { success: true };
        })
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}

export function createCTDT(formData) {
    return fetch(`${URL}/thongtin`, {
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

export function updateCTDTById(id,formData) {
    return fetch(`${URL}/thongtin/${id}`, {
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
export function timKiemThongTinChung(keyWord) {
    return fetch(`${URL}/thongtin/find?keyword=${encodeURIComponent(keyWord)}`)
        .then(response => response.json())
        .catch(error => {
            console.error("Error:", error);
        });
}
