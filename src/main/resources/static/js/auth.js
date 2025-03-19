const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const showLogin = document.getElementById('showLogin');
const showRegister = document.getElementById('showRegister');

// Login

showLogin.addEventListener('click', () => {
    loginForm.classList.remove('hidden');
    registerForm.classList.add('hidden');
    showLogin.classList.add('active');
    showRegister.classList.remove('active');
});

loginForm.addEventListener("submit", function(event) {
    event.preventDefault();
    const data = {
        username: document.getElementById("loginUsername").value,
        password: document.getElementById("loginPassword").value
    };
    console.log("data: " + data)
    authenticateUser(data);
    loginForm.reset();
});

function authenticateUser(data) {
    const promise = apiClient.authUser(data);
    localStorage.setItem("username", data.username );
    promise.then(response => {
        return response.json()
    })
    .then(data => {
        const { token } = data;
        localStorage.setItem("token", token );
        
        alert("Inicio de sesión correcto.");
        window.location.href = "twitter.html";
    })
    .catch(error => console.error("Error:", error));
}

// Register

showRegister.addEventListener('click', () => {
    loginForm.classList.add('hidden');
    registerForm.classList.remove('hidden');
    showRegister.classList.add('active');
    showLogin.classList.remove('active');
});

function registerUser(data) {
    const promise = apiClient.createUser(data);
    promise.then(response => response.text())
    .then(() => {
        alert("Usuario registrado exitosamente.");
    })
    .catch(error => console.error("Error:", error));
}

registerForm.addEventListener("submit", function(event) {
    event.preventDefault();
    const data = {
        username: document.getElementById("regUsername").value,
        password: document.getElementById("regPassword").value
    };
    console.log("data: " + data)
    registerUser(data)
    registerForm.reset();
});