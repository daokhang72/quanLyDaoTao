import { loadCT,addKhungCT} from '/js/frame.js';
import { loadHP,handleSearchStudy,addhStudy} from '/js/study.js';

export  function DetailCTDT(id) {
    const content = document.querySelector(".content");
    if (!content) return;

    content.innerHTML = `
        <div class="tab-container">
            <div class="tab-header"> 
                <button class="tab-btn active" data-tab="ctdt">Khung Chương Trình</button>
                <button class="tab-btn" data-tab="another">Học Phần</button>
            </div>
            <div class="tab-content">
                <div id="ctdt" class="tab-panel active">
                    <h1 class="welcome">KHUNG CHƯƠNG TRÌNH ĐÀO TẠO</h1>
                     <div class="top-bar">
             
                        <div class="add-button-container">
                            <button class="add-button">Thêm Khung</button>
                        </div>
                    </div>
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
                        <tbody></tbody>
                    </table>
                </div>
                <div id="another" class="tab-panel">
                    <h1 class="welcome">HỌC PHẦN</h1>
                    <div class="top-bar">
                        <div class="search-container">
                            <input type="text" id="searchInput" class="search-input"
                                placeholder="Nhập tên, bậc, khoa quản lý, ..." />
                            <button class="search-buttonHP">🔍</button>
                        </div>
                        <div class="add-button-container">
                            <button class="add-buttonHP">➕ Thêm Học Phần</button>
                        </div>
                    </div>
                    <table class="study">
                        <thead>
                            <tr>
                                <th>Tên Học Phần</th>
                                <th>Số Tín Chỉ</th>
                                <th>Lý Thuyết</th>
                                <th>Thực Hành</th>
                                <th>Thực Tập</th>
                                <th>Cộng</th>
                                <th>Hệ số học phần</th>
                                <th>Tác Vụ</th>
                            </tr>
                            <tr>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    `;
    window.onload = loadCT(id);
    window.onload = loadHP(id);

    const tabButtons = content.querySelectorAll(".tab-btn");
    const tabPanels = content.querySelectorAll(".tab-panel");
    const addButton = content.querySelector('.add-button');

    tabButtons.forEach(button => {
        button.addEventListener("click", () => {
            const target = button.dataset.tab;

            tabButtons.forEach(btn => btn.classList.remove("active"));
            button.classList.add("active");

            tabPanels.forEach(panel => {
                panel.classList.toggle("active", panel.id === target);
            });
        });
    });

    if (addButton) {
        addButton.addEventListener('click', function () {
            addKhungCT(id);
        });
    }

    const searchButtonHP = content.querySelector('.search-buttonHP');
    const addButtonHP = content.querySelector('.add-buttonHP');

    if (searchButtonHP) {
        searchButtonHP.addEventListener('click', () => handleSearchStudy(id));
    }

    if (addButtonHP) {
        addButtonHP.addEventListener('click', () => addhStudy(id) );
    }
}
