function startAuth() {

    showAuthOverlay();
    showAuthPopup();
    setAuthType('login')
}

function stopAuth() {

    hideAuthOverlay();
    hideAuthPopup()

    hideAuthTitle('login');
    hideAuthForm('login');
    hideSwitchLink('login');

    hideAuthTitle('register');
    hideAuthForm('register');
    hideSwitchLink('register');
}

function showAuthOverlay() {

    const authOverlay = document.getElementById('auth-overlay');
    const style = authOverlay.style;
    style.visibility = 'visible';
    style.opacity = '80%';
}

function hideAuthOverlay() {

    const authOverlay = document.getElementById('auth-overlay');
    const style = authOverlay.style;
    style.visibility = 'hidden';
    style.opacity = '0%';
}

function showAuthPopup() {

    const authPopup = document.getElementById('auth-popup');
    const style = authPopup.style;
    style.visibility = 'visible';
    style.opacity = '100%';
}

function hideAuthPopup() {

    const authPopup = document.getElementById('auth-popup');
    const style = authPopup.style;
    style.visibility = 'hidden';
    style.opacity = '0%';
}

function setAuthType(type) {

    const authPopup = document.getElementById('auth-popup');
    const style = authPopup.style;

    switch (type) {
        case 'login':
            style.height = '278px';

            hideAuthTitle('register');
            hideAuthForm('register');
            hideSwitchLink('register');

            showAuthTitle('login');
            showAuthForm('login');
            showSwitchLink('login');
            break;
        case 'register':
            style.height = '408px';

            hideAuthTitle('login');
            hideAuthForm('login');
            hideSwitchLink('login');

            showAuthTitle('register');
            showAuthForm('register');
            showSwitchLink('register');
            break;
    }
}

function showAuthTitle(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('login-title');
            break;
        case 'register':
            title = document.getElementById('register-title');
            break;
    }

    const style = title.style;
    style.visibility = 'visible';
}

function hideAuthTitle(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('login-title');
            break;
        case 'register':
            title = document.getElementById('register-title');
            break;
    }

    const style = title.style;
    style.visibility = 'hidden';
}

function showAuthForm(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('login-form');
            break;
        case 'register':
            title = document.getElementById('register-form');
            break;
    }

    const style = title.style;
    style.visibility = 'visible';
}

function hideAuthForm(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('login-form');
            break;
        case 'register':
            title = document.getElementById('register-form');
            break;
    }

    const style = title.style;
    style.visibility = 'hidden';
}

function showSwitchLink(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('register-link');
            break;
        case 'register':
            title = document.getElementById('login-link');
            break;
    }

    const style = title.style;
    style.visibility = 'visible';
}

function hideSwitchLink(type) {

    let title;

    switch (type) {
        case 'login':
            title = document.getElementById('register-link');
            break;
        case 'register':
            title = document.getElementById('login-link');
            break;
    }

    const style = title.style;
    style.visibility = 'hidden';
}