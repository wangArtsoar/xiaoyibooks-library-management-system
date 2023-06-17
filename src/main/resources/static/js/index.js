window.addEventListener('load', () => {
    setTimeout(() => {
        let welcomeMessage = document.getElementById('welcome-message');
        welcomeMessage.classList.add('messageFadeOut');
        setTimeout(() => {
            welcomeMessage.style.display = 'cover';
        }, 3000);
    }, 3000);
});

function showRegister() {
    // 在这里添加显示注册框的代码
    let registerModel = document.getElementById('registerModel');
    registerModel.style.display = 'block'
    for (const elementsByClassNameElement of document.getElementsByClassName('btn')) {
        elementsByClassNameElement.style.display = 'none'
    }
}

function showLogin() {
    // 在这里添加显示登录框的代码
    let loginModal = document.getElementById('loginModal');
    loginModal.style.display = 'block';
    for (const elementsByClassNameElement of document.getElementsByClassName('btn')) {
        elementsByClassNameElement.style.display = 'none'
    }
}

function closeLogin() {
    // 在这里添加关闭登录框的代码
    let loginModal = document.getElementById('loginModal');
    loginModal.style.display = 'none';
    for (const elementsByClassNameElement of document.getElementsByClassName('btn')) {
        elementsByClassNameElement.style.display = 'inline-block'
    }
}

function closeRegister() {
    // 在这里添加关闭登录框的代码
    let registerModel = document.getElementById('registerModel');
    registerModel.style.display = 'none';
    for (const elementsByClassNameElement of document.getElementsByClassName('btn')) {
        elementsByClassNameElement.style.display = 'inline-block'
    }
}
