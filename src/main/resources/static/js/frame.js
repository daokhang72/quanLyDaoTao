import { getAllKhungCT} from '/jsApi/khungctJSAPI.js';

export function loadCT() {
    getAllKhungCT().then(data => {
        const table = document.querySelector('.frameTable');
        const tbody = table.querySelector('tbody');
        tbody.innerHTML = '';

        const groupMap = {};
        data.forEach(item => {
            if (!groupMap[item.khoiKienThuc]) {
                groupMap[item.khoiKienThuc] = [];
            }
            groupMap[item.khoiKienThuc].push(item);
        });

        for (const [khoiKienThuc, items] of Object.entries(groupMap)) {
            const totalBatBuoc = items.reduce((sum, i) => sum + i.soTinChiBatBuoc, 0);
            const totalTuChon = items.reduce((sum, i) => sum + i.soTinChiTuChon, 0);

            const parentRow = document.createElement('tr');
            parentRow.innerHTML = `
                <td><strong>${khoiKienThuc}</strong></td>
                <td><strong>${totalBatBuoc}</strong></td>
                <td><strong>${totalTuChon}</strong></td>
                <td>
                    <button class="button-frame"  onclick="xoatoanbokhung()">Xóa Khối</button>
                </td>
            `;
            tbody.appendChild(parentRow);

            items.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.tenNhom}</td>
                    <td>${item.soTinChiBatBuoc}</td>
                    <td>${item.soTinChiTuChon}</td>
                    <td>
                        <button class="button-frame" onclick="suaKhung(${item.khungId})">Sửa</button>
                        <button class="button-frame"  onclick="suaKhung(${item.khungId})">Xóa</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        }

    }).catch(error => {
        console.error('Lỗi khi tải dữ liệu CT:', error);
    });
}
