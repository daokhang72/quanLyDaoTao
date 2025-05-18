import { getAllCTDT, deleteCTDTByid,createCTDT,timKiemThongTinChung,updateCTDTById } from '/jsApi/ctdtJSAPI.js';
import { loadKhungCTDT } from '/jsPage/framePage.js';

function xemKhungCTDT(id){
    loadKhungCTDT(id);
}

export function loadCTDT() {
    getAllCTDT().then(data => {
        showTable(data)
    }).catch(error => {
        console.error('Lỗi khi tải dữ liệu:', error);
    });
}
export function addCTDT() {
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
function showTable(data){
    const table = document.querySelector('.ctdt-table');
    let tbody = table.querySelector('tbody');

    tbody.innerHTML = '';

    data.forEach(item => {
        const row = document.createElement('tr');
        row.dataset.tenCtdt = item.tenCtdt;
        row.dataset.bac = item.bac;
        row.dataset.loaiBang = item.loaiBang;
        row.dataset.loaiHinhDaoTao = item.loaiHinhDaoTao;
        row.dataset.thoiGian = item.thoiGian;
        row.dataset.soTinChi = item.soTinChi;
        row.dataset.khoaQuanLy = item.khoaQuanLy;
        row.dataset.ngonNgu = item.ngonNgu;
        row.dataset.website = item.website;
        row.dataset.banHanh = item.banHanh;

        row.innerHTML = `
        <td>${item.tenCtdt}</td>
        <td>${item.bac}</td>
        <td>${item.loaiBang}</td>
        <td>${item.loaiHinhDaoTao}</td>
        <td>${item.thoiGian}</td>
        <td>${item.soTinChi}</td>
        <td>${item.khoaQuanLy}</td>
        <td>${item.ngonNgu}</td>
        <td><a href="${item.website}" target="_blank">${item.website}</a></td>
        <td>${item.banHanh}</td>
        <td style="white-space: nowrap;"></td>
    `;

        const tdActions = row.querySelector('td:last-child');

        const deleteBtn = document.createElement('img');
        deleteBtn.src = '/image/delete.svg';
        deleteBtn.alt = 'Xóa';
        deleteBtn.style.cursor = 'pointer';
        deleteBtn.addEventListener('click', () => deleteCTDT(item.ctdtId));

        const editBtn = document.createElement('img');
        editBtn.src = '/image/edit.svg';
        editBtn.alt = 'Chỉnh sửa';
        editBtn.style.cursor = 'pointer';
        editBtn.addEventListener('click', () => editCTDT(item.ctdtId, editBtn));

        const viewBtn = document.createElement('img');
        viewBtn.src = '/image/view.svg';
        viewBtn.alt = 'Xem Khung';
        viewBtn.style.cursor = 'pointer';
        viewBtn.addEventListener('click', () => xemKhungCTDT(item.ctdtId, viewBtn));

        tdActions.appendChild(deleteBtn);
        tdActions.appendChild(editBtn);
        tdActions.appendChild(viewBtn);

        tbody.appendChild(row);
    });
}



function deleteCTDT(id) {
    const overlay = document.createElement('div');
    overlay.style.position = 'fixed';
    overlay.style.top = '0';
    overlay.style.left = '0';
    overlay.style.width = '100%';
    overlay.style.height = '100%';
    overlay.style.background = 'rgba(0, 0, 0, 0.5)';
    overlay.style.display = 'flex';
    overlay.style.justifyContent = 'center';
    overlay.style.alignItems = 'center';
    overlay.style.zIndex = '9999';

    const modal = document.createElement('div');
    modal.style.background = 'white';
    modal.style.padding = '20px';
    modal.style.borderRadius = '8px';
    modal.style.textAlign = 'center';
    modal.innerHTML = `
      <p style="margin-bottom: 20px;">Bạn có chắc muốn xóa CTĐT này?</p>
      <button id="confirm-btn">Xóa</button>
      <button id="cancel-btn">Hủy</button>
    `;

    overlay.appendChild(modal);
    document.body.appendChild(overlay);

    const confirmBtn = modal.querySelector('#confirm-btn');
    const cancelBtn = modal.querySelector('#cancel-btn');

    [confirmBtn, cancelBtn].forEach(btn => {
        btn.style.padding = '10px 20px';
        btn.style.margin = '0 10px';
        btn.style.border = 'none';
        btn.style.borderRadius = '4px';
        btn.style.cursor = 'pointer';
        btn.style.fontSize = '14px';
        btn.style.transition = 'background-color 0.3s ease';
    });

    confirmBtn.style.backgroundColor = '#f44336';
    confirmBtn.style.color = 'white';

    cancelBtn.style.backgroundColor = '#e0e0e0';
    cancelBtn.style.color = '#333';

    confirmBtn.onmouseenter = () => confirmBtn.style.backgroundColor = '#d32f2f';
    confirmBtn.onmouseleave = () => confirmBtn.style.backgroundColor = '#f44336';

    cancelBtn.onmouseenter = () => cancelBtn.style.backgroundColor = '#ccc';
    cancelBtn.onmouseleave = () => cancelBtn.style.backgroundColor = '#e0e0e0';

    confirmBtn.onclick = () => {
        deleteCTDTByid(id)
            .then(data => {
                showBottomNotification('Đã xóa thành công!', true);
                loadCTDT();
            })
            .catch(err => {
                showBottomNotification('Xóa thất bại!', false);
            });
        document.body.removeChild(overlay);
    };

    cancelBtn.onclick = () => {
        document.body.removeChild(overlay);
    };
}

function createFormPortal(title, initialData, onSubmit) {
    const overlay = document.createElement('div');
    overlay.style = `
      position: fixed; top: 0; left: 0; width: 100%; height: 100%;
      background: rgba(0,0,0,0.5); display: flex;
      justify-content: center; align-items: center; z-index: 9999;
    `;

    const modal = document.createElement('div');
    modal.style = `
      background: white; padding: 20px; border-radius: 8px; width: 400px;
      max-height: 80vh; overflow-y: auto;
    `;

    const fields = [
        { name: 'tenCtdt', label: 'Tên CTĐT' },
        { name: 'bac', label: 'Bậc' },
        { name: 'loaiBang', label: 'Loại bằng' },
        { name: 'loaiHinhDaoTao', label: 'Loại hình đào tạo' },
        { name: 'thoiGian', label: 'Thời gian', type: 'number'},
        { name: 'soTinChi', label: 'Số tín chỉ', type: 'number'},
        { name: 'khoaQuanLy', label: 'Khoa quản lý' },
        { name: 'ngonNgu', label: 'Ngôn ngữ' },
        { name: 'website', label: 'Website' },
        { name: 'banHanh', label: 'Ban hành' },
    ];

    const form = document.createElement('form');
    fields.forEach(({ name, label, type = 'text' }) => {
        const input = document.createElement('input');
        input.type = type;
        input.step = 'any';
        input.name = name;
        input.required = true;
        input.placeholder = label;
        input.value = initialData?.[name] || '';
        input.style = `
          display: block;
          width: 100%;
          box-sizing: border-box;
          margin-bottom: 10px;
          padding: 8px;
          border: 1px solid #ccc;
          border-radius: 4px;
        `;
        form.appendChild(input);
    });

    const submitBtn = document.createElement('button');
    submitBtn.textContent = 'Lưu';
    submitBtn.type = 'submit';
    submitBtn.style = 'margin-top: 10px; padding: 10px 20px;';

    const cancelBtn = document.createElement('button');
    cancelBtn.textContent = 'Hủy';
    cancelBtn.type = 'button';
    cancelBtn.onclick = () => document.body.removeChild(overlay);
    cancelBtn.style = 'margin-top: 10px; margin-left: 10px; padding: 10px 20px;';

    form.appendChild(submitBtn);
    form.appendChild(cancelBtn);

    const heading = document.createElement('h3');
    heading.textContent = title;
    heading.style = 'margin-bottom: 20px; text-align: center;';

    modal.appendChild(heading);
    modal.appendChild(form);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);

    form.onsubmit = (e) => {
        e.preventDefault();
        const formData = {};
        fields.forEach(({name}) => {
            const val = form.elements[name].value;
            formData[name] = isNaN(val) ? val : parseFloat(val);
        });
        if (initialData?.ctdtId !== undefined) {
            formData.ctdtId = initialData.ctdtId;
        }
        onSubmit(formData);
        document.body.removeChild(overlay);
    };
}


export function showBottomNotification(message, isSuccess) {
    const existingToasts = document.querySelectorAll('.custom-toast');
    const offset = existingToasts.length * 60;

    const notification = document.createElement('div');
    notification.classList.add('custom-toast');
    notification.textContent = message;
    notification.style.position = 'fixed';
    notification.style.bottom = `${20 + offset}px`;
    notification.style.right = '20px';
    notification.style.backgroundColor = isSuccess ? '#4caf50' : '#f44336';
    notification.style.color = 'white';
    notification.style.padding = '12px 24px';
    notification.style.borderRadius = '4px';
    notification.style.boxShadow = '0 2px 6px rgba(0,0,0,0.2)';
    notification.style.zIndex = '10000';
    notification.style.opacity = '0';
    notification.style.transition = 'opacity 0.3s ease';

    document.body.appendChild(notification);

    requestAnimationFrame(() => {
        notification.style.opacity = '1';
    });

    setTimeout(() => {
        notification.style.opacity = '0';
        notification.addEventListener('transitionend', () => {
            notification.remove();
            const remainingToasts = document.querySelectorAll('.custom-toast');
            remainingToasts.forEach((toast, index) => {
                toast.style.bottom = `${20 + index * 60}px`;
            });
        });
    }, 3000);
}

export function handleSearch() {
    const keyword = document.getElementById("searchInput").value.trim();
    if (keyword) {
        timKiemThongTinChung(keyword).then(data => {
            showTable(data);
        }).catch(error => {
            console.error('Lỗi khi tải dữ liệu:', error);
        });
    }
}

function editCTDT(id,button) {
    const row = button.closest('tr');
    const data = {
        ctdtId: id,
        tenCtdt: row.dataset.tenCtdt,
        bac: row.dataset.bac,
        loaiBang: row.dataset.loaiBang,
        loaiHinhDaoTao: row.dataset.loaiHinhDaoTao,
        thoiGian: parseFloat(row.dataset.thoiGian),
        soTinChi: parseInt(row.dataset.soTinChi),
        khoaQuanLy: row.dataset.khoaQuanLy,
        ngonNgu: row.dataset.ngonNgu,
        website: row.dataset.website,
        banHanh: row.dataset.banHanh,
    };

    createFormEditPortal(data);
}

function createFormEditPortal(data) {
    createFormPortal('Chỉnh Sửa Chương Trình Đào Tạo', data, (data) => {
        const { ctdtId, ...body } = data;
        return updateCTDTById(data.ctdtId,body)
            .then((response) => {
                if(response.status==200){
                    showBottomNotification("Sửa thành công!", true);
                    loadCTDT();
                }else{
                    showBottomNotification(response.data.data.message, false);
                }
            })
            .catch(() => {
                showBottomNotification("Sửa thất bại!", false);
            });
    });
}
