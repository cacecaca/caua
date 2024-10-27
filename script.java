// Carregar status de login ao iniciar a página
window.onload = function() {
  verificarUsuarioLogado();
}

// Função para criar uma conta de usuário
document.getElementById('form-criar-conta').addEventListener('submit', function(event) {
  event.preventDefault();
  
  const nome = document.getElementById('nome-novo-usuario').value;
  const email = document.getElementById('email-novo-usuario').value;
  const senha = document.getElementById('senha-novo-usuario').value;

  const usuario = { nome, email, senha };

  // Verificar se existe lista de usuários no LocalStorage
  let usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];

  // Verificar se o email já está cadastrado
  const usuarioExistente = usuarios.find(u => u.email === email);
  if (usuarioExistente) {
    alert('Email já cadastrado!');
    return;
  }

  // Adicionar novo usuário à lista
  usuarios.push(usuario);

  // Atualizar LocalStorage
  localStorage.setItem('usuarios', JSON.stringify(usuarios));

  // Limpar formulário
  document.getElementById('form-criar-conta').reset();

  alert('Conta criada com sucesso!');
});

// Função de login de usuário
document.getElementById('form-login').addEventListener('submit', function(event) {
  event.preventDefault();
  
  const email = document.getElementById('email-login').value;
  const senha = document.getElementById('senha-login').value;

  // Obter lista de usuários do LocalStorage
  const usuarios = JSON.parse(localStorage.getItem('usuarios')) || [];

  // Verificar se o usuário existe e se a senha está correta
  const usuario = usuarios.find(u => u.email === email && u.senha === senha);
  if (!usuario) {
    alert('Email ou senha incorretos!');
    return;
  }

  // Salvar usuário logado no LocalStorage
  localStorage.setItem('usuarioLogado', JSON.stringify(usuario));

  // Atualizar interface
  verificarUsuarioLogado();
});

// Função para verificar se há um usuário logado
function verificarUsuarioLogado() {
  const usuarioLogado = JSON.parse(localStorage.getItem('usuarioLogado'));

  if (usuarioLogado) {
    document.getElementById('bem-vindo').style.display = 'block';
    document.getElementById('usuario-nome').textContent = usuarioLogado.nome;
    document.getElementById('cadastro-usuario').style.display = 'none';
    document.getElementById('login-usuario').style.display = 'none';
  } else {
    document.getElementById('bem-vindo').style.display = 'none';
    document.getElementById('cadastro-usuario').style.display = 'block';
    document.getElementById('login-usuario').style.display = 'block';
  }
}

// Função para logout
function logout() {
  // Remover usuário logado do LocalStorage
  localStorage.removeItem('usuarioLogado');

  // Atualizar interface
  verificarUsuarioLogado();
}

// Função para carregar e exibir o catálogo de produtos
window.onload = function() {
  carregarCatalogoDeProdutos();
};

// Função para carregar e exibir produtos cadastrados como catálogo
function carregarCatalogoDeProdutos() {
  const produtosContainer = document.getElementById('produtos-container');
  produtosContainer.innerHTML = '';

  // Obter lista de produtos do LocalStorage
  const produtos = JSON.parse(localStorage.getItem('produtos')) || [];

  // Verificar se há produtos cadastrados
  if (produtos.length === 0) {
    produtosContainer.innerHTML = '<p>Nenhum produto disponível no momento.</p>';
    return;
  }

  // Exibir cada produto como um card
  produtos.forEach((produto, index) => {
    const card = document.createElement('div');
    card.className = 'produto-card';

    const nomeProduto = document.createElement('h3');
    nomeProduto.textContent = produto.nome;

    const precoProduto = document.createElement('p');
    precoProduto.textContent = `Preço: R$ ${produto.preco.toFixed(2)}`;

    // Adiciona um botão de "comprar" (simplesmente para demonstrar)
    const botaoComprar = document.createElement('button');
    botaoComprar.textContent = 'Comprar';
    botaoComprar.addEventListener('click', () => {
      alert(`Produto "${produto.nome}" adicionado ao carrinho!`);
    });

    // Monta o card com nome, preço e botão de comprar
    card.appendChild(nomeProduto);
    card.appendChild(precoProduto);
    card.appendChild(botaoComprar);

    produtosContainer.appendChild(card);
  });
}

// Função para carregar e exibir o catálogo de produtos
window.onload = function() {
  carregarCatalogoDeProdutos();
};

// Função para carregar e exibir produtos cadastrados como catálogo
function carregarCatalogoDeProdutos() {
  const produtosContainer = document.getElementById('produtos-container');
  produtosContainer.innerHTML = '';

  // Obter lista de produtos do LocalStorage
  const produtos = JSON.parse(localStorage.getItem('produtos')) || [];

  // Verificar se há produtos cadastrados
  if (produtos.length === 0) {
    produtosContainer.innerHTML = '<p>Nenhum produto disponível no momento.</p>';
    return;
  }

  // Exibir cada produto como um card
  produtos.forEach((produto, index) => {
    const card = document.createElement('div');
    card.className = 'produto-card';

    const nomeProduto = document.createElement('h3');
    nomeProduto.textContent = produto.nome;

    const precoProduto = document.createElement('p');
    precoProduto.textContent = `Preço: R$ ${produto.preco.toFixed(2)}`;

    const disponibilidadeProduto = document.createElement('p');
    disponibilidadeProduto.textContent = `Disponibilidade: ${produto.disponivel ? 'Em estoque' : 'Indisponível'}`;

    // Adiciona um botão de "Ver Detalhes"
    const botaoDetalhes = document.createElement('button');
    botaoDetalhes.textContent = 'Ver Detalhes';
    botaoDetalhes.addEventListener('click', () => {
      exibirDetalhesProduto(produto);
    });

    // Monta o card com nome, preço, disponibilidade e botão de detalhes
    card.appendChild(nomeProduto);
    card.appendChild(precoProduto);
    card.appendChild(disponibilidadeProduto);
    card.appendChild(botaoDetalhes);

    produtosContainer.appendChild(card);
  });
}

// Função para exibir os detalhes do produto no modal
function exibirDetalhesProduto(produto) {
  // Preencher os campos do modal com as informações do produto
  document.getElementById('detalhe-nome-produto').textContent = produto.nome;
  document.getElementById('detalhe-descricao-produto').textContent = `Descrição: ${produto.descricao}`;
  document.getElementById('detalhe-preco-produto').textContent = `Preço: R$ ${produto.preco.toFixed(2)}`;
  document.getElementById('detalhe-disponibilidade-produto').textContent = `Disponibilidade: ${produto.disponivel ? 'Em estoque' : 'Indisponível'}`;

  // Exibir o modal
  const modal = document.getElementById('modal-detalhes-produto');
  modal.style.display = 'block';

  // Fechar o modal quando o usuário clicar no botão de fechar
  const spanClose = document.getElementsByClassName('close')[0];
  spanClose.onclick = function() {
    modal.style.display = 'none';
  };

  // Fechar o modal quando o usuário clicar fora da área do modal
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  };
}

// Estrutura de um produto no LocalStorage
const produtos = [
  {
    nome: 'Notebook Gamer',
    descricao: 'Notebook de alta performance para jogos e trabalho.',
    preco: 5500.00,
    disponivel: true
  },
  {
    nome: 'Mouse Sem Fio',
    descricao: 'Mouse ergonômico e confortável para uso diário.',
    preco: 150.00,
    disponivel: false
  },
  {
    nome: 'Teclado Mecânico',
    descricao: 'Teclado mecânico RGB com switches de alta qualidade.',
    preco: 350.00,
    disponivel: true
  }
];

// Armazenar os produtos no LocalStorage
localStorage.setItem('produtos', JSON.stringify(produtos));

