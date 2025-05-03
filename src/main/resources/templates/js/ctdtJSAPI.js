const URL = 'http://localhost:8081'
function getAllCTDT() {
    return fetch(`${URL}/thongtin`)
        .then(response => response.json())
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
function deleteCTDTByid(id) {
    return fetch(`${URL}/thongtin/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Xóa thất bại');
            }
            return response.json();
        })
        .catch(error => {
            console.error('Lỗi:', error);
            throw error;
        });
}
