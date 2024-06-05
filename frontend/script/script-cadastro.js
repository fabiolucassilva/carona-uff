const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Isobrenome = document.querySelector(".sobrenome");
const Icpf = document.querySelector(".cpf");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".password");

function cadastrar() {
    fetch('http://localhost:8080/usuario', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
         body: JSON.stringify({
            nome: Inome.value,
            sobrenome: Isobrenome.value,
            cpf: Icpf.value,
            email: Iemail.value,
            senha: Isenha.value
         })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro interno na aplicação');
        }
        // Verifica se a resposta está vazia
        const contentType = response.headers.get('content-type');
        if (!contentType || !contentType.includes('application/json')) {
            return null; // Retorna null para indicar sucesso
        }
        return response.json(); // Faz parsing do JSON se houver conteúdo
    })
    .then(data => {
        if (data === null) {
            console.log('Successo: Cadastro realizado!');
            alert('Cadastro realizado!');
            window.location.href = 'login.html'; // Redireciona para o login
        }
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert('Cadastro falhou: ' + error.message);
    });
}

function limpar(){
    Inome.value = "";
    Isobrenome.value = "";
    Icpf.value = "";
    Iemail.value = "";
    Isenha.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    cadastrar();
    limpar();
});
