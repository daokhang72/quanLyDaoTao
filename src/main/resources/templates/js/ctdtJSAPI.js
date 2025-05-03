const URL = 'http://localhost:8080'
function getAllCTDT() {
    return fetch(URL+'/thongtin')
        .then(response => response.json())
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
