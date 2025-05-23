import {loadScript,renderUserStats} from '/js/user.js';
export default function createTK() {
  const content = document.querySelector(".content");
      if (!content) return;

  content.innerHTML = `
    <h1>Trang thống kê</h1>
    <canvas id="userPieChart" width="400" height="300"></canvas>
    <div class="stat-buttons">
      <button class="btn-stat" data-type="ctdt">Thống kê CTĐT</button>
      <button class="btn-stat" data-type="outline">Thống kê Đề cương</button>
      <button class="btn-stat" data-type="plan">Thống kê Kế hoạch</button>
      <button class="btn-stat" data-type="assignment">Thống kê Phân công</button>
      <button class="btn-stat" data-type="giangvien">Thống kê Giảng viên</button>
      <button id="userTKbtn" class="btn-stat" data-type="users">Thống kê Người dùng</button>
    </div>

    <div id="stat-content" class="stat-content">
      <p>Chọn một mục để xem thống kê...</p>
    </div>
  `;
  document.getElementById("userTKbtn").addEventListener("click", () => {
           loadScript("https://cdn.jsdelivr.net/npm/chart.js", () => {
                  console.log("Đã load xong thư viện Chart.js.");
                  renderUserStats();
           });
  });
}