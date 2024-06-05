const formulario = document.querySelector("form");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".password");

function login() {
    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: Iemail.value,
            senha: Isenha.value
        })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error(errorData.message || 'Erro interno na aplicação');
            });
        }
        return response.json();
    })
    .then(data => {
        console.log('Sucesso:', data);
        alert('Login realizado!');
        localStorage.setItem('token', data.token);
        window.location.href = 'pagina-inicial.html';
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Login falhou: ' + error.message);
    });
}

function limpar() {
    Iemail.value = "";
    Isenha.value = "";
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    login();
    limpar();
});
