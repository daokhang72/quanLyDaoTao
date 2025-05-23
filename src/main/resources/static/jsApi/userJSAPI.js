const URL = 'http://localhost:8081'
export function createUser(formData) {
    return fetch("/admin/user/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(async response => {
        const text = await response.text(); // lấy raw text
        let data = {};
        try {
            data = text ? JSON.parse(text) : {}; // tránh lỗi nếu text rỗng
        } catch (e) {
            console.warn("Phản hồi không phải JSON hợp lệ:", text);
        }

        return {
            status: response.status,
            data: data
        };
    })
    .catch(error => {
        console.error('Lỗi gửi dữ liệu:', error);
        throw error;
    });
}

export function fetchUserList() {
    return fetch("/admin/user/list")
        .then(async response => {
            const data = await response.json();
            return {
                status: response.status,
                data: data
            };
        })
        .catch(error => {
            console.error("Lỗi khi tải danh sách người dùng:", error);
            throw error;
        });
}
export function updateUser(user) {
    return fetch('/admin/user/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(async response => {
        const data = await response.json().catch(() => ({}));
        return {
            status: response.status,
            data: data
        };
    })
    .catch(error => {
        console.error("Lỗi khi cập nhật người dùng:", error);
        throw error;
    });
}
export function deleteUserById(userId) {
    return fetch(`/admin/user/delete/${userId}`, {
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
        console.error('Lỗi khi xóa người dùng:', error);
        throw error;
    });
}


