# 📚 RevisaAi Agent
Sistema automatizado de gestão de ciclos de estudo e retenção de conhecimento, utilizando arquitetura stateless e segurança robusta com JWT.

**Live Project:** [RevisaAi-Backend](https://github.com/matheusdelmones/RevisaAi-Backend) 🚀

---

## 📋 Sumário
- [Overview](#-overview)
- [Arquitetura](#-arquitetura)
- [Backend - Design Patterns](#-backend---design-patterns)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Tecnologias](#-tecnologias)
- [Configuração e Execução](#-configuração-e-execução)
- [API Endpoints](#-api-endpoints)

---

## 🎯 Overview
O **RevisaAi Agent** é uma API REST desenvolvida para otimizar o processo de aprendizado através da organização estratégica de tópicos de revisão. O sistema:

1.  **Autentica** usuários de forma segura via JWT.
2.  **Gerencia** tópicos de estudo vinculados a cada perfil de usuário.
3.  **Garante** a persistência e integridade dos dados com PostgreSQL.
4.  **Facilita** a integração com front-end através de documentação OpenAPI automática.

### **Workflow**
**Credenciais** → **Validação JWT** → **Geração de Token** → **Acesso a Recursos Protegidos**

---

## 🏗️ Arquitetura
O projeto segue uma arquitetura em camadas bem definida (**Layered Architecture**), garantindo a separação total de responsabilidades:

```mermaid
graph TD
    A[Client / Swagger] -->|Request| B(REST Controllers)
    B -->|DTOs| C(Service Layer)
    C -->|Business Rules| D(Security Filter)
    C -->|Entities| E(Repository Layer)
    E -->|SQL| F[(PostgreSQL)]
    D -.->|Authorize| B

---

## 🔧 Backend - Design Patterns
O backend foi desenvolvido seguindo os princípios **SOLID** e utilizando padrões de projeto para máxima escalabilidade:

### **1. Repository Pattern 📦**
**Localização:** `src/main/java/com/RevisaAi/JavaProjects/repository/`  
**Propósito:** Abstrair o acesso a dados, permitindo que a lógica de negócio seja independente do banco.
> **Benefício:** Facilita a manutenção e a criação de mocks para testes de integração e unitários.

### **2. DTO Pattern (Data Transfer Object) 📤**
**Localização:** `src/main/java/com/RevisaAi/JavaProjects/dto/`  
**Propósito:** Trafegar dados entre as camadas de forma tipada, protegendo as entidades do banco de exposição desnecessária.
> **Benefício:** Segurança (não expõe campos sensíveis como senhas) e flexibilidade no contrato da API.

### **3. Middleware / Filter Pattern (Security) 🔒**
**Localização:** `src/main/java/com/RevisaAi/JavaProjects/service/SecurityFilter.java`  
**Propósito:** Interceptar requisições HTTP para validar o token JWT antes que elas cheguem aos Controllers.
> **Benefício:** Centralização da lógica de segurança e proteção automática de rotas privadas.

---

## 📂 Estrutura do Projeto
```text
RevisaAi-Backend/
├── src/main/java/com/RevisaAi/JavaProjects/
│   ├── config/         # Configurações (Security, Swagger, CORS)
│   ├── controller/     # Controllers REST (Exposição de Endpoints)
│   ├── dto/            # Objetos de Transferência (Request/Response)
│   ├── model/          # Entidades JPA (Mapeamento do Banco)
│   ├── repository/     # Camada de Persistência (Spring Data JPA)
│   ├── service/        # Lógica de Negócio e Segurança JWT
│   └── Application.java# Entry point da aplicação
└── pom.xml             # Gerenciador de dependências (Maven)
```

---

## 🛠️ Tecnologias
* **Linguagem:** Java 17+ (LTS)
* **Framework:** Spring Boot 3.x
* **Segurança:** Spring Security & JWT (Auth0)
* **Banco de Dados:** PostgreSQL
* **Documentação:** SpringDoc OpenAPI (Swagger UI)
* **Build:** Maven

---

## 🚀 Configuração e Execução
1.  **Clone o repositório:** `git clone https://github.com/matheusdelmones/RevisaAi-Backend.git`
2.  **Banco de Dados:** Certifique-se de que o PostgreSQL está rodando e crie o database `revisaai`.
3.  **Variáveis de Ambiente:** Configure as credenciais no seu arquivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/revisaai
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```
4.  **Executar a aplicação:** `./mvnw spring-boot:run`

---

## 📍 API Endpoints
Documentação interativa disponível em:  
👉 `http://localhost:8080/swagger-ui/index.html`

| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/login` | Autenticação e geração de token | Público |
| `POST` | `/auth/register` | Cadastro de novo usuário | Público |
| `GET` | `/api/users` | Listagem de usuários cadastrados | **Protegido** |
| `GET` | `/api/topics` | Listagem de tópicos de estudo | **Protegido** |
