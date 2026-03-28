# RevisaAi - Backend (Java Spring Boot)

### ⚙️ Configuração Inicial

1. Certifique-se de ter o **PostgreSQL** rodando na sua máquina.
2. Crie um banco de dados chamado `revisaai`.
3. **⚠️ Importante:** Este projeto segue o padrão *Cloud Native* e não armazena credenciais no código. Para rodar a API localmente, você **precisa** configurar as seguintes Variáveis de Ambiente na sua IDE antes de iniciar o Spring Boot:
   * `DB_PASSWORD`: A senha do seu PostgreSQL local.
   * `JWT_SECRET`: Uma chave qualquer para geração de tokens locais (ex: `chave_teste_front_123456`).
   *(Nota: O usuário padrão do banco configurado é `postgres`. Se o seu for diferente, injete também a variável `DB_USER`).*

### 🔐 Autenticação

* **Login:** `POST /auth/login`
* **Registro:** `POST /auth/register`
* **Headers:** Para acessar as rotas protegidas da portaria, envie o header `Authorization: Bearer <SEU_TOKEN>`.

### 📖 Documentação Interativa (Swagger)

Com o projeto rodando, acesse a interface do Swagger para visualizar todos os contratos de dados e testar os *endpoints* diretamente pelo navegador:
👉 http://localhost:8080/swagger-ui/index.html

### 🌐 Configuração de CORS

A API já está configurada e liberada para receber requisições das seguintes origens de desenvolvimento:
* React (Porta 3000)
* Vite (Porta 5173)
* Live Server (Porta 5500)
