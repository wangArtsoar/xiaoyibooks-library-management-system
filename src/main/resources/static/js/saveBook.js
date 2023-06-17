// 监听表单提交事件
const formForAdd = document.getElementById("add-book-form");
formForAdd.addEventListener('submit', function (event) {
    event.preventDefault();
    let bookName = document.querySelector('#book-name').value;
    let bookAuthor = document.querySelector('#book-author').value;
    let bookAssortName = document.querySelector('#book-assortName').value;
    let bookPublishDate = document.querySelector('#book-publishDate').value;
    let bookFile = document.querySelector('#book-file').files[0];
    // 获取其他表单字段的值...
    let formData = new FormData();
    formData.append('name', bookName);
    formData.append('author', bookAuthor);
    formData.append('assortName', bookAssortName);
    formData.append('publishDate', bookPublishDate);
    formData.append('file', bookFile);
    const token = localStorage.getItem("token");
    fetch('http://localhost:8080/api/book/admin/addBook', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token
        },
        body: formData
    })
        .then(r => r.json())
        .then(data => {
            console.log(data);
        });
});
