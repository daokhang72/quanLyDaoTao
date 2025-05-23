import createCTDTInfo from "./informationPage.js";
import createDeCuongChiTiet from "./outlinePage.js";
import createKeHoachDayHoc from "./planPage.js";
import createGiangVien from "./giangVienPage.js";

export default function createHome() {
    const content = document.querySelector(".content");
    if (!content) return;

    const now = new Date().toLocaleString("vi-VN");

    content.innerHTML = `
        <div class="home-container">
            <h1 class="welcome">🎓 CHÀO MỪNG BẠN ĐẾN VỚI<br>HỆ THỐNG QUẢN LÝ<br>CHƯƠNG TRÌNH ĐÀO TẠO</h1>
            <p class="sub-welcome">Thời gian hiện tại: <strong>${now}</strong></p>

            <div class="stats-container">
                <div class="stat-box">📂 <strong id="statCTDT">--</strong><br>Chương trình</div>
                <div class="stat-box">👨‍🏫 <strong id="statGiangVien">--</strong><br>Giảng viên</div>
                <div class="stat-box">📘 <strong id="statHocPhan">--</strong><br>Học phần</div>
                <div class="stat-box">👥 <strong id="statKeHoach">--</strong><br>Nhóm lớp</div>
            </div>

            <div class="home-actions">
                <p>👉 Chức năng nhanh:</p>
                <div class="action-buttons">
                   <button onclick="navigateTo('ctdt')">📚 Thông tin CTĐT</button>
                   <button onclick="navigateTo('giangvien')">👨‍🏫 Giảng viên</button>
                   <button onclick="navigateTo('decuong')">📝 Đề cương chi tiết</button>
                   <button onclick="navigateTo('kehoach')">📅 Kế hoạch dạy học</button>
                </div>
            </div>

            <canvas id="chartThongKe" width="600" height="300"></canvas>

        </div>
    `;

    const style = document.createElement("style");
    style.innerHTML = `
        .home-container {
            text-align: center;
            padding: 40px;
            max-width: 800px;
            margin: auto;
        }

        .welcome {
            font-size: 2.5em;
            font-weight: bold;
            line-height: 1.4;
            color: #2c3e50;
        }

        .sub-welcome {
            font-size: 1em;
            color: #555;
            margin: 10px 0 30px;
        }

        .stats-container {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-bottom: 30px;
        }

        .stat-box {
            background: #f4f4f4;
            padding: 20px;
            margin: 10px;
            border-radius: 12px;
            width: 150px;
            font-size: 1.1em;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .home-actions {
            margin-top: 30px;
        }

        .action-buttons button {
            margin: 8px;
            padding: 10px 20px;
            font-size: 1em;
            border: none;
            border-radius: 8px;
            background-color: #3498db;
            color: white;
            cursor: pointer;
        }

        .action-buttons button:hover {
            background-color: #2980b9;
        }

        .notification {
            margin-top: 40px;
            background: #fff3cd;
            padding: 15px;
            border: 1px solid #ffeeba;
            border-radius: 8px;
            color: #856404;
            font-size: 1em;
        }
    `;
    document.head.appendChild(style);

       // Gán sự kiện chuyển trang
      window.navigateTo = (module) => {
          switch (module) {
              case "ctdt": createCTDTInfo(); break;
              case "giangvien": createGiangVien(); break;
              case "decuong": createDeCuongChiTiet(); break;
              case "kehoach": createKeHoachDayHoc(); break;
              default: alert("Chức năng chưa phát triển!");
          }
      };

      // Hiển thị thống kê và biểu đồ
      loadStats();
   }

   // Hàm tải số liệu thống kê từ backend và tạo biểu đồ
   async function loadStats() {
       try {
           const res = await fetch("http://localhost:8081/api/thongke");
           const data = await res.json();

           // Hiển thị thống kê
           document.querySelector("#statHocPhan").innerText = data.soHocPhan;
           document.querySelector("#statCTDT").innerText = data.soCTDT;
           document.querySelector("#statGiangVien").innerText = data.soGiangVien;
           document.querySelector("#statKeHoach").innerText = data.soKeHoach;

           // Tạo biểu đồ
           const ctx = document.getElementById("chartThongKe").getContext("2d");

           const chart = new Chart(ctx, {
               type: "bar",
               data: {
                   labels: ["Học phần", "CTĐT", "Giảng viên", "Kế hoạch"],
                   datasets: [{
                       label: "Thống kê số lượng",
                       backgroundColor: ["#3498db", "#2ecc71", "#e74c3c", "#f1c40f"],
                       data: [data.soHocPhan, data.soCTDT, data.soGiangVien, data.soKeHoach]
                   }]
               },
               options: {
                   responsive: true,
                   plugins: {
                       legend: { display: false }
                   }
               }
           });
       } catch (error) {
           console.error("Lỗi khi tải thống kê:", error);
       }
   }
