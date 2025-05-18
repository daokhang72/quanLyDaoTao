import {getHocPhanByCtdt} from '/jsApi/hocPhanJSAPI.js';

export function loadHP(id) {
    getHocPhanByCtdt(id).then(data => {
        const table = document.querySelector('.study');
        const tbody = table.querySelector('tbody');
        tbody.innerHTML = '';

        data.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.tenHocPhan || ''}</td>
                <td>${item.soTinChi || 0}</td>
                <td>${item.lyThuyet || 0}</td>
                <td>${item.thucHanh || 0}</td>
                <td>${item.thucTap || 0}</td>
                <td>${item.heSo || 1}</td>
            `;

            // Nếu cần dữ liệu khi sửa/xoá
            row.dataset.id = item.id;

            tbody.appendChild(row);
        });
    }).catch(error => {
        console.error('Lỗi khi tải dữ liệu Học Phần:', error);
    });
}
