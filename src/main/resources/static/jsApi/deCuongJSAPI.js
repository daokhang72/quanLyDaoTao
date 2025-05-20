const API_BASE = 'http://localhost:8081/decuong';

export async function taoDeCuong(data) {
    const response = await fetch(`${API_BASE}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

export async function layToanBoDeCuong() {
    const response = await fetch(`${API_BASE}`);
    return response.json();
}

export async function suaDeCuong(id, data) {
    const response = await fetch(`${API_BASE}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response.json();
}

export async function xoaDeCuong(id) {
    const response = await fetch(`${API_BASE}/${id}`, {
        method: 'DELETE'
    });
    return response.status === 204;
}

export async function layDeCuongTheoIdMon(idMon) {
    const response = await fetch(`${API_BASE}/mon/${idMon}`);
    return response.json();
}