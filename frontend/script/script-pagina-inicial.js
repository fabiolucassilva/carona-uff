document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = 'login.html';
    } else {
        fetch('http://localhost:8080/login/usuario', {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao obter informações do usuário');
            }
            return response.json();
        })
        .then(data => {
            console.log('Usuário logado:', data);
            document.getElementById('user-header').innerText = `Bem-vindo, ${data.nome}`;
            document.getElementById('user-nome').innerText = `Nome Completo: ${data.nome} ${data.sobrenome}`;
            document.getElementById('user-cpf').innerText = `CPF: ${data.cpf}`;
            document.getElementById('user-email').innerText = `E-mail: ${data.email}`;
            document.getElementById('user-reputacao').innerText = `Reputação: ${data.reputacao}`;
        })
        .catch(error => {
            console.error('Erro:', error);
            window.location.href = 'login.html';
        });
    }

    document.getElementById('solicitar-carona-form').addEventListener('submit', function(event) {
        event.preventDefault();
        alert('Solicitação de carona enviada!');
    });

    document.getElementById('oferecer-carona-form').addEventListener('submit', function(event) {
        event.preventDefault();
        alert('Oferta de carona enviada!');
    });
});

function editarInformacoes() {
    alert("Função de edição de informações não implementada ainda.");
}

function editarVeiculos() {
    alert("Função de edição de veículos não implementada ainda.");
}

function pedirCarona() {
    alert("Função de pedir carona não implementada ainda.");
}

function oferecerCarona() {
    alert("Função de oferecer carona não implementada ainda.");
}

function logout() {
    localStorage.removeItem('token');
    window.location.href = 'login.html';
    alert('Logout realizado!');
}
