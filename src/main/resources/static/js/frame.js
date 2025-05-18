import { getAllKhungCT,createKhung,deleteKhungById} from '/jsApi/khungctJSAPI.js';

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
                <td></td>
            `;
            tbody.appendChild(parentRow);

            items.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.tenNhom}</td>
                    <td>${item.soTinChiBatBuoc}</td>
                    <td>${item.soTinChiTuChon}</td>
                    <td>
                        <button class="button-frame edit-btn">Sửa</button>
                        <button class="button-frame delete-btn">Xóa</button>
                    </td>
                `;

                row.dataset.khungId = item.khungId;

                tbody.appendChild(row);
            });
        }

        tbody.querySelectorAll('.edit-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const row = e.target.closest('tr');
                const khungId = row.dataset.khungId;
                editKhungCT(khungId);
            });
        });

        tbody.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', (e) => {
                const row = e.target.closest('tr');
                const khungId = row.dataset.khungId;
                deleteKhungCT(khungId);
            });
        });

    }).catch(error => {
        console.error('Lỗi khi tải dữ liệu CT:', error);
    });
}

export function addKhungCT(id) {
    const initialData = {
        ctdtId: id,
    };
    createFormPortal('Thêm Khung Chương Trình', initialData, (formData) => {
        createKhung(formData)
            .then((response) => {
                if(response.status==200){
                    showBottomNotification("Thêm thành công!", true);
                    loadCT();
                }else{
                    showBottomNotification(response.data.data.message, false);
                }
            })
            .catch(() => {
                showBottomNotification("Thêm thất bại!", false);
            });
    });
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
        { name: 'ctdtId', label: 'CTĐT ID', type: 'hidden' },
        { name: 'khoiKienThuc', label: 'Khối kiến thức' },
        { name: 'tenNhom', label: 'Tên nhóm' },
        { name: 'soTinChiBatBuoc', label: 'Số tín chỉ bắt buộc' },
        { name: 'soTinChiTuChon', label: 'Số tín chỉ tự chọn' },
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

            if (initialData?.ctdtId !== undefined) {
                formData.ctdtId = initialData.ctdtId;
            }
        });
        onSubmit(formData);
        document.body.removeChild(overlay);
    };
}


function showBottomNotification(message, isSuccess) {
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

function deleteKhungCT(id) {
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
<p style="margin-bottom: 20px;">Bạn có chắc muốn xóa Khung CT này?</p>
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
        deleteKhungById(id)
            .then(data => {
                loadCT();
            })
            .catch(err => {
                showBottomNotification('Xóa thất bại!', false);
                console.log(1)
            });
        document.body.removeChild(overlay);
    };

    cancelBtn.onclick = () => {
        document.body.removeChild(overlay);
    };

}