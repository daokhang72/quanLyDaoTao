export default function createInformation() {
    return `
        <h1>Danh sách Chương trình đào tạo</h1>
        <div class="top-bar">
            <div class="search-container">
                <input type="text" id="searchInput" class="search-input"
                       placeholder="Nhập tên, bậc, khoa quản lý, ..."/>
                <button class="search-button">🔍</button>
            </div>
            <div class="add-button-container">
                <button class="add-button">➕ Thêm CTĐT</button>
            </div>
        </div>
        <table class="ctdt-table">
            <thead>
            <tr>
                <th>Tên CTĐT</th>
                <th>Bậc</th>
                <th>Loại bằng</th>
                <th>Loại hình ĐT</th>
                <th>Thời gian</th>
                <th>Số tín chỉ</th>
                <th>Khoa quản lý</th>
                <th>Ngôn ngữ</th>
                <th>Website</th>
                <th>Ban hành</th>
                <th>Tác vụ</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    `;
}