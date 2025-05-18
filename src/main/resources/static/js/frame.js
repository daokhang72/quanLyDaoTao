import { getAllKhungCT,createKhung} from '/jsApi/khungctJSAPI.js';

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
<!--                    <button class="button-frame"  onclick="xoatoanbokhung()">Xóa Khối</button>-->
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
