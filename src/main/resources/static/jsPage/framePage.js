import { loadCT} from '/js/frame.js';

export function loadKhungCTDT() {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <h1 class="welcome">KHUNG CHƯƠNG TRÌNH ĐÀO TẠO</h1>
        <table class="frameTable">
            <thead>
                <tr>
                    <th rowspan="2">Các khối kiến thức</th>
                    <th colspan="2">Số tín chỉ</th>
                     <th rowspan="2">Tác Vụ</th>
                </tr>
                <tr>
                    <th>Bắt buộc</th>
                    <th>Tự chọn</th>
                    
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
    `;

    loadCT();
}
