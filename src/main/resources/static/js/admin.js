// 获取模态框
const addModal = document.getElementById("addModal");
const upgradeModal = document.getElementById("upgradeModal");

// 获取按钮并绑定点击事件
document.getElementById("addBtn").onclick = function () {
    addModal.style.display = "block";
}
document.getElementById("upgradeBtn").onclick = function () {
    upgradeModal.style.display = "block";
}
document.getElementById("indexBtn").onclick = function () {
    // 加载一个新的文档：
    window.location.assign("bookPage.html"); // 跳转到你的首页
}

// 点击模态框外部时关闭模态框
window.onclick = function (event) {
    if (event.target === addModal || event.target === upgradeModal) {
        addModal.style.display = "none";
        upgradeModal.style.display = "none";
    }
}

function addBook() {
    // 获取表单元素
    let form = document.getElementById("add-book-div");
    // 创建新的图书信息元素
    let newBook = document.createElement("div");
    newBook.className = "book";
    newBook.innerHTML = `
                <br>
                <label>
                    <input class="book-name" placeholder="图书名称" type="text">
                </label>

                <label>
                    <input class="book-author" placeholder="作者" type="text">
                </label>

                <label>
                    <input class="book-assortName" placeholder="分类名称" type="text">
                </label>

                <label>
                    <input class="book-publishDate" placeholder="发布日期" type="date">
                </label>

                <label>
                    <input class="book-file" placeholder="上传图书文件" type="file">
                </label>
        `;
    // 将新的图书信息元素添加到表单中
    form.insertBefore(newBook, form.childNodes[form.childNodes.length - 1]);
}

function removeBook() {
    // 获取所有的图书信息元素
    let books = document.querySelectorAll('.book');
    // 如果有多于1个图书信息元素，则删除最后一个
    if (books.length > 1) {
        books[books.length - 1].remove();
    }
}