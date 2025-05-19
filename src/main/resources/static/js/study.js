import {getHocPhanByCtdt,searchHocPhan,createHocPhan} from '/jsApi/hocPhanJSAPI.js';
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
// Giả sử bạn đã khai báo hoặc import hàm này ở nơi khác
// async function getKhungByCTDTId(ctdtId) { ... }

export async function addhStudy(ctdtId) {
    /* ------------------- Lấy danh sách khung ------------------- */
    let khungList = [];
    try {
        khungList = await getKhungByCTDTId(ctdtId);          // [{id, ten}, ...]
    } catch (err) {
        console.error('Lỗi lấy khung:', err);
        alert('Không lấy được danh sách khung. Vui lòng thử lại!');
        return;
    }

    /* ------------------- Tạo overlay + modal ------------------- */
    const overlay = document.createElement('div');
    Object.assign(overlay.style, {
        position: 'fixed',
        inset: '0',
        background: 'rgba(0,0,0,.5)',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        zIndex: '1000'
    });

    const modal = document.createElement('div');
    Object.assign(modal.style, {
        background: '#fff',
        padding: '24px',
        borderRadius: '10px',
        minWidth: '420px',
        maxWidth: '90vw'
    });

    /* ------------------- Tạo HTML form ------------------- */
    modal.innerHTML = `
    <h2 style="margin-top:0">Thêm Học Phần</h2>
    <form id="hocphan-form">
      <div style="margin-bottom:12px">
        <label>Khung <span style="color:red">*</span></label><br/>
        <select name="khungId" required style="width:100%;padding:6px"></select>
      </div>

      <div style="margin-bottom:12px">
        <label>Tên học phần <span style="color:red">*</span></label><br/>
        <input name="tenHocPhan" required style="width:100%;padding:6px"/>
      </div>

      <div style="display:grid;grid-template-columns:1fr 1fr;gap:12px;margin-bottom:12px">
        <div>
          <label>Số tín chỉ</label><br/>
          <input name="soTinChi" type="number" min="0" step="1" style="width:100%;padding:6px"/>
        </div>
        <div>
          <label>Hệ số học phần</label><br/>
          <input name="heSoHocPhan" type="number" min="0" step="0.01" style="width:100%;padding:6px"/>
        </div>
      </div>

      <div style="display:grid;grid-template-columns:repeat(3,1fr);gap:12px;margin-bottom:12px">
        <div>
          <label>Số tiết lý thuyết</label><br/>
          <input name="soTietLyThuyet" type="number" min="0" step="1" style="width:100%;padding:6px"/>
        </div>
        <div>
          <label>Số tiết thực hành</label><br/>
          <input name="soTietThucHanh" type="number" min="0" step="1" style="width:100%;padding:6px"/>
        </div>
        <div>
          <label>Số tiết thực tập</label><br/>
          <input name="soTietThucTap" type="number" min="0" step="1" style="width:100%;padding:6px"/>
        </div>
      </div>

      <div style="text-align:right">
        <button type="submit" style="padding:6px 14px">Thêm</button>
        <button type="button" id="hp-cancel" style="padding:6px 14px;margin-left:8px">Hủy</button>
      </div>
    </form>
  `;

    /* ------------------- Gắn option khung vào select ------------------- */
    console.log(khungList)
    const selectEl = modal.querySelector('select[name="khungId"]');
    if (khungList.length === 0) {
        // Không có khung
        selectEl.innerHTML = `<option value="">Không có khung khả dụng</option>`;
        selectEl.disabled = true;
    } else {
        khungList.forEach(k => {
            const opt = document.createElement('option');
            opt.value = k.khungId;
            opt.textContent = k.tenNhom || `Khung ${k.id}`;
            selectEl.appendChild(opt);
        });
    }

    /* ------------------- Đóng modal ------------------- */
    const close = () => document.body.removeChild(overlay);
    modal.querySelector('#hp-cancel').addEventListener('click', close);

    /* ------------------- Submit form ------------------- */
    modal.querySelector('#hocphan-form').addEventListener('submit', e => {
        e.preventDefault();
        if (selectEl.disabled) {
            alert('Không thể thêm vì chưa có khung!');
            return;
        }

        const data = Object.fromEntries(new FormData(e.target).entries());

        ['khungId', 'soTinChi', 'soTietLyThuyet', 'soTietThucHanh', 'soTietThucTap', 'heSoHocPhan']
            .forEach(k => data[k] = data[k] === '' ? null : Number(data[k]));

        console.log('Payload gửi lên server:', data);
        createHocPhan(data);
        alert('Đã thêm học phần thành công!');
        loadHP(ctdtId)
        close();
    });
    overlay.appendChild(modal);
    document.body.appendChild(overlay);
}

