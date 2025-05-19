import {getHocPhanByCtdt,searchHocPhan} from '/jsApi/hocPhanJSAPI.js';
import { getKhungByCTDTId} from '/jsApi/khungctJSAPI.js';

export function loadHP(id) {
    const khungPromise = getKhungByCTDTId(id);
    const hocPhanPromise = getHocPhanByCtdt(id);

    Promise.all([khungPromise, hocPhanPromise])
        .then(([dataKhungId, dataHocPhan]) => {
            const tenNhomMap = {};
            dataKhungId.forEach(khung => {
                tenNhomMap[khung.khungId] = khung.tenNhom;
            });

            const hocPhanTheoNhom = {};
            dataHocPhan.forEach(hp => {
                const tenNhom = tenNhomMap[hp.khungId] || 'Nhóm không rõ';
                if (!hocPhanTheoNhom[tenNhom]) {
                    hocPhanTheoNhom[tenNhom] = [];
                }
                hocPhanTheoNhom[tenNhom].push(hp);
            });

            const table = document.querySelector('.study');
            const tbody = table.querySelector('tbody');
            tbody.innerHTML = '';

            Object.entries(hocPhanTheoNhom).forEach(([tenNhom, hocPhans]) => {
                const groupRow = document.createElement('tr');
                groupRow.innerHTML = `<td colspan="7" style="font-weight:bold;">${tenNhom}</td>`;
                tbody.appendChild(groupRow);

                hocPhans.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.tenHocPhan || ''}</td>
                        <td>${item.soTinChi || 0}</td>
                        <td>${item.soTietLyThuyet || 0}</td>
                        <td>${item.soTietThucHanh || 0}</td>
                        <td>${item.soTietThucTap || 0}</td>
                        <td>${item.soTietLyThuyet + item.soTietThucHanh + item.soTietThucTap || 0}</td>
                        <td>${item.heSoHocPhan || 1}</td>
                        <td>
                            <button class="button-frame edit-btn">Sửa</button>
                            <button class="button-frame delete-btn">Xóa</button>
                        </td>
                    `;
                    row.dataset.id = item.id;
                    tbody.appendChild(row);
                });
            });
        })
        .catch(error => {
            console.error('Lỗi khi tải dữ liệu học phần hoặc khung:', error);
        });
}

export function handleSearchStudy(idCTDT) {
    const keyword = document.getElementById("searchInput").value.trim();
    if (!keyword) return;

    const khungPromise = getKhungByCTDTId(idCTDT);
    const searchPromise = searchHocPhan(keyword);
    console.log('Dữ liệu học phần:', searchPromise);
    Promise.all([khungPromise, searchPromise])
        .then(([dataKhungId, dataHocPhan]) => {

            console.log('Dữ liệu khung:', dataKhungId);
            console.log('Dữ liệu học phần:', dataHocPhan);



            const tenNhomMap = {};
            dataKhungId.forEach(khung => {
                tenNhomMap[khung.khungId] = khung.tenNhom;
            });

            const hocPhanTheoNhom = {};
            dataHocPhan.forEach(hp => {
                const tenNhom = tenNhomMap[hp.khungId];
                if (!tenNhom) return;

                if (!hocPhanTheoNhom[tenNhom]) {
                    hocPhanTheoNhom[tenNhom] = [];
                }
                hocPhanTheoNhom[tenNhom].push(hp);
            });
            const table = document.querySelector('.study');
            const tbody = table.querySelector('tbody');
            tbody.innerHTML = '';

            Object.entries(hocPhanTheoNhom).forEach(([tenNhom, hocPhans]) => {
                const groupRow = document.createElement('tr');
                groupRow.innerHTML = `<td colspan="7" style="font-weight:bold;">${tenNhom}</td>`;
                tbody.appendChild(groupRow);

                hocPhans.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.tenHocPhan || ''}</td>
                        <td>${item.soTinChi || 0}</td>
                        <td>${item.soTietLyThuyet || 0}</td>
                        <td>${item.soTietThucHanh || 0}</td>
                        <td>${item.soTietThucTap || 0}</td>
                        <td>${item.soTietLyThuyet + item.soTietThucHanh + item.soTietThucTap || 0}</td>
                        <td>${item.heSoHocPhan || 1}</td>
                        <td>
                            <button class="button-frame edit-btn">Sửa</button>
                            <button class="button-frame delete-btn">Xóa</button>
                        </td>
                    `;
                    row.dataset.id = item.id;
                    tbody.appendChild(row);
                });
            });
        })
        .catch(error => {
            console.error('Lỗi khi tìm kiếm học phần hoặc tải khung:', error);
        });
}

export function addhStudy() {
    createFormPortal('Thêm Chương Trình Đào Tạo', null, (formData) => {
        createCTDT(formData)
            .then((response) => {
                if(response.status==200){
                    showBottomNotification("Thêm thành công!", true);
                    loadCTDT();
                }else{
                    showBottomNotification(response.data.data.message, false);
                }
            })
            .catch(() => {
                showBottomNotification("Thêm thất bại!", false);
            });
    });
}