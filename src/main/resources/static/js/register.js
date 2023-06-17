function isValidValueForRegister(name, email, password) {
    let errorRegister = document.getElementById("errorRegister");
    if (name === "" || email === "" || password === "") {
        errorRegister.style.display = 'inline-block'
        errorRegister.innerHTML = "name and Email and password cannot be empty";
        return false
    } else if (!validateEmail(email)) {
        errorRegister.style.display = 'inline-block'
        errorRegister.innerHTML = "Invalid email format";
        return false
    }
    return true
}

// 监听表单提交事件
const formForRegister = document.getElementById("register-form");
formForRegister.addEventListener('submit', (event) => {
    // 阻止表单默认提交行为
    event.preventDefault();
    let name = document.getElementById("name").value;
    let email = document.getElementById("userEmail").value;
    let password = document.getElementById("pwd").value;
    if (!isValidValueForRegister(name, email, password)) {
        return
    }
    const registerDto = {
        email: email,
        name: name,
        pwd: password
    }
    fetch("http://localhost:8080/api/user/auth/register", {
        method: "POST",
        headers: {
            'Content-type': "application/json",
        },
        body: JSON.stringify(registerDto)
    })
        .then(r => JSON.stringify(r))
        .then(data => {
            alert(data)
            console.log(data)
        })
})
