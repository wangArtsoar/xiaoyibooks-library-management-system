let books = [];
let booksPerPage = 21;
let currentPage = 0;
let totalPages = 0;

function renderPage() {
    const bookList = document.getElementById("book-list");
    bookList.innerHTML = "";

    if (books.length === 0) {
        // 如果没有任何图书，则显示图片
        document.getElementById("no-books").style.display = "block";
        document.getElementById("page-controls").style.display = "none"
        document.getElementById("page-info").innerHTML = "";
        return;
    } else {
        document.getElementById("no-books").style.display = "none";
    }

    let startIndex = currentPage * booksPerPage;
    let endIndex = Math.min(startIndex + booksPerPage, books.length);

    let bookElement = document.createElement("div");
    bookElement.style.color = 'white'
    bookElement.style.display = 'flex'
    for (let i = startIndex; i < endIndex; i++) {
        const book = books[i];
        const formatter = new Intl.DateTimeFormat ("zh-CN", {year: "numeric", month: "2-digit", day: "2-digit"});

        // 用格式化器的format()方法来将Date对象转换为字符串
        const createAt = formatter.format (new Date(book.createAt));
        const publishDate = formatter.format (new Date(book.publishDate));

        bookElement.innerHTML +=
            ` <div style="margin: 20px;display: flex">
                <img id="details" style="width: 20%" src="../files/${book.filePath}" alt="${book.name}">
                <div>
                    <p style="font-style: oblique;">《 ${book.name} 》</p>
                    <a>作者 : ${book.author}</a> &nbsp;&nbsp;
                    <a>分类 : ${book.assortName}</a> <br>
                    位置 : ${book.address} <br>
                    发布于 : ${publishDate} <br>
                </div>
              </div>  
            `;
        bookList.appendChild(bookElement);
    }

    document.getElementById("page-info").innerHTML = "第 " + (currentPage + 1) + " 页 / 共 " + totalPages + " 页";
}

document.getElementById("prev-page").onclick = function () {
    if (currentPage > 0) {
        currentPage--;
        fetchBooks();
    }
};

document.getElementById("next-page").onclick = function () {
    if (currentPage < totalPages - 1) {
        currentPage++;
        fetchBooks();
    }
};

document.getElementById("go-to-page-btn").onclick = function () {
    currentPage = parseInt(document.getElementById("go-to-page").value) - 1;
    fetchBooks();
};

document.getElementById("admin-btn").onclick = function () {
    // 跳转到管理页面
    window.location.href = "../html/admin.html";
};

document.getElementById("my-btn").onclick = function () {
    // 当用户点击“我的”按钮时执行这里的代码
    // 例如，跳转到用户个人页面
    window.location.href = "../html/my.html";
};

document.getElementById("logout-btn").onclick = function () {
    const token = localStorage.getItem("token");
    fetch('http://localhost:8080/api/user/auth/logout', {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }).then()
    window.location.href = '/index.html'
};

document.getElementById("search-form").onsubmit = function (event) {
    event.preventDefault();

    // 当用户提交搜索表单时执行这里的代码
    // 例如，获取用户输入的搜索关键字和选择的图书分类
    const query = document.getElementById("search-query").value;
    const category = document.getElementById("search-category").value;

    // 根据用户输入的搜索关键字和选择的图书分类执行搜索操作
    // ...
};

function fetchBooks() {
    // 获取 token
    const token = localStorage.getItem("token");
    const isAdmin = localStorage.getItem("role");

    fetch('http://localhost:8080/api/book/bookPage?page=' + currentPage + '&size=' + booksPerPage, {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(data => {
            // data.content 就是后端发送过来的图书数据
            books = data.content;
            totalPages = data.totalPages;

            // 如果用户是管理员，则显示管理按钮
            if (isAdmin) {
                document.getElementById("admin-btn").style.display = "inline-block";
                document.getElementById("my-btn").style.right = '160px'
                document.getElementById("logout-btn").style.right = '80px'
            }

            renderPage();
        });
}

fetchBooks();