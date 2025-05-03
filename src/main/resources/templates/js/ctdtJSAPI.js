const URL = 'http://localhost:8081'
function getAllCTDT() {
    return fetch(URL+'/thongtin')
        .then(response => response.json())
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
