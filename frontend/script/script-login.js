const formulario = document.querySelector("form");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".password");

function login(dados) {
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
        console.log('Successo:', data);
        alert('Login realizado!');
        // Redirecionar para outra página ou realizar outras ações
        window.location.href = 'pagina-inicial.html'; // Redireciona para a página inicial
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert('Login falhou: ' + error.message);
    });
}

function limpar(){
    Iemail.value = "";
    Isenha.value  = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    login();
    limpar();
});
