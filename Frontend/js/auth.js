// auth-check.js

function checkAuth() {
    const authToken = localStorage.getItem('authToken');
    const loginedUser = JSON.parse(localStorage.getItem('loginedUser'));

    if (!authToken || !loginedUser) {
        window.location.href = '../index.html';
        return;
    }

    const currentTime = new Date().getTime();
    const loginTime = loginedUser.loginTime;
    const hoursSinceLogin = (currentTime - loginTime) / (1000 * 60 * 60);

    if (hoursSinceLogin > 1) {
        localStorage.removeItem('authToken');
        localStorage.removeItem('loginedUser');
        window.location.href = '../index.html';
        return;
    }

    const currentPage = window.location.pathname.split('/').pop();
    if (loginedUser.role === 'ADMIN' && currentPage !== 'adminDash.html') {
        window.location.href = './adminDash.html';
    } else if (loginedUser.role !== 'ADMIN' && currentPage !== 'dash1.html') {
        window.location.href = './dash1.html';
    }
}

checkAuth();