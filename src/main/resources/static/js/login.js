function isValidValueForLogin(email, password) {
    let error = document.getElementById("error");
    if (email === "" || password === "") {
        error.style.display = 'inline-block'
        error.innerHTML = "Email and password cannot be empty";
        return false
    } else if (!validateEmail(email)) {
        error.style.display = 'inline-block'
        error.innerHTML = "Invalid email format";
        return false
    }
    return true
}

function validateEmail(email) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function holderError() {
    let error = document.getElementById("error");
    let errorRegister = document.getElementById("errorRegister");
    error.style.display = 'none'
    errorRegister.style.display = 'none'
}

// 监听表单提交事件
const formForLogin = document.getElementById("login-form");
formForLogin.addEventListener('submit', (event) => {
    // 阻止表单默认提交行为
    event.preventDefault();
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    if (!isValidValueForLogin(email, password)) {
        return
    }
    const loginDto = {
        email: email,
        password: password
    }
    fetch("http://localhost:8080/api/user/auth/login", {
        method: "POST",
        headers: {
            'Content-type': "application/json",
        },
        body: JSON.stringify(loginDto)
    })
        .then(r => r.json())
        .then(data => {
            // 将token存储起来
            localStorage.setItem('token', data.token);
            localStorage.setItem('role', data.isAdmin);
            console.log(data)
            // 跳转到图书分页页面
            window.location.href = "../html/bookPage.html";
        })
})
