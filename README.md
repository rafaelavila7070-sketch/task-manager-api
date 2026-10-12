# Task Manager API

API REST para gerenciamento de tarefas, desenvolvida com Java e Spring Boot, com persistência de dados em PostgreSQL.

O projeto tem como objetivo praticar e demonstrar conhecimentos em desenvolvimento backend, construção de APIs REST, operações CRUD, validação de dados, tratamento de exceções e testes automatizados.

## Tecnologias utilizadas

- **Java 21** — linguagem de programação.
- **Spring Boot** — estrutura para desenvolvimento da aplicação.
- **Spring Data JPA** — integração e persistência de dados.
- **PostgreSQL** — banco de dados relacional.
- **Maven** — gerenciamento de dependências e execução dos testes.
- **Swagger UI / OpenAPI** — documentação interativa e testes dos endpoints.
- **JUnit** — execução de testes automatizados.

## Funcionalidades

- Criar novas tarefas.
- Listar as tarefas cadastradas.
- Consultar tarefas por meio da API.
- Atualizar tarefas existentes.
- Excluir tarefas.
- Validar os dados recebidos.
- Tratar erros e tarefas não encontradas.
- Executar testes automatizados.
- Consultar e testar os endpoints pela interface Swagger UI.

## Endpoints da API

A API utiliza o endereço base `http://localhost:8080`.

| Método HTTP | Endpoint      | Descrição                        |
|----------------------------------------------------------------|
| GET         | `/tasks`      | Lista as tarefas cadastradas     |
| POST        | `/tasks`      | Cria uma nova tarefa             |
| PUT         | `/tasks/{id}` | Atualiza uma tarefa pelo ID      |
| DELETE      | `/tasks/{id}` | Exclui uma tarefa pelo ID        |

## Exemplo de requisição

Para criar uma tarefa, envie uma requisição `POST` para `/tasks` com o seguinte JSON:

```json
{
  "title": "Estudar Java",
  "description": "Praticar desenvolvimento backend",
  "completed": false
}
```

## Documentação interativa

Com a aplicação em execução, acesse a interface Swagger UI pelo navegador:

`http://localhost:8080/swagger-ui.html`

A interface permite visualizar os endpoints disponíveis e executar requisições para testar a API.

## Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java JDK 21.
- PostgreSQL.
- Git (opcional, para clonar o repositório).

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone URL_DO_SEU_REPOSITORIO
cd task-manager-api
```

Substitua 
git clone `https://github.com/rafaelavila7070-sketch/task-manager-api.git` pelo endereço real do repositório no GitHub.

### 2. Configurar o banco de dados

Crie um banco de dados PostgreSQL chamado `task_manager`.

Configure as credenciais do banco nas variáveis de ambiente utilizadas pela aplicação.

### 3. Definir a senha do PostgreSQL

No PowerShell, execute:

```powershell
$env:DB_PASSWORD = "SUA_SENHA_DO_POSTGRES"
```

Substitua `SUA_SENHA_DO_POSTGRES` pela senha do seu usuário PostgreSQL.

### 4. Executar a aplicação

Na pasta que contém o arquivo `pom.xml`, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

`http://localhost:8080`

### 5. Executar os testes automatizados

Para executar os testes, defina a variável de ambiente `DB_PASSWORD` e execute:

```powershell
.\mvnw.cmd test
```

## Aprendizados

Este projeto foi desenvolvido para consolidar conhecimentos em Java, Spring Boot, APIs REST, integração com banco de dados relacional, validação de dados, tratamento de exceções e testes automatizados.