# 🛍️ Sistema de Gestão de Vendas - Loja de Roupas

Este projeto é uma aplicação web desenvolvida com Spring Boot, com autenticação via JWT, controle de usuários, produtos e vendas, seguindo boas práticas de arquitetura e segurança.

## 🚀 Funcionalidades

* Registro e login de usuários (JWT)
* Controle de permissões por papel (`CLIENTE` ou `FUNCIONARIO`)
* Cadastro, edição, listagem e exclusão de produtos
* Realização de vendas com vinculação de produtos e clientes
* Listagem de vendas e recibos por usuário
* Segurança com Spring Security e tokens JWT

---

## 🛠️ Tecnologias Utilizadas

* Java 17+
* Spring Boot 3.x

  * Spring Web
  * Spring Security
  * Spring Data JPA
  * Validation
* PostgreSQL
* Flyway (para versionamento do banco de dados)
* Lombok
* JWT (Auth0)

---

## 🧪 Requisitos

* Java 17+
* PostgreSQL
* Maven

---

## ⚙️ Configuração do Banco de Dados

Configure o arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/loja_roupas
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

api.security.token.secret=SUA_CHAVE_SECRETA
```

> ⚠️ **Importante**: O campo `api.security.token.secret` é usado para assinar e validar tokens JWT. Nunca versionar essa chave.

---

## 📃 Migrations (Flyway)

As tabelas são criadas automaticamente via Flyway, a partir dos arquivos SQL em `src/main/resources/db/migration`.

* `usuario`: dados de usuários
* `produto`: estoque da loja
* `venda`: histórico de compras

---

## 🔐 Rotas e Regras de Acesso

| Rota                    | Método | Acesso              | Descrição                       |
| ----------------------- | ------ | ------------------- | ------------------------------- |
| `/auth/register`        | POST   | Público             | Registro de usuário             |
| `/auth/login`           | POST   | Público             | Autenticação com e-mail e senha |
| `/produto/criar`        | POST   | FUNCIONARIO         | Cadastro de novo produto        |
| `/produto/{id}`         | PUT    | FUNCIONARIO         | Atualização de produto          |
| `/produto/all`          | GET    | FUNCIONARIO         | Lista todos os produtos         |
| `/produto/disponiveis`  | GET    | FUNCIONARIO/CLIENTE | Produtos disponíveis para venda |
| `/venda/criar`          | POST   | FUNCIONARIO/CLIENTE | Realiza uma nova venda          |
| `/venda/all`            | GET    | FUNCIONARIO/CLIENTE | Lista todas as vendas           |
| `/usuario/recibos/{id}` | GET    | FUNCIONARIO         | Lista recibos do usuário        |

---

## 🧪 Exemplos de Requisições

### Login

```http
POST /auth/login
Content-Type: application/json

{
  "email": "cliente@email.com",
  "senha": "123456"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

Use o token nos headers seguintes:

```http
Authorization: SEU_TOKEN
```

---

## 📁 Estrutura de Pacotes

```
sistema.gestao.vendas.loja
├── controllers
├── models
│   ├── produto
│   ├── usuario
│   └── venda
├── repositories
├── services
│   └── impl
├── infra
│   └── security
└── db
    └── migration
```

---

## 🧑‍💻 Desenvolvido por

📧 [Milena Morais](https://github.com/milenamorais20)

---

## 📄 Licença

Este projeto é open-source e está sob a licença [MIT](LICENSE).
