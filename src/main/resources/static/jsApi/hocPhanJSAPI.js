const BASE_URL = 'http://localhost:8081/hocphan';

// Lấy toàn bộ học phần
function getAllHocPhan() {
    return fetch(BASE_URL)
        .then(res => res.json())
        .catch(err => {
            console.error('Lỗi:', err);
            throw err;
        });
}

export function getHocPhanByCtdt(id) {
    return fetch(`${BASE_URL}/${id}`)
        .then(res => res.json())
        .catch(err => {
            console.error('Lỗi:', err);
            throw err;
        });
}


// Tạo học phần mới
export function createHocPhan(data) {
    return fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .catch(err => {
            console.error('Lỗi:', err);
            throw err;
        });
}

// Cập nhật học phần theo ID
export function updateHocPhanById(id, data) {
    return fetch(`${BASE_URL}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .catch(err => {
            console.error('Lỗi:', err);
            throw err;
        });
}

// Xoá học phần theo ID
export async function deleteHocPhanById(id) {
    await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE'
    })
    return
}

// Tìm kiếm nâng cao học phần
export function searchHocPhan(keyword) {
    const url = `${BASE_URL}/searchAdvanced?keyword=${encodeURIComponent(keyword)}`;

    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Lỗi khi gọi API");
            }
            return response.json();
        })
        .then(data => {
            return data;
        })
        .catch(error => {
            console.error("Lỗi:", error);
            return [];
        });
}
