const BASE_URL = "http://localhost:8081/kehoach";

export async function createPlanAPI(data) {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error("Lỗi khi tạo kế hoạch");
    }

    return await response.json();
}

export async function getAllPlans() {
    const response = await fetch(BASE_URL);

    if (!response.ok) {
        throw new Error("Lỗi khi lấy danh sách kế hoạch");
    }

    return await response.json();
}

export async function updatePlanAPI(id, data) {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (!response.ok) {
        throw new Error("Lỗi khi cập nhật kế hoạch");
    }

    return await response.json();
}

export async function deletePlanAPI(id) {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Lỗi khi xoá kế hoạch");
    }

    return true;
}
